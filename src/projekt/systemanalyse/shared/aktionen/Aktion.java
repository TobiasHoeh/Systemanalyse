package projekt.systemanalyse.shared.aktionen;

import java.io.Serializable;

import projekt.systemanalyse.shared.Konto;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.logistik.Lager;

@SuppressWarnings("serial")
public abstract class Aktion implements Serializable {

	protected Zug zug;
	protected Lager lager;
	protected Konto bilanz;
	protected Positionsliste arbeitsListe;
	
	// Konstruktoren
	public Aktion(){}
	
	protected Aktion(Zug einZug) {
		this.zug = einZug;
		this.lager = einZug.getSpieler().getUnternehmen().getLager();
		this.bilanz = einZug.getSpieler().getUnternehmen().getBilanz();
		einZug.hinzufuegenAktion(this);
	}
	protected Aktion(Zug einZug, Positionsliste eineArbeitsListe) {
		this.zug = einZug;
		this.lager = einZug.getSpieler().getUnternehmen().getLager();
		this.bilanz = einZug.getSpieler().getUnternehmen().getBilanz();
		this.arbeitsListe = eineArbeitsListe;
		einZug.hinzufuegenAktion(this);
	}
	/////////////////////////////
	// Ablauf
	public abstract void ausfuehren() throws  NoObjectFoundException,WertNullException, FahrradListeException;
	
	//////////////////
	// Get Methoden
	public Zug getZug() {
		return this.zug;
	}
	public Positionsliste getEinkaufsliste() {
		return arbeitsListe;
	}
	// Set Methoden
	public void setEinkaufsliste(Positionsliste neueEinkaufsliste){
		this.arbeitsListe = neueEinkaufsliste;
	}
}
