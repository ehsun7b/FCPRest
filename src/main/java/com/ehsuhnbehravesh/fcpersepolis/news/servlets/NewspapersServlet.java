package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewspaperSetBean;
import com.ehsunbehravesh.persepolis.entity.Newspaper;
import com.ehsunbehravesh.persepolis.entity.NewspaperSet;
import com.ehsunbehravesh.utils.web.CachedUserAgentParser;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentType;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "NewspapersServlet", urlPatterns = "/newspapers/*")
public class NewspapersServlet extends PageServlet {

  @Inject
  private NewspaperSetBean setBean;

  public NewspapersServlet() {
    super("newspapers.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    NewspaperSet set = setBean.findLast();
    Newspaper newspaper = null;

    CachedUserAgentParser parser = new CachedUserAgentParser();

    String strAgent = req.getHeader("User-Agent");
    ReadableUserAgent agent = parser.parse(strAgent);

    if (agent.getType().equals(UserAgentType.MOBILE_BROWSER)) {
      setTemplate("mobile_newspapers.jsp");
    } else {
      setTemplate("desktop_newspapers.jsp");
    }
    
    if (set != null) {
      String strNewspaperId = req.getPathInfo();
      Long newspaperId = null;

      if (strNewspaperId != null && strNewspaperId.startsWith("/")) {
        strNewspaperId = strNewspaperId.substring(1);
        
        int slashIndex = strNewspaperId.indexOf("/");
        if (slashIndex > 0) {
          strNewspaperId = strNewspaperId.substring(0, slashIndex);
        }
        
        newspaperId = Long.parseLong(strNewspaperId);
      }
      
      if (newspaperId != null && set.getNewspapers() != null) {
        for (Newspaper n : set.getNewspapers()) {
          if (n.getId().equals(newspaperId)) {
            newspaper = n;
            break;
          }
        }

        if (newspaper == null) {
          newspaper = set.getNewspapers().get(0);
        }
      }
    }

    setAttr("set", set);
    setAttr("newspaper", newspaper);
    setAttr("newspapers", set.getNewspapers());
    
    showTemplate(req, resp);
  }

}
