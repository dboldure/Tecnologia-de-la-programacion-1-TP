package tp.p3.logic.objects;

import tp.p3.logic.Game;

public abstract class GameObject {
	
	private int x, y;
	private int resistance, damage, remainingCycles, cycles;
	private String name, shortName;
	protected Game game;
	
	public GameObject(int resistance, int damage, int cycles, String name, String shortName) {
		this.resistance = resistance;
		this.damage = damage;
		this.remainingCycles = cycles;
		this.cycles = cycles;
		this.name = name;
		this.shortName = shortName;
	}
	
	public void update() {
		removeCycles();
		
		if (getRemainingCycles() == 0) {
			execute();
			setRemainingCycles(getCycles());
		}
	}
	
	public abstract void execute();
	public abstract void damage(GameObject object);
	
	public String debugInfo() {
		return shortName + "[l:" + resistance + ",x:" + x + ",y:" + y
				+ ",t:" + remainingCycles + "]";
	}
	
	public String toString() {
		return shortName + " [" + resistance + "]";
	}
	
	public String externalise() {
		return shortName + ":" + resistance + ":" + x + ":" + y + ":" + remainingCycles;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getResistance() {
		return resistance;
	}
	
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getDamage() {
		return damage;
	}

	public int getCycles() {
		return cycles;
	}
	
	public int getRemainingCycles() {
		return remainingCycles;
	}

	public void setRemainingCycles(int remainingCycles) {
		this.remainingCycles = remainingCycles;
	}

	public void removeCycles() {
		remainingCycles--;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
