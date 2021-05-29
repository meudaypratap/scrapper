package com.tretton.scrapper.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlToFileConverterTest {

	@ParameterizedTest
	@CsvSource(value = {
			"http://tretton37.com/meet/erik-linne|erik-linne.html",
			"http://tretton37.com|index.html",
			"http://tretton37.com?id=test|id-test.html",
			"http://tretton37.com?id=test&x=abc&y=query|id-test_x-abc_y-query.html",
	}, delimiter = '|')
	void getFileName(String url, String fileName) throws MalformedURLException {
		URL link = new URL(url);
		String result = UrlToFileConverter.getFileName(link);
		assertEquals(fileName, result);
	}

	@ParameterizedTest
	@CsvSource(value = {
			"http://tretton37.com/meet/erik-linne|tretton37/meet",
			"http://tretton37.com|tretton37",
			"http://www.tretton37.com?id=test|tretton37"}, delimiter = '|')
	void getFolderName(String url, String folder) throws MalformedURLException {
		URL link = new URL(url);
		String result = UrlToFileConverter.getFolderName(link);
		assertEquals(folder, result);
	}
}