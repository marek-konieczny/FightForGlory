package com.fight.heroActions;

import com.fight.gameFlow.GameStatus;
import org.javatuples.Pair;

public interface Navigation {

	Pair<Integer, Integer> getHeroLocation();

	void goLeft(GameStatus gameStatus);

	void goRight(GameStatus gameStatus);

	void goForward(GameStatus gameStatus);

	void goBackward(GameStatus gameStatus);

}
