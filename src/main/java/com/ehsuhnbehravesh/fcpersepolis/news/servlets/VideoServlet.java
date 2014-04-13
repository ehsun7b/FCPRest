package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.VideoBean;
import com.ehsunbehravesh.persepolis.entity.Video;
import java.io.IOException;
import java.util.logging.Level;
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
@WebServlet(name = "VideoServlet", urlPatterns = {"/video/*"})
public class VideoServlet extends PageServlet {

  private static final Logger log = Logger.getLogger(VideoServlet.class.getName());

  @Inject
  private VideoBean videoBean;

  public VideoServlet() {
    super("desktop_video.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String strVideoId = req.getPathInfo();
    Long videoId = null;
    
    if (strVideoId.startsWith("/")) {
      strVideoId = strVideoId.substring(1);
    }
    
    try {
      videoId = Long.parseLong(strVideoId);
    } catch (NumberFormatException ex) {
      log.log(Level.WARNING, "Can not parse video ID. url: {0} id: {1}", new Object[] {req.getRequestURL().toString(), strVideoId});
    }
    
    if (videoId != null) {
      Video video = videoBean.find(videoId);
      setAttr("video", video);
      showTemplate(req, resp);
    } else {
      resp.sendRedirect("/");
    }
  }

}
