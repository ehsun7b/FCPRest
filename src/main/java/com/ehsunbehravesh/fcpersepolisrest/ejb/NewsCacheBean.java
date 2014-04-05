package com.ehsunbehravesh.fcpersepolisrest.ejb;

import com.ehsunbehravesh.persepolis.entity.News;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author ehsun7b
 */
@Singleton
public class NewsCacheBean {

  private final Cache<String, News> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(2, TimeUnit.HOURS).build();
  
  @Inject
  private NewsBean newsBean;

  public News findOne(String uniqueKey) {
    News result = cache.getIfPresent(uniqueKey);
    
    if (result == null) {
      result = newsBean.findOne(uniqueKey);
    }
    
    return result;
  }
}
