package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ehsun7b
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method.
   * It is automatically populated with
   * all resources defined in the project.
   * If required, comment out calling this method in getClasses().
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.ArteshSorkhNews.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.Direction.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.ISNANews.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.Image.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.KhabarOnlineNews.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.Live.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.MatchInfo.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.NewsBoard.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.NewspaperPDF.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.OfficialWebsiteNews.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.Ranking.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.Varzesh3News.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.Video.class);
    resources.add(com.ehsunbehravesh.fcpersepolisrest.rest.WorldCupNews.class);
  }
  
}
