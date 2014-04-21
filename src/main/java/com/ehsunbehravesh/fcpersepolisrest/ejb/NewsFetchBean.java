package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsuhnbehravesh.fcpersepolis.net.OfficialWebsiteNewsFetch;
import com.ehsuhnbehravesh.fcpersepolis.news.Keyword;
import com.ehsuhnbehravesh.fcpersepolis.news.filter.ContentKeywordCondition;
import com.ehsuhnbehravesh.fcpersepolis.news.filter.MatchException;
import com.ehsuhnbehravesh.fcpersepolis.news.filter.NewsFilter;
import com.ehsuhnbehravesh.fcpersepolis.news.filter.NewsMatch;
import com.ehsuhnbehravesh.fcpersepolis.news.filter.Rule;
import com.ehsuhnbehravesh.fcpersepolis.news.filter.RuleSeperator;
import com.ehsuhnbehravesh.fcpersepolis.news.filter.TitleKeywordCondition;
import com.ehsuhnbehravesh.fcpersepolis.utils.Utils;
import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetch;
import com.ehsunbehravesh.fcpersepolis.net.descriptionfetch.NewsDescriptionFetchFactory;
import com.ehsunbehravesh.persepolis.entity.WorldCupNews;
import com.ehsunbehravesh.persepolis.entity.News;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ehsun7b
 */
@Singleton
@Startup
public class NewsFetchBean {

  private static final Logger log = Logger.getLogger(NewsFetchBean.class.getName());
  public static final String OFFICIAL_WEBSITE_URL = "http://www.fc-perspolis.com";
  public static final String OFFICIAL_WEBSITE = "fc-perspolis.com";
  public static final String ARTESH_WEBSITE_URL = "http://www.arteshesorkh.com/index/feed_all/new_rss";
  public static final String ARTESH_WEBSITE = "arteshesorkh.com";
  public static final String VARZESH3_WEBSITE_URL = "http://www.varzesh3.com/rss";
  public static final String VARZESH3_WEBSITE = "varzesh3.com";
  public static final String KHABARONLINE_WEBSITE_URL = "http://khabaronline.ir/RSS/Service/sport";
  public static final String KHABARONLINE_WEBSITE = "khabaronline.ir";
  public static final String ISNA_WEBSITE_URL = "http://www.isna.ir/fa/Sports/feed";
  public static final String ISNA_WEBSITE = "isna.ir";

  private static final List<String> worldcupKeywords;

