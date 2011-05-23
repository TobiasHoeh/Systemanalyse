package projekt.systemanalyse.shared.aktionen;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.Buchung;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.WertNullException;

@SuppressWarnings("serial")
public class UnternehmensAktion extends Aktion implements Serializable{
	
	private int mitarbeiterAenderung = 0;
	private int lagerAenderung = 0;
	private double verkaufspreis = 0;
	
	public UnternehmensAktion(){}
	
	private UnternehmensAktion(Zug einZug, int mitarbeiterAenderung,int lagerAenderung, double verkaufspreis){
		super(einZug);
		this.mitarbeiterAenderung = mitarbeiterAenderung;
		this.lagerAenderung = lagerAenderung;
		this.verkaufspreis = verkaufspreis;
	}
	
	public static UnternehmensAktion erzeugeUnternehmensaktion(Zug einZug, int mitarbeiterAenderung,int lagerAenderung, double verkaufspreis) throws InstanceCreationException{
		if (-mitarbeiterAenderung > einZug.getSpieler().getUnternehmen().getAnzahlMitarbeiter()){
			throw new InstanceCreationException("Die Mitarbeiteranzahl darf nicht negativ sein!");
		}
		if(-lagerAenderung > einZug.getSpieler().getUnternehmen().getLager().berechneFreieKapazitaet()){
			throw new InstanceCreationException("Nur leere Lagerbereiche können abgegeben werden!");
		}
		return new UnternehmensAktion(einZug, mitarbeiterAenderung, lagerAenderung, verkaufspreis);
	}
	
	@Override
	public void ausfuehren() throws NoObjectFoundException, WertNullException,FahrradListeException {
		zug.setVerkaufspreis(this.verkaufspreis);
		zug.getSpieler().getUnternehmen().updateMitarbeiterAnzahl(mitarbeiterAenderung);
		lager.updateLagerkapazitaet(lagerAenderung);
		// Buchungen für Lageränderung
		ArrayList<Object[]> sollbuchungen = new ArrayList<Object[]>();
		ArrayList<Object[]> habenbuchungen = new ArrayList<Object[]>();
		double wertLageraenderung = Spiel.LAGERKOSTENEINHEIT * lagerAenderung; 
		if (lagerAenderung > 0){
			sollbuchungen.add(Buchung.erstelleEinzelbuchung("Gebaeude", wertLageraenderung));
			habenbuchungen.add(Buchung.erstelleEinzelbuchung("Kasse", wertLageraenderung));
			zug.hinzufuegenAusgaben(wertLageraenderung);
		}
		else{
			sollbuchungen.add(Buchung.erstelleEinzelbuchung("Kasse", -wertLageraenderung));
			habenbuchungen.add(Buchung.erstelleEinzelbuchung("Gebaeude", -wertLageraenderung));
			zug.hinzufuegenEinnahmen(-wertLageraenderung);
		}
		Buchung.erstelleBuchung(sollbuchungen, habenbuchungen, zug.getSpieler().getUnternehmen().getBilanz());
	}

}
