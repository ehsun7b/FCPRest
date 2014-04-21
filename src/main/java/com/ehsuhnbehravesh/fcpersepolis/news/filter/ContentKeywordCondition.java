package com.ehsuhnbehravesh.fcpersepolis.news.filter;

import com.ehsuhnbehravesh.fcpersepolis.news.Keyword;
import com.ehsunbehravesh.persepolis.entity.News;

/**
 *
 * @author ehsun7b
 */
public class ContentKeywordCondition implements Condition {

  private final int minCount;
  private final Keyword keyword;

  public ContentKeywordCondition(int minCount, Keyword keyword) {
    this.minCount = minCount;
    this.keyword = keyword;
  }

  @Override
  public boolean condition(News news) {
    if (news.getContent() == null) {      
      return false;
    }
    int lastIndex = 0;
    int count = 0;

    while (lastIndex != -1) {

      lastIndex = news.getContent().indexOf(keyword.getValue(), lastIndex);

      if (lastIndex != -1) {
        count++;
        lastIndex += keyword.getValue().length();
      }
    }
    
    return count >= minCount;
  }

}
