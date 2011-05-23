package projekt.systemanalyse.shared.ereignisse;

import java.io.Serializable;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;

@SuppressWarnings("serial")
public class EinkaufsEreignis extends Ereignis implements Serializable {

	private double aenderungMenge;
	private double aenderungWert;

	// Konstruktoren
	public EinkaufsEreignis() {}
	
	private EinkaufsEreignis(Zug einZug, String beschreibung, double aenderungMenge, double aenderungWert) {
		super(einZug, beschreibung);
		this.aenderungMenge = aenderungMenge;
		this.aenderungWert = aenderungWert;
	}
	public static EinkaufsEreignis erzeugeEinkaufsereignis(Zug einZug, String beschreibung,	double aenderungMenge, double aenderungWert) throws InstanceCreationException {
		if (aenderungMenge > 1) {
			throw new InstanceCreationException("Eine Einkaufsmenge kann nur verringert werden!");
		}
		return new EinkaufsEreignis(einZug, beschreibung, aenderungMenge, aenderungWert);
	}
	/////////////////////////////////////
	// Logik
	@Override
	public void ausfuehren() {
		// TODO
	}

}
