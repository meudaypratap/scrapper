package com.tretton.scrapper.site;

import com.tretton.scrapper.util.UrlContent;
import org.jsoup.internal.StringUtil;

import java.net.URL;

public class FileSystemUrlContentSubscriber implements UrlContentSubscriber {
	private static final String DEFAULT_FILE = "index";

	@Override
	public void subscribe(UrlContent urlContent) {
		URL url = urlContent.getUrl();
		String fileName = getFileName(url);
		String folderName = getFolderName(url);
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
		String[] paths = path.split("/");
		return paths.length < 2 ? url.getHost().split("/")[0] : paths[paths.length - 2];
	}


}
