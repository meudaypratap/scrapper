package com.tretton.scrapper.util;

import com.tretton.scrapper.model.UrlContent;

import java.net.URL;

public interface HtmlParser {
	UrlContent parse(URL url);
}
