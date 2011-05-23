package projekt.systemanalyse.shared.logistik;

import java.io.Serializable;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;


@SuppressWarnings("serial")
public class Lager implements Serializable {

	private int kapazitaet;
	private Positionsliste artikelListe = new Positionsliste();

	// Konstruktoren
	public Lager() {}
	
	private Lager(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}
	public static Lager erzeugeLager(int kapazitaet) {
		return new Lager(kapazitaet);
	}
	/////////////////////////
	// Logik
	public void einlagernArtikel(Artikel artikel, int menge, double wert) {
		try {
			artikelListe.getEinzelPositionID(artikel.getId()).updatePosition(menge, wert);
		} catch (NoObjectFoundException e) {
			artikelListe.erfassePosition(artikel, menge, wert);
		}
	}
	public int berechneFreieKapazitaet() {
		int freieKapazitaet = kapazitaet;
		for (int i = 0; i < artikelListe.getPositionen().size(); i++) {
				freieKapazitaet = freieKapazitaet - artikelListe.getEinzelPositionNr(i).berechneBelegterPlatz();
		}
		return freieKapazitaet;
	}
	public int berechneBelegteKapazitaet() {
		return kapazitaet - berechneFreieKapazitaet();
	}
	public void updateLagerkapazitaet(int lagerAenderung) {
		this.kapazitaet = this.kapazitaet + lagerAenderung;
	}
	//public Artikel holeArtikel(int id) throws NoObjectFoundException {
	//	return artikelListe.getPosition(artikelListe.getPositionsNr(id)).getGut();
	//}
	////////////////////////////////
	// Get Methoden
	public int getKapazitaet() {
		return kapazitaet;
	}
	public Positionsliste getArtikelliste(){
		return artikelListe;
	}
}
