package tp.p3.managers;

import java.util.Random;

import tp.p3.logic.Level;

public class ZombieManager {
	private int numZombies;
	private Level nivel;
	private Random numero;

	public ZombieManager(Level nivel, Random numero) {
		this.nivel = nivel;
		this.numZombies = nivel.getNumZombies();
		this.numero = numero;
	}
	
	public ZombieManager(Level nivel, Random numero, int remZombies) {
		this.nivel = nivel;
		this.numZombies = remZombies;
		this.numero = numero;
	}

	/*
	 * Devuelve true si hay que añadir un Zombie al tablero, false en caso contrario
	 */
	public boolean isZombieAdded() {
		int aleatorio;
		boolean added = false;


		if (numZombies != 0) {
			aleatorio = numero.nextInt(9) + 1;

			if (aleatorio >= (1 - nivel.getFrecuencia()) * 10) {
				added = true;
			} else {
				added = false;
			}
		}

		return added;
	}

	/* Decrementa el n�mero de Zombies que quedan por a�adir */
	public void addZombie() {
		numZombies--;
	}

	/* Devielve el numero de Zombies que quedan por añadir */
	public int getRemainingZombies() {
		return this.numZombies;
	}
	
	public void setRemainingZombies(int remZombies) {
		this.numZombies = remZombies;
	}
	
	public ZombieManager clone() {
		return new ZombieManager(this.nivel, this.numero, this.numZombies);
	}

	public String toString() {
		return Integer.toString(this.numZombies);
	}
}
