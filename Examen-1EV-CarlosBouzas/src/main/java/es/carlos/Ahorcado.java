package es.carlos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

@WebServlet("/ahorcado")
public class Ahorcado  extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession Sesion = request.getSession();
        String letra = "";
        if (request.getParameter("letraIntroducida") != null && !request.getParameter("letraIntroducida").equals("")){
            letra = request.getParameter("letraIntroducida").toLowerCase();
            if(Sesion.getAttribute("palabra").toString().contains(letra) && !Sesion.getAttribute("letrasAcertadas").toString().contains(letra)){
                Sesion.setAttribute("letrasAcertadas", Sesion.getAttribute("letrasAcertadas") + letra);

                for (int i = 0; i < Sesion.getAttribute("palabra").toString().split("").length; i++) {
                    if (Sesion.getAttribute("palabra").toString().split("")[i].equals(letra)) {
                        Sesion.setAttribute("contador", Integer.parseInt(Sesion.getAttribute("contador").toString()) + 1);
                    }
                }

                if (Integer.parseInt(Sesion.getAttribute("contador").toString()) == Sesion.getAttribute("palabra").toString().split("").length) {
                    Sesion.setAttribute("estadoPartida", "ganado");
                }


            } else if (!Sesion.getAttribute("palabra").toString().contains(letra) && !Sesion.getAttribute("letrasFalladas").toString().contains(letra)) {
                Sesion.setAttribute("letrasFalladas", Sesion.getAttribute("letrasFalladas") + letra);
                Sesion.setAttribute("intentos", restarIntentos(Sesion.getAttribute("intentos").toString()));
                if (Sesion.getAttribute("intentos").toString().equals("cero")){
                    Sesion.setAttribute("estadoPartida", "perdido");
                }
            } else if (Sesion.getAttribute("letrasFalladas").toString().contains(letra) || Sesion.getAttribute("letrasAcertadas").toString().contains(letra)){
                response.sendRedirect("index.jsp?letraRepe=" + letra);
            }
            response.sendRedirect("index.jsp");


        } else {
            response.sendRedirect("index.jsp");
        }


    }

    private String restarIntentos (String intento) {
        String numeroInteto = "";
        switch (intento) {
            case "seis" :
                numeroInteto = "cinco";
                break;
            case "cinco" :
                numeroInteto = "cuatro";
                break;
            case "cuatro" :
                numeroInteto = "tres";
                break;
            case "tres" :
                numeroInteto = "dos";
                break;
            case "dos" :
                numeroInteto = "uno";
                break;
            case "uno" :
                numeroInteto = "cero";
                break;
            default:
                numeroInteto = "";
        }

            return numeroInteto;
    }

}
