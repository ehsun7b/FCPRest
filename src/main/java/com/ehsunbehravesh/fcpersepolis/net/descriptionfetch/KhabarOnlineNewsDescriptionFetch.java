package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ehsun7b
 */
class KhabarOnlineNewsDescriptionFetch extends NewsDescriptionFetch {

  private Document doc;

  public KhabarOnlineNewsDescriptionFetch() {

  }

  @Override
  public String loadDescription() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }
    
    Element divDescNews = doc.select("div.newsBodyCont").get(0);

    StringBuilder content = new StringBuilder();
    Elements nodes = divDescNews.select("p");

    for (int i = 0; i < nodes.size(); ++i) {
      Element p = nodes.get(i);
      content.append(p.html());
    }

    return content.toString();
  }

  @Override
  public String loadDate() throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String loadImage() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }
    
    Element divDescNews = doc.select("div.newsBodyCont").get(0);
    Element divImage = divDescNews.select("div.newsPhoto").get(0);

    String result = null;
    if (divImage != null) {
      Element img = divImage.select("img").get(0);
      if (img != null) {
        result = img.attr("abs:src");
      }
    }

    return result;
  }

}
