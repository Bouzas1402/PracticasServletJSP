package es.carlos;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/DatosPersonales")
public class datosPersonales extends HttpServlet {

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

        if (!request.getParameter("nombre").equals("")) {
            Sesion.setAttribute("nombre", request.getParameter("nombre"));
        }
        if (!request.getParameter("apellidos").equals("")) {
            Sesion.setAttribute("apellidos", request.getParameter("apellidos"));
        }
        if (!request.getParameter("fechaNacimiento").equals("")) {
            Sesion.setAttribute("fechaNacimiento", request.getParameter("fechaNacimiento"));
        }
        if (request.getParameter("genero") != null) {
            Sesion.setAttribute("genero", request.getParameter("genero"));
        }
        if (request.getParameter("estado_civil") != null) {
            Sesion.setAttribute("casado", "Casado/a");
        } else {
            Sesion.setAttribute("casado", "Soltero/a");
        }

        if (request.getParameter("hijos") != null) {
            Sesion.setAttribute("hijos", "SI");
        } else {
            Sesion.setAttribute("hijos", "NO");
        }
        Sesion.setAttribute("nacionalidad", request.getParameter("nacionalidad"));

        String rutaContexto = request.getContextPath();
        String vistaDestino = "/Paso2_datosProfesionales.jsp";
        response.sendRedirect(rutaContexto + vistaDestino);
    }
}


