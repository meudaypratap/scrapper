package com.tretton.scrapper;

import com.tretton.scrapper.publisher.LinkProcessor;
import com.tretton.scrapper.publisher.LinkPublisher;
import com.tretton.scrapper.subscriber.FileSystemUrlContentSubscriber;
import com.tretton.scrapper.subscriber.LinkScrapper;
import com.tretton.scrapper.subscriber.ProgressBar;
import com.tretton.scrapper.subscriber.UrlContentSubscriber;
import com.tretton.scrapper.util.FileSystemWriter;
import com.tretton.scrapper.util.JsoupParser;
import lombok.extern.java.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

@Log
public class Application {
	public static void main(String[] args) {
		if (args.length == 1) {
			String url = args[0];
			process(url);
		} else {
			log.info("Site url argument not passed, please run the programe like java -jar <JAR PATH> http://tretton37.com");
		}
	}

	private static void process(String url) {
		try {
			Long start = System.currentTimeMillis();
			LinkPublisher linkPublisher = new LinkPublisher(url);
			UrlContentSubscriber linkFinder = new LinkProcessor(linkPublisher);

			LinkScrapper linkScrapper = new LinkScrapper(new JsoupParser());
			linkScrapper.addSubscriber(new FileSystemUrlContentSubscriber(new FileSystemWriter()));
			linkScrapper.addSubscriber(linkFinder);

			linkPublisher.addSubscriber(linkScrapper);

			linkPublisher.addSubscriber(new ProgressBar());

			Set<URL> urls = linkPublisher.run();
			Long end = System.currentTimeMillis();
			long seconds = (end - start) / 1000;
			log.info("Total URL processed:[" + urls.size() + "], Execution time: [" + seconds + "] seconds");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
