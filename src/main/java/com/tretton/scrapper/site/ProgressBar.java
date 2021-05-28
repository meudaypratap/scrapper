package com.tretton.scrapper.site;

public class ProgressBar implements LinkSubscriber {

	private Integer counter;

	public ProgressBar() {
		this.counter = 0;
	}

	@Override
	public void subscribe(String url) {
		counter++;
		System.out.print("Processing link # [" + counter + "]");
	}
}
