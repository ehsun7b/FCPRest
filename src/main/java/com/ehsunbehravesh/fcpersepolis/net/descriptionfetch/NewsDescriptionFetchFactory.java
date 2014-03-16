package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

public class NewsDescriptionFetchFactory {

  public static final String FC_PERSPOLIS_COM = "fc-perspolis.com";
  public static final String ARTESHESORH_COM = "arteshesorkh.com";
  public static final String PERSEPOLISNEWS_IR = "persepolisnews.ir";
  public static final String VARZESH3 = "varzesh3.com";
  public static final String KHABARONLINE = "khabaronline.ir";
  
  public static NewsDescriptionFetch generateNewsDescriptionFetch(String url) throws Exception {
    if (url.toLowerCase().contains(FC_PERSPOLIS_COM)) {
      NewsDescriptionFetch newsDescriptionFetch = new FCPerspolisNewsDescriptionFetch();
      newsDescriptionFetch.setNewsUrl(url);
      return newsDescriptionFetch;
    } else if (url.toLowerCase().contains(ARTESHESORH_COM)) {
      NewsDescriptionFetch newsDescriptionFetch = new ArteshNewsDescriptionFetch();
      newsDescriptionFetch.setNewsUrl(url);
      return newsDescriptionFetch;
    } else if (url.toLowerCase().contains(PERSEPOLISNEWS_IR)) {
      NewsDescriptionFetch newsDescriptionFetch = new PersepolisNewsDescriptionFetch();
      newsDescriptionFetch.setNewsUrl(url);
      return newsDescriptionFetch;
    } else if (url.toLowerCase().contains(VARZESH3)) {
      NewsDescriptionFetch newsDescriptionFetch = new Varzesh3NewsDescriptionFetch();
      newsDescriptionFetch.setNewsUrl(url);
      return newsDescriptionFetch;
    } else if (url.toLowerCase().contains(KHABARONLINE)) {
      NewsDescriptionFetch newsDescriptionFetch = new KhabarOnlineNewsDescriptionFetch();
      newsDescriptionFetch.setNewsUrl(url);
      return newsDescriptionFetch;
    } else {
      throw new Exception("The type of NewsDescriptionFetch can not be determined! url: " + url);
    }
  }
}
