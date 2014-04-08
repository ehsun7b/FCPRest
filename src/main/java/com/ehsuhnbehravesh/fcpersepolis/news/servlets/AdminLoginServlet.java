package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.AdminBean;
import com.ehsunbehravesh.persepolis.entity.Administrator;
import com.ehsunbehravesh.utils.security.HashUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/admin/login"})
public class AdminLoginServlet extends PageServlet {

  @Inject
  private AdminBean adminBean;
  
  private static final String ADMIN_HOME = "/admin";
  
  public AdminLoginServlet() {
    super("admin_login.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
        
    Administrator admin = adminBean.login(username, password);
    
    if (admin != null) {
      req.getSession().setAttribute("admin", admin);
      resp.sendRedirect(ADMIN_HOME);
    } else {
      setAttr("message", "Wrong username / password!");
      
      /*
      try {
        adminBean.saveNew(username, password);
      } catch (Exception ex) {
        Logger.getLogger(AdminLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
      }*/
      
      showTemplate(req, resp);
    }        
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    showTemplate(req, resp);
  }
  
  
}
