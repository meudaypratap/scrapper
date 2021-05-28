package com.tretton.scrapper;

import com.tretton.scrapper.site.LinkFinder;
import com.tretton.scrapper.site.LinkPublisher;
import com.tretton.scrapper.site.LinkScrapper;
import com.tretton.scrapper.site.LinkSubscriber;
import com.tretton.scrapper.site.ProgressBar;

import java.net.MalformedURLException;

public class Application {
	public static void main(String[] args) {
		try {
			LinkPublisher linkPublisher = new LinkPublisher("https://tretton37.com");
			LinkSubscriber linkFinder = new LinkFinder(linkPublisher);
			linkPublisher.addSubscriber(linkFinder);
			linkPublisher.addSubscriber(new ProgressBar());
			linkPublisher.addSubscriber(new LinkScrapper());
			linkPublisher.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
