package tp.p3.commands;

import tp.p3.logic.Game;

public class ResetCommand extends NoParamsCommand {
	
	public final static String commandText = "reset";
	public final static String commandInfo = "[R]eset";
	public final static String helpInfo = "resets game.";
	
	public ResetCommand() {
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

}
