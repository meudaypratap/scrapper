package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.model.UrlContent;

public interface UrlContentSubscriber {
	void subscribe(UrlContent urlContent);
}
