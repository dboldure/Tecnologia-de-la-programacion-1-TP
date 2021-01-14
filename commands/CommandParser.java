package tp.p3.commands;

import tp.p3.exceptions.CommandParseException;

public class CommandParser {

	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new UpdateCommand(),
			new PrintModeCommand(),
			new SaveCommand(),
			new LoadCommand(),
			new UndoCommand(),
			new RedoCommand()
	};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		Command command;
		
		for (Command c : availableCommands) {
			command = c.parse(commandWords);
			
			if (command != null) {
				return command;
			}
		}
		throw new CommandParseException();
	}
	
	public static String commandHelp() {
		StringBuilder str = new StringBuilder();
		
		for (Command c : availableCommands) {
			str.append(c.helpText() + System.lineSeparator());
		}
		
		return str.toString();
	}
}
