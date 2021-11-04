package es.carlos.contadorVisitias;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

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

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		Cookie unaCookie;

		try {
			Cookie []arrayCookie = request.getCookies();

				out.println("<html>");
				out.println("<head>");
				out.println("<title>Tipo de usuario</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Bienvenido aprendiz</h1>");
				out.println("<form action=''>");
				out.println("<label for=\"Alfarero\">Alfarero</label><input type=\"radio\" name=\"alfarero\" id=\"Alfarero\" value=\"alfarero\" />");
				out.println("<label for=\"Alfarero\">Brujo</label><input type=\"radio\" name=\"brujo\" id=\"Brujo\" value=\"brujo\"/>");
				out.println("<label for=\"Alfarero\">Curtidor</label><input type=\"radio\" name=\"curtidor\" id=\"Curtidor\" value=\"curtidor\" />");
				out.println("<input type=\"submit\" value=\"Enviar\" />");
				out.println("</form>");

				// se inicializa un objeto Cookie
				String nombre = request.getParameter("usuario");
				Integer contador = 1;


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
