package com.tretton.scrapper.publisher;

import com.tretton.scrapper.subscriber.LinkSubscriber;
import org.jsoup.internal.StringUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class LinkPublisher {

	private static final Set<URL> urls = new HashSet<>();

	private final String url;
	private final String host;
	private final Set<LinkSubscriber> subscribers;

	public LinkPublisher(String url) throws MalformedURLException {
		this.url = url;
		this.host = new URL(url).getHost();
		subscribers = new HashSet<>();
	}

	public void process(String url) {
		if (!StringUtil.isBlank(url)) {
			String sanitizedUrl = url.split("#")[0];
			sanitizedUrl = sanitizedUrl.replace("https", "http");
			if (sanitizedUrl.endsWith("/")) {
				sanitizedUrl = sanitizedUrl.substring(0, sanitizedUrl.length() - 1);
			}
			try {
				URL link = new URL(sanitizedUrl);
				String urlHost = link.getHost();
				if (urlHost.equalsIgnoreCase(host)) {
					if (!urls.contains(link)) {
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

	public Set<URL> run() {
		process(url);
		return urls;
	}
}
