package es.carlos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/Resumen")
public class Resumen extends HttpServlet {

	private final String PASO_ACTIVO = "4";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//se declaran variables para recuperar datos de la sesión
		//personales
		String nombre = "";
		String apellidos = "";
		String genero = "";
		String fechaNacimiento = "";
		String casado= "";
		String hijos= "";
		String[] nacionalidad = new String[0];
		//profesionales
		String departamento = "";
		String salario= "";
		String comentarios= "";
		//bancarios
		String cuentaCorriente= "";

		// se comprueba si hay variables de sesión y, de ser así, se recuperan


		//se comprueba si se reciben parámetros => se viene del PASO 3 => hay que guardarlos en la sesión
		// se recupera la sesión
		HttpSession laSesion= request.getSession(false);  //carga la sesión si existe, devuelve null si no
		if(laSesion != null) {
			if (request.getParameter("cuentaCorriente") != null) {
				laSesion.setAttribute("cuentaCorriente", request.getParameter("cuentaCorriente"));
			}

			// se comprueba que haya variables de sesion y, de ser así, se recupera su valor
			if (laSesion.getAttribute("nombre") != null) {
				nombre = (String) laSesion.getAttribute("nombre");
			}
			if (laSesion.getAttribute("apellidos") != null) {
				apellidos = (String) laSesion.getAttribute("apellidos");
			}
			if (laSesion.getAttribute("fechaNacimiento") != null) {
				fechaNacimiento = (String) laSesion.getAttribute("fechaNacimiento");
			}
			if (laSesion.getAttribute("genero") != null) {
				genero = (String) laSesion.getAttribute("genero");
			}
			if (laSesion.getAttribute("casado") != null) {
				casado = (String) laSesion.getAttribute("casado");
			}
			if (laSesion.getAttribute("hijos") != null) {
				hijos = (String) laSesion.getAttribute("hijos");
			}
			if (laSesion.getAttribute("nacionalidad") != null) {
				nacionalidad = (String[]) laSesion.getAttribute("nacionalidad");
			}
			if (laSesion.getAttribute("departamento") != null) {
				departamento = (String) laSesion.getAttribute("departamento");
			}
			if (laSesion.getAttribute("salario") != null) {
				salario = (String) laSesion.getAttribute("salario");
			}
			if (laSesion.getAttribute("comentarios") != null) {
				comentarios = (String) laSesion.getAttribute("comentarios");
			}
			if (laSesion.getAttribute("cuentaCorriente") != null) {
				cuentaCorriente = (String) laSesion.getAttribute("cuentaCorriente");
			}

		}

		// se pasan los atributos al JSP
		request.setAttribute("pasoActivo", PASO_ACTIVO);
		request.setAttribute("nombre", nombre);
		request.setAttribute("apellidos", apellidos);
		request.setAttribute("fechaNacimiento", fechaNacimiento);
		request.setAttribute("genero", genero);
		request.setAttribute("casado", casado);
		request.setAttribute("hijos", hijos);
		request.setAttribute("nacionalidad", nacionalidad);
		request.setAttribute("departamento", departamento);
		request.setAttribute("salario", salario);
		request.setAttribute("comentarios", comentarios);
		request.setAttribute("cuentaCorriente", cuentaCorriente);

		// enruta a la vista
    request.getRequestDispatcher("/Resumen.jsp").forward(request,response);
	}


}
