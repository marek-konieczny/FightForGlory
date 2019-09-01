package com.fight.gui;

import com.fight.gameFlow.AutoHeal;
import com.fight.gameFlow.GameStatus;


public class Display {

	public static void fillMapWithDots(int i, int j, char[][] map) {
		for (int k = 0; k < i; k++) {
			for (int l = 0; l < j; l++) {
				map[k][l] = '.';
			}
		}
	}

	public static void printMap(GameStatus gameStatus) {
		for (int k = 0; k < gameStatus.getHightYAxis(); k++) {
			for (int l = 0; l < gameStatus.getWidthXAxis(); l++) {
				System.out.print(gameStatus.getMap()[k][l]);
			}
			System.out.println();
		}
		displayNavigation();
	}

	public static void placeHero(int i, int j, char[][] map) {
		map[i][j] = 'X';
	}

	public static void placeEmptyFieldOnMap(int i, int j, char[][] map) {
		map[i][j] = '.';
	}

	public static void placeMonter(int i, int j, char[][] map) {
		map[i][j] = 'M';
	}

	public static void createBorder(int i, int j, char[][] map) {
		for (int k = 0; k < i; k++) {
			for (int l = 0; l < j; l++) {
				if (k == 0 || k == i - 1 || l == 0 || l == j - 1) {
					map[k][l] = '*';
				}
			}
		}
	}


	public static void displayGameStatus(GameStatus gameStatus) {
		ColorPrinting.printToConsoleWithColor(ConsoleColors.PURPLE_BRIGHT, gameStatus.toString());

	}

	private static void displayNavigation() {
		ColorPrinting.printToConsoleWithColor(ConsoleColors.PURPLE_BRIGHT,
				"To move your hero use W, A, S, D keys.");
		ColorPrinting.printToConsoleWithColor(ConsoleColors.BLUE,
				"To save your game use o key.");
		ColorPrinting.printToConsoleWithColor(ConsoleColors.BLUE,
				"To load your game use p key.");
		ColorPrinting.printToConsoleWithColor(ConsoleColors.RED_BOLD,
				"To exit press x.");
	}

	public static void displayMapButNotOnExit(char input, GameStatus gameStatus) {
		if (input != 'x') {
			printMap(gameStatus);
			AutoHeal.healCharacter(gameStatus);
			displayGameStatus(gameStatus);
		}
	}

	public static void displayIntro() {
		ColorPrinting.printToConsoleWithColor(ConsoleColors.CYAN, "Let the story begin.");
		ColorPrinting
				.printToConsoleWithColor(ConsoleColors.CYAN, "Your hero will be displayed on map as 'X'");
		ColorPrinting.printToConsoleWithColor(ConsoleColors.CYAN, "Monsters are marked as 'M'");
		ColorPrinting.printToConsoleWithColor(ConsoleColors.CYAN,
				"Fight enemies and gain experience.");
		ColorPrinting.printToConsoleWithColor(ConsoleColors.CYAN,
				"Exploring the map will regenerate your health.");
		ColorPrinting
				.printToConsoleWithColor(ConsoleColors.PURPLE_BOLD, "To start new game press 1.");
		ColorPrinting
				.printToConsoleWithColor(ConsoleColors.PURPLE_BOLD, "To load game press 2.");
	}
}
