package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FahrradListeException extends Exception implements Serializable {
	public FahrradListeException() {
	}

	public FahrradListeException(String message) {
		super(message);
	}
}