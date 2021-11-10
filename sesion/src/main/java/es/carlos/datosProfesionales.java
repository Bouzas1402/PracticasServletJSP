package es.carlos;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/DatosProfesionales")
public class datosProfesionales extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        guardarDatos(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        guardarDatos(request, response);
    }

    protected void guardarDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession Sesion = request.getSession();

        if (request.getParameter("departamento") != null) {
            Sesion.setAttribute("departamento", request.getParameter("departamento"));
        }
        if (request.getParameter("salario") != null) {
            Sesion.setAttribute("salario", request.getParameter("salario"));
        }
        if (request.getParameter("comentarios") != null) {
            Sesion.setAttribute("comentarios", request.getParameter("comentarios"));
        }

        String rutaContexto = request.getContextPath();
        String vistaDestino = "/Paso3_datosBancarios.jsp";
        response.sendRedirect(rutaContexto + vistaDestino);
    }
}

