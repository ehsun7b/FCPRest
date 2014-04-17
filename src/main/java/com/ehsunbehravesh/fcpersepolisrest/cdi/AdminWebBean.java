package com.ehsunbehravesh.fcpersepolisrest.cdi;

import com.ehsunbehravesh.fcpersepolisrest.ejb.AdminBean;
import com.ehsunbehravesh.persepolis.entity.Administrator;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ehsun7b
 */
@Named(value = "adminWeb")
@SessionScoped
public class AdminWebBean implements Serializable {

  private static final Logger log = Logger.getLogger(AdminWebBean.class.getName());
  
  @Inject
  private AdminBean adminBean;

  private Administrator admin;

  private String username;
  private String password;

  public void login() {
    Administrator loggedAdmin = adminBean.login(username, password);
    FacesContext context = FacesContext.getCurrentInstance();
    Properties p = loadProperties();
    
    if (loggedAdmin != null && loggedAdmin.getId() > 0) {
      admin = loggedAdmin;
      //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,p.getProperty("successfulLogin"), ""));
      ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
      try {
        ec.redirect("index.xhtml");
      } catch (IOException ex) {
        log.log(Level.SEVERE, "Can not redirect to admin home.", ex);
      }
    } else {
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,p.getProperty("failedLogin"), ""));      
    }
  }
  
  public String logout() {
    admin = null;
    return "/";
  }

  public Administrator getAdmin() {
    return admin;
  }

  public void setAdmin(Administrator admin) {
    this.admin = admin;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private Properties loadProperties() {
    try {
      InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.properties");
      Properties properties = new Properties();
      properties.load(input);
      return properties;
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Can not load admin.properties resource.", ex);
      return null;
    }
  }

}
