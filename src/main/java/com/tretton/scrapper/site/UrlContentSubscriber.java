package com.tretton.scrapper.site;

import com.tretton.scrapper.util.UrlContent;

public interface UrlContentSubscriber {
	void subscribe(UrlContent urlContent);
}
