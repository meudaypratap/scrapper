package com.tretton.scrapper.site;

import org.jsoup.internal.StringUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class LinkPublisher {

	private static final Set<URL> urls = new HashSet<>();

	private final String url;
	private final String domain;
	private final Set<LinkSubscriber> subscribers;

	public LinkPublisher(String url) throws MalformedURLException {
		this.url = url;
		this.domain = new URL(url).getHost();
		subscribers = new HashSet<>();
	}

	public void process(String url) {
		if (!StringUtil.isBlank(url)) {
			String sanitizedUrl = url.split("#")[0];
			sanitizedUrl = sanitizedUrl.replace("https", "http");
			try {
				URL link = new URL(sanitizedUrl);
				if (!urls.contains(link)) {
					String urlDomain = link.getHost();
					if (urlDomain.equalsIgnoreCase(domain)) {
						urls.add(link);
						notifySubscribers(link);
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addSubscriber(LinkSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	private void notifySubscribers(URL url) {
		subscribers.parallelStream().forEach(subscriber -> subscriber.subscribe(url));
	}

	public void run() {
		process(url);
	}
}
