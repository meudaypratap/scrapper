package com.tretton.scrapper.util;

import com.tretton.scrapper.model.UrlContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

public class JsoupParser implements HtmlParser {
	private static final Integer CONNECTION_TIMEOUT = 10000;

	@Override
	public UrlContent parse(URL url) {
		Document document;
		try {
			document = Jsoup.parse(url, CONNECTION_TIMEOUT);
		} catch (IOException e) {
			return null;
		}
		Elements links = document.select("a[href]");
		Set<String> urls = links.stream().map(element -> element.attr("abs:href")).collect(Collectors.toSet());

		return new UrlContent(url, document.html(), urls);

	}
}
