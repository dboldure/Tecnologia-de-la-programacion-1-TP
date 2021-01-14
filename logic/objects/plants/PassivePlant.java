package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;

public abstract class PassivePlant extends Plant {
	public final static int CICLOS = 1;
	public final static int DANYO = 0;
	public final static int FRECUENCIA = 1;

	public PassivePlant(int resistance, int cost, String name, String shortName) {
		super(resistance, FRECUENCIA, DANYO, CICLOS, cost, name, shortName);
	}
	
	public PassivePlant(int cycles, int resistance, int cost, String name, String shortName) {
		super(resistance, FRECUENCIA, DANYO, cycles, cost, name, shortName);
	}

}
