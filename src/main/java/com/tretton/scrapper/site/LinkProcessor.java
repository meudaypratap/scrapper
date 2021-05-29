package com.tretton.scrapper.site;

import com.tretton.scrapper.util.UrlContent;

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
