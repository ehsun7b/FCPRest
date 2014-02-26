package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


class ArteshNewsDescriptionFetch extends NewsDescriptionFetch {

	private Document doc;
	
	@Override
	public String loadDescription() throws Exception {
		if (doc == null) {
  		doc = Jsoup.connect(newsUrl).get();
  	}
  	
		Document clone = doc.clone();
    Element divDescNews = clone.select("div.text_content").get(0);
    Element divImg = divDescNews.child(0);
    divImg.remove();
    
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
  	
    Element spanDate = doc.select("span.date_add").get(0);
       
    String date = spanDate.html();  
    
    return date;
	}

	@Override
	public String loadImage() throws Exception {
		if (doc == null) {
  		doc = Jsoup.connect(newsUrl).get();
  	}
  	
    Element divDescNews = doc.select("div.text_content").get(0);
    Element divImage = divDescNews.select("div").get(0);
    Element a = divImage.select("a").get(0);
    String result = null;
    if (a != null) {
    	Element img = a.select("img").get(0);
    	if (img != null) {
    		result = img.attr("abs:src");
    	}
    }
    
    return result;
	}

	public static void main(String[] s) throws Exception {
		String url = "http://www.arteshesorkh.com/index/post_44004/%DA%A9%D8%A7%D8%B1-%D8%A7%D9%86%D8%AA%D9%82%D8%A7%D9%84-%D8%AE%D9%84%D8%B9%D8%AA%D8%A8%D8%B1%DB%8C-%D8%A8%D9%87-%D9%BE%D8%B1%D8%B3%D9%BE%D9%88%D9%84%DB%8C%D8%B3-%D8%AA%D9%85%D8%A7%D9%85-%D8%B4%D8%AF-%D8%AD%D8%B6%D9%88%D8%B1-%D8%AF%D8%B1-%D8%AA%D9%85%D8%B1%DB%8C%D9%86%D8%A7%D8%AA/";
		NewsDescriptionFetch fetch = NewsDescriptionFetchFactory.generateNewsDescriptionFetch(url);

		String des = fetch.loadDescription();

		String date = fetch.loadImage();
		System.out.println(date);
		System.out.println(des);
	}
}
