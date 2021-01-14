package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;

public class PotatoMine extends ExplosivePlant {
	public final static int COSTE = 75;
	public final static int RESISTENCIA = 15;
	public final static int DANYO = 10;
	public final static int CICLOS = 1;
	
	public final static String NAME = "[PO]tato Mine";
	public final static String SHORT_NAME = "PO";
	
	private int cuentaAtras;

	public PotatoMine() {
		super(RESISTENCIA, DANYO, CICLOS, COSTE, NAME, SHORT_NAME);
		cuentaAtras = 3;
	}
	
	public void execute() {
		cuentaAtras--;
		if (cuentaAtras == 0) {
			super.execute();
		}
	}

	@Override
	public Plant parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new PotatoMine() : null;
	}
	
	public void removeCycles() {
		Zombie zombie = game.getZombie(getX(), getY() + 1);
		if (zombie != null) {
			setRemainingCycles(getRemainingCycles() - 1);
		}
	}

}
