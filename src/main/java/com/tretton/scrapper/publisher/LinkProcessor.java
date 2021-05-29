package com.tretton.scrapper.publisher;

import com.tretton.scrapper.model.UrlContent;
import com.tretton.scrapper.subscriber.UrlContentSubscriber;

/**
 * This class listens to the content scrapper event and call all the new links for processing
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
public class LinkProcessor implements UrlContentSubscriber {
	private final LinkPublisher linkPublisher;

	public LinkProcessor(LinkPublisher linkPublisher) {
		this.linkPublisher = linkPublisher;
	}

	/**
	 * Send each link for processing via link publisher
	 */
	@Override
	public void subscribe(UrlContent urlContent) {
		urlContent.getLinks().parallelStream().forEach(linkPublisher::process);
	}

}
