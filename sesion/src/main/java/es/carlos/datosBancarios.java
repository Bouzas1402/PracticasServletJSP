package es.carlos;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/DatosBancarios")
public class datosBancarios extends HttpServlet {

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

        if (request.getParameter("cuentaCorriente") != null) {
            Sesion.setAttribute("cuentaCorriente", request.getParameter("cuentaCorriente"));
        }

        String rutaContexto = request.getContextPath();
        String vistaDestino = "/resumen.jsp";
        response.sendRedirect(rutaContexto + vistaDestino);
    }
}
