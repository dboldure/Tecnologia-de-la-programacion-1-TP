package tp.p3.managers;

public class SuncoinManager {
	private int numSuncoins;

	public SuncoinManager() {
		numSuncoins = 1000;
	}

	/* Incrementa el numero de Suncoins */
	public void addSuncoins(int suncoins) {
		numSuncoins += suncoins;
	}

	/* Consume suncoins */
	public void useSuncoins(int suncoins) {
		numSuncoins -= suncoins;
	}

	/* Devuelve true si hay suficientes suncoins para consumir */
	public boolean enoughSuncoins(int suncoins) {
		return (this.numSuncoins - suncoins) >= 0;
	}
	
	public void setSuncoins(int suncoins) {
		this.numSuncoins = suncoins;
	}
	
	public SuncoinManager clone() {
		SuncoinManager sm = new SuncoinManager();
		sm.setSuncoins(this.numSuncoins);
		
		return sm;
	}
	
	public int getSuncoins() {
		return this.numSuncoins;
	}

	public String toString() {
		return Integer.toString(this.numSuncoins);
	}
}
