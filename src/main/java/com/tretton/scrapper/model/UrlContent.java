package com.tretton.scrapper.model;

import java.net.URL;
import java.util.Set;

/**
 * This is wrapper class to contain url html content and links
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
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
