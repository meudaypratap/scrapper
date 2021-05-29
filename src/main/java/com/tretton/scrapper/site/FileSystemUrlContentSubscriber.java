package com.tretton.scrapper.site;

import com.tretton.scrapper.util.UrlContent;
import org.jsoup.internal.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemUrlContentSubscriber implements UrlContentSubscriber {
	private static final String DEFAULT_FILE = "index";

	@Override
	public void subscribe(UrlContent urlContent) {
		URL url = urlContent.getUrl();
		String fileName = getFileName(url);
		String folderName = getFolderName(url);
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

	private String getFileName(URL url) {
		String path = url.getPath();
		String query = url.getQuery();
		String fileName;
		if (StringUtil.isBlank(query)) {
			String[] paths = path.split("/");
			fileName = paths.length == 0 ? DEFAULT_FILE : paths[paths.length - 1];
		} else {
			fileName = query.split("#")[0].replaceAll("&", "-");
		}
		fileName = StringUtil.isBlank(fileName) ? DEFAULT_FILE : fileName;
		return fileName + ".html";
	}

	private String getFolderName(URL url) {
		String path = url.getPath();
		List<String> folders = new ArrayList<>();
		folders.add(url.getHost().split("\\.")[0].replaceAll("\\.", "-"));
		List<String> paths = Stream.of(path.split("/")).filter(value -> !value.isEmpty()).collect(Collectors.toList());
		if (paths.size() > 1) {
			folders.addAll(paths.subList(0, paths.size() - 1));
		}
		return String.join("/", folders);
	}


}
