package projekt.systemanalyse.shared.exceptions;

import java.io.Serializable;

import projekt.systemanalyse.shared.logistik.Artikel;

@SuppressWarnings("serial")
public class InstanceCreationException extends Exception  implements Serializable{
	private Artikel uebergabeArtikel = null;
	public InstanceCreationException() {
	}

	public InstanceCreationException(String message) {
		super(message);
	}
	public InstanceCreationException(String message, Artikel einArtikel) {
		super(message);
		uebergabeArtikel = einArtikel;
	}
	
	public Artikel getUebergabeArtikel(){
		return uebergabeArtikel;
	}
}
