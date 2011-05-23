package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZugEndeException extends Exception implements Serializable{
	public ZugEndeException(){
		
	}
	public ZugEndeException(String message) {
		super(message);
	}
}