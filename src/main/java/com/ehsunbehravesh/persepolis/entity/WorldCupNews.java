/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.persepolis.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author ehsun7b
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class WorldCupNews extends News {

  public WorldCupNews() {
    super();
  }

  public WorldCupNews(News news) {
    content = news.content;
    uniqueKey = news.uniqueKey;
    image = news.image;
    description = news.description;
    link = news.link;
    publishDate = news.publishDate;
    website = news.website;
    title = news.title;
  }
}
