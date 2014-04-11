package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewspaperSetBean;
import com.ehsunbehravesh.persepolis.entity.Newspaper;
import com.ehsunbehravesh.persepolis.entity.NewspaperSet;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ehsun7b
 */
@WebServlet(name = "NewspapersServlet", urlPatterns = {"/newspapers/*", "/newspapers"})
public class NewspapersServlet extends MultiDevicePageServlet {

  @Inject
  private NewspaperSetBean setBean;

  public NewspapersServlet() {
    super("newspapers.jsp");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    NewspaperSet set = setBean.findLast();
    Newspaper newspaper = null;

    if (set != null) {
      String strNewspaperId = req.getPathInfo();
      Long newspaperId = null;

      if (strNewspaperId != null && strNewspaperId.startsWith("/")
              && strNewspaperId.length() > 1) {
        strNewspaperId = strNewspaperId.substring(1);

        int slashIndex = strNewspaperId.indexOf("/");
        if (slashIndex > 0) {
          strNewspaperId = strNewspaperId.substring(0, slashIndex);
        }

        try {
          newspaperId = Long.parseLong(strNewspaperId);
        } catch (NumberFormatException ex) {
          newspaperId = null;
        }
      }

      if (newspaperId != null && set.getNewspapers() != null) {
        for (Newspaper n : set.getNewspapers()) {
          if (n.getId().equals(newspaperId)) {
            newspaper = n;
            break;
          }
        }
      }

      if (newspaper == null) {
        newspaper = set.getNewspapers().get(0);
      }
    }

    setAttr("set", set);
    setAttr("newspaper", newspaper);
    setAttr("count", set.getNewspapers().size());
    setAttr("newspapers", set.getNewspapers());

    showTemplate(req, resp);
  }

}
