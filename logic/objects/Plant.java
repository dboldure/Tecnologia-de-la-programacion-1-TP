package tp.p3.logic.objects;

import tp.p3.logic.objects.factories.PlantFactory;

public abstract class Plant extends GameObject {

	private int cost, frequency;
	
	public Plant(int resistance, int frequency, int damage, int cycles, int cost,
				 String name, String shortName) {
		super(resistance, damage, cycles, name, shortName);
		this.cost = cost;
		this.frequency = frequency;
	}
	
	public abstract Plant parse(String word);
	
	public void damage(GameObject object) {
		setResistance(getResistance() - object.getDamage());

		if (getResistance() <= 0) {
			this.game.removePlant(this, getX(), getY());
		}
	}
	
	public Plant clone() {
		Plant plant = PlantFactory.getPlant(this.getName());
		
		plant.cost = this.cost;
		plant.frequency = this.frequency;
		plant.setGame(this.game);
		plant.setRemainingCycles(this.getRemainingCycles());
		plant.setResistance(this.getResistance());
		plant.setX(this.getX());
		plant.setY(this.getY());
		
		return plant;
	}

	public int getCost() {
		return cost;
	}

	public int getFrequency() {
		return frequency;
	}
	
}
