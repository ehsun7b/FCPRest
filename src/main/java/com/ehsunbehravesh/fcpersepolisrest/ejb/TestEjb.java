package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.News;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ehsun7b
 */
/*
@Stateless
@LocalBean*/
public class TestEjb {

  @Schedule(second = "*/1", minute = "0-59", hour = "0-23", persistent = true)
  public void doWork() {
    System.out.println("timer: " + new Date());
  }
    
  @Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
  public void doWork3() {
    System.out.println("timer3: " + new Date());
  }
}
