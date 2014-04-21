package com.ehsuhnbehravesh.fcpersepolis.news.filter;

import com.ehsunbehravesh.persepolis.entity.News;
import java.util.List;

/**
 *
 * @author ehsun7b
 */
public class NewsMatch {

  protected List<Rule> rules;
  protected List<RuleSeperator> seperators;

  public NewsMatch(List<Rule> rules, List<RuleSeperator> seperators) {
    this.rules = rules;
    this.seperators = seperators;
  }    
  
  public boolean match(News news) throws MatchException {
    if (rules.size() <= 0) {
      return true;
    } else if (rules.size() == 1) {
      return rules.get(0).result(news);
    } else {
      if (seperators.size() != rules.size() - 1) {
        throw new MatchException("Size of seperators must be 1 less than size of rules!");
      }
      
      if (allAreAnds(seperators)) {
        for (Rule rule : rules) {
          if (!rule.result(news)) {
            return false;
          }
        }
        return true;
      } else if (allAreOrs(seperators)) {
        for (Rule rule : rules) {
          if (rule.result(news)) {
            return true;
          }
        }
        return false;
      } else {
        boolean result = rules.get(0).result(news);
        
        for (int i = 1; i < rules.size(); ++i) {
          if (seperators.get(i - 1).equals(RuleSeperator.AND)) {
            result = result && rules.get(i).result(news);
          } else if (seperators.get(i - 1).equals(RuleSeperator.OR)) {
            result = result || rules.get(i).result(news);
          }
        }
        
        return result;
      }
    }
  }

  private boolean allAreAnds(List<RuleSeperator> seperators) {
    for (RuleSeperator seperator : seperators) {
      if (!seperator.equals(RuleSeperator.AND)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean allAreOrs(List<RuleSeperator> seperators) {
    for (RuleSeperator seperator : seperators) {
      if (!seperator.equals(RuleSeperator.OR)) {
        return false;
      }
    }
    return true;
  }
  
}
