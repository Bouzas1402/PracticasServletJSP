<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>

<%@ page isErrorPage="true"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Paso 2 Datos Profesionales</title>
    <link href="css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    HttpSession Sesion = request.getSession(true);

    if (request.getParameter("datos_personales") != null){
        RequestDispatcher rd = request.getRequestDispatcher("Paso1_datosPersonales.jsp");
        rd.forward(request, response);
    } else if (request.getParameter("datos_bancarios") != null){
        RequestDispatcher rd = request.getRequestDispatcher("Paso3_datosBancarios.jsp");
        rd.forward(request, response);
    } else if (request.getParameter("resumen") != null){
        RequestDispatcher rd = request.getRequestDispatcher("resumen.jsp");
        rd.forward(request, response);
    }
%>
<form action="Paso2_datosProfesionales.jsp" method="post">
    <input type="submit" value="1" name="datos_personales" />
    <input type="submit" value="2" name="datos_profesionales"/>
    <input type="submit" value="3" name="datos_bancarios"/>
    <input type="submit" value="Resumen" name="resumen"/>
</form>
<form action="DatosProfesionales" method="post">
    <fieldset>
        <legend>Datos profesionales</legend>
        <label>Departamento :
            <select name="departamento">
                <option name="marketing" value="marketing" <% if (Sesion.getAttribute("departamento") != null) { if(Sesion.getAttribute("departamento").equals("marketing")) { %> selected="selected" <% } } %>>Marketing</option>
                <option name="venta" value="venta" <% if (Sesion.getAttribute("departamento") != null) { if(Sesion.getAttribute("departamento").equals("venta")) { %> selected="selected" <% } } %>>Ventas</option>
                <option name="desarrollo" value="desarrollo" <% if (Sesion.getAttribute("departamento") != null) { if(Sesion.getAttribute("departamento").equals("desarrollo")) { %> selected="selected" <% } } %>>Desarrollo</option>
                <option name="recursosHumanos" value="recursosHumanos" <% if (Sesion.getAttribute("departamento") != null) { if(Sesion.getAttribute("departamento").equals("recursosHumanos")) { %> selected="selected" <% } } %>>Recursos humanos</option>
                <option name="direccion" value="direccion" <% if (Sesion.getAttribute("departamento") != null) { if(Sesion.getAttribute("departamento").equals("direccion")) { %> selected="selected" <% } } %>>Directivos</option>
            </select>
        </label>
        <br/>
        <label> Salario :
            <input name="salario" type="number" placeholder=" <% if (Sesion.getAttribute("salario") != null) { %><%= Sesion.getAttribute("salario") %><%  } %>">
        </label>
        <br/>
        <label for="comentarios">Comentarios : </label> <br>
            <textarea id="comentarios" name="comentarios" rows="10" cols="50" placeholder=" <% if (Sesion.getAttribute("comentarios") != null) { %><%= Sesion.getAttribute("comentarios") %><%  } %>"></textarea>
        <br>
        <input name="datosProfesionales" type="submit" value="Grabar informacion e ir al paso 3 - Datos bancarios"/>
    </fieldset>
</form>
</body>
</html>
