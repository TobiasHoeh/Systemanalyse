package projekt.systemanalyse.shared.ereignisse;

import java.io.Serializable;

import projekt.systemanalyse.shared.Zug;

@SuppressWarnings("serial")
public abstract class Ereignis implements Serializable {

	protected Zug zug = null;
	protected String beschreibung;

	public Ereignis() {	}

	protected Ereignis(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	protected Ereignis(Zug einZug, String beschreibung) {
		this.beschreibung = beschreibung;
		this.zug = einZug;
		zug.hinzufuegenEreignis(this);
	}

	public abstract void ausfuehren();
	
	public String getBeschreibung(){
		return beschreibung;
	}
	
}
