package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.utils.image.ThumbnailUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.commons.io.IOUtils;

@Path("video")
public class Video {

  private static final Logger log = Logger.getLogger(Video.class.getName());
  private static final String VIDEO_IMAGE_PATH = "/root/video/image";

  @GET
  @Path("image/{file}")
  @Produces("image/png")
  public byte[] videoImage(@PathParam("file") String file) {
    File imageFile = new File(VIDEO_IMAGE_PATH + File.separator + file);
    if (imageFile.exists()) {
      try {
        BufferedImage img = ThumbnailUtils.fetchImage(imageFile);
        return ThumbnailUtils.toByteArray(img, "png");
      } catch (IOException | URISyntaxException ex) {
        log.log(Level.SEVERE, null, ex);
        return emptyImage();
      }
    } else {
      return emptyImage();
    }
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
