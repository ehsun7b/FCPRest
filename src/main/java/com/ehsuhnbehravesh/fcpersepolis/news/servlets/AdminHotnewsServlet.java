package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.persepolis.entity.HotNews;
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
@WebServlet(name = "AdminHotnewsServlet", urlPatterns = {"/admin/hotnews"})
public class AdminHotnewsServlet extends AdminPage {

  @Inject
  private NewsBean newsBean;

  public AdminHotnewsServlet() {
    super("admin_hotnews.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
    if (admin != null) {
      List<HotNews> hotNewsList = newsBean.readTop(10);
      setAttr("hotNewsList", hotNewsList);

      showTemplate(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
    if (admin != null) {
      String ids = req.getParameter("newsId");

      if (ids != null && ids.trim().length() > 0) {
        String[] keys = ids.split(",");

        newsBean.newHotNews(keys);
      }

      List<HotNews> hotNewsList = newsBean.readTop(10);
      setAttr("hotNewsList", hotNewsList);

      showTemplate(req, resp);
    }
  }

}
