package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;

public class Nuez extends PassivePlant {
	public final static int COSTE = 50;	
	public final static int RESISTENCIA = 10;
	
	public final static String NAME = "[N]uez";
	public final static String SHORT_NAME = "N";
	
	public Nuez() {		
		super(RESISTENCIA, COSTE, NAME, SHORT_NAME);
	}

	@Override
	public Plant parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new Nuez() : null;
	}

	@Override
	public void execute() { }
	
}
