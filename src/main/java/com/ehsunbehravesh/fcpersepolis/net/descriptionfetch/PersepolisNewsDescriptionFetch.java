package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class PersepolisNewsDescriptionFetch extends NewsDescriptionFetch {

  private Document doc;

  @Override
  public String loadDescription() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element divDescNews = doc.select("div.pic-efect").get(0);

    Elements imgs = divDescNews.select("img");
    for (Element img : imgs) {
      String src = img.attr("abs:src");
      img.attr("src", src);
    }

    String content = divDescNews.html();

    return content;
  }

  @Override
  public String loadDate() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element divEtelat = doc.select("div.etelaat").get(0);
    Element p = divEtelat.select("p").get(2);

    String date = p.html();

    return date;
  }

  @Override
  public String loadImage() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element divEtelat = doc.select("div.tasvire").get(0);
    Element img = divEtelat.select("img").get(0);

    String date = img.attr("abs:src");

    return date;
  }

}
