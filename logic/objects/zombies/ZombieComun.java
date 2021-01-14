package tp.p3.logic.objects.zombies;

import tp.p3.logic.objects.Zombie;

public class ZombieComun extends Zombie {
	
	public final static int VELOCIDAD = 1;
	public final static int CICLOS = 2;
	public final static int RESISTENCIA = 5;
	public final static int DANYO = 1;

	public final static String NAME = "Zombie Común";
	public final static String SHORT_NAME = "Z";

	public ZombieComun() {
		super(RESISTENCIA, DANYO, CICLOS, NAME, SHORT_NAME);
	}

	
	@Override
	public Zombie parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new ZombieComun() : null;
	}
	
}
