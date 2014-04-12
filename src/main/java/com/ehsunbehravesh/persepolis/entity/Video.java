package com.ehsunbehravesh.persepolis.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ehsun7b
 */
@Entity
public class Video implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(nullable = false)  
  private String title;
  @Column(nullable = false)
  private String image;
  @Column(length = 1000)
  private String embedCode;
  @ManyToOne
  private VideoCategory category;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getEmbedCode() {
    return embedCode;
  }

  public void setEmbedCode(String embedCode) {
    this.embedCode = embedCode;
  }

  public VideoCategory getCategory() {
    return category;
  }

  public void setCategory(VideoCategory category) {
    this.category = category;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
