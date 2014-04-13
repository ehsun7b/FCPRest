package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.AdminBean;
import com.ehsunbehravesh.persepolis.entity.Administrator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ehsun7b
 */
public class AdminPage extends PageServlet {

  private static final Logger log = Logger.getLogger(AdminPage.class.getName());
  
  @Inject
  protected AdminBean adminBean;

  protected static final String LOGIN_PAGE = "/admin/login";
  protected Administrator admin;

  public AdminPage(String template) {
    super(template);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    admin = checkLogin(req.getSession());
    if (admin == null) {
      resp.sendRedirect(LOGIN_PAGE);
    } else {
      setAttr("admin", admin);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    admin = checkLogin(req.getSession());
    if (admin == null) {
      resp.sendRedirect(LOGIN_PAGE);
    } else {
      setAttr("admin", admin);
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    admin = checkLogin(req.getSession());
    if (admin == null) {
      resp.sendRedirect(LOGIN_PAGE);
    } else {
      setAttr("admin", admin);
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    admin = checkLogin(req.getSession());
    if (admin == null) {
      resp.sendRedirect(LOGIN_PAGE);
    } else {
      setAttr("admin", admin);
    }
  }

  protected String getUTF8Parameter(HttpServletRequest req, String key) {
    try {
      return new String(req.getParameter(key).getBytes("iso-8859-1"), "UTF-8");
    } catch (UnsupportedEncodingException ex) {
      log.log(Level.SEVERE, null, ex);
      return null;
    }
  }
  
  private Administrator checkLogin(HttpSession session) {
    Object obAdmin = session.getAttribute("admin");
    if (obAdmin == null) {
      return null;
    } else {
      if (obAdmin instanceof Administrator) {
        Administrator admin = (Administrator) obAdmin;
        if (adminBean.login(admin)) {
          return admin;
        } else {
          return null;
        }
      } else {
        return null;
      }
    }
  }
}