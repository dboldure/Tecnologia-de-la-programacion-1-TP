package tp.p3.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;
import tp.p3.utils.MyStringUtils;

public class LoadCommand extends Command {
	
	public final static String commandInfo = "[L]oad <file>";
	public final static String helpInfo = "Loads game from file.";
	
	private String fileName;

	public LoadCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}
	
	public LoadCommand() {
		super("", commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (MyStringUtils.isValidFilename(fileName + ".dat") && MyStringUtils.isReadable(fileName + ".dat")) {
			try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".dat"))) {
				String cabecera = br.readLine();
				
				if (cabecera.equals("Plants Vs Zombies v3.0")) {
					br.readLine();
					game.load(br);
					System.out.println("Game successfully loaded from file <" + fileName + ">");
				}
			}
			catch (IOException ex) {
				throw new CommandExecuteException("Ha habido un problema al cargar el juego del fichero");
			}
			catch (FileContentsException ex) {
				throw new CommandExecuteException(ex.getMessage());
			}
		}
		else
			throw new CommandExecuteException("El fichero " + fileName + " no es valido");

		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if (commandWords[0].equalsIgnoreCase("ld") || commandWords[0].equalsIgnoreCase("load")) {
			if (commandWords.length == 2)  {
				fileName = commandWords[1];
				command = this;
			}
			else {
				throw new CommandParseException("Incorrect number of arguments for load command: " + commandInfo);
			}
		}
		
		return command;
	}

}
