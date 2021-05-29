package com.tretton.scrapper.site;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LinkFinder implements LinkSubscriber {
	private static final Integer CONNECTION_TIMEOUT = 10000;

	private final LinkPublisher linkPublisher;

	public LinkFinder(LinkPublisher linkPublisher) {
		this.linkPublisher = linkPublisher;
	}

	@Override
	public void subscribe(URL url) {
		Set<String> urls = getUrls(url);
		urls.parallelStream().forEach(linkPublisher::process);
	}

	private Set<String> getUrls(URL url) {
		try {
			Document doc = Jsoup.parse(url, CONNECTION_TIMEOUT);

			Elements links = doc.select("a[href]");

			return links.stream().map(element -> element.attr("abs:href")).collect(Collectors.toSet());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashSet<>();
	}

}
