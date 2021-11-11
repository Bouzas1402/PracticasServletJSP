package es.carlos;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/BorrarSesion")
public class borrarSesion extends HttpServlet {

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
        if (request.getSession(false) != null) {  // si existe una sesión
            request.getSession().invalidate();  // ... la elimina
        }
        String rutaContexto = request.getContextPath();
        String vistaDestino = "/Paso1_datosPersonales.jsp";
        response.sendRedirect(rutaContexto + vistaDestino);
    }
    }
