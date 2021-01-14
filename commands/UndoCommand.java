package tp.p3.commands;

import tp.p3.logic.Game;

public class UndoCommand extends NoParamsCommand {

	public final static String commandText = "undo";
	public final static String commandInfo = "[U]ndo";
	public final static String helpInfo = "undo the last command.";
	
	public UndoCommand() {
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		game.undo();
		return true;
	}
}
