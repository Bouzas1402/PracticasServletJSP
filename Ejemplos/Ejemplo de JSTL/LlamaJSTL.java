package es.carlosgs.introjee.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LlamaJSTL")
public class LlamaJSTL extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Empleado emp1 = new Empleado();
        emp1.setEmpno(1);
        emp1.setEname("Smith");
        emp1.setJob("Salesman");
        Empleado emp2 = new Empleado();
        emp2.setEmpno(2);
        emp2.setEname("Jones");
        emp2.setJob("Manager");
        listaEmpleados.add(emp1);
        listaEmpleados.add(emp2);
        request.setAttribute("listaEmpleados", listaEmpleados);
        request.setAttribute("htmlTagData", "La etiqueta <br /> crea un salto de línea");
        request.setAttribute("url", "http://www.journaldev.com");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/llamaJSTL.jsp");
        rd.forward(request, response);
    }


}
