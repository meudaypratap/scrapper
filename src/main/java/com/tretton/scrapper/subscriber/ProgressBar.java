package com.tretton.scrapper.subscriber;

import java.net.URL;

/**
 * This class shows the progress of which url is under process and how many urls are processed
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
public class ProgressBar implements LinkSubscriber {

	private Integer counter;

	public ProgressBar() {
		this.counter = 0;
	}

	/**
	 * Shows the progress on each url processing with url and counter
	 */
	@Override
	public void subscribe(URL url) {
		counter++;
		System.out.println("Processing URL [" + url + "] # " + counter);
	}
}
