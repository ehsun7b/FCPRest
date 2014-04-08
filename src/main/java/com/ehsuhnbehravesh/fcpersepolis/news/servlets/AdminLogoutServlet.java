package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "ADminLogoutServlet", urlPatterns = "/admin/logout")
public class AdminLogoutServlet extends HttpServlet {

  private static final String ADMIN_HOME = "/admin";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession().invalidate();
    resp.sendRedirect(ADMIN_HOME);
  }
  
  
}
