package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.persepolis.entity.Administrator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
  
  private static final Logger log = Logger.getLogger(AdminProfileServlet.class.getName());
  
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
    super.doPost(req, resp);
    if (admin != null) {
      String oldPassword = req.getParameter("oldPassword");
      String newPassword = req.getParameter("newPassword");
      String confirmPassword = req.getParameter("confirmPassword");
      
      if (oldPassword != null && newPassword != null && confirmPassword != null) {
        if (oldPassword.length() > 0 && newPassword.length() > 0 && confirmPassword.length() > 0) {
          if (newPassword.equals(confirmPassword)) {
            Administrator testAdmin = adminBean.login(admin.getUsername(), oldPassword);
            
            if (testAdmin != null && testAdmin.getId().equals(admin.getId())) {
              admin.setPassword(newPassword);
              try {
                adminBean.saveNewPassword(admin);
                setAttr("info", "Password changed successfully.");
              } catch (Exception ex) {
                log.log(Level.SEVERE, null, ex);
                setAttr("error", "Change password failed!!!");
              }
            } else {
              setAttr("warning", "Wrong password!!!");
            }
          } else {
            setAttr("warning", "Confirm password does NOT match!");
          }
        } else {
          setAttr("warning", "Please enter all fields!");
        }
      } else {
        setAttr("warning", "Please enter all fields!");
      }
      
      showTemplate(req, resp);
    }
  }

}
