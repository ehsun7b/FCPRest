package com.ehsunbehravesh.fcpersepolisrest.cdi;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.persepolis.entity.HotNews;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ehsun7b
 */
@Named("newsWeb")
@RequestScoped
public class NewsWebBean {

  @Inject
  private NewsBean newsBean;
  
  private List<HotNews> hotNewsList;

  public List<HotNews> getHotNewsList() {
    if (hotNewsList == null) {
      hotNewsList = newsBean.readTop(10);
    }
    return hotNewsList;
  }  
  
}
