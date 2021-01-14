package tp.p3.logic.printers;

import tp.p3.lists.PlantsList;
import tp.p3.lists.ZombieList;
import tp.p3.logic.Game;

public class DebugPrinter extends BoardPrinter implements GamePrinter {
	
	public final static int CELL_SIZE = 20;

	public DebugPrinter(int dimX, int dimY) {
		super(dimX, dimY);
	}

	@Override
	public void encodeGame(Game game) {
		dimY = game.getNumGameObjects();
		
		board = new String[dimX][dimY];
		int i,  contador = 0;
		PlantsList plantsList = game.getPlantsList();
		ZombieList zombieList = game.getZombieList();
		
		for (i = 0; i < plantsList.getSize(); i++) {
			board[0][i] = plantsList.getPlant(i).debugInfo();
			contador++;
		}
		
		for (int j = 0; j < zombieList.getSize(); j++) {
			board[0][contador] = zombieList.getZombie(j).debugInfo();
			contador++;
		}
	}


	@Override
	public String printGame(Game game) {
		encodeGame(game);
		
		return boardToString(CELL_SIZE);
	}

}
