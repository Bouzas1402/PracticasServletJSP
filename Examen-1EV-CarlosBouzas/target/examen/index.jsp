<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<%@ page isErrorPage="true"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: 2daw
  Date: 25/11/2021
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #seis {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('img/ahorcados.jpg') 0 0;
        }
        #cinco {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('img/ahorcados.jpg') -78.15px 0;
        }
        #cuatro {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('img/ahorcados.jpg') -156.30px 0;
        }
        #tres {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('img/ahorcados.jpg') -234.45px 0;
        }
        #dos {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('img/ahorcados.jpg') -312.60px 0;
        }
        #uno {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('img/ahorcados.jpg') -390.75px 0;
        }
        #cero {
            width: 78.15px;
            height: 92px;
            border: 1px black solid;
            background: url('img/ahorcados.jpg') -468.90px 0;
        }
    </style>
</head>
<body>
<%
    if (request.getParameter("empezarDeNuevo") != null) {
        request.getSession().invalidate();
    }
    if (request.getParameter("letraRepe") != null) {
        out.print("La letra " + request.getParameter("letraRepe").toUpperCase() + " Ya a sido introducida pruebe con otra.");
    }
    String [] palabras = {"elefante", "almohada", "picor", "cohete", "despegar"};
    String palabraAleatoria = palabras[(int) (Math.random() * 4)];
    HttpSession Sesion = request.getSession();

    if (Sesion.getAttribute("estadoPartida") != null && (Sesion.getAttribute("estadoPartida").toString().equals("ganado") || Sesion.getAttribute("estadoPartida").toString().equals("perdido"))){
        out.print("<h1>HAS " + Sesion.getAttribute("estadoPartida").toString() + "</h1>");
        out.print("<form action=\"index.jsp\" method=\"post\">");
        out.print("<input name=\"empezarDeNuevo\" type=\"submit\" value=\"Jugar de nuevo\"/>");
        out.print("</form>");
    }

    if (Sesion.getAttribute("intentos") == null) {
        Sesion.setAttribute("juego", "ahorcado");
        Sesion.setAttribute("palabra", palabraAleatoria);
        Sesion.setAttribute("letrasAcertadas", "");
        Sesion.setAttribute("letrasFalladas", "");
        Sesion.setAttribute("intentos", "seis");
        Sesion.setAttribute("contador", 0);
        Sesion.setAttribute("estadoPartida", "jugando");
    }

%>
<h3>Le quedan <% if (Sesion.getAttribute("intentos") != null) { out.print(Sesion.getAttribute("intentos")); } %> intentos</h3>
<p id="<% if (Sesion.getAttribute("intentos") != null) { out.print(Sesion.getAttribute("intentos")); } %>"></p>

<form action="ahorcado" method="post">
    <label> Introduce una letra:
        <input type="text" name="letraIntroducida"  minlength="1" maxlength="1">
    </label>
    <br>
    <%
        out.print("<p>");
        for (int i = 0; i < Sesion.getAttribute("palabra").toString().length(); i++) {
            if (Sesion.getAttribute("letrasAcertadas").toString().contains(Sesion.getAttribute("palabra").toString().split("")[i])){
                out.print(Sesion.getAttribute("palabra").toString().split("")[i]);
            } else {
                out.print("_");
            }
    }
        out.print("</p>");
        out.print("<h4>Letras falladas:</h4>");
        out.print("<p>");
        for (int i = 0; i < Sesion.getAttribute("letrasFalladas").toString().length(); i++) {
                out.print(Sesion.getAttribute("letrasFalladas").toString().split("")[i]);
        }
        out.print("</p>");
        out.print(Sesion.getAttribute("estadoPartida").toString());
        %>
    <input name="letraIntroducida" type="submit" value="Enviar letra"/>
</form>

</body>
</html>
