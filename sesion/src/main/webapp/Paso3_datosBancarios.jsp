<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<%@ page isErrorPage="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Paso 3 Datos Bancarios</title>
    <link href="css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    HttpSession Sesion = request.getSession(true);
    if (request.getParameter("datos_personales") != null){
        RequestDispatcher redireccion = request.getRequestDispatcher("Paso1_datosPersonales.jsp");
        redireccion.forward(request, response);
    } else if (request.getParameter("datos_profesionales") != null){
        RequestDispatcher redireccion = request.getRequestDispatcher("Paso2_datosProfesionales.jsp");
        redireccion.forward(request, response);
    } else if (request.getParameter("resumen") != null){
        RequestDispatcher redireccion = request.getRequestDispatcher("resumen.jsp");
        redireccion.forward(request, response);
    }
%>
<form action="Paso3_datosBancarios.jsp" method="post">
    <input type="submit" value="1" name="datos_personales" />
    <input type="submit" value="2" name="datos_profesionales"/>
    <input type="submit" value="3" name="datos_bancarios"/>
    <input type="submit" value="Resumen" name="resumen"/>
</form>
<form action="DatosBancarios" method="post">
    <fieldset>
        <legend>Datos bancarios</legend>
        <label> Cuenta corriente:
            <input type="text" name="cuentaCorriente" placeholder="<% if(Sesion.getAttribute("cuentaCorriente") != null) { %><%= Sesion.getAttribute("cuentaCorriente") %><% } %>">
        </label>
        <br>
        <input name="datosBancarios" type="submit" value="Grabar informacion e ir al resumen final"/>
    </fieldset>
</form>
</body>
</html>
