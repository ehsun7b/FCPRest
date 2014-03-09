package com.ehsuhnbehravesh.persepolis.news;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ehsun7b
 */
public class PersepolisNewsMatcher {

  private static final Logger log = Logger.getLogger(PersepolisNewsMatcher.class.getName());

  private static final List<Keyword> keywords;

  static {
    keywords = new ArrayList<>();
    try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("news_keywords.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String line;
      while ((line = reader.readLine()) != null) {
        Keyword keyword = new Keyword(line.trim());
        if (keyword.getValue().length() > 0) {
          keywords.add(keyword);
        }
      }
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Error in reading news_keywords.txt. {0}", ex.getMessage());
    }
  }

  public boolean match(News news) {
    for (Keyword keyword : keywords) {
      if (news.getTitle().contains(keyword.getValue())) {
        return true;
      } else if (news.getDescription().contains(keyword.getValue())) {
        return true;
      }
    }

    return false;
  }

  public List<Keyword> getKeywords() {
    return keywords;
  }
}
