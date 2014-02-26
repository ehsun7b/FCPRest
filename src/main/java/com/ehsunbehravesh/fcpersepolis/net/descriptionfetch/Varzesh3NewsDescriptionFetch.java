package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Varzesh3NewsDescriptionFetch extends NewsDescriptionFetch {

	private Document doc;
	
	@Override
	public String loadDescription() throws Exception {
		if (doc == null) {
			doc = Jsoup.connect(newsUrl).get();
		}

		Element newsTable = doc.select("table#NewsTable").get(0);
		Element nextTable = newsTable.nextElementSibling();
		Element tr = nextTable.select("tr").get(1);
		Element td = tr.select("td").get(0);
		Element div = td.select("div").get(0);
		String desc = div.html();

		return desc;
	}

	@Override
	public String loadDate() throws Exception {
		if (doc == null) {
			doc = Jsoup.connect(newsUrl).get();
		}

		Element newsTable = doc.select("table#NewsTable").get(0);
		Element font = newsTable.select("font").get(0);

		String date = font.html();

		return date;
	}

	@Override
	public String loadImage() throws Exception {
		if (doc == null) {
			doc = Jsoup.connect(newsUrl).get();
		}

		Element newsTable = doc.select("table#NewsTable").get(0);
		String img = newsTable.select("img").get(0).attr("abs:src");

		return img;
	}

	public static void main(String[] s) throws Exception {
		String url = "http://www.varzesh3.com/news.do?itemid=1020326&title=%D9%8A%D9%88%D9%88%D9%86%D8%AA%D9%88%D8%B3_%D8%A8%D9%87_%D8%AF%D9%86%D8%A8%D8%A7%D9%84_%D8%AC%D8%B0%D8%A8_%D9%86%D8%A7%D9%86%D9%8A_%D8%A7%D8%B3%D8%AA";
		NewsDescriptionFetch fetch = NewsDescriptionFetchFactory
				.generateNewsDescriptionFetch(url);

		String img = fetch.loadImage();
		String des = fetch.loadDescription();
		String date = fetch.loadDate();
		System.out.println(date);
		System.out.println(img);
		System.out.println(des);
	}
}
