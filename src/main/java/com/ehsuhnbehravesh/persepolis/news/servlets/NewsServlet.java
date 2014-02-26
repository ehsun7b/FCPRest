package com.ehsuhnbehravesh.persepolis.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ehsuhnbehravesh.persepolis.news.News;
import com.google.gson.Gson;
import java.util.logging.Level;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/news"})
@SuppressWarnings("serial")
public class NewsServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(NewsServlet.class.getName());

  public static final String RSS_URL = "http://www1.fc-perspolis.com/rss";
  public static List<News> cache = new ArrayList<>();
  public static Date cacheDate;
  public static final Object cacheLock = new Object();
  public static final long CACHE_MAX_AGE = 10 * 60 * 1000; // milliseconds //
  // first is minutes
  public static final int MAX_ITEMS = 15;

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    if (cacheIsOld()) {
      try {
        load();
      } catch (ParserConfigurationException | SAXException e) {
        if (cache.size() > 0) {
          cacheDate = new Date();
        }
      }
    }

    writeResponse(resp.getWriter(), req.getParameter("callback"));
    resp.getWriter().close();
    resp.flushBuffer();
  }

  private void writeResponse(PrintWriter writer, String callback) {
    if (callback != null) {
      writer.write(callback);
    }

    Gson gson = new Gson();

    String json = gson.toJson(cache);

    writer.write("(".concat(json).concat(")"));
  }

  private void load() throws ParserConfigurationException,
          MalformedURLException, SAXException, IOException {
    synchronized (cacheLock) {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(RSS_URL).openStream());

      NodeList items = doc.getElementsByTagName("item");
      if (items.getLength() > 0) {
        cache.clear();
      }
      for (int i = 0; i < Math.min(items.getLength(), MAX_ITEMS); i++) {
        try {
          Element item = (Element) items.item(i);
          Element title = (Element) item.getElementsByTagName("title").item(0);
          Element description = (Element) item.getElementsByTagName(
                  "description").item(0);
          Element pubDate = (Element) item.getElementsByTagName("pubDate")
                  .item(0);
          Element link = (Element) item.getElementsByTagName("link").item(0);

          News news = new News();
          news.setTitle(title.getTextContent());
          news.setDescription(description.getTextContent());
          news.setLink(link.getTextContent());
          news.setPublishDate(pubDate.getTextContent());

          cache.add(news);
        } catch (Exception ex) {
          log.log(Level.SEVERE,"Error in loading RSS: " + RSS_URL + " {0}", ex.getMessage());
        }
      }
      cacheDate = new Date();
    }
  }

  private boolean cacheIsOld() {
    if (cacheDate == null) {
      return true;
    }
    Date now = new Date();
    long diff = now.getTime() - cacheDate.getTime();
    return (diff > CACHE_MAX_AGE);
  }
}
