package com.fight.model;

import com.fight.gameFlow.GameStatus;
import com.fight.heroActions.Attack;
import com.fight.heroActions.Navigation;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javatuples.Pair;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character implements Attack, Navigation, Serializable {

	private static final long serialVersionUID = -2408466712132312483L;

	private Integer health;
	private Pair<Integer, Integer> heroLocation;
	private String name;
	private Integer strength;
	private List<Integer> experience;
	private Integer level;

	@Override
	public void goLeft(GameStatus gameStatus) {

	}

	@Override
	public void goRight(GameStatus gameStatus) {

	}

	@Override
	public void goForward(GameStatus gameStatus) {

	}

	@Override
	public void goBackward(GameStatus gameStatus) {

	}

	@Override
	public void attack(Character character) {

	}

	@Override
	public void attackMonster(Character character) {

	}

	@Override
	public String toString() {
		return "Character{" +
				"health=" + health +
				", heroLocation=" + heroLocation +
				", name='" + name + '\'' +
				", strength=" + strength +
				", experience=" + experience +
				", level=" + level +
				'}';
	}

	public void addHeal(int heal) {
		setHealth(getHealth() + heal);
	}

}
