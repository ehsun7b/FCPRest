package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class ArteshNewsDescriptionFetch extends NewsDescriptionFetch {

  private Document doc;

  @Override
  public String loadDescription() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Document clone = doc.clone();
    Element divDescNews = clone.select("div.text_content").get(0);
    Element divImg = divDescNews.child(0);
    divImg.remove();

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

    Element spanDate = doc.select("span.date_add").get(0);

    String date = spanDate.html();

    return date;
  }

  @Override
  public String loadImage() throws Exception {
    if (doc == null) {
      doc = Jsoup.connect(newsUrl).get();
    }

    Element divDescNews = doc.select("div.text_content").get(0);
    Element divImage = divDescNews.select("div").get(0);
    Element a = divImage.select("a").get(0);
    String result = null;
    if (a != null) {
      Element img = a.select("img").get(0);
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
    
    Elements div = doc.select("div.title_with_detail");
    String title = div.select("h1").get(0).text();
    return title;
  }

}
