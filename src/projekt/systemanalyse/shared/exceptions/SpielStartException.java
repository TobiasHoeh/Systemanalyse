package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpielStartException extends Exception implements Serializable{
	public SpielStartException() {
	}
	
	public SpielStartException(String message) {
		super(message);
	}
}