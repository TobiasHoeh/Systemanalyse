package projekt.systemanalyse.shared.ereignisse;

import java.io.Serializable;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.aktionen.Aktion;
import projekt.systemanalyse.shared.aktionen.VerkaufsAktion;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;

@SuppressWarnings("serial")
public class VerkaufsEreignis extends Ereignis implements Serializable {

	private double aenderungMenge;
	private double aenderungWert;

	private VerkaufsEreignis(Zug einZug, String beschreibung, double aenderungMenge, double aenderungWert) {
		super(einZug, beschreibung);
		this.aenderungMenge = aenderungMenge;
		this.aenderungWert = aenderungWert;
	}

	public VerkaufsEreignis() {
	}

	public VerkaufsEreignis erzeugeVerkaufsEreignis(Zug einZug, String beschreibung, double aenderungMenge, double aenderungWert) throws InstanceCreationException {
		if (aenderungMenge > 1) {
			throw new  InstanceCreationException("Eine Verkaufsmenge kann nicht erhöht werden!");
		}
		return new VerkaufsEreignis(einZug,beschreibung, aenderungMenge, aenderungWert);
	}
/////////////////////////////////////
	// Logik
	@Override
	public void ausfuehren() {
		// TODO
	}
}
