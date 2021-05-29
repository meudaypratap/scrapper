package com.tretton.scrapper.util;

import java.net.URL;

public class UrlContent {
	private URL url;
	private String content;

	public UrlContent(URL url, String content) {
		this.url = url;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public URL getUrl() {
		return url;
	}
}
