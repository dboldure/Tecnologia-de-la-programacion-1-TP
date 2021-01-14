package tp.p3.exceptions;

public class CommandParseException extends Exception {

	private static final long serialVersionUID = -4782600238705996578L;

	public CommandParseException(String message) {
		super(message);
	}
	
	public CommandParseException() {
		super("Unknown command. Use ’help’ to see the available commands");
	}
}
