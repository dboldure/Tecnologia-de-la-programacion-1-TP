package tp.p3.exceptions;

@SuppressWarnings("serial")
public class CommandExecuteException extends Exception {

	public CommandExecuteException(String message) {
		super(message);
	}
	
	public CommandExecuteException() {
		super("Command execution error");
	}

}
