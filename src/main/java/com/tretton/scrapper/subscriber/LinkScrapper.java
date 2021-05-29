package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.model.UrlContent;
import com.tretton.scrapper.util.HtmlParser;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * This class scraps the data from url and publishes message to its subscriber in the form of html content and links in the page
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
public class LinkScrapper implements LinkSubscriber {
	private final Set<UrlContentSubscriber> subscribers;
	private final HtmlParser htmlParser;

	public LinkScrapper(HtmlParser htmlParser) {
		subscribers = new HashSet<>();
		this.htmlParser = htmlParser;
	}

	/**
	 * This method process the url content to extract html and links, it publishes the processed message to its subscriber
	 */
	@Override
	public void subscribe(URL url) {
		UrlContent urlContent = htmlParser.parse(url);
		if (urlContent != null) {
			notifySubscribers(urlContent);
		}
	}

	/**
	 * Subscribers can be added for processed html response
	 */
	public void addSubscriber(UrlContentSubscriber subscriber) {
		subscribers.add(subscriber);
	}

	private void notifySubscribers(UrlContent urlContent) {
		subscribers.parallelStream().forEach(subscriber -> subscriber.subscribe(urlContent));
	}

}
