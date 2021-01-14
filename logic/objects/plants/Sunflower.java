package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;

public class Sunflower extends PassivePlant {
	public final static int COSTE = 20;
	public final static int NUM_SOLES = 10;
	public final static int CICLOS = 2;
	public final static int RESISTENCIA = 1;
	
	public final static String NAME = "[S]unflower";
	public final static String SHORT_NAME = "S";

	public Sunflower() {
		super(CICLOS, RESISTENCIA, COSTE, NAME, SHORT_NAME);
	}

	/*
	 * Incrementa el ciclo particular del elemeto y devuelve true si debe generar
	 * soles
	 */
	public void execute() {
		game.addSuncoins(NUM_SOLES);
	}

	@Override
	public Plant parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new Sunflower() : null;
	}
	
}
