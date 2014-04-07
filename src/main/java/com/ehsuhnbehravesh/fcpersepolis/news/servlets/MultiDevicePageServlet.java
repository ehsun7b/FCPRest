/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.utils.web.CachedUserAgentParser;
import java.io.IOException;
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

    if (agent.getType().equals(UserAgentType.MOBILE_BROWSER)) {
      template = "mobile_" + template;
    } else {
      template = "desktop_" + template;
    }

    super.showTemplate(req, resp);
  }
  
  
}
