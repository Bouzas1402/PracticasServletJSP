package com.example.servlets;



import com.example.db.dbinfo;
import com.example.db.jpaLifeCycle;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/jpa-ciclo")
public class JpaCiclo extends HttpServlet {

  @Inject
  dbinfo DBinfo;

  @Inject
  jpaLifeCycle jpalc;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter printWriter = response.getWriter();
    response.setContentType("text/plain;charset=UTF-8");
    printWriter.print(DBinfo.getVersion());
  }


}
