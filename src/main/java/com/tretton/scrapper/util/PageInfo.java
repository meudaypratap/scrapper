package com.tretton.scrapper.util;

import java.util.HashSet;
import java.util.Set;

public class PageInfo {
	private String content;
	private Set<String> urls;

	public PageInfo() {
		content = "";
		urls = new HashSet<>();
	}

	public PageInfo(String content, Set<String> urls) {
		this.content = content;
		this.urls = urls;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
}
