package tp.p3.logic;

import java.lang.reflect.Array;
import java.util.Scanner;

public enum Level {
	EASY(3, 0.1), HARD(5, 0.2), INSANE(10, 0.3), CUSTOM(), BLITZ(3, 0.1);

	private int numZombies;
	private double frecuencia;

	Level(int numZombies, double frecuencia) {
		this.numZombies = numZombies;
		this.frecuencia = frecuencia;
	}
	
	Level() {
		this.numZombies = 0;
		this.frecuencia = 0;
	}

	public int getNumZombies() {
		return this.numZombies;
	}

	public double getFrecuencia() {
		return this.frecuencia;
	}
	
	public static Level nextLevel(Level level) {	
		/*if (level.name().equals("INSANE"))
			return null;
		
		Level[] lvs = Level.values();		
		return lvs[level.ordinal() + 1];*/
		
		if (level.name().equals("BLITZ")) {
			Level lv = Level.BLITZ;
			lv.frecuencia += 0.1;
			System.out.println("Antes " + lv.numZombies);
			lv.numZombies *= 2;
			System.out.println("Despues " + lv.numZombies);
			
			return lv.numZombies > 12 ? null : lv;
		}
		return null;		
	}
	
	public static Level parse(String inputString) {
		for (Level level : Level. values() )
			if (level . name().equalsIgnoreCase(inputString)) return level;
			else if (new String("CUSTOM").equalsIgnoreCase(inputString)) return createLevel();
		return null;
	}
	
	public static Level createLevel() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Numero de zombies: ");
		int numZombies = in.nextInt();
		
		System.out.println("Frecuencia de aparicion: ");
		float frecuencia = in.nextFloat();
		
		Level level = Level.CUSTOM;
		level.numZombies = numZombies;
		level.frecuencia = frecuencia;
		
		return level;
	}
	
	public static String all(String separator) {
		StringBuilder sb = new StringBuilder();
		
		for (Level level : Level.values())
			sb.append(level.name() + separator);
		String allLevels = sb.toString();
		
		return allLevels.substring(0, allLevels.length() - separator.length());
	}
	
	public Level copy() {
		Level level = Level.parse(this.name());
		level.frecuencia = this.frecuencia;
		level.numZombies = this.numZombies;
		
		return level;
	}
}
