<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <h1>Calcular factorial</h1>
    <form method="POST" action="Factorial.jsp">
        Número: <input type="text" name="numero" size="3" maxlength="2" /> <br />
        <input type="submit" value="Calcular" />
    </form>

<%
    String parametro = request.getParameter("numero");
    if (parametro != null && parametro != "") {
        try {
            int numero = Integer.parseInt(request.getParameter("numero"));
            out.println("<br />");
            out.println("Factorial de " + numero + " = " + factorial(numero));
        } catch (Exception e) {
            out.println("<br />");
            out.println("Excepción: se intodujo el caracter " + parametro + " no numérico");
        }
    } else {
        out.println("<br />");
        out.println("No se intodujo ningún caracter");
    }
%>

<%!
  // la admiración de cierre es necesaria para declarar una función
  // Indica que se trata de una sección declarativa
    int factorial(int n) {
        if (n == 1) {
            return n;
        } else {
            return n * factorial(n - 1);
        }
    }
%>

</body>
</html>
