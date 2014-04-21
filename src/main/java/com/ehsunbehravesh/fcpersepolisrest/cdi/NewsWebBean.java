package com.ehsunbehravesh.fcpersepolisrest.cdi;

import com.ehsunbehravesh.fcpersepolisrest.ejb.NewsBean;
import com.ehsunbehravesh.persepolis.entity.HotNews;
import com.ehsunbehravesh.persepolis.entity.News;
import com.ehsunbehravesh.persepolis.entity.WorldCupNews;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

  private static final Logger log = Logger.getLogger(NewsWebBean.class.getName());
  
  @Inject
  private NewsBean newsBean;

  private HotNews hotNews;
  private String hotNewsKeys;
  private List<HotNews> hotNewsList;
  private List<News> newsList;
  private List<WorldCupNews> worldCupNewsList;

  @PostConstruct
  public void init() {
    hotNews = new HotNews();
    newsList = newsBean.readTop(null, 20);
    worldCupNewsList = newsBean.readTopWorldCup(20);
  }

  public List<HotNews> getHotNewsList() {
    if (hotNewsList == null) {
      hotNewsList = newsBean.readTop(10);
    }
    return hotNewsList;
  }

  public void saveHotNews() {
    FacesContext context = FacesContext.getCurrentInstance();
    try {
      String[] split = hotNewsKeys.split(",");

      hotNews.getNewsList().clear();

      for (String key : split) {
        News news = newsBean.findOne(key);
        if (news != null) {
          hotNews.getNewsList().add(news);
        }
      }

      Properties p = loadProperties();
      newsBean.save(hotNews);
      context.addMessage(null, new FacesMessage(p.getProperty("videoCategorySavedSuccessfully"), ""));
    } catch (Exception ex) {      
      context.addMessage(null, new FacesMessage("Database Error", ex.getMessage()));
    }
  }
  
  private Properties loadProperties() {
    try {
      InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.properties");
      Properties properties = new Properties();
      properties.load(input);
      return properties;
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Can not load admin.properties resource.", ex);
      return null;
    }
  }
  
  public void delete(News news) {
    newsBean.delete(news);
    newsList = newsBean.readTop(null, 20);
    worldCupNewsList = newsBean.readTopWorldCup(20);
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

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }

  public List<WorldCupNews> getWorldCupNewsList() {
    return worldCupNewsList;
  }

  public void setWorldCupNewsList(List<WorldCupNews> worldcupNewsList) {
    this.worldCupNewsList = worldcupNewsList;
  } 
}
