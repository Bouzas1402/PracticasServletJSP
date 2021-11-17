package com.example.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/Contador")
public class contadorVisitas extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		procesaSolicitud(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		procesaSolicitud(request, response);
	}

	protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String []profesiones = {"alfarero", "brujo", "curtidor"};
		String nombre = request.getParameter("usuario");
		boolean usuarioValido = comprobarUsuarios(nombre);
		if (!usuarioValido) {
			String rutaContexto = request.getContextPath();
			String vistaDestino = "/contadorVisitas.html";
			response.sendRedirect(rutaContexto + vistaDestino);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Tipo de usuario</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Bienvenido aprendiz</h1>");
			Cookie unaCookie = null;
		try {
			Cookie[] arrayCookie = request.getCookies();
			if (arrayCookie != null) {
				for (int i = 0; i < arrayCookie.length; i++) {
					if (arrayCookie[i].getName().equals(nombre)) {
						for (int j = 0; j < profesiones.length; j++) {
							if (arrayCookie[i].getValue().split("=")[0].equals(profesiones[j]) && arrayCookie[i].getValue().split("=")[1].matches("[0-9]+")) {
								unaCookie = arrayCookie[i];
							}
						}
					}
				}
		}
			if (unaCookie == null) {
				out.println(dibujarFormulario(1, profesiones, "alfarero", nombre));
			} else {
				out.println(dibujarFormulario((Integer.parseInt(unaCookie.getValue().split("=")[1]) + 1), profesiones, unaCookie.getValue().split("=")[0], nombre));
			}
		} catch (Exception e){
			out.println("Se produce una excepción: ");
			out.println(e.getMessage());
		} finally {
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}
}

	private String dibujarFormulario(int contador, String[] profesiones, String profesion, String nombre){
		String checked;
		String respuesta = "";
		respuesta += "<form action='GuardaCookie'>";
		for (String profesione : profesiones) {
			if (profesione.equals(profesion)) {
				checked = " checked ";
			} else {
				checked = "";
			}
			respuesta += "<label for='" + profesione + "'>" + profesione + "</label><input type='radio'  id='" + profesione + "' name='profesiones' value='" + profesione + "=" + contador + "=" + nombre + "' " + checked + " />";
		}
		respuesta += "<input type='submit' value='Enviar' />";
		respuesta += "<p>Contador de visitas : " + contador + "</p>";
		respuesta += "</form>";
		return respuesta;
	}


	public boolean comprobarUsuarios (String usuario) {
		Map<String, String> usuariosValidos = new LinkedHashMap<>();
		usuariosValidos.put("carlos", "quevedo");
		usuariosValidos.put("juan", "quevedo");
		usuariosValidos.put("ana", "quevedo");
			return usuariosValidos.containsKey(usuario);
	}
}
