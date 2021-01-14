package tp.p3.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.logic.objects.Plant;
import tp.p3.logic.objects.factories.PlantFactory;

public class AddCommand extends Command {
	
	public final static String commandInfo = "[A]dd <plant> <x> <y>";
	public final static String helpInfo = "Add a plant in position (x, y).";
	
	private Plant plant;

	public AddCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}
	
	public AddCommand() {
		super("", commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		plant.setGame(game);
		if (game.addPlant(plant.getX(), plant.getY(), plant)) {
			game.addCycle();
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if (commandWords[0].equalsIgnoreCase("a") || commandWords[0].equalsIgnoreCase("add")) {
			if (commandWords.length == 4)  {
				plant = PlantFactory.getPlant(commandWords[1]);
				
				try {
					if (plant != null) {
						plant.setX(Integer.parseInt(commandWords[2]));
						plant.setY(Integer.parseInt(commandWords[3]));
						command = this;
					}
					else {
						throw new CommandParseException("Unknown plant name: " + commandWords[1]);
					}
				} catch (NumberFormatException ex) {
					throw new CommandParseException("Coordinates have to be a number");
				}
			}
			else {
				throw new CommandParseException("Incorrect number of arguments for add command: " + commandInfo);
			}
		}
		
		return command;
	}

}
