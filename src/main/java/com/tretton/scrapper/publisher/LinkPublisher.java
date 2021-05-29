package com.tretton.scrapper.publisher;

import com.tretton.scrapper.subscriber.LinkSubscriber;
import org.jsoup.internal.StringUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * This class maintains all the links that are processed and pulishes event if there is any new link found for processing
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
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

	/**
	 * Sanitizes the url and publish event if any new url found
	 */
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

	/**
	 * Add subscribers who would be notified whenever there is any new url
	 */
	public void addSubscriber(LinkSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	private void notifySubscribers(URL url) {
		subscribers.parallelStream().forEach(subscriber -> subscriber.subscribe(url));
	}

	/**
	 * Run scrapping on assigned url in construtor
	 */
	public Set<URL> run() {
		process(url);
		return urls;
	}
}
