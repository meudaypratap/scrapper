package com.tretton.scrapper.publisher;

import com.tretton.scrapper.subscriber.LinkSubscriber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LinkPublisherTest {

	@ParameterizedTest
	@CsvSource(value = {
			"http://tretton37.com/meet|1",
			"https://tretton37.com/meet|0",
			"http://tretton37.com/meet|0",
			"http://tretton37.com/meet/|0",
			"test|0",
			"http://google.com|0",
			"|0",
	}, delimiter = '|')
	void process(String url, Integer executionCounter) throws MalformedURLException {
		LinkSubscriber linkSubscriber = mock(LinkSubscriber.class);
		LinkPublisher linkPublisher = new LinkPublisher("https://tretton37.com");
		linkPublisher.addSubscriber(linkSubscriber);
		linkPublisher.process(url);
		verify(linkSubscriber, times(executionCounter)).subscribe(any());
	}

	@Test
	void addSubscriber() throws Exception {
		Field subscriberField = LinkPublisher.class.getDeclaredField("subscribers");
		subscriberField.setAccessible(true);
		LinkSubscriber linkSubscriber = mock(LinkSubscriber.class);
		LinkPublisher linkPublisher = new LinkPublisher("http://google.com");
		linkPublisher.addSubscriber(linkSubscriber);

		Set<LinkSubscriber> subscribers = (Set<LinkSubscriber>) subscriberField.get(linkPublisher);
		assertEquals(1, subscribers.size());

	}

	@Test
	void run() throws MalformedURLException {
		LinkSubscriber linkSubscriber = mock(LinkSubscriber.class);
		LinkPublisher linkPublisher = new LinkPublisher("http://google.com");
		linkPublisher.addSubscriber(linkSubscriber);
		linkPublisher.run();
		verify(linkSubscriber, times(1)).subscribe(any());
	}
}