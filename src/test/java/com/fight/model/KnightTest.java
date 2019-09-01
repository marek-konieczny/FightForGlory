package com.fight.model;

import com.fight.gameFlow.GameStatus;
import java.util.ArrayList;
import org.javatuples.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class KnightTest {

	private final GameStatus gameStatus = GameStatus.builder().character(
			new Knight(50, Pair.with(5, 5), "Mario", 2,
					new ArrayList<>(), 1))
			.map(new char[40][40]).build();

	@BeforeAll
	static void setup() {
		System.out.println("Running navigation tests");
	}

	@Test
	void goLeft() {
		gameStatus.getCharacter().goLeft(gameStatus);
		Assertions.assertEquals(4, gameStatus.getCharacter().getHeroLocation().getValue1());
	}

	@Test
	void goRight() {
		gameStatus.getCharacter().goRight(gameStatus);
		Assertions.assertEquals(5, gameStatus.getCharacter().getHeroLocation().getValue1());
	}

	@Test
	void goForward() {
		gameStatus.getCharacter().goForward(gameStatus);
		Assertions.assertEquals(4, gameStatus.getCharacter().getHeroLocation().getValue0());
	}

	@Test
	void goBackward() {
		gameStatus.getCharacter().goBackward(gameStatus);
		Assertions.assertEquals(5, gameStatus.getCharacter().getHeroLocation().getValue0());
	}
}