package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;

public abstract class ExplosivePlant extends Plant {
	public final static int FRECUENCIA = 1;

	public ExplosivePlant(int resistance, int damage, int cycles, int cost, String name,
			String shortName) {
		super(resistance, FRECUENCIA, damage, cycles, cost, name, shortName);
	}
	
	public void execute() {
		int row = getX();
		int column = getY();
	
		Zombie zombie = null;
		
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				if (!(i == row && j == column)) {
					zombie = game.getZombie(i, j);
					if (zombie != null) {
						zombie.damage(this);
					}
				}
					
			}
		}
	}

}
