package tp.p3.commands;

import tp.p3.logic.Game;

public class RedoCommand extends NoParamsCommand {
	public final static String commandText = "x";
	public final static String commandInfo = "[R]edo";
	public final static String helpInfo = "redo the last command.";
	
	public RedoCommand() {
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		game.redo();
		return true;
	}
}
