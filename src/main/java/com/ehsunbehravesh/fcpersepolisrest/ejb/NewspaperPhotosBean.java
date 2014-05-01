package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.Newspaper;
import com.ehsunbehravesh.persepolis.entity.NewspaperSet;
import com.ehsunbehravesh.utils.image.AnimatedGifEncoder;
import com.ehsunbehravesh.utils.image.ThumbnailUtils;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

  private static final String url = "http://varzesh3.com";

  @Schedule(minute = "0", hour = "*/1", persistent = false)
  public void check() {
    log.info("Adding new newspapers");

    String currentDate = fetchCurrentDate();

    if (currentDate != null) {
      List<Newspaper> newspapers = fetchPhotos();
      saveNewspaperSet(currentDate, newspapers);
    }

  }

  @Schedule(minute = "*/10", hour = "*", persistent = false)
  public void fixThumbnail() {
    log.info("Fixing newspaper GIF");

    NewspaperSet set = setBean.findLast();

    if (set.getGif() == null) {
      log.info("GIF is null. Try to generate.");
      
      try {
        String gif = generateGifAnimation(set);
        
        if (gif != null) {
          set.setGif(gif);
          setBean.save(set);
        }
      } catch (IOException | URISyntaxException ex) {
        log.log(Level.SEVERE, "Exception in gif generation in fixing gif. {0}", ex.getMessage());
      }
    }
    
  }
  
  private void saveNewspaperSet(String date, List<Newspaper> newspapers) {
    NewspaperSet newspaperSet = new NewspaperSet(date);
    newspaperSet.setNewspapers(newspapers);

    try {
      String gif = generateGifAnimation(newspaperSet);
      newspaperSet.setGif(gif);
    } catch (IOException | URISyntaxException ex) {      
      log.log(Level.SEVERE, "ERROR IN GIF generation.", ex);
    }

    for (Newspaper newspaper : newspapers) {
      newspaper.setSet(newspaperSet);
    }

    setBean.save(newspaperSet);
  }

  private List<Newspaper> fetchPhotos() {
    log.info("Fetching newspapers ...");
    List<Newspaper> newPhotoURLs = new ArrayList<>();

    try {
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
        } catch (IOException ex) {
          log.log(Level.SEVERE, "Error in finding real newspaper photo url. {0}", ex.getMessage());
        }

        if (photoUrl != null) {
          newspaper.setPhotoURL(photoUrl);
          newspaper.setTitle(ownText.trim());
          newPhotoURLs.add(newspaper);
        }
      }

    } catch (IOException ex) {
      log.log(Level.SEVERE, "Error in fetching newspaper photos. {0}", ex.getMessage());
    }

    return newPhotoURLs;
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

  public static String pathOfNewspaperImages() {
    return System.getProperty("user.home") + File.separator + "newspaper/image";
  }

  private String generateGifAnimation(NewspaperSet set) throws IOException, URISyntaxException {
    AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
    File file = new File(pathOfNewspaperImages() + File.separator + set.getPublishDate().replaceAll("/", "-") + ".gif");
    gifEncoder.start(file.getAbsolutePath());
    gifEncoder.setDelay(3000); // 3 seconds
    gifEncoder.setRepeat(0);

    for (Newspaper newspaper : set.getNewspapers()) {
      BufferedImage image = ThumbnailUtils.fetchImage(newspaper.getPhotoURL());

      if (image != null) {
        image = ThumbnailUtils.thumbnailForce(image, new Dimension(70, 50));
        gifEncoder.addFrame(image);
      }
    }

    if (!gifEncoder.finish()) {
      file.delete();
      return null;
    } else {
      return file.getName();
    }
  }
}
