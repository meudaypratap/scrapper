package com.tretton.scrapper;

import com.tretton.scrapper.site.LinkPublisher;
import com.tretton.scrapper.site.LinkSubscriber;
import com.tretton.scrapper.site.LinkSubscriberImpl;

import java.net.MalformedURLException;

public class Application {
	public static void main(String[] args) {
		try {
			LinkPublisher linkPublisher = new LinkPublisher("https://tretton37.com");
			LinkSubscriber linkSubscriber = new LinkSubscriberImpl(linkPublisher);
			linkPublisher.addSubscriber(linkSubscriber);
			linkPublisher.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
