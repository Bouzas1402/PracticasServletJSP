package es.carlos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/Paso1_datosPersonales")
public class Paso1_datosPersonales extends HttpServlet {

	private final String PASO_ACTIVO = "1";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// se comprueba si hay variables de sesión y, de ser así, se recuperan
		String nombre= "";
		String apellidos= "";
		String genero= "M";
		String fechaNacimiento= "";
		String casado= "";
		String hijos= "";
		String[] nacionalidades= new String[0];

		// se recupera la sesión
		HttpSession laSesion= request.getSession(false);  //carga la sesión si existe, devuelve null si no
		if(laSesion != null) {
			if (request.getParameter("empezar") != null) {  // se ha recibido el parámetro empezar
				laSesion.invalidate();  // se inactiva la sesión
			} else {
				if (laSesion.getAttribute("nombre") != null) {
					nombre = (String) laSesion.getAttribute("nombre");
				}
				if (laSesion.getAttribute("apellidos") != null) {
					apellidos = (String) laSesion.getAttribute("apellidos");
				}
				if (laSesion.getAttribute("genero") != null) {
					genero = (String) laSesion.getAttribute("genero");
				}
				if (laSesion.getAttribute("casado") != null) {
					casado = "checked=\"checked\"";
				}
				if (laSesion.getAttribute("hijos") != null) {
					hijos = (String) laSesion.getAttribute("hijos");
				}
				if (laSesion.getAttribute("fechaNacimiento") != null) {
					fechaNacimiento = (String) laSesion.getAttribute("fechaNacimiento");
				}
				if (laSesion.getAttribute("nacionalidad") != null) {
					nacionalidades = (String[]) laSesion.getAttribute("nacionalidad");
				}
			}
		}

		// se pasan los atributos al JSP
		request.setAttribute("pasoActivo", PASO_ACTIVO);
		request.setAttribute("nombre", nombre);
		request.setAttribute("apellidos", apellidos);
		request.setAttribute("genero", genero);
		request.setAttribute("casado", casado);
		request.setAttribute("hijos", hijos);
		request.setAttribute("fechaNacimiento", fechaNacimiento);
		request.setAttribute("nacionalidades", nacionalidades);

		// enruta a la vista
		/*
		String vista = "/Paso1_datosPersonales.jsp";  // la ruta debe comenzar por /
		 */
		// el destino puede ser un servlet, un jsp, un html
		/*
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request,response);
		 */

    // Si la ruta es relativa dentro de la misma aplicación no es necesario obtener el contexto
    request.getRequestDispatcher("/Paso1_datosPersonales.jsp").forward(request,response);
	}


}
