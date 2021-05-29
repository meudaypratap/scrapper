package com.tretton.scrapper.site;

import com.tretton.scrapper.util.UrlContent;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class LinkScrapper implements LinkSubscriber {
	private static final Integer CONNECTION_TIMEOUT = 10000;
	private final Set<UrlContentSubscriber> subscribers;

	public LinkScrapper() {
		subscribers = new HashSet<>();
	}

	@Override
	public void subscribe(URL url) {
		process(url);
	}

	public void addSubscriber(UrlContentSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	private void process(URL url) {
		try {
			String content = Jsoup.parse(url, CONNECTION_TIMEOUT).html();
			UrlContent urlContent = new UrlContent(url, content);
			notifySubscribers(urlContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void notifySubscribers(UrlContent urlContent) {
		subscribers.parallelStream().forEach(subscriber -> subscriber.subscribe(urlContent));
	}

}
