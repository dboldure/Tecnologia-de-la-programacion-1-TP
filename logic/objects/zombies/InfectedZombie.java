package tp.p3.logic.objects.zombies;

import tp.p3.logic.Game;
import tp.p3.logic.objects.GameObject;
import tp.p3.logic.objects.Zombie;
import tp.p3.logic.objects.factories.ZombieFactory;

public class InfectedZombie extends Zombie {
	
	public final static int VELOCIDAD = 1;
	public final static int CICLOS = 2;
	public final static int RESISTENCIA = 5;
	public final static int DANYO = 1;

	public final static String NAME = "Infected Zombie";
	public final static String SHORT_NAME = "IZ";

	public InfectedZombie() {
		super(RESISTENCIA, DANYO, CICLOS, NAME, SHORT_NAME);
	}
	
	public void execute() {
		 if (this.game.moveZombie(this)) {
			setY(getY() + 1);

			if (getY() == Game.NUM_COLUMNS) {
				setResistance(0);
			}
		}
		attack();
	}
	
	/* Ataca a una planta si es posible */
	public void attack() {
		Zombie zombie = null;

		int row = getX(), column = getY();
		zombie = game.getZombie(row, column + 1);

		if (zombie != null) {
			zombie.damage(this);
		}
	}
	

	/* Reduce la resistencia debido a un ataque recibido */
	public void damage(GameObject object) {
		
		if (ZombieFactory.getZombie(object.getName()) != null) { // ¿cómo mejorar esto?
			setResistance(getResistance() - object.getDamage());

			if (getResistance() <= 0) {
				game.removeZombie(this, getX(), getY());
			}
		}
	}

	@Override
	public Zombie parse(String word) {
		return word.equalsIgnoreCase(getName()) || word.equalsIgnoreCase(getShortName()) ? new InfectedZombie() : null;
	}
}
