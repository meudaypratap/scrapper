package com.tretton.scrapper.util;

import com.tretton.scrapper.model.UrlContent;
import org.jsoup.internal.StringUtil;

import java.net.URL;
import java.util.Set;

public class MockHtmlParser implements HtmlParser {
	private final Set<String> links;
	private final String content;

	public MockHtmlParser(String content, Set<String> links) {
		this.links = links;
		this.content = content;
	}

	@Override
	public UrlContent parse(URL url) {
		return StringUtil.isBlank(content) ? null : new UrlContent(url, content, links);
	}
}
