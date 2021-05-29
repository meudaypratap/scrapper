package com.tretton.scrapper.publisher;

import com.tretton.scrapper.subscriber.UrlContentSubscriber;
import com.tretton.scrapper.model.UrlContent;

public class LinkProcessor implements UrlContentSubscriber {
	private final LinkPublisher linkPublisher;

	public LinkProcessor(LinkPublisher linkPublisher) {
		this.linkPublisher = linkPublisher;
	}

	@Override
	public void subscribe(UrlContent urlContent) {
		urlContent.getLinks().parallelStream().forEach(linkPublisher::process);
	}

}
