package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ehsun7b
 */
class IrnaNewsDescriptionFetch extends NewsDescriptionFetch {

  private Document doc;

  @Override
  public String loadDescription() throws Exception {
    if (doc == null) {                  
      doc = Jsoup.connect(newsUrl).get();
    }
    
    Elements p = doc.select("p#ctl00_ctl00_ContentPlaceHolder_ContentPlaceHolder_NewsContent3_BodyLabel");
    return p.html();
  }

  @Override
  public String loadDate() throws Exception {
    if (doc == null) {                  
      doc = Jsoup.connect(newsUrl).get();
    }
    
    String date = doc.select("span#ctl00_ctl00_ContentPlaceHolder_ContentPlaceHolder_NewsContent3_NofaDateLabel2").text();
    return date;
  }

  @Override
  public String loadImage() throws Exception {
    if (doc == null) {                  
      doc = Jsoup.connect(newsUrl).get();
    }

    Element img = doc.select("img#ctl00_ctl00_ContentPlaceHolder_ContentPlaceHolder_NewsContent3_Image1").get(0);

    String result = null;

    if (img != null) {
      result = img.attr("abs:src");
    }

    return result;
  }

  @Override
  public String loadTitle() throws Exception {
    if (doc == null) {            
      doc = Jsoup.connect(newsUrl).get();
    }
    
    String title = doc.select("div.ContentStyle").get(0).select("h1").get(0).text();
    
    return title;
  }

}
