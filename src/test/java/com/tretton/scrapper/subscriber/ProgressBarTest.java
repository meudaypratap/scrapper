package com.tretton.scrapper.subscriber;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgressBarTest {

	@Test
	void subscribe() throws Exception {
		Field counterField = ProgressBar.class.getDeclaredField("counter");
		counterField.setAccessible(true);
		ProgressBar progressBar = new ProgressBar();
		progressBar.subscribe(new URL("http://google.com"));
		Integer fieldValue = (Integer) counterField.get(progressBar);
		assertEquals(1, fieldValue);
	}
}