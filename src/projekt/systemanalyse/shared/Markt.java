package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.logistik.Artikel;

@SuppressWarnings("serial")
public class Markt implements Serializable {

	private int marktvolumen;
	private Spiel spiel;
	private Positionsliste marktArtikel = new Positionsliste();

	// Konstruktoren
	private Markt(int marktvolumen, Spiel spiel) {
		this.marktvolumen = marktvolumen;
		this.spiel = spiel;
	}
	public Markt() {}

	public static Markt erzeugeMarkt(int marktvolumen, Spiel spiel) {	
		return new Markt(marktvolumen, spiel);
	}
	////////////////////////////////////////////////
	// Ablauf
	// zusätzlich Artikel auf dem Markt anbieten
	public void hinzufuegenArtikel(Artikel artikel, int menge, double wert) {
		marktArtikel.erfassePosition(artikel, menge, wert);
	}
	// Absätze für Spieler generieren
	public void genereiereAbsaetze() throws NoObjectFoundException{
		int summeAngebot = 0;
		double summePreise = 0;
		double mittelPreis = 0;
		double abweichung = 0;
		int basisAbsatz = marktvolumen / spiel.getSpieleranzahl();
		int spielerMoegVerkauft = 0;
		int spielerVerkauft = 0;
		int marktVerkauft = 0;
		for (int i = 0;i<spiel.getSpieler().size();i++){
			Positionsliste LagerArtikel = spiel.getSpieler().get(i).getUnternehmen().getLager().getArtikelliste();
			summeAngebot = summeAngebot + LagerArtikel.getEinzelPositionID(Artikel.getFahrradArtikel().getId()).getMenge();
			summePreise = summePreise + spiel.getAktuelleRunde().getZugDesSpielers(spiel.getSpieler().get(i)).getVerkaufspreis();
		}
		mittelPreis = summePreise / spiel.getSpieleranzahl();
		for (int i = 0;i<spiel.getSpieler().size();i++){
			Positionsliste LagerArtikel = spiel.getSpieler().get(i).getUnternehmen().getLager().getArtikelliste();
			abweichung = mittelPreis / spiel.getAktuelleRunde().getZugDesSpielers(spiel.getSpieler().get(i)).getVerkaufspreis();
			spielerMoegVerkauft = (int)Math.round(abweichung * basisAbsatz);
			if(spielerMoegVerkauft <= LagerArtikel.getEinzelPositionID(Artikel.getFahrradArtikel().getId()).getMenge()){
				spielerVerkauft = spielerMoegVerkauft;
			}
			else {
				spielerVerkauft = LagerArtikel.getEinzelPositionID(Artikel.getFahrradArtikel().getId()).getMenge();
			}		
			// Buchungen
			ArrayList<Object[]> sollbuchungen = new ArrayList<Object[]>();
			ArrayList<Object[]> habenbuchungen = new ArrayList<Object[]>();
			habenbuchungen.add(Buchung.erstelleEinzelbuchung("Fertige Erzeugnisse", spielerVerkauft * LagerArtikel.getEinzelPositionID(Artikel.getFahrradArtikel().getId()).getWert()));
			sollbuchungen.add(Buchung.erstelleEinzelbuchung("Abschreibungen", spielerVerkauft * LagerArtikel.getEinzelPositionID(Artikel.getFahrradArtikel().getId()).getWert()));
			
			spiel.getAktuelleRunde().getZugDesSpielers(spiel.getSpieler().get(i)).setVerkauft(spielerVerkauft);
			spiel.getSpieler().get(i).getUnternehmen().getLager().einlagernArtikel(Artikel.getFahrradArtikel(), - spielerVerkauft, 0);
			marktVerkauft = marktVerkauft + spielerVerkauft;
			
			sollbuchungen.add(Buchung.erstelleEinzelbuchung("Kasse", spielerVerkauft * spiel.getAktuelleRunde().getZugDesSpielers(spiel.getSpieler().get(i)).getVerkaufspreis()));
			habenbuchungen.add(Buchung.erstelleEinzelbuchung("Umsatzerloese", spielerVerkauft * spiel.getAktuelleRunde().getZugDesSpielers(spiel.getSpieler().get(i)).getVerkaufspreis()));
			Buchung.erstelleBuchung(sollbuchungen, habenbuchungen, spiel.getSpieler().get(i).getUnternehmen().getBilanz());
		}
		spiel.getAktuelleRunde().setMarktVerkauft(marktVerkauft);
	}
	// Markt auf Einkauf / Verkauf der Teile reagieren lassen
	public void updateMarktangebot(){
		// TODO!!!!
		for (int i = 0;i<marktArtikel.getPositionen().size();i++){	
			double quotMenge = Spiel.ARTIKELMENGEMARKT / (double)marktArtikel.getEinzelPositionNr(i).getMenge();
				marktArtikel.getEinzelPositionNr(i).setWert(quotMenge * marktArtikel.getEinzelPositionNr(i).getWert());
				marktArtikel.getEinzelPositionNr(i).setMenge(Spiel.ARTIKELMENGEMARKT);
		}
	}
	/////////////////////////////////////////////
	// Get Methoden
	public int getMartkvolumen() {
		return marktvolumen;
	}
	public Spiel getSpiel(){
		return spiel;
	}
	public Positionsliste getMarktArtikel() {
		return marktArtikel;
	}
/*	public Artikel getArtikel(int artikelID) throws NoObjectFoundException {
		return marktArtikel.getGut(artikelID);
	}
	public double getMarktpreis(int artikelID) throws NoObjectFoundException {
		return marktArtikel.getPosition(marktArtikel.getPositionsNr(artikelID)).getWert();
	}
*/	
	// Set Methoden
	public void setMartkvolumen(int neuesVolumen) {
		this.marktvolumen = neuesVolumen;
	}
	public void setMarktArtikel(Positionsliste neueMarktListe) {
		this.marktArtikel = neueMarktListe;
	}
	////////////////////////////////////////////

}
