package com.fight.gameFlow;

import com.fight.model.Character;
import com.fight.model.Enemy;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GameStatus implements Serializable {

	private static final long serialVersionUID = -6777241957381984454L;
	private int hightYAxis;
	private int widthXAxis;
	private List<Enemy> enemies;
	private Character character;
	private char[][] map;
	private boolean isRunning;

	public GameStatus(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public String toString() {
		return "GameStatus { " +
				"character = " + character +
				'}';
	}
}
