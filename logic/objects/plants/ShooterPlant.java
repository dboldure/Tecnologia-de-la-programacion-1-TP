package tp.p3.logic.objects.plants;

import tp.p3.logic.Game;
import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;

public abstract class ShooterPlant extends Plant {

	public ShooterPlant(int resistance, int frequency, int damage, int cycles, int cost, String name,
			String shortName) {
		super(resistance, frequency, damage, cycles, cost, name, shortName);
	}
	
	public abstract void shoot(Zombie zombie);
	
	public void execute() {
		int row = getX();
		int column = getY() + 1;
		boolean found = false;
		Zombie zombie = null;

		/* Hay algun Zombie en la misma fila? */

		while (column < Game.NUM_COLUMNS && !found) {
			zombie = game.getZombie(row, column);
			
			if (zombie != null) {
				found = true;
			}
			else {
				column++;
			}
		}

		if (zombie != null) {
			shoot(zombie);
		}
	}

}
