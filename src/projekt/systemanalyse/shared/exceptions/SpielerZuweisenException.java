package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpielerZuweisenException extends Exception implements Serializable{
	public SpielerZuweisenException(){
		
	}
	public SpielerZuweisenException(String message) {
		super(message);
	}
}
