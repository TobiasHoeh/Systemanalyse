package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.ereignisse.Ereignis;
import projekt.systemanalyse.shared.ereignisse.MarktEreignis;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.RundeEndeException;
import projekt.systemanalyse.shared.exceptions.SpielAbbruchException;
import projekt.systemanalyse.shared.exceptions.SpielEndeException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.exceptions.ZugEndeException;

@SuppressWarnings("serial")
public class Runde implements Serializable {

	private Spiel spiel;
	private int rundenNr;
	private ArrayList<Zug> zuege = new ArrayList<Zug>();
	private Ereignis marktEreignis = null;
	private int marktVerkauft = 0;
	private boolean istBeendet = false;

	// Konstruktoren
	public Runde() {}
	
	private Runde(Spiel einSpiel) {
		this.spiel = einSpiel;
		this.rundenNr = this.spiel.getRunden().size() +1;
		for (int i = 0; i < this.spiel.getSpieler().size(); i++) {
			zuege.add(Zug.erzeugeZug(this.spiel.getSpieler().get(i),this));
		}
		this.spiel.rundeHinzufuegen(this);
		// Markt zurücksetzen
		this.spiel.getMarkt().setMartkvolumen(Spiel.MARKTVOLUMEN);
	}
	public static Runde erzeugeRunde(Spiel einSpiel) {
		return new Runde(einSpiel);
	}
	////////////////////////////////////////////
	// Logik
	public void beendeRunde() throws ZugEndeException, NoObjectFoundException, WertNullException, FahrradListeException, SpielEndeException, RundeEndeException, SpielAbbruchException {
		// Prüfen ob beendet werden kann
		// falls eine Runde schon beendet war, ist er nicht in den SpielEndeScreen gesprungen, da er die Fehlermeldung nicht bekommen hat.
		// dadurch, dass wenn sie beendet wurde, dies nun zuerst geprüft wird, ist das kein Problem mehr.
		if (istBeendet == true){
			// Siegbedingungen prüfen
			if (spiel.pruefeSiegbedingung() == true) {
				throw new SpielEndeException("Die Siegbedingung des Spiels wurde ereicht!");
			}
			// Prüfen obb noch alle Spieler dabei sind
			if(spiel.getMaxSpielerAnzahl() != spiel.getSpieleranzahl()){
				throw new SpielAbbruchException("Das Spiel wurde abgebrochen.");
			}
			throw new RundeEndeException("Die Runde ist bereits beendet worden!");
		}	
		for (int i = 0;i<zuege.size();i++){
			if (zuege.get(i).istBeendet() == false){
				throw new ZugEndeException("Ein Spieler hat den Zug noch nicht beendet!");
			}
		}
		istBeendet = true;
		// Marktereignis auswählen
		// 10 Prozent gutes Ereignis + 10 Prozent schlechtes Ereignis -> später Balancing
		double wahrscheinlichkeit = Math.random();
		if (wahrscheinlichkeit < 0.1){
		marktEreignis = MarktEreignis.erzeugeMarktereignis(spiel.getMarkt(), 1.2,
				"Die Tour de France, die grade statt findet, löst einen wahren Fahrradhype aus. " +
				"Alle wollen plötzlich auch Fahrrad fahren. Es konnten bis zu 20 Prozent mehr Fahrräder verkauft werden!");
		}
		else if ( wahrscheinlichkeit > 0.9){
		marktEreignis = MarktEreignis.erzeugeMarktereignis(spiel.getMarkt(), 0.8,	
				"Früher Wintereinbruch: Bei 10 cm Schnee hat keiner mehr Lust, Fahrrad zu fahren." +
				"Der Markt ist um 20 Prozent eingebrochen.");
		}
		if (marktEreignis != null){
			marktEreignis.ausfuehren();
		}
		// Züge durchführen Teil 1
		for (int i = 0;i<zuege.size();i++) {
			zuege.get(i).ausfuehrenZugVorMarkt();
		}
		// Markt auf Aktionen reagieren lassen
		spiel.getMarkt().genereiereAbsaetze();
		spiel.getMarkt().updateMarktangebot();
		// Züge durchführen Teil 2
		for (int i = 0;i<zuege.size();i++) {
			zuege.get(i).ausfuehrenZugNachMarkt();
		}	
		// Siegbedingungen prüfen
		if (spiel.pruefeSiegbedingung() == true) {
			throw new SpielEndeException("Die Siegbedingung des Spiels wurde ereicht!");
		}
		// Prüfen obb noch alle Spieler dabei sind
		if(spiel.getMaxSpielerAnzahl() != spiel.getSpieleranzahl()){
			throw new SpielAbbruchException("Das Spiel wurde abgebrochen.");
		}
		// nächste Runde starten
		Runde.erzeugeRunde(spiel);
	}	
	////////////////////////////////
	// Get Methoden
	public Spiel getSpiel() {
		return spiel;
	}

	public int getRundenNr() {
		return rundenNr;
	}

	public ArrayList<Zug> getZuege(){
		return zuege;
	}
	public ArrayList<Zug> getBeendeteZuege(){
		ArrayList<Zug> beendeteZuege = new ArrayList<Zug>();
		for (int i = 0;i<zuege.size();i++){
			if (zuege.get(i).istBeendet() == true){
				beendeteZuege.add(zuege.get(i));
			}
		}
		return beendeteZuege;
	}
	public ArrayList<Zug> getOffeneZuege(){
		ArrayList<Zug> offeneZuege = new ArrayList<Zug>();
		for (int i = 0;i<zuege.size();i++){
			if (zuege.get(i).istBeendet() == false){
				offeneZuege.add(zuege.get(i));
			}
		}
		return offeneZuege;
	}
	public Zug getZugDesSpielers(Spieler einSpieler) throws NoObjectFoundException {
		Zug einZug = null;
		for (int i = 0;i<zuege.size();i++){
			if(zuege.get(i).getSpieler() == einSpieler){
				einZug = zuege.get(i);
			}
		}
		if (einZug == null){
			throw new NoObjectFoundException("Es existiert kein Zug für diesen Spieler!");
		}
		return einZug;
	}
	public int getMarktVerkauft(){
		return marktVerkauft;
	}
	public Ereignis getMarktEreignis() throws NoObjectFoundException{
		if (marktEreignis == null) throw new NoObjectFoundException ("Es wurde kein Ereignis ausgelöst");
		return marktEreignis;
	}
	// ToString
	public String ausgebenLagerbestaendeAlle(){
		String rueckgabe = "";
		for (int i = 0;i<spiel.getSpieler().size();i++){
			rueckgabe = rueckgabe + " --- Spieler " +  spiel.getSpieler().get(i).getSpielerID() + " --- \n";
			rueckgabe = rueckgabe + spiel.getSpieler().get(i).getUnternehmen().getLager().getArtikelliste().listeAusgeben();
		}
		return rueckgabe;
	}
	// Set Methoden
	public void setMarktVerkauft(int marktVerkauft){
		this.marktVerkauft = marktVerkauft;
	}

}
