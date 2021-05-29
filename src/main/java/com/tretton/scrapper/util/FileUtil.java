package com.tretton.scrapper.util;

import org.jsoup.internal.StringUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {
	private static final String DEFAULT_FILE = "index";

	public static String getFileName(URL url) {
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

	public static String getFolderName(URL url) {
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
