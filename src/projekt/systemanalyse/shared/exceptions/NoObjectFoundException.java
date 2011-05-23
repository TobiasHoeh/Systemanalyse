package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NoObjectFoundException extends Exception implements Serializable {
	public NoObjectFoundException() {
	}
	
	public NoObjectFoundException(String message) {
		super(message);
	}
}
