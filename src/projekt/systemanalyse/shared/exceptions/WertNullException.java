package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WertNullException  extends Exception implements Serializable{
	public WertNullException () {
	}
	
	public WertNullException (String message) {
		super(message);
	}
}
