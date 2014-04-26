package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.HotNews;
import com.ehsunbehravesh.persepolis.entity.News;
import com.ehsunbehravesh.persepolis.entity.WorldCupNews;
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
    if (!em.contains(news)) {
      news = em.merge(news);
    }
    em.persist(news);
  }

  public void save(WorldCupNews news) {
    if (!em.contains(news)) {
      news = em.merge(news);
    }
    em.persist(news);
  }

  public void save(HotNews news) {
    if (news.getId() != null) {
      em.merge(news);
    } else {
      em.persist(news);
    }
  }

  public void delete(News news) {
    if (!em.contains(news)) {
      news = em.merge(news);
    }
    em.remove(news);
  }

  public Long count() {
    Query q = em.createQuery("SELECT count(n) FROM NEWS n WHERE TYPE(n) = :type");
    q.setParameter("type", News.class);
    Object singleResult = q.getSingleResult();
    return (Long) singleResult;
  }

  public void deleteAllHotNewsExceptLast() {
    Query q = em.createQuery("DELETE FROM HotNews n WHERE n.id < (SELECT max(n1.id) FROM HotNews n1)");
    q.executeUpdate();
  }

  public void deleteAllExceptTop(int size) {
    Query q = em.createQuery("DELETE FROM News n WHERE TYPE(n) = :type and n.id < ((SELECT max(n1.id) FROM News n1) - :size)");
    q.setParameter("type", News.class);
    q.setParameter("size", size);
    q.executeUpdate();
  }

  public List<News> readTop(String website, int size) {
    TypedQuery<News> query = null;

    if (website != null) {
      query = em.createQuery("Select n FROM News n WHERE TYPE(n) = :type and n.website = :website and n.enable = :enable order by n.id DESC", News.class);
    } else {
      query = em.createQuery("Select n FROM News n WHERE TYPE(n) = :type and n.enable = :enable order by n.id DESC", News.class);
    }

    query.setParameter("type", News.class);
    query.setParameter("enable", Boolean.TRUE);
    
    if (website != null) {
      query.setParameter("website", website);
    }

    query.setMaxResults(size);
    return query.getResultList();
  }

  public List<News> readNewsWithoutContent(int size) {
    TypedQuery<News> query = em.createQuery("Select n FROM News n WHERE TYPE(n) = :type and n.content is null and n.enable = :enable order by n.id DESC", News.class);
    query.setParameter("type", News.class);
    query.setParameter("enable", Boolean.TRUE);
    query.setMaxResults(size);
    return query.getResultList();
  }

  public News findOne(String uniqueKey) {
    TypedQuery<News> query = em.createQuery("Select n FROM News n WHERE TYPE(n) = :type and n.uniqueKey = :key", News.class);
    query.setParameter("key", uniqueKey);
    query.setParameter("type", News.class);
    try {
      return query.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  public List<News> currentHotNews() {
    TypedQuery<HotNews> query = em.createQuery("Select h FROM HotNews h order by h.id DESC", HotNews.class);
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

  @Deprecated
  public List<News> findKeyword(List<String> keywords, int size) {
    StringBuilder jpaq = new StringBuilder("Select n FROM News n where ");

    int i = 0;
    for (String keyword : keywords) {
      jpaq.append("n.content like :p").append(i++);

      if (i < keywords.size()) {
        jpaq.append(" or ");
      }
    }

    //System.out.println(jpaq);
    TypedQuery<News> query = em.createQuery(jpaq.toString(), News.class);

    i = 0;
    for (String keyword : keywords) {
      query.setParameter("p" + i++, "%" + keyword + "%");
      //System.out.println(keyword);
    }

    query.setMaxResults(size);
    return query.getResultList();
  }

  public WorldCupNews findWorldCupNews(String uniqueKey) {
    TypedQuery<WorldCupNews> query = em.createQuery("Select n FROM WorldCupNews n WHERE n.uniqueKey = :key", WorldCupNews.class);
    query.setParameter("key", uniqueKey);
    try {
      return query.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  public List<WorldCupNews> readTopWorldCup(int size) {
    TypedQuery<WorldCupNews> query = null;
    query = em.createQuery("Select n FROM WorldCupNews n where n.enable = :enable order by n.id DESC", WorldCupNews.class);
    query.setParameter("enable", Boolean.TRUE);
    query.setMaxResults(size);
    return query.getResultList();
  }

  public List<News> readAnyNews(int size) {
    TypedQuery<News> query = em.createQuery("Select n FROM News n WHERE n.enable = :enable order by n.id DESC", News.class);
    query.setParameter("enable", Boolean.TRUE);
    query.setMaxResults(size);
    return query.getResultList();
  }
}
