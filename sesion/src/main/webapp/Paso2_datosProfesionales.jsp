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
        RequestDispatcher redireccion = request.getRequestDispatcher("Paso1_datosPersonales.jsp");
        redireccion.forward(request, response);
    } else if (request.getParameter("datos_bancarios") != null){
        RequestDispatcher redireccion = request.getRequestDispatcher("Paso3_datosBancarios.jsp");
        redireccion.forward(request, response);
    } else if (request.getParameter("resumen") != null){
        RequestDispatcher redireccion = request.getRequestDispatcher("resumen.jsp");
        redireccion.forward(request, response);
    }

    String [] departamentos = {"Marketing", "Ventas", "Desarrollo", "Administrativo", "Direccion", "Recursos humanos"};
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
                <%
                    for (String departamento : departamentos) {
                        String selected;
                        if (Sesion.getAttribute("departamento") != null && Sesion.getAttribute("departamento").equals(departamento)) {
                            selected = " selected";
                        } else {
                            selected = "";
                        }
                        out.print("<option  name='" + departamento + "' value='" + departamento + "' " + selected + ">" + departamento.toUpperCase() + "</option>");
                    }
                %>
               </select>
        </label>
        <br/>
        <label> Salario :
            <input name="salario" type="number" value="<% if (Sesion.getAttribute("salario") != null) { out.print(Sesion.getAttribute("salario")); } %>">
        </label>
        <br/>
        <label for="comentarios">Comentarios : </label> <br>
            <textarea id="comentarios" name="comentarios" rows="10" cols="50" placeholder="<% if (Sesion.getAttribute("comentarios") != null) { out.print(Sesion.getAttribute("comentarios")); } %>"></textarea>
        <br>
        <input name="datosProfesionales" type="submit" value="Grabar informacion e ir al paso 3 - Datos bancarios"/>
    </fieldset>
</form>
</body>
</html>
