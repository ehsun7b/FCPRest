package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.persepolis.entity.News;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "AdminNewsServlet", urlPatterns = {"/admin/news"})
public class AdminNewsServlet extends AdminPage {

  @Inject
  private NewsBean newsBean;
  
  public AdminNewsServlet() {
    super("admin_news.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp); 
    if (admin != null) {
      List<News> newsList = newsBean.readTop(null, 50);
      setAttr("newsList", newsList);
      showTemplate(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
  
  
}
