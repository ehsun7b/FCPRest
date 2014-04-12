package com.ehsuhnbehravesh.fcpersepolis.news.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Level;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ImageServlet", urlPatterns = {"/image"})
@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(ImageServlet.class.getName());

  private static final int BUFFER_SIZE = 4096;
  private static final int CACHE_SIZE = 10;
  private static Map<String, byte[]> cache = new HashMap<>();

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    byte[] content;
    String url = req.getParameter("url");

    if (url != null) {

      if (cache.containsKey(url)) {
        log.log(Level.INFO, "Image found in cache for url: {0}", url);
        content = cache.get(url);
      } else {
        log.log(Level.INFO, "Image not found in cache for url: {0}", url);

        content = load(url);
        log.log(Level.INFO, "Image fetched from url: {0}", url);

        if (content != null) {
          if (cache.size() > CACHE_SIZE) {
            cache.clear();
          }
          cache.put(url, content); // put into cache
        }
      }

      String mimeType = mimeType(url);

      if (mimeType != null) {
        resp.setContentType(mimeType);
        try (ServletOutputStream outputStream = resp.getOutputStream()) {
          try (ByteArrayInputStream is = new ByteArrayInputStream(content)) {
            int len;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((len = is.read(buffer)) > 0) {
              outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            outputStream.close();
          }
        }
      }
    }
  }

  private String mimeType(String url) {
    if (url.toLowerCase().endsWith("gif")) {
      return "image/gif";
    } else if (url.toLowerCase().endsWith("jpg")
            || url.toLowerCase().endsWith("jpeg")) {
      return "image/jpeg";
    } else if (url.toLowerCase().endsWith("png")) {
      return "image/png";
    }
    return null;
  }

  private byte[] load(String url) {
    try {
      URL imageUrl = new URL(url);
      URLConnection connection = imageUrl.openConnection();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try (InputStream is = connection.getInputStream()) {
        int n;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((n = is.read(buffer)) > 0) {
          baos.write(buffer, 0, n);
        }
      }

      return baos.toByteArray();
    } catch (Exception ex) {
      log.severe(ex.getMessage());
    }
    return null;
  }
}
