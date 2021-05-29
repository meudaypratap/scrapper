package com.tretton.scrapper.subscriber;

import com.tretton.scrapper.model.UrlContent;
import com.tretton.scrapper.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemUrlContentSubscriber implements UrlContentSubscriber {

	@Override
	public void subscribe(UrlContent urlContent) {
		URL url = urlContent.getUrl();
		String fileName = FileUtil.getFileName(url);
		String folderName = FileUtil.getFolderName(url);
		File directory = new File(folderName);
		if (!directory.exists()) {
			directory.mkdir();
		}
		String fullPath = folderName + "/" + fileName;
		Path path = Paths.get(fullPath);
		try {
			Files.write(path, urlContent.getContent().getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			System.out.println("Error writing file: [" + fullPath + "]");
		}
	}

}
