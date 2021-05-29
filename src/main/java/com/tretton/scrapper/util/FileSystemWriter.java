package com.tretton.scrapper.util;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log
public class FileSystemWriter implements FileWriter {
	@Override
	public void write(String folderName, String fileName, String content) {
		String fullPath = folderName + "/" + fileName;
		File directory = new File(folderName);
		if (!directory.exists()) {
			directory.mkdir();
		}
		Path path = Paths.get(fullPath);
		try {
			Files.write(path, content.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			log.info("Error writing file: [" + fullPath + "]");
		}
	}
}
