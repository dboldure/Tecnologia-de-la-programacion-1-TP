package tp.p3.logic.objects.plants;

import tp.p3.logic.objects.GameObject;
import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;
import tp.p3.logic.objects.factories.ZombieFactory;

public class HypnoShroom extends PassivePlant {
	public final static int COSTE = 125;
	public final static int RESISTENCIA = 1;
	
	public final static String NAME = "[H]ypnoShroom";
	public final static String SHORT_NAME = "H";

	public HypnoShroom() {
		super(RESISTENCIA, COSTE, NAME, SHORT_NAME);
	}
	
	public void execute() {	}
	
	public void damage(GameObject object) {
		setResistance(getResistance() - object.getDamage());

		if (getResistance() <= 0) {		
			this.game.removePlant(this, getX(), getY());
			Zombie zombie = ZombieFactory.getZombie("IZ");
			zombie.setGame(game);
			zombie.setX(getX());
			zombie.setY(getY());
			this.game.addZombie(getX(), getY(), zombie);
		}
	}

	@Override
	public Plant parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new HypnoShroom() : null;
	}

}
