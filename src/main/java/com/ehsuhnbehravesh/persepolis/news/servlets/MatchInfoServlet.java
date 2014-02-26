package com.ehsuhnbehravesh.persepolis.news.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ehsuhnbehravesh.persepolis.net.PreviousAndNextMatchFetch;
import com.ehsuhnbehravesh.persepolis.news.PreviousNextMatchInfo;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/matchinfo"})
@SuppressWarnings("serial")
public class MatchInfoServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(MatchInfoServlet.class
          .getName());

  public static final long CACHE_MAX_AGE = 120 * 60 * 1000; // milliseconds //
  public static Date cacheDate;
  public static final Object cacheLock = new Object();
  private PreviousNextMatchInfo cache = null;
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    if (cacheIsOld()) {
      load();
    }

    PrintWriter writer = resp.getWriter();
    Gson gson = new Gson();
    String json = gson.toJson(cache);
    writer.write(req.getParameter("callback") + "(".concat(json).concat(")"));

    resp.getWriter().close();
    resp.flushBuffer();

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
