package com.fight;

import com.fight.gameFlow.FightMode;
import com.fight.gameFlow.GameInitializer;
import com.fight.gameFlow.GameStatus;
import com.fight.gameFlow.LoadGame;
import com.fight.gameFlow.Save;
import com.fight.gui.ColorPrinting;
import com.fight.gui.ConsoleColors;
import com.fight.gui.Display;
import com.fight.model.Knight;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.BasicConfigurator;
import org.javatuples.Pair;


public class Main {

	private final static int MAP_HIGHT_Y_AXIS = 20;
	private final static int MAP_WIDTH_X_AXIS = 45;
	private final static int HERO_Y = 2;
	private final static int HERO_X = 4;
	private final static Integer HERO_HEALTH = 100;
	private final static Integer HERO_STRENGHT = 5;

	public static void main(String[] args) {

		BasicConfigurator.configure();
		Display.displayIntro();
		Scanner scanner = new Scanner(System.in);
		Scanner scannerMenu = new Scanner(System.in);
		GameStatus gameStatus = new GameStatus(true);

		if (newGame(scannerMenu)) {
			gameStatus = getGameStatus(scanner);
		} else {
			gameStatus = LoadGame.loadGame(gameStatus);
		}
		GameInitializer.initGame(gameStatus);
		play(scanner, gameStatus);

	}

	private static boolean newGame(Scanner scannerMenu) {
		return scannerMenu.next().charAt(0) == '1';
	}

	private static void play(Scanner scanner, GameStatus gameStatus) {
		char input;
		Display.printMap(gameStatus);
		while (gameStatus.isRunning()) {
			switch (input = scanner.next().charAt(0)) {
				case 'a':
					gameStatus.getCharacter().goLeft(gameStatus);
					break;
				case 'd':
					gameStatus.getCharacter().goRight(gameStatus);
					break;
				case 'w':
					gameStatus.getCharacter().goForward(gameStatus);
					break;
				case 's':
					gameStatus.getCharacter().goBackward(gameStatus);
					break;
				case 'm':
					Display.displayGameStatus(gameStatus);
					break;
				case 'o':
					Save.witeObjectToFile(gameStatus);
					break;
				case 'p':
					System.out.println("Your game has been loaded.");
					LoadGame.loadGame(gameStatus);
					break;
				case 'x':
					System.out.println("Thank you for playing.");
					gameStatus.setRunning(false);
					break;

				default:
					System.out.println("Key " + input + " is not supported.");
					break;
			}
			FightMode.checkIfInAggroZoneAndFight(gameStatus);
			FightMode.manageHeroLevel(gameStatus);
			Display.displayMapButNotOnExit(input, gameStatus);
		}
	}

	private static GameStatus getGameStatus(Scanner scanner) {
		return GameStatus.builder()
				.map(new char[MAP_HIGHT_Y_AXIS][MAP_WIDTH_X_AXIS])
				.character(
						new Knight(HERO_HEALTH, Pair.with(HERO_Y, HERO_X), getHeroName(scanner), HERO_STRENGHT,
								new ArrayList<>(), 1))
				.hightYAxis(MAP_HIGHT_Y_AXIS)
				.widthXAxis(MAP_WIDTH_X_AXIS)
				.enemies(new ArrayList<>())
				.isRunning(true)
				.build();
	}

	private static String getHeroName(Scanner scanner) {
		ColorPrinting.printToConsoleWithColor(ConsoleColors.YELLOW, "Enter a hero name");
		String name;
		name = scanner.nextLine();
		return name;
	}

}
