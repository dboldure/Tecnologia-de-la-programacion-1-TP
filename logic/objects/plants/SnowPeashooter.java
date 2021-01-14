package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;

public class SnowPeashooter extends ShooterPlant {
	public final static int COSTE = 175;
	public final static int FRECUENCIA = 1;
	public final static int CICLOS = 1;
	public final static int RESISTENCIA = 10;
	public final static int DANYO = 3;
	
	public final static String NAME = "[SP]eashooter";
	public final static String SHORT_NAME = "SP";

	public SnowPeashooter() {
		super(RESISTENCIA, FRECUENCIA, DANYO, CICLOS, COSTE, NAME, SHORT_NAME);
	}

	@Override
	public void shoot(Zombie zombie) {
		zombie.freeze();
		zombie.damage(this);
	}

	@Override
	public Plant parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new SnowPeashooter() : null;
	}

}
