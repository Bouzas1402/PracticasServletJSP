<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<%@ page isErrorPage="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Paso 1 Datos Personales</title>
    <link href="css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
    HttpSession Sesion = request.getSession(true);
        if (request.getParameter("datos_profesionales") != null){
            RequestDispatcher redireccion = request.getRequestDispatcher("Paso2_datosProfesionales.jsp");
            redireccion.forward(request, response);
        } else if (request.getParameter("datos_bancarios") != null){
            RequestDispatcher redireccion = request.getRequestDispatcher("Paso3_datosBancarios.jsp");
            redireccion.forward(request, response);
        } else if (request.getParameter("resumen") != null){
            RequestDispatcher redireccion = request.getRequestDispatcher("resumen.jsp");
            redireccion.forward(request, response);
        }
%>
<form action="Paso1_datosPersonales.jsp" method="post">
    <input type="submit" value="1" name="datos_personales" />
    <input type="submit" value="2" name="datos_profesionales"/>
    <input type="submit" value="3" name="datos_bancarios"/>
    <input type="submit" value="Resumen" name="resumen"/>
</form>
<form action="DatosPersonales" method="post">
    <fieldset>
        <legend>Datos personales</legend>
        <label>
            Nombre:   <input type="text" name="nombre" placeholder=" <% if (Sesion.getAttribute("genero") != null) { %><%= Sesion.getAttribute("nombre") %><%  } %>"/>
        </label>
        <label>
            Apellidos:   <input type="text" name="apellidos"  placeholder="<% if (Sesion.getAttribute("genero") != null) { %><%= Sesion.getAttribute("apellidos") %><%  } %>"/>
        </label>
         <br/>
        <label>
            Fecha:  <input type="date" name="fechaNacimiento" value="<% if (Sesion.getAttribute("genero") != null) { %><%= Sesion.getAttribute("fechaNacimiento") %><%  } %>"/>
        </label>
        <br/>
        <label>Genero : </label>
            <div>
            <input type="radio" name="genero" value="hombre"  <% if (Sesion.getAttribute("genero") != null) { if(Sesion.getAttribute("genero").equals("hombre")) { %> checked  <% } } %>/>
            Hombre</div>
        <div>
            <div>
            <input type="radio" name="genero" value="mujer" <% if (Sesion.getAttribute("genero") != null) { if(Sesion.getAttribute("genero").equals("mujer")) { %> checked   <% } } %>/>
            Mujer</div>
            <div>
                <input type="radio" name="genero" value="otros" <% if (Sesion.getAttribute("genero") != null) { if (Sesion.getAttribute("genero").equals("otros")) { %> checked <% } } %>/>
            Otros</div>
        </div>
        <label>Casado pareja de hecho:
            <input type="checkbox" name="estado_civil" value="casado" <% if(Sesion.getAttribute("casado") != null && Sesion.getAttribute("casado").equals("Casado/a")) { %> <%= "checked" %><% } %>/>
        </label>
        <label>Hijos:
            <input type="checkbox" name="hijos" value="hijos" <% if(Sesion.getAttribute("hijos") != null && Sesion.getAttribute("hijos").equals("SI")) { %> <%= "checked" %><% } %> />
        </label>
        <label>Nacionalidad:
            <select name="nacionalidad">
                <option name="española" value="española" <% if (Sesion.getAttribute("nacionalidad") != null) { if(Sesion.getAttribute("nacionalidad").equals("española")) { %> selected="selected" <% } } %>>Español</option>
                <option name="portuguesa" value="portuguesa" <% if (Sesion.getAttribute("nacionalidad") != null) { if(Sesion.getAttribute("nacionalidad").equals("portuguesa")) { %> selected="selected" <% } } %>>Portugues</option>
                <option name="francesa" value="francesa" <% if (Sesion.getAttribute("nacionalidad") != null) { if(Sesion.getAttribute("nacionalidad").equals("francesa")) { %>  selected="selected" <% } } %>>Frances</option>
                <option name="italiana" value="italiana" <%  if (Sesion.getAttribute("nacionalidad") != null) { if(Sesion.getAttribute("nacionalidad").equals("italiana")) { %> selected="selected" <% } } %>>Italiano</option>
                <option name="alemana" value="alemana" <%  if (Sesion.getAttribute("nacionalidad") != null) { if(Sesion.getAttribute("nacionalidad").equals("alemana")) { %> selected="selected" <% } } %>>Aleman</option>
            </select>
        </label>
        <br/>
        <input type="submit" value="Grabar informacion e ir al paso 2 - Datos profesionales" name="datosPersonales"/>
    </fieldset>
</form>
</body>
</html>
