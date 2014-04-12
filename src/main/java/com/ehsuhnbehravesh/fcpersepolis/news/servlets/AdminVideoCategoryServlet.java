package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.VideoBean;
import com.ehsunbehravesh.persepolis.entity.VideoCategory;
import java.io.IOException;
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
@WebServlet(name = "AdminVideoCategoryServlet", urlPatterns = {"/admin/videocategory"})
public class AdminVideoCategoryServlet extends AdminPage {

  private static final Logger log = Logger.getLogger(AdminVideoCategoryServlet.class.getName());

  @Inject
  private VideoBean videoBean;
  
  public AdminVideoCategoryServlet() {
    super("");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
            
    if (admin != null) {
      String title = req.getParameter("title");
      String strCode = req.getParameter("code");
      VideoCategory category = new VideoCategory();
      category.setTitle(title);      
      category.setCode(Long.parseLong(strCode));
      
      videoBean.save(category);
      resp.sendRedirect("/admin/video");
    }
  }
  
  
  
}
