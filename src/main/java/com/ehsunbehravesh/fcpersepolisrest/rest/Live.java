package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.persepolis.entity.LiveResult;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author ehsun7b
 */
@Path("live")
public class Live {

  private static final Logger log = Logger.getLogger(Live.class.getName());
  private static LiveResult liveResult;
  private static Long updated;
  private static final Object updatedLock = new Object();
  private static final Object resultLock = new Object();

  static {
    setUpdated(System.currentTimeMillis());
  }

  @GET
  @Path("json")
  @Produces("application/json; charset=UTF-8")
  public String json() {
    HashMap<String, Object> result = new HashMap<>();

    if (liveResult != null) {
      result.put("result", liveResult);
      result.put("updated", updated);
    }

    Gson gson = new Gson();

    return gson.toJson(result);
  }

  @GET
  @Path("jsonp")
  @Produces("application/javascript; charset=UTF-8")
  public String jsonp(@QueryParam("callback") String callback) {
    HashMap<String, Object> result = new HashMap<>();

    if (liveResult != null) {
      result.put("result", liveResult);
      result.put("updated", updated);
    }

    Gson gson = new Gson();
    String json = gson.toJson(result);

    if (callback == null) {
      callback = "";
    }

    return callback.concat("(").concat(json).concat(")");
  }

  @GET
  @Path("start/{homeTeam}/{awayTeam}/{time}")
  @Produces("application/json; charset=UTF-8")
  public String start(@PathParam("homeTeam") String homeTeam,
          @PathParam("awayTeam") String awayTeam,
          @PathParam("time") String time) {

    setUpdated(System.currentTimeMillis());
    synchronized (resultLock) {
      liveResult = new LiveResult();
      liveResult.setHomeTeam(homeTeam);
      liveResult.setAwayTeam(awayTeam);
      liveResult.setHomeScore(0);
      liveResult.setAwayScore(0);
      liveResult.setTime(time);
    }

    return "";
  }

  @GET
  @Path("update/{homeScore}/{awayScore}/{time}")
  @Produces("application/json; charset=UTF-8")
  public String update(@PathParam("homeScore") int homeScore,
          @PathParam("awayScore") int awayScore,
          @PathParam("time") String time) {

    if (liveResult != null) {
      liveResult.setHomeScore(homeScore);
      liveResult.setAwayScore(awayScore);
      liveResult.setTime(time);
    }
    
    return "";
  }

  @GET
  @Path("finish")
  @Produces("application/json; charset=UTF-8")
  public void finish() {
    liveResult = null;
  }

  public static Long getUpdated() {
    synchronized (updatedLock) {
      return updated;
    }
  }

  public static void setUpdated(Long updated) {
    synchronized (updatedLock) {
      Live.updated = updated;
    }
  }
}
