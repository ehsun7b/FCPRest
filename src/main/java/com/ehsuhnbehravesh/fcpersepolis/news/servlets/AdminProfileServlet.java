package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.persepolis.entity.HotNews;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "AdminProfileServlet", urlPatterns = {"/admin/profile"})
public final class AdminProfileServlet extends AdminPage {

  public AdminProfileServlet() {
    super("admin_profile.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
    if (admin != null) {            
      showTemplate(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
  }
  
  
  
}
