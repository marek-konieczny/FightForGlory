package com.fight.gameFlow;

import com.fight.gui.ColorPrinting;
import com.fight.gui.ConsoleColors;

public abstract class AutoHeal {

	public static void healCharacter(GameStatus gameStatus) {
		if (gameStatus.getCharacter().getHealth() < 100) {
			gameStatus.getCharacter().addHeal(1);
			ColorPrinting.printToConsoleWithColor(ConsoleColors.GREEN,
					"Hero health was regenerated.");
		}
	}

}
