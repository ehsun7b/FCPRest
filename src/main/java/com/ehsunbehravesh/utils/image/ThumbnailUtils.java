package com.ehsunbehravesh.utils.image;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import sun.misc.BASE64Encoder;

/**
 *
 * @author ehsun7b
 */
public class ThumbnailUtils {

  public static BufferedImage fetchImage(String url) throws IOException, URISyntaxException {
    return fetchImage(new URL(url));
  }

  public static BufferedImage fetchImage(URL url) throws IOException, URISyntaxException {
    BufferedImage image = ImageIO.read(url);
    return image;
  }
  
  public static BufferedImage fetchImage(File file) throws IOException, URISyntaxException {
    BufferedImage image = ImageIO.read(file);
    return image;
  }

  public static BufferedImage thumbnail(BufferedImage image, Dimension size) throws IOException {
    File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + System.nanoTime() + ".png");
    Thumbnails.of(image).size(size.width, size.height).toFile(tempFile);
    BufferedImage thumb = ImageIO.read(tempFile);
    tempFile.delete();
    return thumb;
  }
  
  public static BufferedImage thumbnailForce(BufferedImage image, Dimension size) throws IOException {
    File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + System.nanoTime() + ".png");
    Thumbnails.of(image).forceSize(size.width, size.height).toFile(tempFile);
    BufferedImage thumb = ImageIO.read(tempFile);
    tempFile.delete();
    return thumb;
  }

  public static BufferedImage thumbnailCrop(BufferedImage image, Dimension size) throws IOException {
    File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + System.nanoTime() + ".png");
    Thumbnails.of(image).size(size.width, size.height).crop(Positions.TOP_CENTER).toFile(tempFile);
    BufferedImage thumb = ImageIO.read(tempFile);
    tempFile.delete();
    return thumb;
  }

  public static String toBase64(BufferedImage image) throws IOException {
    BASE64Encoder encoder = new BASE64Encoder();
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(image, "png", os);
    os.flush();
    String value = encoder.encode(os.toByteArray());
    return value;
  }

  public static String remoteImageToBase64(String url, Dimension size) throws IOException, URISyntaxException {
    BufferedImage image = fetchImage(url);
    BufferedImage thumbnail = thumbnail(image, size);
    return toBase64(thumbnail);
  }

  public static byte[] toByteArray(BufferedImage image, String format) throws IOException {
    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    ImageIO.write(image, format, bao);
    bao.flush();

    return bao.toByteArray();
  }
}
