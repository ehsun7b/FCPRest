package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Varzesh3NewsDescriptionFetch extends NewsDescriptionFetch {

  private Document doc;

  @Override
  public String loadDescription() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element newsTable = doc.select("table#NewsTable").get(0);
    Element nextTable = newsTable.nextElementSibling();
    Element tr = nextTable.select("tr").get(1);
    Element td = tr.select("td").get(0);
    Element div = td.select("div").get(0);
    String desc = div.html();

    return desc;
  }

  @Override
  public String loadDate() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element newsTable = doc.select("table#NewsTable").get(0);
    Element font = newsTable.select("font").get(0);

    String date = font.html();

    return date;
  }

  @Override
  public String loadImage() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element newsTable = doc.select("table#NewsTable").get(0);
    String img = newsTable.select("img").get(0).attr("abs:src");

    return img;
  }

}
