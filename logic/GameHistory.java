package tp.p3.logic;

import java.util.ArrayList;

import tp.p3.lists.PlantsList;
import tp.p3.lists.ZombieList;
import tp.p3.logic.objects.GameObject;

public class GameHistory {
	private GameObject[][] board;
	private PlantsList plantsList;
	private ZombieList zombieList;
	private int cycles;
	private int remainingZombies;
	private int suncoins;
	private Level level;
	
	public GameHistory(GameObject[][] board, PlantsList plantsList, ZombieList zombieList, int cycles,
			int remainingZombies, int suncoins, Level level) {
		this.board = board;
		this.plantsList = plantsList;
		this.zombieList = zombieList;
		this.cycles = cycles;
		this.remainingZombies = remainingZombies;
		this.suncoins = suncoins;
		this.level = level;
	}

	public GameObject[][] getBoard() {
		return board;
	}

	public PlantsList getPlantsList() {
		return plantsList;
	}

	public ZombieList getZombieList() {
		return zombieList;
	}

	public int getCycles() {
		return cycles;
	}

	public int getRemainingZombies() {
		return remainingZombies;
	}

	public int getSuncoins() {
		return suncoins;
	}

	public Level getLevel() {
		return level;
	}
	
	
}
