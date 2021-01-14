package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;

public class Petacereza extends ExplosivePlant {
	public final static int COSTE = 50;
	public final static int CICLOS = 2;
	public final static int DANYO = 10;
	public final static int RESISTENCIA = 2;
	
	public final static String NAME = "Peta[c]ereza";
	public final static String SHORT_NAME = "C";

	public Petacereza() {
		super(RESISTENCIA, DANYO, CICLOS, COSTE, NAME, SHORT_NAME);
	}
	
	public void execute() {
		super.execute();
		this.game.removePlant(this, getX(), getY());
	}

	@Override
	public Plant parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new Petacereza() : null;
	}

}
