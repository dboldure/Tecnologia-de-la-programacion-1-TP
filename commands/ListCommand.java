package tp.p3.commands;

import tp.p3.logic.Game;

public class ListCommand extends NoParamsCommand {
	
	public final static String commandText = "list";
	public final static String commandInfo = "[L]ist";
	public final static String helpInfo = "print the list of available plants.";
	
	public ListCommand() {
		super(commandText, commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		game.list();
		return false;
	}

}
