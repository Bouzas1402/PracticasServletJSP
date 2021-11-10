<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>

<%@ page isErrorPage="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Resumen</title>
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
    } else if (request.getParameter("datos_bancarios") != null){
        RequestDispatcher redireccion = request.getRequestDispatcher("Paso3_datosBancarios.jsp");
        redireccion.forward(request, response);
    }
%>
<form action="resumen.jsp" method="post">
    <input type="submit" value="1" name="datos_personales" />
    <input type="submit" value="2" name="datos_profesionales"/>
    <input type="submit" value="3" name="datos_bancarios"/>
    <input type="submit" value="Resumen" name="resumen"/>
</form>
<fieldset>
    <legend>Datos personales:</legend>
    <label>Nombre : <span><% if(Sesion.getAttribute("nombre") != null) { %><%= Sesion.getAttribute("nombre") %><% } %></span></label>
    <label>Apellidos : <span><% if(Sesion.getAttribute("apellidos") != null) { %><%= Sesion.getAttribute("apellidos") %><% } %></span></label>
    <br>
    <label>Fecha de nacimiento : <% if(Sesion.getAttribute("fechaNacimiento") != null) { %><%= Sesion.getAttribute("fechaNacimiento") %><% } %></label>
    <br>
    <label>Genero : <% if(Sesion.getAttribute("genero") != null) { %><%= Sesion.getAttribute("genero") %><% } %></label>
    <label>Casado : <% if(Sesion.getAttribute("casado") != null && Sesion.getAttribute("casado").equals("Casado/a")) { %> SI <% } else { %> NO <% } %></label>
    <label>Hijos : <% if(Sesion.getAttribute("hijos") != null && Sesion.getAttribute("hijos").equals("SI")) { %> SI <% } else { %> NO <% } %></label>
    <br>
    <label>Nacionalidad : <% if(Sesion.getAttribute("nacionalidad") != null) { %><%= Sesion.getAttribute("nacionalidad") %><% } %></label>
</fieldset>
<fieldset>
    <legend>Datos profesionales</legend>
    <label>Departamento : <% if(Sesion.getAttribute("departamento") != null) { %><%= Sesion.getAttribute("departamento") %><% } %></label>
    <br>
    <label>Salario : <% if(Sesion.getAttribute("salario") != null) { %><%= Sesion.getAttribute("salario") %><% } %></label>
    <br>
    <label>Comentario : <% if(Sesion.getAttribute("comentarios") != null) { %><%= Sesion.getAttribute("comentarios") %><% } %></label>
</fieldset>
<fieldset>
    <legend>Datos bancarios</legend>
    <label>Cuenta corriente : <% if(Sesion.getAttribute("cuentaCorriente") != null) { %><%= Sesion.getAttribute("cuentaCorriente") %><% } %></label>
</fieldset>
<a href="Paso1_datosPersonales.jsp">Volver al principio</a>
</body>
</html>
