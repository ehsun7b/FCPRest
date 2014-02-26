package com.ehsuhnbehravesh.persepolis.net;

import com.ehsuhnbehravesh.persepolis.news.News;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ehsun7b
 */
public class OfficialWebsiteNewsFetch {

  private static final Logger log = Logger.getLogger(OfficialWebsiteNewsFetch.class.getName());
  private String pageUrl;

  public OfficialWebsiteNewsFetch(String pageUrl) {
    this.pageUrl = pageUrl;
  }

  public List<News> fetch() {
    List<News> result = new ArrayList<>();

    Document doc = getDocument(pageUrl);

    if (doc != null) {
      Element div = doc.select("div.latest-news-content-title-container-div-class").first();
      if (div != null) {
        try {
          Elements lis = div.select("li");
          
          for (Element li : lis) {            
            Element div1 = li.select("div").first();
            Elements divs = div1.select("div");
            Element divImage = divs.first();
            Element divText = divs.get(2);
            String image = divImage.select("img").first().absUrl("src");
            Element a = divText.select("a").first();
            String link = a.absUrl("href");
            String title = a.text();
            String description = divText.select("p").first().ownText();

            News news = new News();
            news.setTitle(title);
            news.setLink(link);
            news.setImage(image);
            news.setDescription(description);

            result.add(news);
          }
        } catch (Exception ex) {
          log.log(Level.SEVERE, "Fining element ins the page {0} failed.", pageUrl);
        }
      } else {
        log.log(Level.SEVERE, "Fining element ins the page {0} failed.", pageUrl);
      }
    } else {
      log.log(Level.SEVERE, "Fetching the page {0} failed.", pageUrl);
    }

    return result;
  }

  private Document getDocument(String url) {
    Document doc = null;
    while (doc == null) {
      try {
        doc = Jsoup.connect(url).get();
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    }

    return doc;
  }

  public static void main(String[] args) {
    OfficialWebsiteNewsFetch fetch = new OfficialWebsiteNewsFetch("http://www.fc-perspolis.com");
    List<News> news = fetch.fetch();
    System.out.println("size: " + news.size());

    for (News news1: news) {
      System.out.println("title: " + news1.getTitle());
      System.out.println("description: " + news1.getDescription());
      System.out.println("link: " + news1.getLink());
      System.out.println("");
    }
  }
}
