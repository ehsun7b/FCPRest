package com.ehsuhnbehravesh.fcpersepolis.news.filter;

import com.ehsuhnbehravesh.fcpersepolis.news.Keyword;
import com.ehsunbehravesh.persepolis.entity.News;

/**
 *
 * @author ehsun7b
 */
public class TitleKeywordCondition implements Condition {

  private final Keyword Keyword;

  public TitleKeywordCondition(Keyword Keyword) {
    this.Keyword = Keyword;
  }    
  
  @Override
  public boolean condition(News news) {
    return news.getTitle().contains(Keyword.getValue());
  }
  
}
