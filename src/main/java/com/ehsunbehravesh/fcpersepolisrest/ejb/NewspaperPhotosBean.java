package com.ehsunbehravesh.fcpersepolisrest.ejb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
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
@LocalBean
public class NewspaperPhotosBean {

  private static final Logger log = Logger.getLogger(NewspaperPhotosBean.class.getName());

  private List<Newspaper> photoURLs = new ArrayList<>();
  private final String url = "http://varzesh3.com";

  @PostConstruct
  public void init() {
    log.info("post construct");
    fetchPhotos();
  }

  @Schedule(minute = "*/15", persistent = false)
  public void fetchPhotos() {
    try {
      List<Newspaper> newPhotoURLs = new ArrayList<>();
      log.log(Level.INFO, "fetching photos ... {0}", new Date());
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
        newspaper.setPhotoURL(src);
        newspaper.setTitle(ownText);

        newPhotoURLs.add(newspaper);
      }

      for (Newspaper newspaper : newPhotoURLs) {
        try {
          getPhotoURL(newspaper);
        } catch (Exception ex) {
          newPhotoURLs.remove(newspaper);
          log.log(Level.SEVERE, "Error in fining real URL of newspaper image. {0}", ex.getMessage());
        }
      }

      photoURLs = newPhotoURLs;
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Error in fetching newspaper photos. {0}", ex.getMessage());
    }

    for (Newspaper n : photoURLs) {
      System.out.println(n.getTitle() + " !!! " + n.getPhotoURL());
    }
  }

  public List<Newspaper> getPhotoURLs() {
    return photoURLs;
  }

  public String randomPhoto() {
    Random random = new Random(System.currentTimeMillis());
    int index = random.nextInt(getPhotoURLs().size());
    return getPhotoURLs().get(index).getPhotoURL();    
  }
  
  private void getPhotoURL(Newspaper newspaper) throws IOException {
    Document doc = Jsoup.connect(newspaper.getPhotoURL()).get();
    Elements div = doc.select("div#news-col-right-old");
    Element h1 = div.select("h1").get(0);
    //newspaper.setTitle(h1.text());
    Element img = div.select("img").get(0);
    newspaper.setPhotoURL(img.attr("abs:src"));
  }
}
