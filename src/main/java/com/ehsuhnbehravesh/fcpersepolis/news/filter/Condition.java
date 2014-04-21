package com.ehsuhnbehravesh.fcpersepolis.news.filter;

import com.ehsunbehravesh.persepolis.entity.News;

/**
 *
 * @author ehsun7b
 */
public interface Condition {    
  public boolean condition(News news);
}
