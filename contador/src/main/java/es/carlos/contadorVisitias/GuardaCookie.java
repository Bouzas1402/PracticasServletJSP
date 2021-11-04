package es.carlos.contadorVisitias;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class GuardaCookie extends HttpServlet {
    private static final String UTF_8 = "UTF_8";

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            procesaSolicitud(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            procesaSolicitud(request, response);
        }

        protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Guardar cookie</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Guarda Cookie</h1>");
                out.println("<h2>Cookies recibidas:</h2>");
            } catch (Exception e){
                out.println("Se produce una excepcion <br>");
                e.printStackTrace(out);
            } finally {
                out.println("</body>");
                out.println("</html>");
                out.close();
            }



        }



}
