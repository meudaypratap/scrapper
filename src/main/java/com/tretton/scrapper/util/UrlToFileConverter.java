package com.tretton.scrapper.util;

import org.jsoup.internal.StringUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converter class to extract tree structured folder path and file name for url by sanitizing the url parameters.
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
public class UrlToFileConverter {
	private static final String DEFAULT_FILE = "index";

	/**
	 * Extracts file name from url using query parameter and path parameters.
	 */
	public static String getFileName(URL url) {
		String path = url.getPath();
		String query = url.getQuery();
		String fileName;
		if (StringUtil.isBlank(query)) {
			String[] paths = path.split("/");
			fileName = paths.length == 0 ? DEFAULT_FILE : paths[paths.length - 1];
		} else {
			fileName = sanitizeQueryParameters(query);
		}
		fileName = StringUtil.isBlank(fileName) ? DEFAULT_FILE : fileName;
		return fileName.endsWith(".html") ? fileName : (fileName + ".html");
	}

	/**
	 * Extracts folder name from url using domain name of the url and path variables.
	 */
	private static String sanitizeQueryParameters(String query) {
		return query.split("#")[0].replaceAll("&", "_").replaceAll("=", "-");
	}

	public static String getFolderName(URL url) {
		String path = url.getPath();
		List<String> folders = new ArrayList<>();
		folders.add(getBaseFolder(url.getHost()));
		List<String> paths = Stream.of(path.split("/")).filter(value -> !value.isEmpty()).collect(Collectors.toList());
		if (paths.size() > 1) {
			folders.addAll(paths.subList(0, paths.size() - 1));
		}
		return String.join("/", folders);
	}

	private static String getBaseFolder(String host) {
		return host.replace("www.", "").split("\\.")[0].replaceAll("\\.", "\\-");
	}
}
