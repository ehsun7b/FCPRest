package com.ehsunbehravesh.fcpersepolisrest.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author ehsun7b
 */
@Startup
@Singleton
public class NewspaperPDF {

  private static final Logger log = Logger.getLogger(NewspaperPDF.class.getName());
  
  private static List<String> photos = new ArrayList<String>();
  private static final String url = "http://varzesh3.com";

  @PostConstruct
  public void init() {
    fetchPhotos();
  }
  
  @Schedule(minute = "*/15", persistent = false)
  public void fetchPhotos() {
    log.log(Level.INFO, "fetching photos ...");
    
    
  }
}
