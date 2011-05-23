package projekt.systemanalyse.shared.aktionen;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.Buchung;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.logistik.Fahrrad;

@SuppressWarnings("serial")
public class VerkaufsAktion extends Aktion implements Serializable {

	// Konstruktoren
	public VerkaufsAktion() {}
	
	private VerkaufsAktion(Zug einZug, Positionsliste verkaufsListe) {
		super(einZug, verkaufsListe);
	}
	public static VerkaufsAktion erzeugeVerkaufsAktion(Zug einZug, Positionsliste verkaufsListe) {
		return new VerkaufsAktion(einZug, verkaufsListe);
	}
	/////////////////////////////////////////////
	// Logik
	@Override
	public void ausfuehren() throws WertNullException, FahrradListeException, NoObjectFoundException {
		Positionsliste marktListe = zug.getSpieler().getSpiel().getMarkt().getMarktArtikel();
		for (int i = 0; i < arbeitsListe.getSize(); i++) {
			ArrayList<Object[]> sollbuchungen = new ArrayList<Object[]>();
			ArrayList<Object[]> habenbuchungen = new ArrayList<Object[]>();
			int gutID;
			int menge = 0;
			double artikelwertMarkt = 0;
			double artikelwertLager = 0;
			double summeMarkt = 0;
			double summeLager = 0;
			double summeDiff = 0;
			if (arbeitsListe.getEinzelPositionNr(i).getGut() instanceof Fahrrad) {
				throw new FahrradListeException("Fahrräder werden über eine Marktmethode verkauft!");
			}			
			gutID = arbeitsListe.getEinzelPositionNr(i).getGut().getId();
			menge = arbeitsListe.getEinzelPositionNr(i).getMenge();
			artikelwertMarkt = marktListe.getEinzelPositionID(gutID).getWert();
			artikelwertLager = lager.getArtikelliste().getEinzelPositionID(gutID).getWert();
			if (menge == 0){
				continue;
			}
			lager.einlagernArtikel(arbeitsListe.getEinzelPositionID(gutID).getGut(),-menge, artikelwertLager);
			marktListe.getEinzelPositionID(gutID).updatePosition(menge, artikelwertMarkt);
			// Buchungen
			summeLager = artikelwertLager * menge;
			summeMarkt = artikelwertMarkt * menge;
			summeDiff = summeLager - 0.5 * summeMarkt;
			habenbuchungen.add(Buchung.erstelleEinzelbuchung("Fremdbauteile",  summeLager));
			sollbuchungen.add(Buchung.erstelleEinzelbuchung("Umsatzerloese", 0.5 * summeMarkt));
			if (summeDiff > 0){ // Artikel im Lager sind mehr Wert als der halbe Marktpreis
				sollbuchungen.add(Buchung.erstelleEinzelbuchung("Abschreibungen", summeDiff));
			} 
			else { // Artikel im Lager sind weniger Wert als halber Marktpreis....
				habenbuchungen.add(Buchung.erstelleEinzelbuchung("Umsatzerloese", -summeDiff));
			}
			Buchung.erstelleBuchung(sollbuchungen, habenbuchungen, bilanz);
			zug.hinzufuegenEinnahmen(0.5 * summeMarkt);
		}
	}
}