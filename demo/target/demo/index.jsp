<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="com.example.demo.contadorVisitas" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <%
            Cookie unaCookie = null;
            if (request.getParameter("profesiones") != null){
                String[] parametros = request.getParameter("profesiones").split("=");
                Cookie cookie = new Cookie(parametros[2], "" + parametros[0] + "-" + parametros[1]);
                cookie.setMaxAge(60 * 60 * 24 * 365 * 4);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        if (request.getParameter("usuario") != null) {
            contadorVisitas comprobar = new contadorVisitas();
            if (!comprobar.comprobarUsuarios(request.getParameter("usuario"))) {
            out.print(formularioInicio());
       } else {
                Cookie []arrayCookie = request.getCookies();
                String [] profesiones = {"alfarero", "brujo", "curtidor"};
                for (int i = 0; i < arrayCookie.length; i++) {
                    if (arrayCookie[i].getName().equals(request.getParameter("usuario"))){
                        for (int j = 0; j < profesiones.length; j++) {
                            if (arrayCookie[i].getValue().split("-")[0].equals(profesiones[j]) && arrayCookie[i].getValue().split("-")[1].matches("[0-9]+")){
                                unaCookie = arrayCookie[i];
                            }
                        }
                    }
                }
           if (unaCookie == null) {
               out.print(formularioProfesiones("alfarero", 1, request.getParameter("usuario")));
        } else {
           int valorContador = Integer.parseInt(unaCookie.getValue().split("-")[1]);
           valorContador++;
           out.print(formularioProfesiones(unaCookie.getValue().split("-")[0], valorContador, request.getParameter("usuario")));
        }
    }
       } else {
            out.print(formularioInicio());
       }
%>
</body>
</html>
<%!
    public String formularioInicio (){
        return "<form name='f1' action='index.jsp'><label>Usuario <input type='text' name='usuario'></label> <br><label>Clave <input type='password' name='clave'></label> <br><input type='submit' value='Contador'/></form>";
    }
    public String formularioProfesiones(String profesion, int contador, String usuario){
        String [] profesiones = {"alfarero", "brujo", "curtidor"};
        String respuesta = "<h1>Bienvenido aprendiz</h1><form action='index.jsp'>";
        for (int i = 0; i < profesiones.length; i++) {
            String checked;
            if (profesiones[i].equals(profesion)){
                checked = " checked ";
            } else {
                checked = "";
            }
            respuesta += "<span>" + profesiones[i] + "<input type='radio' name='profesiones' value='" + profesiones[i] + "=" + contador + "=" + usuario + "'" + checked + "/></span>";
        }
        respuesta += "<input type='submit' value='Enviar'/><p>Contador de visitas : " + contador +"</p></form>";
        return respuesta;
    }
%>
