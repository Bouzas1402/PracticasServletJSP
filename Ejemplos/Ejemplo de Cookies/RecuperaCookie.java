package es.carlosgs.introjee.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RecuperaCookie")
public class RecuperaCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Crea y Recupera</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Recupera Cookie</h1>");
            out.println("<h2>Cookies recibidas:</h2>");

            // Recepción de cookies
            Cookie[] arrayCookies = request.getCookies();
            if (arrayCookies.length > 0) {
              for (Cookie arrayCookie : arrayCookies) {
                String contenidoCookie = arrayCookie.getValue();
                out.print("Nombre: <b>" + arrayCookie.getName() + "</b>" + " <br />");
                out.print("Contenido: " + contenidoCookie + "<br />");
                Map<String, List<String>> parametros = splitQuery2(contenidoCookie);

                if (!parametros.isEmpty()) {
                  for (Entry<String, List<String>> elemento : parametros.entrySet()) {
                    String clave = elemento.getKey();
                    out.print("&emsp;&emsp;" + clave + " = [&nbsp;");
                    for (String valor : elemento.getValue()) {
                      out.print(valor + "&nbsp;");
                    }
                    out.print("]" + "<br />");
                  }
                }

              }
            }
            else {
                out.print("El navegador no ha enviado ninguna cookie");
            }
            out.println("</body>");
            out.println("</html>");
    }

    // extrae los pares campo=valor de una QUERY_STRING
    // si un parámetro se envía con múltiples valores, lo "procesa" como si fuera un array
    // permite valores nulos
    private static Map<String, List<String>> splitQuery(String queryString)  {
    	  Map<String, List<String>> queryPairs = new LinkedHashMap<>();
    	  String[] parametros = queryString.split("&");
    	  for (String pair : parametros) {
    		  int idx = pair.indexOf("=");
    		  String clave = idx > 0 ? decode(pair.substring(0, idx)) : pair;
    		  if (!queryPairs.containsKey(clave)) {
    			  queryPairs.put(clave, new LinkedList<>());
    		  }
    		  String valor = idx > 0 && pair.length() > idx + 1 ? decode(pair.substring(idx + 1)) : null;
    		  queryPairs.get(clave).add(valor);
    	  }
    	  return queryPairs;
    }


    private static String decode(final String encoded) {
      return Optional.ofNullable(encoded)
        .map(e -> URLDecoder.decode(e, StandardCharsets.UTF_8))
        .orElse(null);
    }

    private static Map<String, List<String>> splitQuery2(String queryString) {
      return Stream.of(queryString.split("&"))
        .map(s -> s.split("="))
        .collect(Collectors.groupingBy(
          s -> decode(s[0]),
          Collectors.mapping(s -> decode((s.length > 1) ? s[1] : null), Collectors.toList())));
    }

}
