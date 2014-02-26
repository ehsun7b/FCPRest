package com.ehsuhnbehravesh.persepolis.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehsuhnbehravesh.persepolis.net.RankingFetch;
import com.ehsuhnbehravesh.persepolis.news.Ranking;
import com.google.gson.Gson;
import java.util.logging.Level;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/ranking"})
@SuppressWarnings("serial")
public class RankingServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(RankingServlet.class.getName());

  public static final long CACHE_MAX_AGE = 10 * 60 * 1000; // milliseconds // //
  // first is minutes
  private static Ranking ranking;
  private static Date loadDate;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    if (cacheIsOld()) {
      load();
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

    String json = gson.toJson(ranking);

    writer.write("(".concat(json).concat(")"));
  }

  private void load() {
    RankingFetch rankingFetch = new RankingFetch();
    try {
      ranking = rankingFetch.load();
      loadDate = new Date();
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Error in loading ranking table: {0}", ex.getMessage());
      ranking = new Ranking();
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
