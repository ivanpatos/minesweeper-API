package ar.com.deviget.minesweeperapi.exception;

public class InvalidGameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidGameException(String message) {
		super(message);
	}

}
