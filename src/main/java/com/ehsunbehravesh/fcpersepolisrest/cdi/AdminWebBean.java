package com.ehsunbehravesh.fcpersepolisrest.cdi;

import com.ehsunbehravesh.fcpersepolisrest.ejb.AdminBean;
import com.ehsunbehravesh.persepolis.entity.Administrator;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ehsun7b
 */
@Named(value = "adminWeb")
@SessionScoped
public class AdminWebBean implements Serializable {
  
  @Inject
  private AdminBean adminBean;
  
  private Administrator admin;

  public Administrator getAdmin() {
    return admin;
  }

  public void setAdmin(Administrator admin) {
    this.admin = admin;
  }    
}
