<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>

        <%
            Cookie unaCookie = null;
            if (request.getParameter("Alfarero") != null || request.getParameter("Brujo") != null ||request.getParameter("Curtidor") != null){
                String datos = request.getQueryString();
                String [] separaDatos = datos.split("%3D");
                String profesion = separaDatos[0].split("=")[1];
                String nombre = separaDatos[2];
                String contador = separaDatos[1];
                Cookie cookie = new Cookie(nombre, "" + profesion + "-" + contador);
                cookie.setMaxAge(60 * 60 * 24 * 365 * 4);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }

        if (request.getParameter("usuario") != null) {
            Map<String, String> usuariosValidos = new LinkedHashMap<>();
            usuariosValidos.put("carlos", "quevedo");
            usuariosValidos.put("juan", "quevedo");
            usuariosValidos.put("ana", "quevedo");
            boolean nombreValido = usuariosValidos.containsKey(request.getParameter("usuario"));
            boolean claveValida = usuariosValidos.containsValue(request.getParameter("clave"));
            if (!nombreValido && !claveValida) { %>

                <form name="f1" action="index.jsp">
                    <label>Usuario <input type="text" name="usuario"></label> <br>
                    <label>Clave <input type="password" name="clave"></label> <br>
                    <input type="submit" value="Contador"/>
                </form>


            <% } else {
                Cookie []arrayCookie = request.getCookies();
                for (int i = 0; i < arrayCookie.length; i++) {
                    if (arrayCookie[i].getName().equals(request.getParameter("usuario"))){
                        String valorCookie = arrayCookie[i].getValue();
                        if (valorCookie.split("-")[0] != null && valorCookie.split("-")[1] != null){
                            unaCookie = arrayCookie[i];
                        }
                    }
                }
           if (unaCookie == null) { %>
               <h1>Bienvenido aprendiz</h1>
               <form action='index.jsp'>
                    <span>Alfarero<input type="radio" name="Alfarero" value="alfarero=1<%="="+request.getParameter("usuario")%>"/></span>
                    <span>Brujo<input type="radio" name="Brujo" value="brujo=1<%="="+request.getParameter("usuario")%>"/></span>
                    <span>Curtidor<input type="radio" name="Curtidor" value="curtidor=1<%="="+request.getParameter("usuario")%>" /></span>
                    <input type="submit" value="Enviar"/>
                    <p>Contador de visitas : 1</p>
               </form>
       <% } else {
           String valorCookie = unaCookie.getValue();
           String [] valores = valorCookie.split("-");
           int valorContador = Integer.parseInt(valores[1]);
           valorContador++;
       %>
                <h1>Bienvenido aprendiz</h1>
                    <form action='index.jsp'>
                    <span>Alfarero<input type="radio" name="Alfarero" value="alfarero<%="="+valorContador+"="+request.getParameter("usuario")%>"/></span>
                    <span>Brujo<input type="radio" name="Brujo" value="brujo<%="="+valorContador+"="+request.getParameter("usuario")%>"/></span>
                    <span>Curtidor<input type="radio" name="Curtidor" value="curtidor<%="="+valorContador+"="+request.getParameter("usuario")%>" /></span>
                    <input type="submit" value="Enviar"/>
                    <p>Contador de visitas : <%= valorContador %></p>
                </form>
       <% }
    }
       } else {
    %>
    <form name="f1" action="index.jsp">
        <label>Usuario <input type="text" name="usuario"></label> <br>
        <label>Clave <input type="password" name="clave"></label> <br>
        <input type="submit" value="Contador"/>
    </form>
        <%
       }
%>
</body>
</html>


