package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetch;
import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetchFactory;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsCacheBean;
import com.ehsunbehravesh.persepolis.entity.News;
import com.ehsunbehravesh.utils.web.CachedUserAgentParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentType;

@WebServlet(name = "NewsTextServlet", urlPatterns = {"/news/*"})
@SuppressWarnings("serial")
public class NewsTextServlet extends HttpServlet {

  @Inject
  private NewsCacheBean newsBean;

  private static final Logger log = Logger.getLogger(NewsTextServlet.class.getName());

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");

    String url = req.getPathInfo();

    if (url != null && url.startsWith("/")) {
      url = url.substring(1);
    }

    News news = loadNews(url);

    String template = "newstext.jsp";

    CachedUserAgentParser parser = new CachedUserAgentParser();

    String strAgent = req.getHeader("User-Agent");
    ReadableUserAgent agent = parser.parse(strAgent);

    if (agent.getType().equals(UserAgentType.MOBILE_BROWSER)) {
      template = "newstext.jsp";
    } else if (agent.getType().equals(UserAgentType.BROWSER)) {
      template = "newstext_desktop.jsp";
    }

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

    req.setAttribute("news", news);
    RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + template);
    rd.forward(req, resp);
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
