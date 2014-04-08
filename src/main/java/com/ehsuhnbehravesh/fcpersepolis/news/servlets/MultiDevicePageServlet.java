/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import static com.ehsuhnbehravesh.fcpersepolis.news.servlets.PageServlet.templateDirectory;
import com.ehsunbehravesh.utils.web.CachedUserAgentParser;
import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentType;

/**
 *
 * @author ehsun7b
 */
public class MultiDevicePageServlet extends PageServlet {

  public MultiDevicePageServlet(String template) {
    super(template);
  }

  @Override
  public void showTemplate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CachedUserAgentParser parser = new CachedUserAgentParser();

    String strAgent = req.getHeader("User-Agent");
    ReadableUserAgent agent = parser.parse(strAgent);
    
    String tempTemplate;
    if (agent.getType().equals(UserAgentType.MOBILE_BROWSER)) {
      tempTemplate = "mobile_" + template;
    } else {
      tempTemplate = "desktop_" + template;
    }

    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
      req.setAttribute(entry.getKey(), entry.getValue());
    }

    attributes.clear();

    String templatePath = templateDirectory.concat(tempTemplate);
    RequestDispatcher rd = getServletContext().getRequestDispatcher(templatePath);
    rd.forward(req, resp);
  }
  
  
}
