package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsuhnbehravesh.persepolis.news.News;
import com.ehsuhnbehravesh.persepolis.news.PersepolisNewsMatcher;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ehsun7b
 */
@Path("isna")
public class ISNANews {
  
  private static final Logger log = Logger.getLogger(ISNANews.class.getName());
  public static final String RSS_URL = "http://www.isna.ir/fa/Sports/feed";
  public static List<News> cache = new ArrayList<>();
  public static Date cacheDate;
  public static final Object cacheLock = new Object();
  public static final long CACHE_MAX_AGE = 10 * 60 * 1000; // milliseconds //
  // first is minutes
  public static final int MAX_ITEMS = 15;
  
  @GET
  @Path("json")
  @Produces("application/json; charset=UTF-8")
  public List<News> newsJson() throws IOException {

    if (cacheIsOld()) {
      try {
        load();
      } catch (ParserConfigurationException | SAXException e) {
        if (cache.size() > 0) {
          cacheDate = new Date();
        }
      }
    }

    return cache;
  }

  @GET
  @Path("jsonp")
  @Produces("application/javascript; charset=UTF-8")
  public String newsJsonp(@QueryParam("callback") String callback) throws IOException {

    if (cacheIsOld()) {
      try {
        load();
      } catch (ParserConfigurationException | SAXException e) {
        if (cache.size() > 0) {
          cacheDate = new Date();
        }
      }
    }

    Gson gson = new Gson();
    String json = gson.toJson(cache);

    if (callback == null) {
      callback = "";
    }

    return callback.concat("(").concat(json).concat(")");
  }

  private void load() throws ParserConfigurationException, MalformedURLException, SAXException, IOException {
    synchronized (cacheLock) {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(RSS_URL).openStream());

      NodeList items = doc.getElementsByTagName("item");
      if (items.getLength() > 0) {
        cache.clear();
      }
      
      for (int i = 0; i < items.getLength(); i++) {
        try {
          Element item = (Element) items.item(i);
          Element title = (Element) item.getElementsByTagName("title").item(0);
          Element description = (Element) item.getElementsByTagName("description").item(0);
          Element pubDate = (Element) item.getElementsByTagName("pubDate").item(0);
          Element link = (Element) item.getElementsByTagName("link").item(0);

          News news = new News();
          news.setTitle(title.getTextContent());
          news.setDescription(description.getTextContent());
          news.setLink(link.getTextContent());
          news.setPublishDate(pubDate.getTextContent());

          if (isPersepolisNews(news) && cache.size() < MAX_ITEMS) {
            cache.add(news);
          }
        } catch (DOMException ex) {
          log.log(Level.SEVERE, "Error in loading RSS: " + RSS_URL + " {0}", ex.getMessage());
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

  private boolean isPersepolisNews(News news) {
    PersepolisNewsMatcher matcher = new PersepolisNewsMatcher();    
    return matcher.match(news);        
  }
}
