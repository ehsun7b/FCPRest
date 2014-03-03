package com.ehsuhnbehravesh.persepolis.news.servlets;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(urlPatterns = {"/mobile"})
public class MobileHomeServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(MobileHomeServlet.class.getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.getWriter().write("MOB");
  }
  
  
}
