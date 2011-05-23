package projekt.systemanalyse.shared.aktionen;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.Buchung;
import projekt.systemanalyse.shared.Position;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.logistik.Fahrrad;

@SuppressWarnings("serial")
public class EinkaufsAktion extends Aktion implements Serializable{

	// Konstruktoren
	public EinkaufsAktion() {}
	
	private EinkaufsAktion(Zug einZug, Positionsliste einkaufsListe) {
		super(einZug, einkaufsListe);
	}
	public static EinkaufsAktion erzeugeEinkaufsaktion(Zug einZug, Positionsliste einkaufsListe) throws InstanceCreationException {
		ArrayList<Position> allePositionen = einkaufsListe.getPositionen();
		int benoetigteKapazitaet = 0;
//		for (int i=0;i<allePositionen.size();i++){
//			benoetigteKapazitaet = benoetigteKapazitaet +allePositionen.get(i).getMenge() * allePositionen.get(i).getGut().getGroesse();
//		}
//		if (benoetigteKapazitaet > einZug.getSpieler().getUnternehmen().getLager().berechneFreieKapazitaet()){
//			throw new InstanceCreationException("Der Lagerplatz reicht für diesen Einkauf nicht aus!");
//		}
		return new EinkaufsAktion(einZug, einkaufsListe);
	}	
	//////////////////////////////////////
	// Logik
	@Override
	public void ausfuehren() throws WertNullException, FahrradListeException, NoObjectFoundException {
		Positionsliste marktListe = zug.getSpieler().getSpiel().getMarkt().getMarktArtikel();
		for (int i = 0; i < arbeitsListe.getSize(); i++) {
			ArrayList<Object[]> sollbuchungen = new ArrayList<Object[]>();
			ArrayList<Object[]> habenbuchungen = new ArrayList<Object[]>();
			int gutID;
			int menge = 0;
			double artikelwert = 0;
			double summe = 0;
			if (arbeitsListe.getEinzelPositionNr(i).getGut() instanceof Fahrrad) {
				throw new FahrradListeException("Fahrräder können nicht gekauft werden!");
			}
			gutID = arbeitsListe.getEinzelPositionNr(i).getGut().getId();
			menge = arbeitsListe.getEinzelPositionNr(i).getMenge();
			artikelwert = marktListe.getEinzelPositionID(gutID).getWert();
			if (menge == 0){
				continue;
			}
			lager.einlagernArtikel(arbeitsListe.getEinzelPositionNr(i).getGut(), menge, artikelwert);
			marktListe.getEinzelPositionID(gutID).updatePosition(-menge, artikelwert);
			// Buchungen
			summe = artikelwert * menge;
			sollbuchungen.add(Buchung.erstelleEinzelbuchung("Fremdbauteile", summe));
			habenbuchungen.add(Buchung.erstelleEinzelbuchung("Kasse", summe));
			Buchung.erstelleBuchung(sollbuchungen, habenbuchungen, bilanz);
			zug.hinzufuegenAusgaben(summe);
		}
	}
}