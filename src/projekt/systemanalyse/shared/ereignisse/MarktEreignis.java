package projekt.systemanalyse.shared.ereignisse;

import java.io.Serializable;

import projekt.systemanalyse.shared.Markt;

@SuppressWarnings("serial")
public class MarktEreignis extends Ereignis implements Serializable  {

	private Markt markt;
	private double aenderung;
	// Konstruktoren
	public MarktEreignis(){}
	
	private MarktEreignis(Markt einMarkt, double aenderung, String beschreibung) {
		super(beschreibung);
		this.aenderung = aenderung;
		this.markt = einMarkt;
	}
	public static MarktEreignis erzeugeMarktereignis(Markt einMarkt, double aenderung, String beschreibung) {
		return new MarktEreignis(einMarkt, aenderung, beschreibung);
	}
	
	@Override
	public void ausfuehren() {
		markt.setMartkvolumen((int)(markt.getMartkvolumen()*aenderung));	
	}
	
	
	
}
