package tp.p3.lists;

import tp.p3.logic.objects.Zombie;

public class ZombieList {
	private Zombie[] zombieList;
	private int contador;
	
	public static final int MAX = 10;

	public ZombieList() {
		zombieList = new Zombie[MAX];
	}

	/* Añade un nuevo Zombie a la lista */
	public void insertarElemento(Zombie zombie) {
		zombieList[contador] = zombie;
		contador++;
	}

	/* Elimina un Zombie de la lista */
	public void eliminarElemento(Zombie zombie) {
		boolean encontrado = false;
		int i = 0;
		
		while (i < contador && !encontrado) {
			if (zombieList[i] == zombie) {
				encontrado = true;
				
				// Desplazamiento a la izquierda
				for (int j = i; j < contador -1; j++) {
					zombieList[j] = zombieList[j + 1];
				}
				
				contador--;
			}
			else {
				i++;
			}
		}
	}

	/* Retorna el Zombie que se encuentra en la posicion i */
	public Zombie getZombie(int i) {
		return zombieList[i];
	}

	/* Retorna el Zombie que se encuentra en la fila row y la columna column */
	public Zombie getZombie(int row, int column) {
		int i = 0;
		boolean found = false;
		Zombie zombie = null;

		while (i < contador && !found) {
			zombie = this.zombieList[i];
			found = zombie.getX() == row && zombie.getY() == column;

			if (!found) {
				i++;
			}
		}

		if (!found) {
			zombie = null;
		}

		return zombie;
	}

	/* Obtiene el numero de Zombies que hay en la lista */
	public int getSize() {
		return contador;
	}
	
	public ZombieList clone() {
		ZombieList lista = new ZombieList();
		
		for (int i = 0; i < contador; i++) {
			lista.insertarElemento(this.zombieList[i].clone());
		}
		
		lista.contador = this.contador;
		
		return lista;
	}
	
	public String externalise() {
		StringBuilder str = new StringBuilder();
		String separator;
		
		for (int i = 0; i < contador; i++) {
			separator = i == contador - 1 ? "" : ", ";
			str.append(zombieList[i].externalise() + separator);
		}
		
		return str.toString();
	}
}
