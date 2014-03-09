package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsuhnbehravesh.persepolis.net.RankingFetch;
import com.google.gson.Gson;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author ehsun7b
 */
@Path("ranking")
public class Ranking {

  private static final Logger log = Logger.getLogger(Ranking.class.getName());

  public static final long CACHE_MAX_AGE = 10 * 60 * 1000; // milliseconds // //
  // first is minutes
  private static com.ehsuhnbehravesh.persepolis.news.Ranking ranking;
  private static Date loadDate;

  @Path("json")
  @GET
  @Produces("application/json; charset=UTF-8")
  public String json() {
    if (cacheIsOld()) {
      load();
    }

    Gson gson = new Gson();
    String json = gson.toJson(ranking);

    return json;
  }

  @Path("jsonp")
  @GET
  @Produces("application/javascript; charset=UTF-8")
  public String jsonp(@QueryParam("callback") String callback) {
    if (cacheIsOld()) {
      load();
    }

    Gson gson = new Gson();
    String json = gson.toJson(ranking);

    if (callback == null) {
      callback = "";
    }

    return callback.concat("(").concat(json).concat(")");
  }

  private void load() {
    RankingFetch rankingFetch = new RankingFetch();
    try {
      ranking = rankingFetch.load();
      loadDate = new Date();
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Error in loading ranking table: {0}", ex.getMessage());
      ranking = new com.ehsuhnbehravesh.persepolis.news.Ranking();
    }
  }

  private boolean cacheIsOld() {
    if (loadDate == null) {
      return true;
    }
    Date now = new Date();
    long diff = now.getTime() - loadDate.getTime();
    return (diff > CACHE_MAX_AGE);
  }

}
