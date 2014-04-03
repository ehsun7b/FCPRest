package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.News;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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

  public List<News> readTop(String website, int size) {
    TypedQuery<News> query = em.createQuery("Select news FROM News news WHERE news.website = :website order by news.id DESC", News.class);
    query.setParameter("website", website);
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
}
