package com.tretton.scrapper.util;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class writes the content on file system
 *
 * @author Uday
 * @version 1.0
 * @since 1.0
 */
@Log
public class FileSystemWriter implements FileWriter {

	/**
	 * This method creates the directory and then write file on the system
	 */
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
