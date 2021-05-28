package com.tretton.scrapper.util;

public class UrlContent {
	private String url;
	private String content;

	public UrlContent(String url, String content) {
		this.url = url;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public String getUrl() {
		return url;
	}
}
