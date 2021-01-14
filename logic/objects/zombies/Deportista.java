package tp.p3.logic.objects.zombies;

import tp.p3.logic.objects.Zombie;

public class Deportista extends Zombie {
	
	public final static int VELOCIDAD = 1;
	public final static int CICLOS = 1;
	public final static int RESISTENCIA = 2;
	public final static int DANYO = 1;
	
	public final static String NAME = "Deportista";
	public final static String SHORT_NAME = "X";

	public Deportista() {
		super(RESISTENCIA, DANYO, CICLOS, NAME, SHORT_NAME);
	}
	
	
	@Override
	public Zombie parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new Deportista() : null;
	}

}
