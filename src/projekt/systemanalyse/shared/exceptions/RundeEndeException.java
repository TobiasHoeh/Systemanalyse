package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RundeEndeException extends Exception implements Serializable{
	public RundeEndeException(){
		
	}
	public RundeEndeException(String message) {
		super(message);
	}
}