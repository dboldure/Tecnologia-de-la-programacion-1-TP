package tp.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import tp.p3.commands.CommandParser;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.lists.PlantsList;
import tp.p3.lists.ZombieList;
import tp.p3.logic.objects.GameObject;
import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.Zombie;
import tp.p3.logic.objects.factories.PlantFactory;
import tp.p3.logic.objects.factories.ZombieFactory;
import tp.p3.logic.printers.GamePrinter;
import tp.p3.logic.printers.ReleasePrinter;
import tp.p3.managers.SuncoinManager;
import tp.p3.managers.ZombieManager;

public class Game {
	public final static int NUM_ROWS = 4;
	public final static int NUM_COLUMNS = 8;

	private GameObject[][] board;
	private PlantsList plantsList;
	private ZombieList zombieList;
	private int cycles;
	private SuncoinManager suncoinManager;
	private ZombieManager zombieManager;
	private Random rand;
	private boolean end;
	private Level level;
	private int seed;
	private GamePrinter gamePrinter;
	private boolean updateEnabled;
	
	private ArrayList<GameHistory> history;
	private int idxHistory;


	public Game(Level level, int seed) {
		plantsList = new PlantsList();
		zombieList = new ZombieList();
		cycles = 0;
		suncoinManager = new SuncoinManager();

		rand = new Random();
		rand.setSeed(seed);

		this.level = level;
		this.seed = seed;
		zombieManager = new ZombieManager(level, rand);

		this.end = false;
		
		this.gamePrinter = new ReleasePrinter(Game.NUM_ROWS, Game.NUM_COLUMNS);

		initializeBoard();
		
		this.updateEnabled = true;
		this.history = new ArrayList<GameHistory>();
		this.idxHistory = -1;
	}

	/* It updates all the board elements */
	public boolean update() {
		boolean playerWins = false;

		// Sunflowers update

		for (int i = 0; i < this.plantsList.getSize(); i++) {
			this.plantsList.getPlant(i).update();
		}


		// Zombies update
		for (int i = 0; i < this.zombieList.getSize() && !end; i++) {
			Zombie zombie = this.zombieList.getZombie(i);

			zombie.update(); // si los zombies llegan al principio del tablero acaba el juego
		}
		
		
		if (this.zombieList.getSize() == 0 && this.zombieManager.getRemainingZombies() == 0) {
			Level lv = Level.nextLevel(level);
			
			if (lv != null) {
				level = lv;
				zombieManager.setRemainingZombies(lv.getNumZombies());
			}
		}

		playerWins = (this.zombieList.getSize() == 0 && this.zombieManager.getRemainingZombies() == 0);

		end = end || playerWins;
		
		GameHistory hs = new GameHistory(boardCopy(), 
										 plantsList.clone(),
										 zombieList.clone(),
										 cycles,
										 zombieManager.getRemainingZombies(),
										 suncoinManager.getSuncoins(),
										 level.copy());
		history.add(hs);
		idxHistory++;

		return playerWins;
	}
	
	public boolean undo() {
		if (idxHistory >= 0) {
			board = history.get(idxHistory).getBoard();
			plantsList = history.get(idxHistory).getPlantsList();
			zombieList = history.get(idxHistory).getZombieList();
			cycles = history.get(idxHistory).getCycles();
			level = history.get(idxHistory).getLevel();
			int remainingZombies = history.get(idxHistory).getRemainingZombies();
			zombieManager = new ZombieManager(level, rand, remainingZombies);
			int suncoins = history.get(idxHistory).getSuncoins();
			suncoinManager = new SuncoinManager();
			suncoinManager.setSuncoins(suncoins);
			
			this.updateEnabled = false;
			idxHistory--;
			return true;
		}
		return false;
	}
	
	public boolean redo() {
		System.out.println(idxHistory + " " + history.size());
		if (cycles != 0 && idxHistory <= history.size()) {
			System.out.println("REDO");
			board = history.get(idxHistory + 1).getBoard();
			plantsList = history.get(idxHistory + 1).getPlantsList();
			zombieList = history.get(idxHistory + 1).getZombieList();
			cycles = history.get(idxHistory + 1).getCycles();
			level = history.get(idxHistory + 1).getLevel();
			int remainingZombies = history.get(idxHistory + 1).getRemainingZombies();
			zombieManager = new ZombieManager(level, rand, remainingZombies);
			int suncoins = history.get(idxHistory + 1).getSuncoins();
			suncoinManager = new SuncoinManager();
			suncoinManager.setSuncoins(suncoins);
			
			this.updateEnabled = false;
			idxHistory++;
			return true;
		}
		return false;
	}
	
	public Zombie getZombie(int row, int column) {
		return zombieList.getZombie(row, column);
	}
	
	public Plant getPlant(int row, int column) {
		return plantsList.getPlant(row, column);
	}

