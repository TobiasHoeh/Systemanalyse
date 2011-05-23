package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpielAbbruchException extends Exception implements Serializable{
	public SpielAbbruchException() {
	}
	
	public SpielAbbruchException(String message) {
		super(message);
	}
}