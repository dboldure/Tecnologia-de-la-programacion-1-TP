package tp.p3.commands;

import tp.p3.exceptions.CommandParseException;

public abstract class NoParamsCommand extends Command {
	private static Command[] availableCommands = {
		new ExitCommand(),
		new ListCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new UpdateCommand(),
		new UndoCommand(),
		new RedoCommand()
	};

	public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		for (Command c : availableCommands) {
			if (commandWords[0].length() == 0) {
				return new UpdateCommand();
			}
			else if (c.commandName.equalsIgnoreCase(commandWords[0])
					|| commandWords[0].length() == 1 && 
					(Character.toLowerCase(c.commandName.charAt(0)) == Character.toLowerCase(commandWords[0].charAt(0)))) {
				
				if (commandWords.length > 1) 
					throw new CommandParseException(commandWords[0] + " command has no arguments");
				return c;
			}
		}

		return null;
	}
	
	

}
