package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "AdminHomeServlet", urlPatterns = {"/admin", "/admin/"})
public class AdminHomeServlet extends AdminPage {

  public AdminHomeServlet() {
    super("admin_home.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
    if (admin != null) {      
      showTemplate(req, resp);
    }
  }

}
