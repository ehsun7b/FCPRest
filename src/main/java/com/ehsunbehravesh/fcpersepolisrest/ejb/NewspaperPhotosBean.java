package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.Newspaper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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

  private List<Newspaper> photoURLs = new ArrayList<>();
  private final String url = "http://varzesh3.com";
  private static final Object lock = new Object();

  @PostConstruct
  public void init() {    
    fetchPhotos();
  }

  @Schedule(minute = "*/15", hour = "*", persistent = false)
  public void fetchPhotos() {
    try {
      List<Newspaper> newPhotoURLs = new ArrayList<>();
      log.log(Level.INFO, "fetching photos ...");
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
          newspaper.setTitle(ownText);
          newPhotoURLs.add(newspaper);
        }
      }

      synchronized (lock) {
        photoURLs = newPhotoURLs;
      }
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Error in fetching newspaper photos. {0}", ex.getMessage());
    }
  }

  public List<Newspaper> getNewspapers() {
    synchronized (lock) {
      List<Newspaper> result = (List<Newspaper>) ((ArrayList<Newspaper>) photoURLs).clone();
      return result;
    }
  }

  public String randomPhoto() {
    Random random = new Random(System.currentTimeMillis());
    List<Newspaper> newspapers = getNewspapers();
    if (newspapers.size() > 0) {
      int index = random.nextInt(newspapers.size());
      return newspapers.get(index).getPhotoURL();
    } else {
      return "";
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
}
