package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author ehsun7b
 */
class IrnaNewsDescriptionFetch extends NewsDescriptionFetch {

  private Document doc;

  @Override
  public String loadDescription() throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String loadDate() throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String loadImage() throws Exception {
    if (doc == null) {            
      System.out.println("urLLL: " + newsUrl);
      doc = Jsoup.connect(newsUrl).get();
    }

    Element img = doc.select("img#ctl00_ctl00_ContentPlaceHolder_ContentPlaceHolder_NewsContent3_Image1").get(0);

    String result = null;

    if (img != null) {
      result = img.attr("abs:src");
    }

    return result;
  }

}
