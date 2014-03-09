package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsuhnbehravesh.persepolis.net.PreviousAndNextMatchFetch;
import com.ehsuhnbehravesh.persepolis.news.PreviousNextMatchInfo;
import com.google.gson.Gson;
import java.util.Date;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author ehsun7b
 */
@Path("matchinfo")
public class MatchInfo {

  private static final Logger log = Logger.getLogger(MatchInfo.class.getName());

  public static final long CACHE_MAX_AGE = 120 * 60 * 1000; // milliseconds //
  public static Date cacheDate;
  public static final Object cacheLock = new Object();
  private PreviousNextMatchInfo cache = null;

  @GET
  @Path("json")
  @Produces("application/json; charset=UTF-8")
  public PreviousNextMatchInfo json() {
    
    if (cacheIsOld()) {
      load();
    }
    
    return cache;
  }
  
  
  @GET
  @Path("jsonp")
  @Produces("application/javascript; charset=UTF-8")
  public String jsonp(@QueryParam("callback") String callback) {
    
    if (cacheIsOld()) {
      load();
    }
    
    Gson gson = new Gson();
    String json = gson.toJson(cache);

    if (callback == null) {
      callback = "";
    }

    return callback.concat("(").concat(json).concat(")");
  }
  
  private void load() {
    PreviousAndNextMatchFetch fetch = new PreviousAndNextMatchFetch();
    synchronized (cacheLock) {
      try {
        fetch.load();
        cache = fetch.getPreviousNextMatchInfo();
        cacheDate = new Date();
      } catch (Exception ex) {
        log.severe(ex.getMessage());
      }
    }
  }

  private boolean cacheIsOld() {
    if (cache == null) {
      return true;
    }
    Date now = new Date();
    long diff = now.getTime() - cacheDate.getTime();
    return (diff > CACHE_MAX_AGE);
  }
}
