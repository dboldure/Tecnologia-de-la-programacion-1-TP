package tp.p3.commands;

import tp.p3.logic.Game;

public class ExitCommand extends NoParamsCommand {
	
	public final static String commandText = "exit";
	public final static String commandInfo = "[E]xit";
	public final static String helpInfo = "terminate the program.";
	
	public ExitCommand() {
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		game.setEnd(true);
		return false;
	}

}
