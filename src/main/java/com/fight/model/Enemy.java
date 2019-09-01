package com.fight.model;

import com.fight.gui.ColorPrinting;
import com.fight.gui.ConsoleColors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.javatuples.Pair;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Enemy extends Character implements Serializable {

	@Override
	public String toString() {
		return "Enemy{" +
				"location= " + getHeroLocation() +
				"health= " + getHealth() +
				"strenght= " + getStrength() +
				'}';
	}

	List<Pair<Integer, Integer>> aggroZone;

	public Enemy(Integer health, Pair<Integer, Integer> heroLocation, String name,
			Integer strength, List<Integer> experience, Integer level) {
		super(health, heroLocation, name, strength, experience, level);
	}

	private static final long serialVersionUID = -2408466718973727533L;


	public void attack(Character character) {
		ColorPrinting.printToConsoleWithColor(ConsoleColors.RED,
				"He's trying to kill you creating " + getStrength() + " damage");
		character.setHealth(character.getHealth() - getStrength());
	}

	public Enemy createAggroZone() {
		setAggroZone(new ArrayList<>());
		getAggroZone().add(getHeroLocation());
		addAggroField(getHeroLocation().getValue0(), getHeroLocation().getValue1() + 1);
		addAggroField(getHeroLocation().getValue0(), getHeroLocation().getValue1() - 1);
		addAggroField(getHeroLocation().getValue0() - 1, getHeroLocation().getValue1());
		addAggroField(getHeroLocation().getValue0() - 1, getHeroLocation().getValue1() - 1);
		addAggroField(getHeroLocation().getValue0() - 1, getHeroLocation().getValue1() + 1);
		addAggroField(getHeroLocation().getValue0() + 1, getHeroLocation().getValue1());
		addAggroField(getHeroLocation().getValue0() + 1, getHeroLocation().getValue1() - 1);
		addAggroField(getHeroLocation().getValue0() + 1, getHeroLocation().getValue1() + 1);
		return this;
	}

	private void addAggroField(Integer value0, int i) {
		getAggroZone()
				.add(Pair.with(value0, i));
	}

	@Override
	public void attackMonster(Character character) {
		//TBD
	}
}
