package com.tretton.scrapper.site;

import com.tretton.scrapper.util.PageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class LinkSubscriberImpl implements LinkSubscriber {
	private final LinkPublisher linkPublisher;

	public LinkSubscriberImpl(LinkPublisher linkPublisher) {
		this.linkPublisher = linkPublisher;
	}

	@Override
	public void subscribe(String url) {
		PageInfo pageInfo = getPageInfo(url);
		pageInfo.getUrls().forEach(linkPublisher::process);
	}

	private PageInfo getPageInfo(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			String content = doc.html();

			Elements links = doc.select("a[href]");
			Set<String> urls = links.stream().map(element -> element.attr("abs:href")).collect(Collectors.toSet());

			return new PageInfo(content, urls);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PageInfo();
	}

}
