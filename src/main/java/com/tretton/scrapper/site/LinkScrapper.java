package com.tretton.scrapper.site;

import org.jsoup.Jsoup;

import java.io.IOException;

public class LinkScrapper implements LinkSubscriber {

	@Override
	public void subscribe(String url) {
		String content = getContent(url);
	}

	private String getContent(String url) {
		try {
			return Jsoup.connect(url).get().html();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
