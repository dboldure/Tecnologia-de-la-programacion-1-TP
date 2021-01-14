package tp.p3.logic.objects.factories;

import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.plants.*;

public class PlantFactory {

	private static Plant[] availablePlants = {
		new Nuez(),
		new Peashooter(),
		new Petacereza(),
		new Sunflower(),
		new SnowPeashooter(),
		new HypnoShroom(),
		new PotatoMine()
	};
	
	public static Plant getPlant(String plantName) {
		for (Plant plant : availablePlants) {
			
			Plant parsedPlant = plant.parse(plantName);
			
			if (parsedPlant != null) {
				return parsedPlant;
			}
		}
		return null;
	}
	
	public static String listOfAvailablePlants() {
		StringBuilder str = new StringBuilder();
		
		for (Plant plant : availablePlants) {
			str.append(plant.getName() + ": Cost " + plant.getCost() + " suncoins  " 
					   + "Harm: " + plant.getDamage() + System.lineSeparator());
		}
		
		return str.toString();
	}
}
