package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetch;
import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetchFactory;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsCacheBean;
import com.ehsunbehravesh.persepolis.entity.News;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NewsTextServlet", urlPatterns = {"/news/*"})
@SuppressWarnings("serial")
public class NewsTextServlet extends MultiDevicePageServlet {

  @Inject
  private NewsCacheBean newsBean;
  
  @Inject
  private NewsBean noCacheNewsBean;

  private static final Logger log = Logger.getLogger(NewsTextServlet.class.getName());

  public NewsTextServlet() {
    super("newstext.jsp");
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");

    String url = req.getPathInfo();

    if (url != null && url.startsWith("/")) {
      url = url.substring(1);
    }

    News news = loadNews(url);
    
    if (news == null) {
      log.log(Level.WARNING, "News not found! {0}", url);
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    } else if (news.getContent() == null) {
      try {
        NewsDescriptionFetch fetch = NewsDescriptionFetchFactory.generateNewsDescriptionFetch(news.getLink());
        String description = fetch.loadDescription();
        String date = fetch.loadDate();
        String image = fetch.loadImage();
        String title = fetch.loadTitle();
        news.setContent(description);
        news.setPublishDate(date);
        if (!title.equalsIgnoreCase(news.getTitle())) {
          news.setTitle(title);
        }
        news.setImage(image);
      } catch (Exception ex) {
        log.severe(ex.getMessage());
        redirectToNewsLink(req, resp, news.getLink());
        return;
      }
    }

    if (news != null) {
      List<News> newsList = noCacheNewsBean.readTop(news.getWebsite(), 20);
      setAttr("newsList", newsList);
    }
    
    setAttr("news", news);
    showTemplate(req, resp);
  }

  private void redirectToNewsLink(HttpServletRequest req,
          HttpServletResponse resp, String url) throws IOException {
    resp.sendRedirect(url);
    log.log(Level.INFO, "Redirected to: {0}", url);
  }

  private News loadNews(String uniqueKey) {
    return newsBean.findOne(uniqueKey);
  }
}
