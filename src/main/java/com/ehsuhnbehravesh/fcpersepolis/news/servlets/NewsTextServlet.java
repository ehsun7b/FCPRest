package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehsunbehravesh.persepolis.entity.News;
import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetch;
import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetchFactory;
import com.ehsunbehravesh.utils.web.CachedUserAgentParser;
import java.util.logging.Level;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentType;

@WebServlet(urlPatterns = {"/newstext"})
@SuppressWarnings("serial")
public class NewsTextServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(NewsTextServlet.class.getName());

  private static Map<String, CacheNode> cache = new HashMap<>();
  private static final int MAX_CACHE_SIZE = 10;
  private static final Object cacheLock = new Object();

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");
    
    String url = req.getParameter("url");    
    String template = "newstext.jsp";
    
    CachedUserAgentParser parser = new CachedUserAgentParser();
    
    String strAgent = req.getHeader("User-Agent");
    ReadableUserAgent agent = parser.parse(strAgent);
    
    if (agent.getType().equals(UserAgentType.MOBILE_BROWSER)) {      
      template = "newstext.jsp";
    } else if (agent.getType().equals(UserAgentType.BROWSER)) {      
      template = "newstext_desktop.jsp";
    }

    CacheNode node = null;
    synchronized (cacheLock) {
      node = cache.get(url);
    }

    News news = null;

    if (node != null) {
      news = node.getNews();
      node.setAccessCount(node.getAccessCount() + 1);
      log.info("URL found in Cache!");
    } else {
      log.info("URL NOT found in Cache!");
      try {
        NewsDescriptionFetch fetch = NewsDescriptionFetchFactory.generateNewsDescriptionFetch(url);
        String description = fetch.loadDescription();        
        String date = fetch.loadDate();
        String image = fetch.loadImage();
        String title = fetch.loadTitle();
        log.log(Level.INFO, "fetch content: {0} url: {1}", new Object[]{description, url});
        news = new News();
        news.setDescription(description);
        news.setPublishDate(date);
        news.setTitle(title);
        news.setImage(image);
        news.setLink(url);
        node = new CacheNode();
        node.setNews(news);

        synchronized (cacheLock) {
          if (cache.size() >= MAX_CACHE_SIZE) {
            removeOneFromCache();
          }
          cache.put(url, node);
        }

        log.info("content put in cache.");        
      } catch (Exception ex) {
        log.severe(ex.getMessage());
        redirectToNewsLink(req, resp, url);
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
    log.info("Redirected to: " + url);    
  }

  private void removeOneFromCache() {
    int min = Integer.MAX_VALUE;
    String minKey = null;

    for (String key : cache.keySet()) {
      CacheNode cacheNode = cache.get(key);
      if (cacheNode.getAccessCount() < min) {
        minKey = key;
      }
    }

    if (minKey != null) {
      cache.remove(minKey);
    }
  }
}

class CacheNode {

  private int accessCount;
  private News news;

  public News getNews() {
    return news;
  }

  public void setNews(News news) {
    this.news = news;
  }

  public int getAccessCount() {
    return accessCount;
  }

  public void setAccessCount(int accessCount) {
    this.accessCount = accessCount;
  }
}
