package com.example.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/GuardaCookie")
public class GuardaCookie extends HttpServlet {
    private static final String UTF_8 = "UTF_8";

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            procesaSolicitud(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            procesaSolicitud(request, response);
        }

        protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try {
                String nombre = request.getParameter("profesiones").split("=")[2];
                Cookie cookie = new Cookie(nombre, "" + request.getParameter("profesiones").split("=")[0] + "=" + request.getParameter("profesiones").split("=")[1]);
                cookie.setMaxAge(60 * 60 * 24 * 365 * 4);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/contadorVisitas.html");
            } catch (Exception e) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("Se produce una excepcion <br>");
                e.printStackTrace(out);
                out.close();
            }
        }
}
