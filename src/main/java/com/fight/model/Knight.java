package com.fight.model;

import com.fight.errorHnadling.ErrorCode;
import com.fight.errorHnadling.GameExceptions;
import com.fight.gameFlow.GameStatus;
import com.fight.gui.ColorPrinting;
import com.fight.gui.ConsoleColors;
import com.fight.gui.Display;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.log4j.Logger;
import org.javatuples.Pair;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Knight extends Character {

	private static final long serialVersionUID = -2408466718973727483L;

	final static Logger logger = Logger.getLogger(Knight.class);

	public Knight(Integer health,
			Pair<Integer, Integer> heroLocation, String name, Integer strength,
			List<Integer> experience, Integer level) {
		super(health, heroLocation, name, strength, experience, level);
	}

	public void attackMonster(Character character) {
		ColorPrinting.printToConsoleWithColor(ConsoleColors.RED_BOLD,
				"Hit monster with damage " + attackDamage());
		character.setHealth(character.getHealth() - (attackDamage()));
	}

	private int attackDamage() {
		return getStrength() + getLevel() * 2;
	}

	public void goLeft(GameStatus gameStatus) {
		Integer xCoordinate = getHeroLocation().getValue1();

		if (xCoordinate - 1 != 0) {
			clearOldHeroLocation(gameStatus.getMap());
			setHeroLocation(Pair.with(getHeroLocation().getValue0(), (xCoordinate - 1)));

			Display.placeHero(gameStatus.getCharacter().getHeroLocation().getValue0(),
					gameStatus.getCharacter().getHeroLocation().getValue1(), gameStatus.getMap());
		} else {
			logger.info(new GameExceptions(ErrorCode.WALK_ONLY_ON_MAP, "Cannot go outside map.", null)
					.toString());
		}
	}


	public void goRight(GameStatus gameStatus) {
		Integer xCoordinate = getHeroLocation().getValue1();
		if (xCoordinate + 1 < gameStatus.getWidthXAxis() - 1) {
			clearOldHeroLocation(gameStatus.getMap());
			setHeroLocation(Pair.with(getHeroLocation().getValue0(), (xCoordinate + 1)));
			Display.placeHero(gameStatus.getCharacter().getHeroLocation().getValue0(),
					gameStatus.getCharacter().getHeroLocation().getValue1(), gameStatus.getMap());
		} else {
			logger.info(new GameExceptions(ErrorCode.WALK_ONLY_ON_MAP, "Cannot go outside map.", null)
					.toString());
		}
	}


	public void goForward(GameStatus gameStatus) {
		Integer yCoordinate = getHeroLocation().getValue0();
		if (yCoordinate - 1 != 0) {
			clearOldHeroLocation(gameStatus.getMap());
			setHeroLocation(Pair.with(yCoordinate - 1, getHeroLocation().getValue1()));
			Display.placeHero(gameStatus.getCharacter().getHeroLocation().getValue0(),
					gameStatus.getCharacter().getHeroLocation().getValue1(), gameStatus.getMap());
		} else {
			logger.info(new GameExceptions(ErrorCode.WALK_ONLY_ON_MAP, "Cannot go outside map.", null)
					.toString());
		}

	}

	public void goBackward(GameStatus gameStatus) {
		Integer yCoordinate = getHeroLocation().getValue0();
		if (yCoordinate + 1 < gameStatus.getHightYAxis() - 1) {
			clearOldHeroLocation(gameStatus.getMap());
			setHeroLocation(Pair.with(yCoordinate + 1, getHeroLocation().getValue1()));
			Display.placeHero(gameStatus.getCharacter().getHeroLocation().getValue0(),
					gameStatus.getCharacter().getHeroLocation().getValue1(), gameStatus.getMap());
		} else {
			logger.info(new GameExceptions(ErrorCode.WALK_ONLY_ON_MAP, "Cannot go outside map.", null)
					.toString());
		}

	}

	private void clearOldHeroLocation(char[][] map) {
		map[getHeroLocation().getValue0()][getHeroLocation().getValue1()] = '.';
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
