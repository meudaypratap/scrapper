package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.model.UrlContent;
import com.tretton.scrapper.util.FileWriter;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class FileSystemUrlContentSubscriberTest {

	@Test
	void subscribe() throws MalformedURLException {
		FileWriter mockedFileWriter = mock(FileWriter.class);
		FileSystemUrlContentSubscriber fileSystemUrlContentSubscriber = new FileSystemUrlContentSubscriber(mockedFileWriter);
		fileSystemUrlContentSubscriber.subscribe(new UrlContent(new URL("http://google.com"), "", new HashSet<>()));
		verify(mockedFileWriter, times(1)).write(any(), any(), any());
	}
}