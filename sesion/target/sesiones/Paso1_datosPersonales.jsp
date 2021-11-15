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

    if (request.getParameter("borrar") != null && request.getParameter("borrar").equals("true")){
        request.getSession().invalidate();
    }


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

        String [] genero = {"hombre", "mujer", "otros"};
        String [] nacionalidades = {"española", "portuguesa", "italiana", "francesa", "alemana", "inglesa"};
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
            Nombre:   <input type="text" name="nombre" value="<% if (Sesion.getAttribute("nombre") != null) { out.print(Sesion.getAttribute("nombre"));   } %>" />
        </label>
        <label>
            Apellidos:   <input type="text" name="apellidos"  value="<% if (Sesion.getAttribute("apellidos") != null) { out.print(Sesion.getAttribute("apellidos"));  } %>"/>
        </label>
         <br/>
        <label>
            Fecha:  <input type="date" name="fechaNacimiento" value="<% if (Sesion.getAttribute("fechaNacimiento") != null) { out.print(Sesion.getAttribute("fechaNacimiento")); } %>"/>
        </label>
        <br/>
        <label>Genero : </label>
        <%
            for (int i = 0; i < genero.length; i++){
                String checked;
                if (Sesion.getAttribute("genero") != null && Sesion.getAttribute("genero").equals(genero[i])){
                    checked =  " checked ";
                } else {
                    checked = "";
                }
                out.print("<label> " + genero[i] + "" + "<input type='radio' name='genero' value='" + genero[i] + "' " + checked + "/></label>");
        }
        %>
        <br>
        <label>Casado pareja de hecho:
            <input type="checkbox" name="estado_civil" value="casado" <% if(Sesion.getAttribute("casado") != null && Sesion.getAttribute("casado").equals("Casado/a")) { %> checked <% } %>/>
        </label>
        <label>Hijos:
            <input type="checkbox" name="hijos" value="hijos" <% if(Sesion.getAttribute("hijos") != null && Sesion.getAttribute("hijos").equals("SI")) { %> checked <% } %> />
        </label>
        <label>Nacionalidad:
            <select name="nacionalidad">
                <%
                for (int i = 0; i < nacionalidades.length; i++) {
                    String selected;
                        if (Sesion.getAttribute("nacionalidad") != null && Sesion.getAttribute("nacionalidad").equals(nacionalidades[i])){
                            selected =  " selected";
                        } else {
                            selected = "";
                        }
                        out.print("<option  name='" + nacionalidades[i] + "' value='" + nacionalidades[i] +  "' " + selected + ">" + nacionalidades[i].toUpperCase() + "</option>");
                }
                %>
            </select>
        </label>
        <br/>
        <input type="submit" value="Grabar informacion e ir al paso 2 - Datos profesionales" name="datosPersonales"/>
    </fieldset>
</form>
</body>
</html>
