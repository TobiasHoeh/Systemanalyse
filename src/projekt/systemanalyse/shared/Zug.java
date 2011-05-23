package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;
import projekt.systemanalyse.shared.aktionen.Aktion;
import projekt.systemanalyse.shared.ereignisse.Ereignis;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.WertNullException;


@SuppressWarnings("serial")
public class Zug implements Serializable {
	
	private Spieler spieler;
	private Runde runde;
	private boolean beendet = false;
	private ArrayList<Aktion> aktionen = new ArrayList<Aktion>();
	private Ereignis einzelEreignis = null;
	
	private int produziert = 0;
	private int verkauft = 0;
	private double verkaufspreis = 0;
	private Positionsliste ausschuesse;
	private double fixKosten = 0;
	private double einnahmen = 0;
	private double ausgaben = 0;
	
	// Konstruktoren
	public Zug() {}
	
	private Zug(Spieler spieler, Runde runde) {
		this.spieler = spieler;
		this.runde = runde;
	}
	public static Zug erzeugeZug(Spieler spieler, Runde runde) {
		return new Zug(spieler, runde);
	}
	/////////////////////////////////////
	// Ablauf
	public void hinzufuegenAktion(Aktion neueAktion) {
		aktionen.add(neueAktion);
	}
	public void hinzufuegenEreignis(Ereignis einEreignis) {
		einzelEreignis = einEreignis;;
	}
	public void hinzufuegenAusgaben(double eineAusgabe){
		this.ausgaben = this.ausgaben + eineAusgabe;
	}
	public void hinzufuegenEinnahmen(double eineEinnahme){
		this.einnahmen = this.einnahmen + eineEinnahme;
	}
	public void beendeZug() {
		
		
		
		
		
		beendet = true;
		// 10 Prozent gutes Ereignis + 10 Prozent schlechtes Ereignis -> später Balancing
		double wahrscheinlichkeit = Math.random();
		if (wahrscheinlichkeit < 0.1){
			// TODO erzeugen des Ereignisses
		}
		else if ( wahrscheinlichkeit > 0.9){
			// TODO erzeugen des Ereignisses
		}			
	}
	public void ausfuehrenZugVorMarkt() throws NoObjectFoundException, WertNullException, FahrradListeException{
		// Ereignis ausführen
		try{
			getEinzelEreignis().ausfuehren();
		} catch (WertNullException e){
		}
		// Aktionen ausführen
		for (int i = 0; i<aktionen.size();i++){
			aktionen.get(i).ausfuehren();
		}		
	}
	public void ausfuehrenZugNachMarkt(){
		hinzufuegenEinnahmen(verkauft * verkaufspreis);
		this.fixKosten = spieler.getUnternehmen().berechneFixkosten();
		hinzufuegenAusgaben(this.fixKosten);	
	}
	
	/////////////////////////////////////
	// Get Methoden
	public Spieler getSpieler() {
		return this.spieler;
	}
	public Runde getRunde() {
		return this.runde;
	}
	public boolean istBeendet(){
		return beendet;
	}
	public ArrayList<Aktion> getAktionen(){
		return aktionen;
	}
	public Ereignis getEinzelEreignis() throws WertNullException{
		if (einzelEreignis == null){
			throw new WertNullException ("Diesem Zug wurde kein Ereignis zugewiesen!");
		}
		return einzelEreignis;
	}
	public int getProduziert(){
		return produziert;
	}
	public Positionsliste getAusschuesse(){
		return ausschuesse;
	}
	public int getVerkauft(){
		return verkauft;
	}
	public double getVerkaufspreis(){
		return verkaufspreis;
	}
	public double getEinnahmen() {
		return einnahmen;
	}
	public double getAusgaben() {
		return ausgaben;
	}
	public double getGewinn(){
		return einnahmen - ausgaben;
	}
	// Set Methoden
	public void setProduziert(int produziert){
		this.produziert = produziert;
	}
	public void setAusschuesse(Positionsliste ausschuesse){
		this.ausschuesse = ausschuesse;
	}
	public void setVerkauft(int verkauft){
		this.verkauft = verkauft;
	}
	public void setVerkaufspreis(double verkaufspreis){
		this.verkaufspreis = verkaufspreis;
	}		
	
}
