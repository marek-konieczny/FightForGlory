package com.fight.gameFlow;

import com.fight.errorHnadling.ErrorCode;
import com.fight.errorHnadling.GameExceptions;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;
import org.apache.log4j.Logger;

public class Save {

	private final static Logger logger = Logger.getLogger(Save.class);
	private static final String FILE_PATH = "src\\main\\resources\\save";

	public static void witeObjectToFile(GameStatus gameStatus) {

		try {

			FileOutputStream fileOut = new FileOutputStream(FileHandling.getFilePath(FILE_PATH));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(gameStatus);
			objectOut.close();
			System.out.println("Game was succesfully saved.");
			logger.info("Game was succesfully saved.");

		} catch (IOException ex) {
			logger
					.info(new GameExceptions(ErrorCode.SAVING_FAILED, "Game saving failed.", Optional.of(ex))
							.toString());
		}
	}

}
