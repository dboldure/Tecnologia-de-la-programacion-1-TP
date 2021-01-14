package tp.p3.commands;

import tp.p3.logic.Game;

public class UpdateCommand extends NoParamsCommand {
	
	public final static String commandText = "none";
	public final static String commandInfo = "none";
	public final static String helpInfo = "skips cycle.";
	
	public UpdateCommand() {
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		game.addCycle();
		return true;
	}
}
