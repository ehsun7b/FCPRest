package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.HotNews;
import com.ehsunbehravesh.persepolis.entity.News;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ehsun7b
 */
@Stateless
public class NewsBean {

  @PersistenceContext
  private EntityManager em;

  public News findOne(Long id) {
    return em.find(News.class, id);
  }

  public void save(News news) {
    em.persist(news);
  }

  public void delete(News news) {
    em.remove(news);
  }

  public Long count() {
    Query q = em.createQuery("SELECT count(n) FROM NEWS n");
    Object singleResult = q.getSingleResult();
    return (Long) singleResult;
  }
  
  public void deleteAllHotNewsExceptLast() {
    Query q = em.createQuery("DELETE FROM HotNews n WHERE n.id < (SELECT max(n1.id) FROM HotNews n1)");    
    q.executeUpdate();
  }
  
  public void deleteAllExceptTop(int size) {    
    Query q = em.createQuery("DELETE FROM News n WHERE n.id < ((SELECT max(n1.id) FROM News n1) - :size)");
    q.setParameter("size", size);
    q.executeUpdate();    
  }

  public List<News> readTop(String website, int size) {
    TypedQuery<News> query = null;

    if (website != null) {
      query = em.createQuery("Select n FROM News n WHERE n.website = :website order by n.id DESC", News.class);
    } else {
      query = em.createQuery("Select n FROM News n order by n.id DESC", News.class);
    }

    if (website != null) {
      query.setParameter("website", website);
    }

    query.setMaxResults(size);
    return query.getResultList();
  }

  public List<News> readNewsWithoutContent(int size) {
    TypedQuery<News> query = em.createQuery("Select n FROM News n WHERE n.content is null order by n.id DESC", News.class);
    query.setMaxResults(size);
    return query.getResultList();
  }

  public News findOne(String uniqueKey) {
    TypedQuery<News> query = em.createQuery("Select news FROM News news WHERE news.uniqueKey = :key", News.class);
    query.setParameter("key", uniqueKey);
    try {
      return query.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  public List<News> currentHotNews() {
    TypedQuery<HotNews> query = em.createQuery("Select hotnews FROM HotNews hotnews order by hotnews.id DESC", HotNews.class);
    List<HotNews> list = query.getResultList();

    if (list.isEmpty()) {
      return new ArrayList<>();
    } else {
      HotNews hotNews = list.get(0);
      return hotNews.getNewsList();
    }
  }

  public List<HotNews> readTop(int size) {
    TypedQuery<HotNews> query = em.createQuery("Select hnews FROM HotNews hnews order by hnews.id DESC", HotNews.class);
    query.setMaxResults(size);
    return query.getResultList();
  }

  public void newHotNews(String[] newsKeys) {
    HotNews hotNews = new HotNews();
    List<News> newsList = new ArrayList<>();

    for (String key : newsKeys) {
      News news = findOne(key);
      newsList.add(news);
    }

    hotNews.setNewsList(newsList);

    em.persist(hotNews);
  }
}
