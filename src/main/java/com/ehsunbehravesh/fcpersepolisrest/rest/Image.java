package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetch;
import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetchFactory;
import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsCacheBean;
import com.ehsunbehravesh.persepolis.entity.News;
import com.ehsunbehravesh.utils.image.ThumbnailUtils;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author ehsun7b
 */
@Stateless
@Path("image")
public class Image {

  private static final Logger log = Logger.getLogger(Image.class.getName());

  @Inject
  private NewsCacheBean newsCache;

  private static final int CACHE_SIZE = 50;
  private static final Map<String, BufferedImage> cache = new HashMap<>();

  @Context
  private ServletContext context;

  @GET
  @Path("")
  @Produces("image/png")
  public byte[] image(@QueryParam("url") String url) {
    try {
      String key = url;
      BufferedImage cachedContent = fromCache(key);
      if (cachedContent == null) {
        BufferedImage image = ThumbnailUtils.fetchImage(url);
        toCache(key, image);

        return ThumbnailUtils.toByteArray(image, "png");
      } else {
        return ThumbnailUtils.toByteArray(cachedContent, "png");
      }
    } catch (IOException | URISyntaxException ex) {
      log.log(Level.SEVERE, "Error in sending thumbnail to client. {0}", ex.getMessage());
      return emptyImage();
    }
  }

  @GET
  @Path("thumbnail/{width}/{height}")
  @Produces("image/png")
  public byte[] thumbnail(@QueryParam("url") String url,
          @PathParam("width") int width,
          @PathParam("height") int height) {
    try {
      String key = url + width + "-" + height;
      BufferedImage cachedContent = fromCache(key);
      if (cachedContent == null) {
        key = url;
        cachedContent = fromCache(key);
        if (cachedContent == null) {
          BufferedImage image = ThumbnailUtils.fetchImage(url);
          BufferedImage newContent = ThumbnailUtils.thumbnail(image, new Dimension(width, height));
          toCache(key, newContent);
          return ThumbnailUtils.toByteArray(newContent, "png");
        } else {
          BufferedImage newContent = ThumbnailUtils.thumbnail(cachedContent, new Dimension(width, height));
          toCache(key, newContent);
          return ThumbnailUtils.toByteArray(newContent, "png");
        }
      } else {
        return ThumbnailUtils.toByteArray(cachedContent, "png");
      }
    } catch (IOException | URISyntaxException ex) {
      log.log(Level.SEVERE, "Error in sending thumbnail to client. {0}", ex.getMessage());
      return emptyImage();
    }
  }

  @GET
  @Path("link/{width}/{height}")
  @Produces("image/png")
  public byte[] fromNewsLink(@QueryParam("url") String newsUrl,
          @PathParam("width") int width,
          @PathParam("height") int height) {
    try {

      String url = findImageUrl(newsUrl);

      if (url != null) {
        String key = url.concat(width + "").concat(height + "");
        BufferedImage cachedContent = null;
        fromCache(key);
        if (cachedContent == null) {
          key = url;
          cachedContent = null;
          fromCache(key);
          if (cachedContent == null) {
            BufferedImage image = ThumbnailUtils.fetchImage(url);
            BufferedImage newContent = ThumbnailUtils.thumbnail(image, new Dimension(width, height));
            toCache(key, newContent);
            return ThumbnailUtils.toByteArray(newContent, "png");
          } else {
            BufferedImage newContent = ThumbnailUtils.thumbnail(cachedContent, new Dimension(width, height));
            toCache(key, newContent);
            return ThumbnailUtils.toByteArray(newContent, "png");
          }
        } else {
          return ThumbnailUtils.toByteArray(cachedContent, "png");
        }
      } else {
        return emptyImage();
      }
    } catch (IOException | URISyntaxException ex) {
      log.log(Level.SEVERE, "Error in sending thumbnail to client. {0}", ex.getMessage());
      return null;
    }
  }

  @GET
  @Path("news/{uniqueKey}/{width}/{height}/photo.png")
  @Produces("image/png")
  public byte[] fromNews(@PathParam(value = "uniqueKey") String uniqueKey,
          @PathParam("width") int width,
          @PathParam("height") int height) {

    File imageFile = new File(pathOfNewsImages() + File.separator + uniqueKey + ".png");
    
    if (imageFile.exists()) {
      try {
        BufferedImage image = ThumbnailUtils.fetchImage(imageFile);
        BufferedImage newContent = ThumbnailUtils.thumbnail(image, new Dimension(width, height));
        //log.log(Level.INFO, "Image sent to client from file system. {0}", imageFile.getAbsolutePath());
        return ThumbnailUtils.toByteArray(newContent, "png");
      } catch (IOException | URISyntaxException ex) {
        log.log(Level.WARNING, "Error in sending thumbnail to client. {0}", ex.getMessage());
      }
    }
    
    News news = newsCache.findOne(uniqueKey);

    try {
      if (news != null && news.getImage() != null) {
        BufferedImage image = ThumbnailUtils.fetchImage(news.getImage());
        BufferedImage newContent = ThumbnailUtils.thumbnail(image, new Dimension(width, height));
        return ThumbnailUtils.toByteArray(newContent, "png");
      }
    } catch (IOException | URISyntaxException ex) {
      log.log(Level.WARNING, "Error in sending thumbnail to client. {0}", ex.getMessage());      
    }

    return emptyImage();
  }

  private String pathOfNewsImages() {
    return System.getProperty("user.home") + File.separator + "news/image";
  }
  
  private BufferedImage fromCache(String key) {
    //if (cache.containsKey(key)) {
    //return cache.get(key);
    //} else {
    return null;
    //}
  }

  private synchronized void toCache(String key, BufferedImage content) {
    if (cache.size() >= CACHE_SIZE) {
      cache.clear();
    }
    cache.put(key, content);
  }

  private String findImageUrl(String newsUrl) {
    String result = null;
    try {
      NewsDescriptionFetch fetch = NewsDescriptionFetchFactory.generateNewsDescriptionFetch(newsUrl);
      result = fetch.loadImage();
    } catch (Exception ex) {
      log.log(Level.WARNING, "Finding image of news failed. {0} {1}", new Object[]{newsUrl, ex.getMessage()});
    }
    return result;
  }

  private byte[] emptyImage() {
    try {
      InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("1x1-transparent.png");
      return IOUtils.toByteArray(is);
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Error in reading empty image file. {0}", ex.getMessage());
    }
    return null;
  }
}
