package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ehsun7b
 */
public class PageServlet extends HttpServlet {

  protected static final String templateDirectory = "/WEB-INF/jsp/";
  protected String template;
  protected HashMap<String, Object> attributes = new HashMap<>(); 

  public PageServlet(String template) {
    this.template = template;
  }

  public void showTemplate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
      req.setAttribute(entry.getKey(), entry.getValue());
    }

    attributes.clear();

    String templatePath = templateDirectory.concat(template);
    RequestDispatcher rd = getServletContext().getRequestDispatcher(templatePath);
    rd.forward(req, resp);
  }

  public void putAttrsIntoSession(HttpSession session) {
    if (attributes != null) {
      for (Map.Entry<String, Object> entry : attributes.entrySet()) {
        session.setAttribute("attr_".concat(entry.getKey()), entry.getValue());
      }
    }
  }

  public void getAttrsFromSession(HttpSession session) {
    Enumeration<String> attributeNames = session.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
      String key = attributeNames.nextElement();
      if (key.startsWith("attr_")) {
        setAttr(key.replace("attr_", ""), session.getAttribute(key));
        session.setAttribute(key, null);
      }
    }
  }

  public void setAttr(String name, Object value) {
    attributes.put(name, value);
  }

  public Object getAttr(String name) {
    return attributes.get(name);
  }

  public void clearAttrs() {
    attributes.clear();
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public HashMap<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(HashMap<String, Object> attributes) {
    this.attributes = attributes;
  }
}
