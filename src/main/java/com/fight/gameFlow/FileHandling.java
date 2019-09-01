package com.fight.gameFlow;

import java.io.File;

abstract class FileHandling {

	static String getFilePath(String filePath) {
		File file = new File(filePath);
		return file.getAbsolutePath();
	}
}
