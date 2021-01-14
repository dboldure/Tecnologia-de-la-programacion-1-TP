package tp.p3.logic.objects;

import tp.p3.logic.objects.factories.ZombieFactory;

public abstract class Zombie extends GameObject {

	public Zombie(int resistance, int damage, int cycles, String name, String shortName) {
		super(resistance, damage, cycles, name, shortName);
	}
	
	public Zombie clone() {
		Zombie zombie = ZombieFactory.getZombie(this.getName());
		
		zombie.setGame(game);
		zombie.setRemainingCycles(getRemainingCycles());
		zombie.setResistance(getResistance());
		zombie.setX(getX());
		zombie.setY(getY());
		
		return zombie;
		
	}
	public abstract Zombie parse(String word);
	
	public void execute() {
		if (this.game.moveZombie(this)) {
			setY(getY() - 1);

			if (getY() == 0) {
				game.setEnd(true);
			}
		}
		attack();
	}
	
	
	/* Reduce la resistencia debido a un ataque recibido */
	public void damage(GameObject object) {
		setResistance(getResistance() - object.getDamage());

		if (getResistance() <= 0) {
			game.removeZombie(this, getX(), getY());
		}
	}
	
	/* Ataca a una planta si es posible */
	public void attack() {
		Plant plant = null;

		int row = getX(), column = getY();
		plant = game.getPlant(row, column - 1);
		Zombie zombie = game.getZombie(row, column - 1);
		
		if (plant != null) {
			plant.damage(this);
		}
		else if (zombie != null && zombie.getName().equals("Infected Zombie")){
			zombie.damage(this);
		}
	}
	
	public void freeze() {
		setRemainingCycles(getRemainingCycles() * 2);
	}

}
