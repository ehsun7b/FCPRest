package com.ehsuhnbehravesh.fcpersepolis.news.filter;

import com.ehsunbehravesh.persepolis.entity.News;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ehsun7b
 */
public class Rule {  
  private List<Condition> conditions;

  public Rule(List<Condition> conditions) {
    this.conditions = conditions;
  }    

  public Rule() {
    conditions = new ArrayList<>();
  }

  public List<Condition> getConditions() {
    return conditions;
  }

  public void setConditions(List<Condition> conditions) {
    this.conditions = conditions;
  }
  
  public void addCondition(Condition condition) {
    conditions.add(condition);
  }
  
  public boolean result(News news) {
    for (Condition condition : conditions) {
      if (!condition.condition(news)) {
        return false;
      }
    }
    return true;
  }
}
