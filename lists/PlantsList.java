package tp.p3.lists;

import tp.p3.logic.objects.Plant;

public class PlantsList {
	private Plant[] plantList;
	private int contador;
	
	public static final int MAX = 32;

	public PlantsList() {
		plantList = new Plant[MAX];
		contador = 0;
	}

	public void insertarElemento(Plant plant) {
		plantList[contador] = plant;
		contador++;
	}

	public void eliminarElemento(Plant plant) {
		boolean encontrado = false;
		int i = 0;
		
		while (i < contador && !encontrado) {
			if (plantList[i] == plant) {
				encontrado = true;
				
				// Desplazamiento a la izquierda
				for (int j = i; j < contador -1; j++) {
					plantList[j] = plantList[j + 1];
				}
				
				contador--;
			}
			else {
				i++;
			}
		}
	}

	public Plant getPlant(int i) {
		return plantList[i];
	}

	public Plant getPlant(int row, int column) {
		int i = 0;
		boolean found = false;
		Plant plant = null;

		while (i < contador && !found) {
			plant = plantList[i];
			found = plant.getX() == row && plant.getY() == column;

			if (!found) {
				i++;
			}
		}

		if (!found) {
			plant = null;
		}

		return plant;
	}

	public int getSize() {
		return contador;
	}
	
	public PlantsList clone() {
		PlantsList lista = new PlantsList();
		
		for (int i = 0; i < contador; i++) {
			lista.insertarElemento(this.plantList[i].clone());
		}
		
		lista.contador = this.contador;
		
		return lista;
	}
	
	public String externalise() {
		StringBuilder str = new StringBuilder();
		String separator;
		
		for (int i = 0; i < contador; i++) {
			separator = i == contador - 1 ? "" : ", ";
			str.append(plantList[i].externalise() + separator);
		}
		
		return str.toString();
	}
}
