package tp.p3.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.utils.MyStringUtils;

public class SaveCommand extends Command {
	
	public final static String commandInfo = "[S]ave <file>";
	public final static String helpInfo = "Save game to a file.";
	
	private String fileName;

	public SaveCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}
	
	public SaveCommand() {
		super("", commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (MyStringUtils.isValidFilename(fileName + ".dat")) {
			try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName + ".dat"))) {
				br.write("Plants Vs Zombies v3.0");
				br.write(System.lineSeparator() + System.lineSeparator());
				
				game.store(br);
				System.out.println("Game successfully saved in file " + fileName + ".dat. Use the load command to reload it.");
			}
			catch (IOException ex) {
				throw new CommandExecuteException("Ha habido un problema de escritura en el fichero " + fileName);
			}
		}
		else {
			throw new CommandExecuteException("El fichero " + fileName + " no es valido");
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if (commandWords[0].equalsIgnoreCase("s") || commandWords[0].equalsIgnoreCase("save")) {
			if (commandWords.length == 2)  {
				fileName = commandWords[1];
				command = this;
			}
			else {
				throw new CommandParseException("Incorrect number of arguments for save command: " + commandInfo);
			}
		}
		
		return command;
	}

}
