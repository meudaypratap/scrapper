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
		if (args.length == 1) {
			String url = args[0];
			process(url);
		} else {
			System.out.println("Site url argument not passed, please run the programe like java -jar <JAR PATH> http://tretton37.com");
		}
	}

	private static void process(String url) {
		try {
			Long start = System.currentTimeMillis();
			LinkPublisher linkPublisher = new LinkPublisher(url);
			UrlContentSubscriber linkFinder = new LinkProcessor(linkPublisher);

			LinkScrapper linkScrapper = new LinkScrapper();
			linkScrapper.addSubscriber(new FileSystemUrlContentSubscriber());
			linkScrapper.addSubscriber(linkFinder);

			linkPublisher.addSubscriber(linkScrapper);

			linkPublisher.addSubscriber(new ProgressBar());

			Set<URL> urls = linkPublisher.run();
			Long end = System.currentTimeMillis();
			Long seconds = (end - start) / 1000;
			System.out.println("Total URL processed:[" + urls.size() + "], Execution time: [" + seconds + "] seconds");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
