package com.tretton.scrapper.util;

import java.net.URL;
import java.util.Set;

public class UrlContent {
	private final URL url;
	private final Set<String> links;
	private final String content;

	public UrlContent(URL url, String content, Set<String> links) {
		this.url = url;
		this.content = content;
		this.links = links;
	}

	public String getContent() {
		return content;
	}

	public URL getUrl() {
		return url;
	}

	public Set<String> getLinks() {
		return links;
	}
}
