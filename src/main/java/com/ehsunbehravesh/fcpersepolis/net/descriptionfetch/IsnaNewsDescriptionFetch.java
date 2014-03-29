/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author ehsun7b
 */
class IsnaNewsDescriptionFetch extends NewsDescriptionFetch {

  private Document doc;

  @Override
  public String loadDescription() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }
    
    String html = doc.select("div.body").html();
    return html;
  }

  @Override
  public String loadDate() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }
    
    String date = doc.select("div.newsPubDate").get(0).text();
    return date;
  }

  @Override
  public String loadImage() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element divImage = doc.select("div.main-image").get(0);

    String result = null;
    if (divImage != null) {
      Element img = divImage.select("img").get(0);
      if (img != null) {
        result = img.attr("abs:src");
      }
    }

    return result;
  }

  @Override
  public String loadTitle() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element titr = doc.select("div.titr").get(0);
    Element h1 = titr.select("h1").get(0);
    String title = h1.text();
    return title;
  }

}
