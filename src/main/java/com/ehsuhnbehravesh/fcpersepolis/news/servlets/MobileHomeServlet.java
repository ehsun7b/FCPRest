package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(urlPatterns = {"/mobile"})
public class MobileHomeServlet extends PageServlet {

  private static final Logger log = Logger.getLogger(MobileHomeServlet.class.getName());

  public MobileHomeServlet() {
    super("mobile_home.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    showTemplate(req, resp);
  }
  
  
}
