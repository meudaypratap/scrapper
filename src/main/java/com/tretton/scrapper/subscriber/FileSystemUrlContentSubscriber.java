package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.model.UrlContent;
import com.tretton.scrapper.util.FileWriter;
import com.tretton.scrapper.util.UrlToFileConverter;

import java.net.URL;

/**
 * This class writes the html on the file system by subscribing to content scraped event
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
public class FileSystemUrlContentSubscriber implements UrlContentSubscriber {

	private final FileWriter fileWriter;

	public FileSystemUrlContentSubscriber(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	/**
	 * This method gets the url and content to write it on file system
	 */
	@Override
	public void subscribe(UrlContent urlContent) {
		URL url = urlContent.getUrl();
		String fileName = UrlToFileConverter.getFileName(url);
		String folderName = UrlToFileConverter.getFolderName(url);
		String content = urlContent.getContent();

		fileWriter.write(folderName, fileName, content);
	}

}