	/*
	 * Añade un Sunflower al tablero Return: devuelve true si se ha podido añadir,
	 * false en caso contrario
	 */
	public boolean addPlant(int x, int y, Plant plant) throws CommandExecuteException {
		boolean ok = false, empty, enoughSuncoins;

		if (x >= 0 && x < NUM_ROWS && y >= 0 && y < NUM_COLUMNS) {

			empty = this.board[x][y] == null;
			enoughSuncoins = this.suncoinManager.enoughSuncoins(plant.getCost());
			
			if (!enoughSuncoins)
				throw new CommandExecuteException("Failed to add " + plant.getName() + ": not enough suncoins to buy it");
			
			if (!empty)
				throw new CommandExecuteException("Failed to add " + plant.getName() + ": position (" + plant.getX() + ", " + plant.getY() + ") is already occupied");


			board[x][y] = plant;
			plantsList.insertarElemento(plant);

			this.suncoinManager.useSuncoins(plant.getCost());

			ok = true;

		} else {
			throw new CommandExecuteException("Failed to add " + plant.getName() + ": position (" + plant.getX() + ", " + plant.getY() + ") is an invalid position");
		}

		return ok;
	}

	/*
	 * Añade un Zombie al tablero Return: devuelve true si se ha podido añadir,
	 * false en caso contrario
	 */
	public boolean addZombie(int x, int y, Zombie zombie) {
		boolean ok = false;

		if (this.board[x][y] == null) {
			this.board[x][y] = zombie;
			this.zombieList.insertarElemento(zombie);

			if (!zombie.getName().equals("Infected Zombie"))
				this.zombieManager.addZombie();
			ok = true;
		}

		return ok;
	}

	/*
	 * Elimina el elemento element que se encuentre en la posición x, y del tablero
	 */
	public void removePlant(Plant plant, int x, int y) {

		this.board[x][y] = null;
		plantsList.eliminarElemento(plant);
	}
	
	public void removeZombie(Zombie zombie, int x, int y) {

		this.board[x][y] = null;
		zombieList.eliminarElemento(zombie);
	}

	/* Mueve el Zombie de posición para avanzar en el tablero */
	public boolean moveZombie(Zombie zombie) {
		boolean moved = false;
		
		// Move Infected Zombie
		if (zombie.getName().equals("Infected Zombie")) {
			if (zombie.getY() >= NUM_COLUMNS) {
				this.removeZombie(zombie, zombie.getX(), zombie.getY());
			}
			else if (this.board[zombie.getX()][zombie.getY() + 1] == null) {
				this.board[zombie.getX()][zombie.getY()] = null;
				this.board[zombie.getX()][zombie.getY() + 1] = zombie;

				moved = true;
			}
		}

		else if (this.board[zombie.getX()][zombie.getY() - 1] == null) {
			this.board[zombie.getX()][zombie.getY()] = null;
			this.board[zombie.getX()][zombie.getY() - 1] = zombie;

			moved = true;
		}

		return moved;
	}

	/*
	 * La máquina decide si hay que añadir un Zombie y en caso afirmativo lo añade
	 */
	public void computerAction() {
		int row;

		if (this.zombieManager.isZombieAdded()) {
			row = this.rand.nextInt(NUM_ROWS);
			
			Zombie zombie = ZombieFactory.getZombie();
			zombie.setX(row);
			zombie.setY(NUM_COLUMNS - 1);
			zombie.setGame(this);

			this.addZombie(row, NUM_COLUMNS - 1, zombie);
		}
	}

	/* Incrementa el ciclo actual del juego */
	public void addCycle() {
		cycles++;
	}
	
	public void addSuncoins(int suncoins) {
		suncoinManager.addSuncoins(suncoins);
	}

	/*
	 * Obtiene una cadena del elemento x, y del tablero indicando su resistencia
	 * actual
	 */
	public GameObject getBoardElement(int x, int y) {
		return board[x][y];
	}
	
	public PlantsList getPlantsList() {
		return this.plantsList.clone();
	}
	
	public ZombieList getZombieList() {
		return this.zombieList.clone();
	}
	
	public int getNumGameObjects() {
		return plantsList.getSize() + zombieList.getSize();
	}

	/* Reinicia el juego */
	public void reset() {
		plantsList = new PlantsList();
		zombieList = new ZombieList();
		cycles = 0;
		suncoinManager = new SuncoinManager();
		zombieManager = new ZombieManager(level, rand);
		this.end = false;

		initializeBoard();
	}

	/* Devuelve si es el final del juego */
	public boolean getEnd() {
		return this.end;
	}
	
	public void setEnd(boolean end) {
		this.end = end;
	}

	/* Obtiene una cadena con la información del ciclo actual del juego */
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("Random seed used: " + this.seed + System.lineSeparator());
		str.append("Number of cycles: " + this.cycles + System.lineSeparator());
		str.append("Sun coins: " + this.suncoinManager.toString() + System.lineSeparator());
		str.append("Remaining zombies: " + this.zombieManager.toString());

