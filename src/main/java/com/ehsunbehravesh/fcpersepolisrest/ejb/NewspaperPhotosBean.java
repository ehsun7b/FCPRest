package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.Newspaper;
import com.ehsunbehravesh.persepolis.entity.NewspaperSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ehsun7b
 */
@Startup
@Singleton
public class NewspaperPhotosBean {

  private static final Logger log = Logger.getLogger(NewspaperPhotosBean.class.getName());

  @Inject
  private NewspaperSetBean setBean;

  private final String url = "http://varzesh3.com";

  @Schedule(minute = "*/1", hour = "*", persistent = false)
  public void check() {
    log.info("Checking for newspapers ...");
    NewspaperSet lastSet = setBean.findLast();

    if (lastSet == null) {
      fetchPhotos(null);
    } else {
      String currentDate = fetchCurrentDate();

      if (currentDate != null && !currentDate.equals(lastSet.getPublishDate())) {
        fetchPhotos(currentDate);
      }
    }
  }

  private void fetchPhotos(String date) {
    log.info("Fetching newspapers ...");

    if (date == null) {
      date = fetchCurrentDate();
    }

    if (date != null) {
      NewspaperSet newspaperSet = new NewspaperSet(date);
      try {
        List<Newspaper> newPhotoURLs = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element divGalleryBox = doc.select("div.gallery-box").get(0);
        Element divShowImage = divGalleryBox.select("div#showImage").get(0);
        Element ul = divShowImage.select("ul").get(0);
        Elements lis = ul.select("li");

        for (Element li : lis) {
          Element a = li.select("a").get(0);
          String src = a.attr("abs:href");

          Element p = li.select("p").get(0);
          String ownText = p.text();
          Newspaper newspaper = new Newspaper();
          newspaper.setSet(newspaperSet);

          String photoUrl = null;

          try {
            if (src != null && src.length() > 0) {
              photoUrl = findPhotoURL(src);
            }
          } catch (Exception ex) {
            log.log(Level.SEVERE, "Error in finding real newspaper photo url. {0}", ex.getMessage());
          }

          if (photoUrl != null) {
            newspaper.setPhotoURL(photoUrl);
            newspaper.setTitle(ownText.trim());
            newPhotoURLs.add(newspaper);
          }
        }

        newspaperSet.setNewspapers(newPhotoURLs);
        
        setBean.save(newspaperSet);
      } catch (IOException ex) {
        log.log(Level.SEVERE, "Error in fetching newspaper photos. {0}", ex.getMessage());
      }
    } else {
      log.log(Level.SEVERE, "Can not fetch newspaper dates.");
    }
  }

  private String findPhotoURL(String url) throws IOException {
    Document doc = Jsoup.connect(url).get();
    Elements div = doc.select("div#news-col-right-old");
    Element h1 = div.select("h1").get(0);
    //newspaper.setTitle(h1.text());
    Element img = div.select("img").get(0);
    return img.attr("abs:src");
  }

  private String fetchCurrentDate() {
    String allowedChars = "0123456789/";
    String result = null;
    try {
      log.log(Level.INFO, "fetching date of newspapers ...");
      Document doc = Jsoup.connect(url).get();
      Element divGalleryBox = doc.select("div.gallery-box").get(0);
      Element divShowImage = divGalleryBox.select("div#showImage").get(0);
      Element ul = divShowImage.select("ul").get(0);
      Elements lis = ul.select("li");

      Element li = lis.get(0);

      Element p = li.select("p").get(0);
      String ownText = p.text();
      ownText = ownText.trim();

      StringBuilder temp = new StringBuilder();
      for (int i = 0; i < ownText.length(); ++i) {
        char ch = ownText.charAt(i);
        if (allowedChars.indexOf(ch) >= 0) {
          temp.append(ch);
        }
      }

      result = temp.toString();
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Error in fetching newspaper date. {0}", ex.getMessage());
    }

    return result;
  }
}
