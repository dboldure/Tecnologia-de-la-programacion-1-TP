package tp.p3.logic.printers;

import tp.p3.logic.Game;
import tp.p3.logic.objects.GameObject;

public class ReleasePrinter extends BoardPrinter implements GamePrinter {
	
	public final static int CELL_SIZE = 7;

	public ReleasePrinter(int dimX, int dimY) {
		super(dimX, dimY);
	}

	@Override
	public void encodeGame(Game game) {		
		board = new String[dimX][dimY];
		
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				GameObject object =  game.getBoardElement(i, j);
				
				if (object != null) {
					board[i][j] = object.toString();
				}
				else {
					board[i][j] = "";
				}
			}
		}		
	}

	@Override
	public String printGame(Game game) {
		encodeGame(game);
		
		return boardToString(CELL_SIZE);
	}

}
