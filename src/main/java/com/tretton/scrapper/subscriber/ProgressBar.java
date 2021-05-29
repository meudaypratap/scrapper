package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.subscriber.LinkSubscriber;

import java.net.URL;

public class ProgressBar implements LinkSubscriber {

	private Integer counter;

	public ProgressBar() {
		this.counter = 0;
	}

	@Override
	public void subscribe(URL url) {
		counter++;
		System.out.println("Processing URL [" + url + "] # " + counter);
	}
}
