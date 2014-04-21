package com.ehsuhnbehravesh.fcpersepolis.news.filter;

import com.ehsunbehravesh.persepolis.entity.News;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ehsun7b
 */
public class NewsFilter {  
  
  protected NewsMatch match;

  public NewsFilter(NewsMatch match) {
    this.match = match;
  }
  
  public List<News> filter(List<News> list) throws MatchException {
    List<News> result = new ArrayList<>();
    
    for (News news : list) {
      if (match.match(news)) {
        result.add(news);
      }
    }
    
    return result;
  }
}
