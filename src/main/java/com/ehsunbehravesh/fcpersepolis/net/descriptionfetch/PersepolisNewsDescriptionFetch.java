package com.ehsunbehravesh.fcpersepolis.net.descriptionfetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class PersepolisNewsDescriptionFetch extends NewsDescriptionFetch {

	private Document doc;

	@Override
	public String loadDescription() throws Exception {
		if (doc == null) {
			doc = Jsoup.connect(newsUrl).get();
		}

		Element divDescNews = doc.select("div.pic-efect").get(0);

		Elements imgs = divDescNews.select("img");
		for (Element img : imgs) {
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

		Element divEtelat = doc.select("div.etelaat").get(0);
		Element p = divEtelat.select("p").get(2);

		String date = p.html();

		return date;
	}

	@Override
	public String loadImage() throws Exception {
		if (doc == null) {
			doc = Jsoup.connect(newsUrl).get();
		}

		Element divEtelat = doc.select("div.tasvire").get(0);
		Element img = divEtelat.select("img").get(0);

		String date = img.attr("abs:src");

		return date;
	}

	public static void main(String[] s) throws Exception {
		String url = "http://www.persepolisnews.ir/1392/06/05/%D8%B3%DB%8C%D8%AF%D8%B5%D8%A7%D9%84%D8%AD%DB%8C-%D8%AD%D8%AA%D9%85%D8%A7-%D9%84%DB%8C%D8%A7%D9%82%D8%AA-%D9%86%D8%AF%D8%A7%D8%B4%D8%AA%D9%87%E2%80%8C%D8%A7%D9%85-%D8%A8%DB%8C%D8%B4%D8%AA%D8%B1.html";
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
