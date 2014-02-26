package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FCPerspolisNewsDescriptionFetch extends NewsDescriptionFetch {

	private Document doc;
	
  @Override
  public String loadDescription() throws IOException {
  	if (doc == null) {
  		doc = Jsoup.connect(newsUrl).get();
  	}
  	
    Element divDescNews = doc.select("div.desc").get(0);
    
    //Element firstA = divDescNews.select("span.printerico").get(0);
    //firstA.remove();
    
    Elements imgs = divDescNews.select("img");
    for (Element img: imgs) {
    	String src = img.attr("abs:src");
    	img.attr("src", src);
    }
    
    String content = divDescNews.html();  
    
    return content;
  }

	@Override
	public String loadDate() throws Exception {
  	if (doc == null) {
  		doc = Jsoup.connect(newsUrl).get();
  	}
  	
    Element divDate = doc.select("div.date").get(0);//.select("span").get(1);
        
    String date = divDate.html();
    date = divDate.text();
    
    return date;
	}

	@Override
	public String loadImage() throws Exception {
		if (doc == null) {
  		doc = Jsoup.connect(newsUrl).get();
  	}
  	
    Element image = doc.select("div.desc-news").get(0).select("img").get(1);
    
    String src = image.attr("abs:src");
    return src;
	}
	
	public static void main(String[] s) throws Exception {
		String url = "http://www1.fc-perspolis.com/news_content/main/newsId:12988";
		NewsDescriptionFetch fetch = NewsDescriptionFetchFactory.generateNewsDescriptionFetch(url);
		String date = fetch.loadImage();
		System.out.println(date);
	}
}
