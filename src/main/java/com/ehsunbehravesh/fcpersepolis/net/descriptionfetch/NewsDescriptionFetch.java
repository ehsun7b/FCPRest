package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

public abstract class NewsDescriptionFetch {

  protected String newsUrl;

  public String getNewsUrl() {
    return newsUrl;
  }

  public void setNewsUrl(String newsUrl) {
    this.newsUrl = newsUrl;
  }

  public abstract String loadDescription() throws Exception;
  
  public abstract String loadDate() throws Exception;
  
  public abstract String loadImage() throws Exception;
  
  public abstract String loadTitle() throws Exception;
}
