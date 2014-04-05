package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import static com.ehsunbehravesh.fcpersepolisrest.ejb.NewsFetchBean.OFFICIAL_WEBSITE;
import com.ehsunbehravesh.persepolis.entity.News;
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
@WebServlet(name = "DesktopHomeServlet", urlPatterns = {"/home"})
public class DesktopHomeServlet extends PageServlet {

  @Inject
  private NewsBean newsBean;
  
  private static final Logger log = Logger.getLogger(DesktopHomeServlet.class.getName());

  public DesktopHomeServlet() {
    super("desktop_home.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    List<News> officialNewsList = newsBean.readTop(OFFICIAL_WEBSITE, 10);
    setAttr("officialNewsList", officialNewsList);
    
    showTemplate(req, resp);
  }
  
  
}
