package es.carlos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/Paso3_datosBancarios")
public class Paso3_datosBancarios extends HttpServlet {

	private final String PASO_ACTIVO = "3";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// se comprueba si hay variables de sesión y, de ser así, se recuperan
		String cuentaCorriente= "";

		//se comprueba si se reciben parámetros => se viene del PASO 1 => hay que guardarlos en la sesión
		// se recupera la sesión
		HttpSession laSesion= request.getSession(false);  //carga la sesión si existe, devuelve null si no
		if(laSesion != null) {
			if (request.getParameter("departamento") != null) {
				laSesion.setAttribute("departamento", request.getParameter("departamento"));
			}
			if (request.getParameter("salario") != null) {
				laSesion.setAttribute("salario", request.getParameter("salario"));
			}
			if (request.getParameter("comentarios") != null) {
				laSesion.setAttribute("comentarios", request.getParameter("comentarios"));
			}

			// se comprueba que haya variables de sesion y, de ser así, se recupera su valor
			if (laSesion.getAttribute("cuentaCorriente") != null) {
				cuentaCorriente = (String) laSesion.getAttribute("cuentaCorriente");
			}
		}

		// se pasan los atributos al JSP
		request.setAttribute("pasoActivo", PASO_ACTIVO);
		request.setAttribute("cuentaCorriente", cuentaCorriente);

		// enruta a la vista
    request.getRequestDispatcher("/Paso3_datosBancarios.jsp").forward(request,response);
	}


}
