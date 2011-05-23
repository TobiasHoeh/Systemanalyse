package projekt.systemanalyse.client;

import java.util.ArrayList;

import projekt.systemanalyse.shared.Konto;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Runde;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.SpielAbbruchException;
import projekt.systemanalyse.shared.exceptions.SpielStartException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.exceptions.ZugEndeException;
import projekt.systemanalyse.shared.exceptions.SpielEndeException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("spielService")
public interface SpielService extends RemoteService {

	// Konstruktoren
	Spieler erzeugeSpieler(String name, String firma) throws InstanceCreationException;
	Spiel erzeugeSpiel(String name, int spieleranzahl, String siegbedingung, int hostSpielerID) throws InstanceCreationException, SpielerZuweisenException, NoObjectFoundException;
	
	//Ablauf
	void SpielUndSpielerVerbinden (int spielerID, int spielID)throws SpielerZuweisenException, InstanceCreationException, NoObjectFoundException;
	void starteSpiel(int spielID)throws NoObjectFoundException,InstanceCreationException,SpielStartException;
	void erzeugeEinkaufsAktion(int spielerID, Positionsliste einkaufsListe)throws NoObjectFoundException, InstanceCreationException;
	void erzeugeVerkaufsAktion(int spielerID, Positionsliste verkaufsListe)throws NoObjectFoundException;
	void erzeugeProduktionsAktion(int spielerID, Positionsliste produktionsListe)throws NoObjectFoundException;	
	void erzeugeUnternehmensAktion(int spielerID, int mitarbeiterAenderung,int lagerAenderung, double verkaufspreis) throws NoObjectFoundException, InstanceCreationException;	
	void beendeZug(int spielerID) throws NoObjectFoundException;
	// @ Tobi: true bedeutet Runde wurde beendet - false wird gemeldet, wenn Runde schon beendet ist
	boolean beendeRunde(int spielID, int rundenNr) throws ZugEndeException, NoObjectFoundException, WertNullException, FahrradListeException,  SpielEndeException ,SpielAbbruchException  ;
	void beendeSpiel(int spielID) throws  NoObjectFoundException;
	void SpielUndSpielerTrennen(int spielerID, int spielID) throws NoObjectFoundException;
	
	// Getter
	ArrayList<Spiel> getAlleSpiele();
	ArrayList<Spiel> getOffeneSpiele();
	String getSpielName(int spielID) throws NoObjectFoundException;
	int getSpielerAnzahl(int spielID) throws NoObjectFoundException;
	boolean getSpielIstGestartet(int spielID) throws NoObjectFoundException;
	ArrayList<Spieler> getSpielerListe(int spielID) throws NoObjectFoundException;
	Spieler getHostDesSpiels(int spielID) throws NoObjectFoundException;
	int getSpielAktuelleRundenNr(int spielID) throws NoObjectFoundException;
	ArrayList<Runde> getRundenDesSpiels(int spielID) throws NoObjectFoundException;
	ArrayList<Spieler> getSpielerOffenerZug(int spielID, int rundenNr) throws NoObjectFoundException;
	ArrayList<Spieler> getSpielerBeendeterZug(int spielID, int rundenNr) throws NoObjectFoundException;
	Spieler getSpieler(int spielerID) throws NoObjectFoundException;
	ArrayList<Spieler> getAlleSpieler();
	ArrayList<Spieler> getSpielerOhneSpiel();
	String getSpielerName(int spielerID)throws NoObjectFoundException;
	String getSpielerFirma(int spielerID)throws NoObjectFoundException;
	Spiel getSpielEinesSpielers(int spielerID)throws NoObjectFoundException;
	Positionsliste getMarktArtikel(int spielID) throws NoObjectFoundException;
	Konto getBilanz(int spielerID)throws NoObjectFoundException;
	Konto getGuV(int spielerID)throws NoObjectFoundException;
	int getAnzahlMitarbeiter(int spielerID)throws NoObjectFoundException;
	int getLagerKapazitaet(int spielerID)throws NoObjectFoundException;
	int getLagerBelegt(int spielerID)throws NoObjectFoundException;
	int getLagerFrei(int spielerID)throws NoObjectFoundException;
	Positionsliste getLagerbestaende(int spielerID) throws NoObjectFoundException;
	
	// Getter für Statistik
	Positionsliste getAusschuss(int spielerID)throws NoObjectFoundException;
	int getSpielerProduziert(int spielerID)throws NoObjectFoundException;
	int getMarktVerkauft(int spielID)throws NoObjectFoundException;
	int getSpielerVerkauft(int spielerID)throws NoObjectFoundException;
	double getSpielerEinnahmen(int spielerID)throws NoObjectFoundException;
	double getSpielerAusgaben(int spielerID)throws NoObjectFoundException;
	double getSpielerGewinn(int spielerID)throws NoObjectFoundException;
	String getMarktEreignisText(int spielID) throws NoObjectFoundException;
	ArrayList<Spieler> getRangfolge(int spielID) throws NoObjectFoundException;
	
	
	// Setter
	void setSpielerName(int spielerID, String name)throws NoObjectFoundException, InstanceCreationException;
	void setSpielerFirma(int spielerID, String firma)throws NoObjectFoundException;
}
