package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import static com.ehsunbehravesh.fcpersepolisrest.ejb.NewsFetchBean.OFFICIAL_WEBSITE;
import com.ehsunbehravesh.persepolis.entity.News;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author ehsun7b
 */
@Stateless
@Path("news")
public class OfficialWebsiteNews {

  private static final Logger log = Logger.getLogger(OfficialWebsiteNews.class.getName());
  
  @Inject
  private NewsBean newsBean;

  public static List<News> cache = new ArrayList<>();
  public static Date cacheDate;
  public static final Object cacheLock = new Object();
  public static final long CACHE_MAX_AGE = 10 * 60 * 1000; // milliseconds //
  // first is minutes
  public static final int MAX_ITEMS = 15;  

  @Path("json")
  @GET
  @Produces("application/json; charset=UTF-8")
  public List<News> news() throws IOException {

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
      List<News> news = newsBean.readTop(OFFICIAL_WEBSITE, MAX_ITEMS);
      if (news.size() > 0) {
        cache.clear();
        cache = news;
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