  static {
    worldcupKeywords = new ArrayList<>();
    try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("worldcup_keywords.txt")) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().length() > 0) {
          worldcupKeywords.add(line.trim());
        }
      }
    } catch (IOException ex) {
      log.log(Level.SEVERE, "Error in reading worldcup_keywords.txt. {0}", ex.getMessage());
    }
  }

  @Inject
  private NewsBean newsBean;

  @Schedule(minute = "*/5", hour = "*", persistent = false)
  public void fetchOfficialNews() {
    log.log(Level.INFO, "Fetching official news ...");
    OfficialWebsiteNewsFetch fetch = new OfficialWebsiteNewsFetch(OFFICIAL_WEBSITE_URL);
    List<News> newsList = fetch.fetch();

    for (News news : newsList) {
      if (newsBean.findOne(Utils.uniqueNewsKeyGen(news.getLink())) == null) {
        news.setWebsite(OFFICIAL_WEBSITE);
        newsBean.save(news);
      }
    }
  }

  @Schedule(minute = "3/5", hour = "*", persistent = false)
  public void fetchArteshNews() {
    log.log(Level.INFO, "Fetching artesh news ...");

    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(ARTESH_WEBSITE_URL).openStream());

      NodeList items = doc.getElementsByTagName("item");
      for (int i = 0; i < items.getLength(); i++) {
        Element item = (Element) items.item(i);
        Element title = (Element) item.getElementsByTagName("title").item(0);
        Element description = (Element) item.getElementsByTagName("description").item(0);
        Element pubDate = (Element) item.getElementsByTagName("pubDate").item(0);
        Element link = (Element) item.getElementsByTagName("link").item(0);

        News news = new News();
        news.setTitle(title.getTextContent());
        news.setDescription(description.getTextContent());
        news.setLink(link.getTextContent());
        news.setPublishDate(pubDate.getTextContent());
        news.setWebsite(ARTESH_WEBSITE);

        if (newsBean.findOne(news.getUniqueKey()) == null) {
          newsBean.save(news);
        }
      }
    } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
      log.log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(minute = "*/10", hour = "*", persistent = false)
  public void fetchVarzeshNews() {
    log.log(Level.INFO, "Fetching varzesh3 news ...");
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(VARZESH3_WEBSITE_URL).openStream());

      NodeList items = doc.getElementsByTagName("item");

      for (int i = 0; i < items.getLength(); i++) {

        Element item = (Element) items.item(i);
        Element title = (Element) item.getElementsByTagName("title").item(0);
        Element description = (Element) item.getElementsByTagName("description").item(0);
        Element pubDate = (Element) item.getElementsByTagName("pubDate").item(0);
        Element link = (Element) item.getElementsByTagName("link").item(0);

        News news = new News();
        news.setTitle(title.getTextContent());
        news.setDescription(description.getTextContent());
        news.setLink(link.getTextContent());
        news.setPublishDate(pubDate.getTextContent());
        news.setWebsite(VARZESH3_WEBSITE);

        if (isPersepolisNews(news) && newsBean.findOne(news.getUniqueKey()) == null) {
          newsBean.save(news);
        }
      }
    } catch (ParserConfigurationException | IOException | SAXException | DOMException ex) {
      log.log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(minute = "5/10", hour = "*", persistent = false)
  public void fetchKhabaronlineNews() {
    log.log(Level.INFO, "Fetching khabaronline news ...");
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(KHABARONLINE_WEBSITE_URL).openStream());

      NodeList items = doc.getElementsByTagName("item");

      for (int i = 0; i < items.getLength(); i++) {

        Element item = (Element) items.item(i);
        Element title = (Element) item.getElementsByTagName("title").item(0);
        Element description = (Element) item.getElementsByTagName("description").item(0);
        Element pubDate = (Element) item.getElementsByTagName("pubDate").item(0);
        Element link = (Element) item.getElementsByTagName("link").item(0);

        News news = new News();
        news.setTitle(title.getTextContent());
        news.setDescription(description.getTextContent());
        news.setLink(link.getTextContent());
        //news.setPublishDate(pubDate.getTextContent());
        news.setWebsite(KHABARONLINE_WEBSITE);

        if (isPersepolisNews(news) && newsBean.findOne(news.getUniqueKey()) == null) {
          newsBean.save(news);
        }
      }
    } catch (ParserConfigurationException | IOException | SAXException | DOMException ex) {
      log.log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(minute = "7/10", hour = "*", persistent = false)
  public void fetchIsnaNews() {
    log.log(Level.INFO, "Fetching isna news ...");
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(ISNA_WEBSITE_URL).openStream());

      NodeList items = doc.getElementsByTagName("item");

      for (int i = 0; i < items.getLength(); i++) {

        Element item = (Element) items.item(i);
        Element title = (Element) item.getElementsByTagName("title").item(0);
        Element description = (Element) item.getElementsByTagName("description").item(0);
        Element pubDate = (Element) item.getElementsByTagName("pubDate").item(0);
        Element link = (Element) item.getElementsByTagName("link").item(0);

        News news = new News();
        news.setTitle(title.getTextContent());
        news.setDescription(description.getTextContent());
        news.setLink(link.getTextContent());
        news.setPublishDate(pubDate.getTextContent());
        news.setWebsite(ISNA_WEBSITE);

        if (isPersepolisNews(news) && newsBean.findOne(news.getUniqueKey()) == null) {
          newsBean.save(news);
        }
      }
    } catch (ParserConfigurationException | IOException | SAXException | DOMException ex) {
      log.log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(minute = "4/5", hour = "*", persistent = false)
  public void fetchNewsText() {
    log.info("Fetching news content ...");
    List<News> newsList = newsBean.readNewsWithoutContent(20);
    log.log(Level.INFO, "News without content {0}", newsList.size());

    for (News news : newsList) {
      try {
        NewsDescriptionFetch fetch = NewsDescriptionFetchFactory.generateNewsDescriptionFetch(news.getLink());
        String description = fetch.loadDescription();
        String date = fetch.loadDate();
        String image = fetch.loadImage();
        String title = fetch.loadTitle();
        news.setContent(description);
        news.setPublishDate(date);
        news.setTitle(title);
        news.setImage(image);

        newsBean.save(news);
      } catch (Exception ex) {
        log.log(Level.WARNING, "Fetchign news failed. {0} {1} - {2}", new Object[]{news.getId(), news.getUniqueKey(), ex.getMessage()});
      }
    }
  }

  private boolean isPersepolisNews(News news) {
    return true; /*
     PersepolisNewsMatcher matcher = new PersepolisNewsMatcher();
     return matcher.match(news);*/

  }

  @Schedule(minute = "0", hour = "4", dayOfWeek = "1", persistent = false)
  private void cleanupHotNews() {
    log.log(Level.INFO, "Cleaning hot news table.");
    newsBean.deleteAllHotNewsExceptLast();
  }

  @Schedule(minute = "*", hour = "5", dayOfWeek = "1", persistent = false)
  private void cleanupNews() {
    log.log(Level.INFO, "Cleaning news table.");
    newsBean.deleteAllExceptTop(10000);
  }

  @Schedule(minute = "5/15", hour = "*", persistent = false)
  public void findAndSaveWorldCupNews() {
    log.log(Level.INFO, "finding worldcup news.");
    List<News> newsList = newsBean.readTop(null, 100);

    List<Rule> rules = new ArrayList<>();
    List<RuleSeperator> seperators = new ArrayList();
    
    int i = 0;
    for (String word : worldcupKeywords) {
      TitleKeywordCondition titleCondition = new TitleKeywordCondition(new Keyword(word));
      ContentKeywordCondition contentCondition = new ContentKeywordCondition(2, new Keyword(word));

      Rule rule1 = new Rule();
      rule1.addCondition(titleCondition);

      Rule rule2 = new Rule();
      rule2.addCondition(contentCondition);

      rules.add(rule1);
      rules.add(rule2);

      seperators.add(RuleSeperator.OR);
      
      if (++i < worldcupKeywords.size()) {
        seperators.add(RuleSeperator.OR);
      }
    }

    NewsMatch newsMatch = new NewsMatch(rules, seperators);
    NewsFilter filter = new NewsFilter(newsMatch);

    List<News> worldCupRelatedNews = null;

    try {
      worldCupRelatedNews = filter.filter(newsList);
    } catch (MatchException ex) {
      log.log(Level.SEVERE, null, ex);
    }

    if (worldCupRelatedNews != null && worldCupRelatedNews.size() > 0) {
      List<WorldCupNews> worldCupNewsList = new ArrayList<>(worldCupRelatedNews.size());

      for (News news : worldCupRelatedNews) {
        WorldCupNews worldCupNews = new WorldCupNews(news);
        worldCupNewsList.add(worldCupNews);
      }

      for (WorldCupNews news : worldCupNewsList) {
        if (newsBean.findWorldCupNews(news.getUniqueKey()) == null) {
          newsBean.save(news);
          log.log(Level.INFO, "World cup news saved to DB: {0}", news.getId());
        }
      }
    }
  }
}
