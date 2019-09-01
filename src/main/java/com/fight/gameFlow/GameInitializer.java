package com.fight.gameFlow;

import com.fight.gui.Display;
import com.fight.model.Enemy;
import java.util.ArrayList;
import org.javatuples.Pair;

public class GameInitializer {

	public static void initGame(GameStatus gameStatus) {
		Display.fillMapWithDots(gameStatus.getHightYAxis(), gameStatus.getWidthXAxis(),
				gameStatus.getMap());
		Display.placeHero(gameStatus.getCharacter().getHeroLocation().getValue0(),
				gameStatus.getCharacter().getHeroLocation().getValue1(), gameStatus.getMap());
		putMonstersOnMap(gameStatus);
		Display
				.createBorder(gameStatus.getHightYAxis(), gameStatus.getWidthXAxis(), gameStatus.getMap());
	}

	private static void putMonstersOnMap(GameStatus gameStatus) {

		gameStatus.getEnemies().add(createEnemy(5, 10, "Ogr", 5));
		gameStatus.getEnemies().add(createEnemy(13, 25, "Dwarf", 2));
		gameStatus.getEnemies().add(createEnemy(10, 8, "Troll", 10));
		gameStatus.getEnemies().add(createEnemy(1, 1, "Zerling", 1));
		gameStatus.getEnemies().add(createEnemy(1, 20, "Zerling", 1));
		gameStatus.getEnemies().add(createEnemy(4, 30, "Zerling", 1));
		gameStatus.getEnemies().add(createEnemy(9, 35, "Zerling", 1));
		gameStatus.getEnemies().add(createEnemy(5, 35, "Zerling", 1));
		gameStatus.getEnemies().add(createEnemy(18, 25, "Zerling", 1));
		gameStatus.getEnemies().add(createEnemy(2, 22, "Zerling", 1));
		gameStatus.getEnemies().add(createEnemy(14, 13, "Gremlin", 3));
		gameStatus.getEnemies().add(createEnemy(7, 7, "Gremlin", 3));
		gameStatus.getEnemies()
				.forEach(objects -> {
					Display.placeMonter(objects.getHeroLocation().getValue0(),
							objects.getHeroLocation().getValue1(), gameStatus.getMap());
				});
	}

	private static Enemy createEnemy(int x, int y, String name, int strength) {
		Integer health = 100;
		Enemy enemy = new Enemy(health, Pair.with(x, y), name, strength, new ArrayList<>(), 1);
		enemy.createAggroZone();
		return enemy;
	}
}
