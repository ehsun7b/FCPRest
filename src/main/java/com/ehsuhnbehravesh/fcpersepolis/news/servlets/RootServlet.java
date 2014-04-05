package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.utils.web.CachedUserAgentParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.UserAgentType;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "RootServlet", urlPatterns = {""})
public class RootServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(RootServlet.class.getName());
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);

    CachedUserAgentParser parser = new CachedUserAgentParser();
    String strAgent = request.getHeader("User-Agent");
    ReadableUserAgent agent = parser.parse(strAgent);
    
    if (agent.getType().equals(UserAgentType.MOBILE_BROWSER)) {
      log.log(Level.INFO, "Redirect to /mobile user-agent: {0}", strAgent);
      response.sendRedirect("/mobile");
    } else if (agent.getType().equals(UserAgentType.BROWSER)) {
      log.log(Level.INFO, "Redirect to /home {1} user-agent: {0}", new Object[]{strAgent, request.getRequestURI()});
      response.sendRedirect("/home");
    }
  }

}
