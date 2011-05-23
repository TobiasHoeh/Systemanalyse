package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpielEndeException extends Exception implements Serializable{
	public SpielEndeException() {
	}
	
	public SpielEndeException(String message) {
		super(message);
	}
}