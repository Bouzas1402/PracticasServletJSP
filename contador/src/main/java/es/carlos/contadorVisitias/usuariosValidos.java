package es.carlos.contadorVisitias;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/Usuarios")
public class usuariosValidos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        usuariosValidos(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        usuariosValidos(request, response);
    }

    protected void usuariosValidos(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String nombre = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        Map<String, String> usuariosValidos = new LinkedHashMap<>();
        usuariosValidos.put("carlos", "quevedo");
        usuariosValidos.put("juan", "quevedo");
        usuariosValidos.put("ana", "quevedo");
        boolean nombreValido = usuariosValidos.containsKey(clave);
        boolean claveValida = usuariosValidos.containsValue(nombre);
        if (nombreValido && claveValida) {
            String rutaContexto = request.getContextPath();
            String vistaDestino = "/contadorVisitas.html";
            response.sendRedirect(rutaContexto + vistaDestino);
        } else {
            String rutaContexto = request.getContextPath();
            String vistaDestino = "/index.jsp";
            response.sendRedirect(rutaContexto + vistaDestino);
        }

    }

}