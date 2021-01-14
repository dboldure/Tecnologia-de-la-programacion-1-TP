package tp.p3.logic.objects.factories;

import java.util.Random;

import tp.p3.logic.objects.Zombie;
import tp.p3.logic.objects.zombies.*;

public class ZombieFactory {
	
	private static Zombie[] availableZombies = {
			new Caracubo(),
			new Deportista(),
			new ZombieComun(),
			new InfectedZombie()
	};
	
	public static Zombie getZombie() {
		int zombie = new Random().nextInt(availableZombies.length - 1);
		
		return availableZombies[zombie].clone();
	}
	
	public static Zombie getZombie(String zombieName) {
		for (Zombie zombie : availableZombies) {
			
			Zombie parsedZombie = zombie.parse(zombieName);
			
			if (parsedZombie != null) {
				return parsedZombie;
			}
		}
		return null;
	}
	
	public static String listOfAvailableZombies() {
		StringBuilder str = new StringBuilder();
		
		for (Zombie zombie : availableZombies) {
			str.append(zombie.getName() + ": speed " + zombie.getCycles()
					   + "Harm: " + zombie.getDamage() 
					   + " Life: " + zombie.getResistance() + System.lineSeparator());
		}
		
		return str.toString();
	}
}
