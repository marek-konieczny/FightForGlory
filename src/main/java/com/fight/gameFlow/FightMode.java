package com.fight.gameFlow;

import com.fight.gui.ColorPrinting;
import com.fight.gui.ConsoleColors;
import com.fight.gui.Display;
import com.fight.model.Enemy;
import com.fight.model.Knight;
import org.javatuples.Pair;

public abstract class FightMode {

	public static void checkIfInAggroZoneAndFight(GameStatus gameStatus) {
		gameStatus.getEnemies().forEach(enemy -> enemy.getAggroZone().forEach(coordinates -> {
			if (isHeroInAggroZone(gameStatus, coordinates)) {
				while (enemyAndHeroIsAlive(gameStatus, enemy)) {
					if (enemyIsAlive(enemy)) {
						fightEnemyUsingKnight(gameStatus, enemy);
					}
					if (enemyIsAlive(enemy)) {
						enemy.attack(gameStatus.getCharacter());
						endGameWhenHeroIsDead(gameStatus);
					}
				}
			}
		}));
		clearMapAndRemoveMonster(gameStatus);
	}

	private static void endGameWhenHeroIsDead(GameStatus gameStatus) {
		if (heroIsDead(gameStatus)) {
			ColorPrinting.printToConsoleWithColor(ConsoleColors.YELLOW, "GameOver");
			gameStatus.setRunning(false);
		}
	}

	private static boolean heroIsDead(GameStatus gameStatus) {
		return gameStatus.getCharacter().getHealth() <= 0;
	}

	private static boolean enemyAndHeroIsAlive(GameStatus gameStatus, Enemy enemy) {
		return enemy.getHealth() > 0 && gameStatus.getCharacter().getHealth() > 0;
	}

	public static void manageHeroLevel(GameStatus gameStatus) {
		switch (gameStatus.getCharacter().getExperience().stream().mapToInt(Integer::intValue).sum()) {
			case 10:
				setLevel(gameStatus, 1);
				break;
			case 30:
				setLevel(gameStatus, 2);
				break;
			case 60:
				setLevel(gameStatus, 2);
				break;
			default:
				break;
		}
	}

	private static void setLevel(GameStatus gameStatus, int levelToVerify) {
		if (gameStatus.getCharacter().getLevel() == levelToVerify) {
			gameStatus.getCharacter().setLevel(levelToVerify + 1);
			ColorPrinting.printToConsoleWithColor(ConsoleColors.YELLOW_BOLD,
					"You reached level " + (levelToVerify + 1));
			ColorPrinting.printToConsoleWithColor(ConsoleColors.YELLOW_BOLD,
					"Now you're hero will deal more damage.");
		}
	}

	private static void clearMapAndRemoveMonster(GameStatus gameStatus) {
		gameStatus.getEnemies().forEach(enemy -> {
			if (enemy.getHealth() <= 0) {
				Display.placeEmptyFieldOnMap(enemy.getHeroLocation().getValue0(),
						enemy.getHeroLocation().getValue1(), gameStatus.getMap());
			}
		});
		gameStatus.getEnemies().removeIf(enemy -> enemy.getHealth() <= 0);
	}

	private static boolean enemyIsAlive(Enemy enemy) {
		return !(enemy.getHealth() <= 0);
	}

	//when more hero types will be added have to be adjusted to dynamic
	private static void fightEnemyUsingKnight(GameStatus gameStatus, Enemy enemy) {
		if (gameStatus.getCharacter().getClass().equals(Knight.class)) {
			Knight knight = (Knight) gameStatus.getCharacter();
			knight.attackMonster(enemy);
			if (!enemyIsAlive(enemy)) {
				addExperience(knight);
				ColorPrinting.printToConsoleWithColor(ConsoleColors.YELLOW, "You killed the monster.");
			}
		}
	}

	private static void addExperience(Knight knight) {
		knight.getExperience().add(10);
	}

	private static boolean isHeroInAggroZone(GameStatus gameStatus,
			Pair<Integer, Integer> coordinates) {
		return (coordinates.getValue0() == (gameStatus.getCharacter().getHeroLocation().getValue0()))
				&&
				(coordinates.getValue1() == (gameStatus.getCharacter().getHeroLocation()
						.getValue1()));
	}

}
