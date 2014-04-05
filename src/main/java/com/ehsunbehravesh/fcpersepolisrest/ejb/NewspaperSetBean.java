package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.NewspaperSet;
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
public class NewspaperSetBean {
  
  @PersistenceContext
  private EntityManager em;
  
  public NewspaperSet findLast() {
    
    TypedQuery<NewspaperSet> query = em.createQuery("Select nset From NewspaperSet nset order by nset.id DESC", NewspaperSet.class);
    List<NewspaperSet> list = query.getResultList();
    
    if (list.isEmpty()) {
      return null;
    } else {
      return list.get(0);
    }
  }
  
  public void save(NewspaperSet set) {
    em.persist(set);
  }
}
