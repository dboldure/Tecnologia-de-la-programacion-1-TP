package tp.p3.logic.objects.zombies;

import tp.p3.logic.objects.Zombie;

public class Caracubo extends Zombie {
	
	public final static int VELOCIDAD = 1;
	public final static int CICLOS = 4;
	public final static int RESISTENCIA = 8;
	public final static int DANYO = 1;

	public final static String NAME = "Caracubo";
	public final static String SHORT_NAME = "W";

	public Caracubo() {
		super(RESISTENCIA, DANYO, CICLOS, NAME, SHORT_NAME);
	}
	
	@Override
	public Zombie parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new Caracubo() : null;
	}

}
