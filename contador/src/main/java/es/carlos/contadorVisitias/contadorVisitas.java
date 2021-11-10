package es.carlos.contadorVisitias;

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
		String nombre = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		boolean usuarioValido = comprobarUsuarios(nombre,clave);
		if (!usuarioValido) {
			String rutaContexto = request.getContextPath();
			String vistaDestino = "/contadorVisitas.html";
			response.sendRedirect(rutaContexto + vistaDestino);
		} else {

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			Cookie unaCookie = null;
		try {
			Cookie []arrayCookie = request.getCookies();

			for (int i = 0; i < arrayCookie.length; i++) {
				if (arrayCookie[i].getName().equals(nombre)){
					String valorCookie = arrayCookie[i].getValue();
					if (valorCookie.split("-")[0] != null && valorCookie.split("-")[1] != null){
						unaCookie = arrayCookie[i];
					}
				}
			}
			if (unaCookie == null) {
				String query = request.getQueryString();
				out.println(dibujarFormulario(query, 1));
			} else {
				String valorCookie = unaCookie.getValue();
				String [] valores = valorCookie.split("-");
				int valorContador = Integer.parseInt(valores[1]);
				valorContador++;
				String query = request.getQueryString();
				out.println(dibujarFormulario(query, valorContador));
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

	private String dibujarFormulario(String query, int contador){
		String respuesta = "";
		respuesta += "<html>";
		respuesta += "<head>";
		respuesta += "<title>Tipo de usuario</title>";
		respuesta += "</head>";
		respuesta += "<body>";
		respuesta += "<h1>Bienvenido aprendiz</h1>";
		respuesta += "<form action='GuardaCookie'>";
		respuesta += "<label for=\"Alfarero\">Alfarero</label><input type=\"radio\" name=\"" + query + "=" + contador + "\" id=\"Alfarero\" value=\"alfarero\" checked/>";
		respuesta += "<label for=\"Brujo\">Brujo</label><input type=\"radio\" name=\"" + query + "=" + contador + "\" id=\"Brujo\" value=\"Brujo\"/>";
		respuesta += "<label for=\"Curtidor\">Curtidor</label><input type=\"radio\" name=\"" + query + "=" + contador +  "\" id=\"Curtidor\" value=\"curtidor\" />";
		respuesta += "<input type=\"submit\" value=\"Enviar\" />";
		respuesta += "<p>Contador de visitas : " + contador + "</p>";
		respuesta += "</form>";
		return respuesta;
	}

	private boolean comprobarUsuarios (String usuario, String clave) {
		Map<String, String> usuariosValidos = new LinkedHashMap<>();
		usuariosValidos.put("carlos", "quevedo");
		usuariosValidos.put("juan", "quevedo");
		usuariosValidos.put("ana", "quevedo");
		boolean nombreValido = usuariosValidos.containsKey(usuario);
		boolean claveValida = usuariosValidos.containsValue(clave);
		if (nombreValido && claveValida){
			return true;
		}
		return false;
	}
}
