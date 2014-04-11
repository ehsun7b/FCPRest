package com.ehsunbehravesh.fcpersepolisrest.rest;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.persepolis.entity.News;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ehsun7b
 */
@Stateless
@Path("hotnews")
public class NewsBoard {

  @Inject
  private NewsBean newsBean;    
  
  @GET
  @Path("json")
  @Produces("application/json; charset=UTF-8")
  public List<News> json() {
    List<News> result;

    result = newsBean.currentHotNews();
   
    return result;
  }
}
