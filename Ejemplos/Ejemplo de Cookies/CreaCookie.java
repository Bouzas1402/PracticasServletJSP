package es.carlosgs.introjee.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/CreaCookie")
public class CreaCookie extends HttpServlet {
  private static final String BR = "<br />";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    procesaSolicitud(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    procesaSolicitud(request, response);
  }

  protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // se inicializa un objeto Cookie
        Cookie unaCookie;

        // Recepción de parámetros
        String nombreCookie = request.getParameter("usuario");

        String contenidoCookie=request.getQueryString();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Crea y Recupera</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Crea y Recupera</h1>");

        try {
          // se crea el objeto cookie en el servidor
          unaCookie = new Cookie(nombreCookie, contenidoCookie);

          // se añade a la respuesta para enviar al cliente
          response.addCookie(unaCookie);

          out.println("Se crea una cookie de nombre " + nombreCookie);
          out.println(BR);
          out.println("El contenido de la cookie es " + contenidoCookie);
          out.println(BR);
      }
      catch (Exception e){
          out.println("Se produce una excepción: ");
          out.println(e.getMessage());
          out.println(BR);
      }
      finally {
          out.println("<a href=\"RecuperaCookie\">Ir a RecuperaCookie <a/> ");
          out.println("</body>");
          out.println("</html>");
    	    out.close();
      }
    }



}
