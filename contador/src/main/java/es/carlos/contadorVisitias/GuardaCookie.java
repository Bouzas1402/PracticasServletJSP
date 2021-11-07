package es.carlos.contadorVisitias;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/GuardaCookie")
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
            try {
                String query = request.getQueryString();
                String [] separaDatos = query.split("=");
                String profesion = separaDatos[1];
                String [] separarDatos2 = separaDatos[0].split("%3D");
                String nombre = separarDatos2[1].split("%26")[0];
                String contador = separarDatos2[3];
                Cookie cookie = new Cookie(nombre, "" + profesion + "-" + contador);
                cookie.setMaxAge(60 * 60 * 24 * 365 * 4);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/contadorVisitas.html");
            } catch (Exception e) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("Se produce una excepcion <br>");
                e.printStackTrace(out);
                out.close();
            }
        }
}
