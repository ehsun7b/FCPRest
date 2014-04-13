/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.Video;
import com.ehsunbehravesh.persepolis.entity.VideoCategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ehsun7b
 */
@Stateless
public class VideoBean {

  @PersistenceContext
  private EntityManager em;
  
  public void save(Video video) {
    em.persist(video);
  }
  
  public List<Video> readTop(int size) {
    TypedQuery<Video> query = em.createQuery("Select v FROM Video v  order by v.id DESC", Video.class);;    
    query.setMaxResults(size);
    return query.getResultList();
  }
  
  public List<VideoCategory> readCategories() {
    TypedQuery<VideoCategory> query = em.createQuery("Select vc FROM VideoCategory vc  order by vc.code", VideoCategory.class);;        
    return query.getResultList();
  }

  public VideoCategory findCategory(long code) {
    return em.find(VideoCategory.class, code);
  }

  public void save(VideoCategory category) {
    em.persist(category);
  }

  public Video find(Long id) {
    return em.find(Video.class, id);
  }
}
