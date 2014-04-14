package com.ehsunbehravesh.fcpersepolisrest.cdi;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.persepolis.entity.HotNews;
import com.ehsunbehravesh.persepolis.entity.News;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ehsun7b
 */
@Named("newsWeb")
@ViewScoped
public class NewsWebBean {

  @Inject
  private NewsBean newsBean;

  private HotNews hotNews;
  private String hotNewsKeys;
  private List<HotNews> hotNewsList;

  @PostConstruct
  public void init() {
    hotNews = new HotNews();
  }

  public List<HotNews> getHotNewsList() {
    if (hotNewsList == null) {
      hotNewsList = newsBean.readTop(10);
    }
    return hotNewsList;
  }

  public void saveHotNews() {
    try {
      String[] split = hotNewsKeys.split(",");

      hotNews.getNewsList().clear();

      for (String key : split) {
        News news = newsBean.findOne(key);
        if (news != null) {
          hotNews.getNewsList().add(news);
        }
      }

      newsBean.save(hotNews);
    } catch (Exception ex) {
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage("Database Error", ex.getMessage()));
    }
  }

  public HotNews getHotNews() {
    return hotNews;
  }

  public void setHotNews(HotNews hotNews) {
    this.hotNews = hotNews;
  }

  public NewsBean getNewsBean() {
    return newsBean;
  }

  public void setNewsBean(NewsBean newsBean) {
    this.newsBean = newsBean;
  }

  public String getHotNewsKeys() {
    return hotNewsKeys;
  }

  public void setHotNewsKeys(String hotNewsKeys) {
    this.hotNewsKeys = hotNewsKeys;
  }

}
