package com.fight.gameFlow;

import com.fight.errorHnadling.ErrorCode;
import com.fight.errorHnadling.GameExceptions;
import com.fight.gui.ColorPrinting;
import com.fight.gui.ConsoleColors;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;
import org.apache.log4j.Logger;

public class LoadGame {

	private final static Logger logger = Logger.getLogger(LoadGame.class);
	private static final String FILE_PATH = "src\\main\\resources\\save";

	public static GameStatus loadGame(GameStatus gameStatus) {

		try {

			FileInputStream fileIn = new FileInputStream(FILE_PATH);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			GameStatus gameStatusFromFile = (GameStatus) objectIn.readObject();
			gameStatus.setCharacter(gameStatusFromFile.getCharacter());
			gameStatus.setEnemies(gameStatusFromFile.getEnemies());
			gameStatus.setHightYAxis(gameStatusFromFile.getHightYAxis());
			gameStatus.setWidthXAxis(gameStatusFromFile.getWidthXAxis());
			gameStatus.setMap(gameStatusFromFile.getMap());
			gameStatus.setEnemies(gameStatusFromFile.getEnemies());
			gameStatus.setRunning(gameStatusFromFile.isRunning());
			objectIn.close();
			ColorPrinting.printToConsoleWithColor(ConsoleColors.YELLOW,
					"Game loaded.");
			return gameStatus;

		} catch (IOException | ClassNotFoundException ex) {
			logger.info(
					new GameExceptions(ErrorCode.LOADING_FAILED, "Game loading failed.", Optional.of(ex))
							.toString());
			return null;
		}
	}

}
