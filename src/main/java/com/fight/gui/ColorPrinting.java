package com.fight.gui;

public class ColorPrinting {

	public static void printToConsoleWithColor(String consoleColors, String text) {
		String system = System.getProperty("os.name");
		if (system.contains("Windows")) {
			System.out.println(text);
		} else if (system.contains("Unix")) {
			System.out.println(consoleColors + text + ConsoleColors.RESET);
		}

	}
}
