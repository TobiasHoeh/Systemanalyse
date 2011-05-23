package projekt.systemanalyse.client;

import java.util.ArrayList;

import projekt.systemanalyse.shared.Konto;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Runde;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SpielServiceAsync {

	// Konstruktoren
	void erzeugeSpieler(String name, String firma, AsyncCallback<Spieler> callback);
	void erzeugeSpiel(String name, int spieleranzahl, String siegbedingung, int hostSpielerID, AsyncCallback<Spiel> callback);
	
	//Ablauf
	void SpielUndSpielerVerbinden (int spielerID, int spielID, AsyncCallback<Void> callback);
	void starteSpiel(int spielID, AsyncCallback<Void> callback);
	void erzeugeEinkaufsAktion(int spielerID, Positionsliste einkaufsListe, AsyncCallback<Void> callback);
	void erzeugeVerkaufsAktion(int spielerID, Positionsliste verkaufsListe, AsyncCallback<Void> callback);
	void erzeugeProduktionsAktion(int spielerID, Positionsliste produktionsListe, AsyncCallback<Void> callback);	
	void erzeugeUnternehmensAktion(int spielerID, int mitarbeiterAenderung,int lagerAenderung, double verkaufspreis, AsyncCallback<Void> callback);	
	void beendeZug(int spielerID, AsyncCallback<Void> callback);
	// @ Tobi: true bedeutet Runde wurde beendet - false wird gemeldet, wenn Runde schon beendet ist
	void beendeRunde(int spielID, int rundenNr, AsyncCallback<Boolean> callback)  ;
	void beendeSpiel(int spielID, AsyncCallback<Void> callback);
	void SpielUndSpielerTrennen(int spielerID, int spielID, AsyncCallback<Void> callback);
	
	// Getter
	void getAlleSpiele(AsyncCallback<ArrayList<Spiel>> callback);
	void getOffeneSpiele(AsyncCallback<ArrayList<Spiel>> callback);
	void getSpielName(int spielID, AsyncCallback<String> callback);
	void getSpielerAnzahl(int spielID, AsyncCallback<Integer> callback);
	void getSpielIstGestartet(int spielID, AsyncCallback<Boolean> callback);
	void getSpielerListe(int spielID, AsyncCallback<ArrayList<Spieler>> callback);
	void getHostDesSpiels(int spielID, AsyncCallback<Spieler> callback);
	void getSpielAktuelleRundenNr(int spielID, AsyncCallback<Integer> callback);
	void getRundenDesSpiels(int spielID, AsyncCallback<ArrayList<Runde>> callback);
	void getSpielerOffenerZug(int spielID, int rundenNr, AsyncCallback<ArrayList<Spieler>> callback);
	void getSpielerBeendeterZug(int spielID, int rundenNr, AsyncCallback<ArrayList<Spieler>> callback);
	void getSpieler(int spielerID, AsyncCallback<Spieler> callback);
	void getAlleSpieler(AsyncCallback<ArrayList<Spieler>> callback);
	void getSpielerOhneSpiel(AsyncCallback<ArrayList<Spieler>> callback);
	void getSpielerName(int spielerID, AsyncCallback<String> callback);
	void getSpielerFirma(int spielerID, AsyncCallback<String> callback);
	void getSpielEinesSpielers(int spielerID, AsyncCallback<Spiel> callback);
	void getMarktArtikel(int spielID, AsyncCallback<Positionsliste> callback);
	void getBilanz(int spielerID, AsyncCallback<Konto> callback);
	void getGuV(int spielerID, AsyncCallback<Konto> callback);
	void getAnzahlMitarbeiter(int spielerID, AsyncCallback<Integer> callback);
	void getLagerKapazitaet(int spielerID, AsyncCallback<Integer> callback);
	void getLagerBelegt(int spielerID, AsyncCallback<Integer> callback);
	void getLagerFrei(int spielerID, AsyncCallback<Integer> callback);
	void getLagerbestaende(int spielerID, AsyncCallback<Positionsliste> callback);
	
	// Getter für Statistik
	void getAusschuss(int spielerID, AsyncCallback<Positionsliste> callback);
	void getSpielerProduziert(int spielerID, AsyncCallback<Integer> callback);
	void getMarktVerkauft(int spielID, AsyncCallback<Integer> callback);
	void getSpielerVerkauft(int spielerID, AsyncCallback<Integer> callback);
	void getSpielerEinnahmen(int spielerID, AsyncCallback<Double> callback);
	void getSpielerAusgaben(int spielerID, AsyncCallback<Double> callback);
	void getSpielerGewinn(int spielerID, AsyncCallback<Double> callback);
	void getMarktEreignisText(int spielID, AsyncCallback<String> callback);
	void getRangfolge(int spielID, AsyncCallback<ArrayList<Spieler>> callback);
	
	
	// Setter
	void setSpielerName(int spielerID, String name, AsyncCallback<Void> callback);
	void setSpielerFirma(int spielerID, String firma, AsyncCallback<Void> callback);
}
