package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.publisher.LinkProcessor;
import com.tretton.scrapper.util.MockHtmlParser;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LinkScrapperTest {

	@Test
	void test_subscriber_not_called() throws MalformedURLException {
		LinkScrapper linkScrapper = new LinkScrapper(new MockHtmlParser("", new HashSet<>()));
		UrlContentSubscriber urlContentSubscriber = mock(LinkProcessor.class);
		linkScrapper.addSubscriber(urlContentSubscriber);
		URL url = new URL("http://google.com");
		linkScrapper.subscribe(url);
		verify(urlContentSubscriber, times(0)).subscribe(any());
	}

	@Test
	void test_subscriber_called() throws MalformedURLException {
		LinkScrapper linkScrapper = new LinkScrapper(new MockHtmlParser("tstestse", new HashSet<>()));
		UrlContentSubscriber urlContentSubscriber = mock(LinkProcessor.class);
		linkScrapper.addSubscriber(urlContentSubscriber);
		URL url = new URL("http://google.com");
		linkScrapper.subscribe(url);
		verify(urlContentSubscriber, times(1)).subscribe(any());
	}
}