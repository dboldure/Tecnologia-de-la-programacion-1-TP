package tp.p3.exceptions;

public class FileContentsException extends Exception {

	private static final long serialVersionUID = -4589248827757554081L;

	public FileContentsException(String message) {
		super(message);
	}
	
	public FileContentsException() {
		super("Error with file");
	}
}
