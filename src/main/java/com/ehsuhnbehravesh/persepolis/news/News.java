package com.ehsuhnbehravesh.persepolis.news;

public class News {

  private String title;
  private String link;
  private String description;
  private String publishDate;
  private long firstLikeTime;
  private String image;  

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public long getFirstLikeTime() {
    return firstLikeTime;
  }

  public void setFirstLikeTime(long firstLikeTime) {
    this.firstLikeTime = firstLikeTime;
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
}
