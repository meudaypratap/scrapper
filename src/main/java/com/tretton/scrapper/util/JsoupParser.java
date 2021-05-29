package com.tretton.scrapper.util;

import com.tretton.scrapper.model.UrlContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * This class scraps the data from url
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
public class JsoupParser implements HtmlParser {
	private static final Integer CONNECTION_TIMEOUT = 10000;

	/**
	 * This method extract html content of url and the links available on the page
	 */
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
