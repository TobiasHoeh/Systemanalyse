package projekt.systemanalyse.shared.aktionen;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.Buchung;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.logistik.Artikel;
import projekt.systemanalyse.shared.logistik.Bauteil;

@SuppressWarnings("serial")
public class ProduktionsAktion extends Aktion implements Serializable{
	
	// Konstruktoren
	public ProduktionsAktion(){	}
	
	private ProduktionsAktion(Zug einZug, Positionsliste produktionsListe){
		super(einZug, produktionsListe);
	}
	public static ProduktionsAktion erzeugeProdutkionsaktion(Zug einZug, Positionsliste produktionsListe){
		return new ProduktionsAktion(einZug, produktionsListe);
	}
	///////////////////////////////////
	// Logik
	public void ausfuehren() throws NoObjectFoundException, WertNullException  {
		// Produktionsmenge ermitteln
		ArrayList<Integer> alleVerbraeuche = new ArrayList<Integer>();
		Positionsliste alleAusschuesse = new Positionsliste();
		for (int i = 0; i < arbeitsListe.getSize(); i++) {
			int verbrauchbar = 0;
			int ausschuss = 0;
			for (int j = 0;j<arbeitsListe.getEinzelPositionNr(i).getMenge();j++){
				double wahrscheinlichkeit = Math.random();
				if (((Bauteil)arbeitsListe.getEinzelPositionNr(i).getGut()).getQualitaet() == Bauteil.QUAL_GUT){
					if (wahrscheinlichkeit > 0.1){
						verbrauchbar++;
					}
					else{
						ausschuss++;
					}
				}
				else if (((Bauteil)arbeitsListe.getEinzelPositionNr(i).getGut()).getQualitaet() == Bauteil.QUAL_SCHLECHT){
					if (wahrscheinlichkeit > 0.2){
						verbrauchbar++;
					}
					else{
						ausschuss++;
					}
				}
			}
			alleVerbraeuche.add(verbrauchbar);
			alleAusschuesse.erfassePosition(arbeitsListe.getEinzelPositionNr(i).getGut(), ausschuss, 0.0);
		}
		// Minimum der Verbrauchbaren Mengen ermitteln um so Produktionsmege festzustellen
		int minVerbrauch = Integer.MAX_VALUE;
		for (int i = 0;i<alleVerbraeuche.size();i++){
			if (alleVerbraeuche.get(i) < minVerbrauch){
				minVerbrauch = alleVerbraeuche.get(i);
			}
		}
		if (minVerbrauch == Integer.MAX_VALUE){
			throw new WertNullException("Die Produktionsliste ist leer?! Behandeln?");
		}
		// Lager verändern und Buchungen durchführen	
		double fahrradwert = 0;

		for (int i = 0; i < arbeitsListe.getSize(); i++) {
				ArrayList<Object[]> sollbuchungen = new ArrayList<Object[]>();
				ArrayList<Object[]> habenbuchungen = new ArrayList<Object[]>();
				double artikelwert = 0;
				int prodMenge = 0;
				double summeProd = 0;
				double summeAuss = 0;
				int gutID = arbeitsListe.getEinzelPositionNr(i).getGut().getId();
				artikelwert = lager.getArtikelliste().getEinzelPositionID(gutID).getWert();
				summeProd = minVerbrauch * artikelwert;
				try {
					summeAuss = alleAusschuesse.getEinzelPositionID(gutID).getMenge() * artikelwert;
					prodMenge = minVerbrauch + alleAusschuesse.getEinzelPositionID(gutID).getMenge();
				} catch (NoObjectFoundException e) {
					summeAuss = 0;
					prodMenge = minVerbrauch;
				}
				
				
				habenbuchungen.add(Buchung.erstelleEinzelbuchung("Fremdbauteile", summeProd));
				habenbuchungen.add(Buchung.erstelleEinzelbuchung("Fremdbauteile", summeAuss));
				sollbuchungen.add(Buchung.erstelleEinzelbuchung("Abschreibungen", summeAuss));
				sollbuchungen.add(Buchung.erstelleEinzelbuchung("Fertige Erzeugnisse", summeProd));
				Buchung.erstelleBuchung(sollbuchungen, habenbuchungen, bilanz);
				lager.einlagernArtikel(arbeitsListe.getEinzelPositionID(gutID).getGut(),-prodMenge, artikelwert);
				// Infos Speichern
				fahrradwert = fahrradwert + artikelwert;
		}
		zug.setAusschuesse(alleAusschuesse);
		zug.setProduziert(minVerbrauch);
		lager.einlagernArtikel(Artikel.getFahrradArtikel(), minVerbrauch, fahrradwert);
	}
}
