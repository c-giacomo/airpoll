package it.airpoll.common.exception;

/**
 * @author Giacomo
 * 
 * Custom Exception to thrown when a Entity is not found
 *
 */

public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ItemNotFoundException(String message) {
		super(message);
	}

}
