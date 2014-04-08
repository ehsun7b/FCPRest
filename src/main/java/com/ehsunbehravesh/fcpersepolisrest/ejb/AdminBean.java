package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.Administrator;
import com.ehsunbehravesh.utils.security.HashUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ehsun7b
 */
@Stateless
public class AdminBean {

  private static final Logger log = Logger.getLogger(AdminBean.class.getName());

  @PersistenceContext
  private EntityManager em;

  public void save(Administrator admin) {
    em.persist(admin);
  }

  public void saveNew(String username, String password) throws Exception {
    Administrator admin = new Administrator();
    admin.setUsername(username);
    admin.setSalt(HashUtils.generateSalt(10));
    try {
      String hashedPassword = HashUtils.SHA256(password.concat(admin.getSalt()));

      for (int i = 0; i < 300; i++) {
        hashedPassword = HashUtils.SHA256(hashedPassword);
      }
      
      admin.setPassword(hashedPassword);
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
      log.log(Level.SEVERE, "Error in hashing password {0}", ex.getMessage());
      throw new Exception("Saving new admin failed.");
    }
    
    em.persist(admin);
  }

  public boolean login(Administrator admin) {
    TypedQuery<Administrator> query = em.createQuery("select adminis from Administrator adminis where adminis.username = :username and adminis.password = :password", Administrator.class);
    query.setParameter("username", admin.getUsername());
    query.setParameter("password", admin.getPassword());

    try {
      Administrator result = query.getSingleResult();

      if (result.getId().equals(admin.getId())) {
        return true;
      } else {
        return false;
      }
    } catch (NoResultException ex) {
      return false;
    }
  }

  public Administrator login(String username, String password) {
    TypedQuery<Administrator> query = em.createQuery("select adminis from Administrator adminis where adminis.username = :username", Administrator.class);
    query.setParameter("username", username);

    Administrator result = null;

    try {
      result = query.getSingleResult();

      String salt = result.getSalt();
      String hashedPassword = HashUtils.SHA256(password.concat(salt));

      for (int i = 0; i < 300; i++) {
        hashedPassword = HashUtils.SHA256(hashedPassword);
      }

      if (!hashedPassword.equals(result.getPassword())) {
        result = null;
      }
    } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
      log.log(Level.SEVERE, "Error in admin login. {0}", ex.getMessage());
    } catch (NoResultException ex) {
      result = null;
    }

    return result;
  }

}
