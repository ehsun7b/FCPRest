/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

/**
 *
 * @author ehsun.behravesh
 */
public class Test {

  public static void main(String[] args) throws Exception {
    NewsDescriptionFetch fetch = NewsDescriptionFetchFactory.generateNewsDescriptionFetch("http://www1.fc-perspolis.com/news_content/main/newsId:12893");
    String des = fetch.loadDescription();
    System.out.println(des);
  }
}
