package tp.p3.commands;

import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.printers.DebugPrinter;
import tp.p3.logic.printers.ReleasePrinter;

public class PrintModeCommand extends Command {
	
	public final static String commandInfo = "[P]rintMode <mode>";
	public final static String helpInfo = "Change the print mode [Release|Debug].";
	
	private String printer;

	public PrintModeCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}
	
	public PrintModeCommand() {
		super("", commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		switch (printer) {
		case "RELEASE":
			game.setPrinter(new ReleasePrinter(Game.NUM_ROWS, Game.NUM_COLUMNS));
			break;
		case "DEBUG":
			game.setPrinter(new DebugPrinter(1, 1));
			break;
		}
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if (commandWords[0].equalsIgnoreCase("p") || commandWords[0].equalsIgnoreCase("printmode")) {
			if (commandWords.length == 2) {
				if (commandWords[1].equalsIgnoreCase("r") || commandWords[1].equalsIgnoreCase("release")) {
					this.printer = "RELEASE";
					command = this;
				}
				else if (commandWords[1].equalsIgnoreCase("d") || commandWords[1].equalsIgnoreCase("debug")) {
					this.printer = "DEBUG";
					command = this;
				}
				else {
					throw new CommandParseException("Unkown printmode: " + commandWords[1]);
				}
			}
			else {
				throw new CommandParseException("Incorrect number of arguments for printmode command: " + commandInfo);
			}
		}
		
		return command;
	}
	

}
