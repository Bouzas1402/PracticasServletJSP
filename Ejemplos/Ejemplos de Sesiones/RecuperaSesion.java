package es.carlosgs.introjee.sesiones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RecuperaSesion")
public class RecuperaSesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // se recuperan las varibles de sesión previamente creadas
            HttpSession laSesion = request.getSession(true);

            //Se imprime el contenido de la sesión
            Enumeration<String> enumeracionAtributos;
            String nombreAtributo;
            String valorAtributo;
            enumeracionAtributos = laSesion.getAttributeNames();
            out.println("<h1>PasoVariablesSesion</h1>");
            out.println("<h3>Servlet RecuperaSesion</h3>");
            out.println("<p>Variables de sesión:</p>");
            while (enumeracionAtributos.hasMoreElements()) {
                nombreAtributo = enumeracionAtributos.nextElement();
                valorAtributo = laSesion.getAttribute(nombreAtributo).toString();
                out.println(nombreAtributo + " = " + valorAtributo + "<br />");
            }
            out.println("<hr />");
        }
        catch (Exception e){
            out.println("Se produce una excepción <br />");
            out.println(e.getMessage());
        }
    }

}