		return str.toString();
	}

	/* Devuelve una cadena con todos los elementos que puede añadir el jugador */
	public void list() {
		System.out.println(PlantFactory.listOfAvailablePlants());
	}

	/* Devuelve una cadena con informacion de ayuda */
	public void help() {		
		System.out.println(CommandParser.commandHelp());
	}
	
	public void setPrinter(GamePrinter printer) {
		this.gamePrinter = printer;
	}
	
	public void draw() {
		System.out.println(this);
		System.out.println(gamePrinter.printGame(this));
	}

	/* Incializa el tablero */
	private void initializeBoard() {
		board = new GameObject[NUM_ROWS][NUM_COLUMNS];

		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				board[i][j] = null;
			}
		}
	}
	
	public void store(BufferedWriter br) throws IOException {
		
		br.write("cycle: " + this.cycles + System.lineSeparator());
		br.write("suncoins: " + this.suncoinManager.toString() + System.lineSeparator());
		br.write("level: " + this.level.name() + System.lineSeparator());
		br.write("remZombies: " + this.zombieManager.getRemainingZombies() + System.lineSeparator());
		br.write("plantList: " + this.plantsList.externalise() + System.lineSeparator());
		br.write("zombieList: " + this.zombieList.externalise());
		
		br.flush();				
	}
	
	public void load(BufferedReader br) throws FileContentsException, CommandExecuteException, IOException {
		Game gameCopy = clone();
		
		try {

			String cycle = br.readLine();
			if (cycle.startsWith("cycle:")) cycle = cycle.split(": ")[1];
			String suncoins = br.readLine();
			if (suncoins.startsWith("suncoins:")) suncoins = suncoins.split(": ")[1];
			String level = br.readLine();
			if (level.startsWith("level:")) level = level.split(": ")[1];
			Level lv = Level.parse(level);
			
			if (lv == null) throw new FileContentsException("El nivel especificado en el fichero no existe");
			
			String remZombies = br.readLine();
			if (remZombies.startsWith("remZombies:")) remZombies = remZombies.split(": ")[1];
			
			String plants = br.readLine();
			if (plants.startsWith("plantList:")) plants = plants.substring(11);
			
			String zombies = br.readLine();
			if (zombies.startsWith("zombieList:")) zombies = zombies.substring(12);
			
			initializeBoard();
			
			String[] _plantList = plants.split(", ");
			
			PlantsList plantListNew = new PlantsList();
			this.plantsList = plantListNew;
			
			for (String plant : _plantList) {
				String[] plantData = plant.split(":");
				Plant _plant = PlantFactory.getPlant(plantData[0]);
				_plant.setResistance(Integer.parseInt(plantData[1]));
				_plant.setX(Integer.parseInt(plantData[2]));
				_plant.setY(Integer.parseInt(plantData[3]));
				_plant.setGame(this);
				_plant.setRemainingCycles(Integer.parseInt(plantData[4]));
				
				addPlant(_plant.getX(), _plant.getY(), _plant);
			}
			
			String[] _zombieList = zombies.split(", ");
			ZombieList zombieListNew = new ZombieList();
			this.zombieList = zombieListNew;
			
			for (String zombie : _zombieList) {
				String[] zombieData = zombie.split(":");
				Zombie _zombie = ZombieFactory.getZombie(zombieData[0]);
				_zombie.setResistance(Integer.parseInt(zombieData[1]));
				_zombie.setX(Integer.parseInt(zombieData[2]));
				_zombie.setY(Integer.parseInt(zombieData[3]));
				_zombie.setGame(this);
				_zombie.setRemainingCycles(Integer.parseInt(zombieData[4]));
				
				addZombie(_zombie.getX(), _zombie.getY(), _zombie);
			}
			
			this.cycles = Integer.parseInt(cycle);
			this.suncoinManager.setSuncoins(Integer.parseInt(suncoins));
			this.level = lv;
			this.zombieManager = new ZombieManager(lv, this.rand, Integer.parseInt(remZombies));
			this.gamePrinter = new ReleasePrinter(Game.NUM_ROWS, Game.NUM_COLUMNS);
		
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			restoreGame(gameCopy);
			throw new FileContentsException("El fichero de carga no tiene el formato correcto");
		}
	}
	
	public GameObject[][] boardCopy() {
		GameObject[][] newBoard = new GameObject[NUM_ROWS][NUM_COLUMNS];

		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		
		return newBoard;
	}
	
	public void restoreGame(Game game) {
		System.out.println("Restaurando juego...");
		this.cycles = game.cycles;
		this.board = game.board;
		this.plantsList = game.plantsList;
		this.zombieList = game.zombieList;
		this.suncoinManager = game.suncoinManager;
		this.zombieManager = game.zombieManager;
	}
	
	public Game clone() {
		Game newGame = new Game(this.level, this.seed);
		
		newGame.plantsList = plantsList.clone();
		newGame.zombieList = zombieList.clone();
		newGame.cycles = cycles;
		newGame.suncoinManager = suncoinManager.clone();
		newGame.zombieManager = zombieManager.clone();
		newGame.board = boardCopy();
		
		return newGame;
	}

	public boolean isUpdateEnabled() {
		return updateEnabled;
	}

	public void setUpdateEnabled(boolean updateEnabled) {
		this.updateEnabled = updateEnabled;
	}
	
	
}
