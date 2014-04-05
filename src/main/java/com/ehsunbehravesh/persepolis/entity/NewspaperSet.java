package com.ehsunbehravesh.persepolis.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class NewspaperSet implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String publishDate;

  @OneToMany(mappedBy = "set", cascade = CascadeType.ALL)
  private List<Newspaper> newspapers;

  public NewspaperSet(String date) {
    this.publishDate = date;
  }

  public NewspaperSet() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Newspaper> getNewspapers() {
    return newspapers;
  }

  public void setNewspapers(List<Newspaper> newspapers) {
    this.newspapers = newspapers;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

}
