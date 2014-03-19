package com.ehsuhnbehravesh.persepolis.news.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet("/test_temp")
public class TestFile extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    File temp = new File(System.getProperty("java.io.tmpdir"));
    File test = new File(temp, "test.txt");
    try (PrintWriter writer = new PrintWriter(test)) {
      writer.println(new Date().toString());      
      writer.flush();
    }
    
    BufferedReader reader = new BufferedReader(new FileReader(test));
    
    resp.getWriter().write(test.getAbsolutePath());
    resp.getWriter().write(" --- ");
    resp.getWriter().write(reader.readLine());
  }
  
  
}
