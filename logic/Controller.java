package tp.p3.logic;

import java.util.Scanner;

import tp.p3.commands.Command;
import tp.p3.commands.CommandParser;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;

public class Controller {
	private Game game;
	private Scanner in;

	public Controller(Game game) {
		this.game = game;
		in = new Scanner(System.in);
	}

	/* Ejecuta el bucle principal del juego */
	public void run() {
		boolean playerWins = false;

		//game.draw();
		while (!this.game.getEnd()) { // Mientras no acabe el juego y el usuario no decida salir

			// UPDATE
			if (game.isUpdateEnabled())
				playerWins = game.update();
			
			game.setUpdateEnabled(true);
			game.draw();

			if (!this.game.getEnd()) {
				// USER COMMAND
				System.out.print("Command > ");
				String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
				
				try {
					Command command = CommandParser.parseCommand(words);
					//if (command.execute(game)) game.draw();
					command.execute(game);
					
				} catch (CommandExecuteException | CommandParseException ex) {
					System.out.format(ex.getMessage() + " %n %n");
				}
				
				// COMPUTER ACTION
				if (!this.game.getEnd() && this.game.isUpdateEnabled()) {
					game.computerAction();
				}
			} else {
				game.draw();
				System.out.println("Game over");
				if (playerWins) {
					System.out.println("Player wins!");
				} else {
					System.out.println("Computer wins!");
				}
			}
		}
	}

}
