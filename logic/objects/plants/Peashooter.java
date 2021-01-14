package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;

public class Peashooter extends ShooterPlant {
	public final static int COSTE = 50;
	public final static int CICLOS = 1;
	public final static int FRECUENCIA = 1;
	public final static int DANYO = 1;
	public final static int RESISTENCIA = 3;
	
	public final static String NAME = "[P]eashooter";
	public final static String SHORT_NAME = "P";

	public Peashooter() {
		super(RESISTENCIA, FRECUENCIA, DANYO, CICLOS, COSTE, NAME, SHORT_NAME);
	}
	
	public void shoot(Zombie zombie) {
		zombie.damage(this);
	}

	@Override
	public Plant parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new Peashooter() : null;
	}

}
