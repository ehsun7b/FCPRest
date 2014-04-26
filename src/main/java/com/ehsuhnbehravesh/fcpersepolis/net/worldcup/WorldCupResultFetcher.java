package com.ehsuhnbehravesh.fcpersepolis.net.worldcup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author ehsun7b
 */
public class WorldCupResultFetcher {

  private static final Logger log = Logger.getLogger(WorldCupResultFetcher.class.getName());
  
  private static final String DEFAULT_URL = "http://www.fifa.com/worldcup/groups/index.html";
  private URL url;

  public WorldCupResultFetcher() {
    try {
      this.url = new URL(DEFAULT_URL);
    } catch (MalformedURLException ex) {
      log.log(Level.SEVERE, "Error in getting world cup page content. {0}", ex.getMessage());
    }
  }
  
  public WorldCupResultFetcher(URL url) {
    this.url = url;
  }
  
  public WorldCupResultFetcher(String url) throws MalformedURLException {
    this.url = new URL(url);
  }
  
  public WorldCupResult fetch() throws IOException {
    Document doc = Jsoup.parse(url, 10000);
    
    //doc.select(DEFAULT_URL)
    
    return null;
  }
}
