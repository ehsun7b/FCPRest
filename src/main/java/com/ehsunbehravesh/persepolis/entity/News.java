package com.ehsunbehravesh.persepolis.entity;

import com.ehsuhnbehravesh.fcpersepolis.utils.Utils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class News implements Serializable {

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false, unique = true)
  private String uniqueKey;
  @Column(nullable = false)
  private String title;
  @Column(nullable = false)
  private String link;
  @Column(length = 5000)
  private String description;
  private String publishDate;
  private String image;
  private String website;

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
    this.uniqueKey = Utils.uniqueNewsKeyGen(link);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUniqueKey() {
    return uniqueKey;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }
}
