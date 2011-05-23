package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.logistik.Lager;

@SuppressWarnings("serial")
public class Unternehmen implements Serializable {

	private Spieler spieler; // Benötigt, da jeder Spieler nur ein Unternehmen besitzen soll
	private String firma;
	private Lager lager;
	private Konto bilanz;
	private Konto GuV;
	private int mitarbeiter;
	private static ArrayList<Unternehmen> alleUnternehmen = new ArrayList<Unternehmen>();

	// Konstruktoren
	public Unternehmen() {
	}

	private Unternehmen(Spieler einSpieler, int lagerkapazitaet, double startkapital,
			int mitarbeiter) {
		Konto temp;
		ArrayList<Object[]> sollbuchungen = new ArrayList<Object[]>();
		ArrayList<Object[]> habenbuchungen = new ArrayList<Object[]>();
		double gebauedeWert = lagerkapazitaet * Spiel.LAGERKOSTENEINHEIT;
		// Bilanz erstellen
		bilanz = Konto.erzeugeKonto("Bilanz", null, "Bestandskonto");
		// Haben-Seite der Bilanz erweitern
		temp = bilanz.addUnterKonto("Eigenkapital", "Haben");
		temp.addUnterKonto("Reinvermoegen", "Haben");
		// GuV erstellen und erweitern
		temp = temp.addUnterKonto("GuV", "Haben");
		//temp.addUnterKonto("Aufwendungen fuer Fremdbauteile", "Soll");
		temp.addUnterKonto("Abschreibungen", "Soll");
		temp.addUnterKonto("Umsatzerloese", "Haben");
		GuV = temp;
		// Soll-Seite der Bilanz erweitern
		bilanz.addUnterKonto("Gebaeude", "Soll");
		bilanz.addUnterKonto("Fremdbauteile", "Soll");
		bilanz.addUnterKonto("Fertige Erzeugnisse", "Soll");
		bilanz.addUnterKonto("Kasse", "Soll");
		sollbuchungen.add(Buchung.erstelleEinzelbuchung("Kasse",
				startkapital));
		sollbuchungen.add(Buchung.erstelleEinzelbuchung("Gebaeude", gebauedeWert));
		habenbuchungen.add(Buchung.erstelleEinzelbuchung(
				"Reinvermoegen", startkapital + gebauedeWert));
		Buchung.erstelleBuchung(sollbuchungen, habenbuchungen, bilanz);
		
		this.spieler = einSpieler;
		this.firma = einSpieler.getFirma();
		this.lager = Lager.erzeugeLager(lagerkapazitaet);
		this.mitarbeiter = mitarbeiter;
		alleUnternehmen.add(this);
	}

	public static Unternehmen gruendeUnternehmen(Spieler einSpieler,
			int lagerkapazitaet, double startkapital, int mitarbeiter) throws InstanceCreationException {
		for (int i = 0; i < alleUnternehmen.size(); i++) {
			if (alleUnternehmen.get(i).getSpieler() == einSpieler) {
				throw new InstanceCreationException("Der Spieler hat bereits eine Firma.");
			}
		}
		return new Unternehmen(einSpieler, lagerkapazitaet, startkapital, mitarbeiter);
	}
	/////////////////////////////
	// Logik
	public double berechneFixkosten(){
		ArrayList<Object[]> sollbuchungen = new ArrayList<Object[]>();
		ArrayList<Object[]> habenbuchungen = new ArrayList<Object[]>();
		double fixKosten =  mitarbeiter * Spiel.MITARBEITERGEHALT  + lager.berechneFreieKapazitaet() * Spiel.LAGERUNTERHALTLEEREINHEIT + lager.berechneBelegteKapazitaet() * Spiel.LAGERUNTERHALTVOLLEINHEIT;
		habenbuchungen.add(Buchung.erstelleEinzelbuchung("Kasse", fixKosten));
		sollbuchungen.add(Buchung.erstelleEinzelbuchung("Abschreibungen", fixKosten));
		Buchung.erstelleBuchung(sollbuchungen, habenbuchungen, bilanz);
		return fixKosten;
	}
	public void updateMitarbeiterAnzahl(int mitarbeiterAenderung){
		this.mitarbeiter = this.mitarbeiter + mitarbeiterAenderung;
	}
	////////////////////////////////////////
	// Get Methoden
	public Spieler getSpieler(){
		return spieler;
	}
	public String getFirma() {
		return firma;
	}
	public Konto getBilanz() {
		return bilanz;
	}
	public Konto getGuV() {
		return GuV;
	}
	public Lager getLager() {
		return lager;
	}
	public int getAnzahlMitarbeiter() {
		return mitarbeiter;
	}
	////////////////////////////////////////
	// Set Methoden
	public static void entferneUnternehmen(Unternehmen einUnternehmen){
		alleUnternehmen.remove(einUnternehmen);
	}
}
