package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.model.UrlContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
			Document document = Jsoup.parse(url, CONNECTION_TIMEOUT);
			Elements links = document.select("a[href]");
			Set<String> urls = links.stream().map(element -> element.attr("abs:href")).collect(Collectors.toSet());

			UrlContent urlContent = new UrlContent(url, document.html(), urls);
			notifySubscribers(urlContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void notifySubscribers(UrlContent urlContent) {
		subscribers.parallelStream().forEach(subscriber -> subscriber.subscribe(urlContent));
	}

}
