package com.tretton.scrapper;

import com.tretton.scrapper.site.FileSystemUrlContentSubscriber;
import com.tretton.scrapper.site.LinkProcessor;
import com.tretton.scrapper.site.LinkPublisher;
import com.tretton.scrapper.site.LinkScrapper;
import com.tretton.scrapper.site.ProgressBar;
import com.tretton.scrapper.site.UrlContentSubscriber;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class Application {
	public static void main(String[] args) {
		try {
			LinkPublisher linkPublisher = new LinkPublisher("https://tretton37.com");
			UrlContentSubscriber linkFinder = new LinkProcessor(linkPublisher);

			LinkScrapper linkScrapper = new LinkScrapper();
			linkScrapper.addSubscriber(new FileSystemUrlContentSubscriber());
			linkScrapper.addSubscriber(linkFinder);

			linkPublisher.addSubscriber(linkScrapper);

			linkPublisher.addSubscriber(new ProgressBar());

			Set<URL> urls = linkPublisher.run();
			System.out.println("Total URL processed:[" + urls.size() + "]");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
