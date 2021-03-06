package com.ehsunbehravesh.persepolis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author ehsun7b
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class HotNews implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;  
  @OneToMany  
  private List<News> newsList;

  public HotNews() {
    newsList = new ArrayList<>();
  }  
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<News> getNewsList() {
    return newsList;
  }

  public void setNewsList(List<News> newsList) {
    this.newsList = newsList;
  }
  
  public String getNewsKeys() {
    int size = newsList.size();
    StringBuilder result = new StringBuilder();
    if (size > 0) {
      for (int i = 0; i < size; i++) {
        result.append(newsList.get(i).getUniqueKey());
        if (i < size - 1) {
          result.append(",");
        }
      }
      return result.toString();
    } else {
      return "";
    }
  }
}
