package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.fcpersepolisrest.ejb.VideoBean;
import com.ehsunbehravesh.persepolis.entity.News;
import com.ehsunbehravesh.persepolis.entity.Video;
import com.ehsunbehravesh.persepolis.entity.WorldCupNews;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "HomeServlet", urlPatterns = {""})
public class HomeServlet extends MultiDevicePageServlet {

  @Inject
  private NewsBean newsBean;
  
  @Inject
  private VideoBean videoBean;
  
  private static final Logger log = Logger.getLogger(HomeServlet.class.getName());

  public HomeServlet() {
    super("home.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    List<WorldCupNews> worldCupNewsList = newsBean.readTopWorldCup(10);
    List<News> newsList = newsBean.readTop(null, 10);    
    
    List<Video> videos = videoBean.readTop(10);
    setAttr("newsList", newsList);
    setAttr("videos", videos);
    setAttr("hotNewsList", newsBean.currentHotNews());
    setAttr("worldCupNewsList", worldCupNewsList);
    
    
    showTemplate(req, resp);
  }
  
  
}
