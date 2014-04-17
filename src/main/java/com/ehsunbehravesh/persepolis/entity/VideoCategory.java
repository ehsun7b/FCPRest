package com.ehsunbehravesh.persepolis.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ehsun7b
 */
@Entity
public class VideoCategory implements Serializable {

  @Id  
  private Long code;
  @Column(unique = true)
  private String title;
  @OneToMany(mappedBy = "category")
  private List<Video> videos;

  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Video> getVideos() {
    return videos;
  }

  public void setVideos(List<Video> videos) {
    this.videos = videos;
  }

  @Override
  public String toString() {
    return title;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof VideoCategory) {
      VideoCategory vc = (VideoCategory) obj;
      return code.equals(vc.code);
    }
    
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 37 * hash + Objects.hashCode(this.code);
    return hash;
  }

  
  
}
