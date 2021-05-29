package com.tretton.scrapper.publisher;

import com.tretton.scrapper.model.UrlContent;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LinkProcessorTest {

	@Test
	void subscribe() throws MalformedURLException {
		LinkPublisher linkPublisher = mock(LinkPublisher.class);
		LinkProcessor linkProcessor = new LinkProcessor(linkPublisher);
		linkProcessor.subscribe(new UrlContent(new URL("http://google.com"), "", Collections.singleton("http:/google.com")));
		verify(linkPublisher, times(1)).process(any());

	}
}