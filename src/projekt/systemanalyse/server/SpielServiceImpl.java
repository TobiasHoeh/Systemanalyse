package projekt.systemanalyse.server;

import java.util.ArrayList;

import projekt.systemanalyse.client.SpielService;
import projekt.systemanalyse.shared.Konto;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.Runde;
import projekt.systemanalyse.shared.aktionen.EinkaufsAktion;
import projekt.systemanalyse.shared.aktionen.ProduktionsAktion;
import projekt.systemanalyse.shared.aktionen.UnternehmensAktion;
import projekt.systemanalyse.shared.aktionen.VerkaufsAktion;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.RundeEndeException;
import projekt.systemanalyse.shared.exceptions.SpielAbbruchException;
import projekt.systemanalyse.shared.exceptions.SpielEndeException;
import projekt.systemanalyse.shared.exceptions.SpielStartException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.exceptions.ZugEndeException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SpielServiceImpl extends RemoteServiceServlet implements
		SpielService {
	
	public SpielServiceImpl() {
	}

	
	// Konstruktoren
	@Override
	public Spieler erzeugeSpieler(String name, String firma)throws InstanceCreationException{
		return Spieler.erzeugeSpieler(name, firma);
	}
	@Override
	public Spiel erzeugeSpiel(String name, int spieleranzahl, String siegbedingung, int hostSpielerID) throws InstanceCreationException, SpielerZuweisenException, NoObjectFoundException{
		return Spiel.erzeugeSpiel(name, spieleranzahl, siegbedingung, Spieler.getSpieler(hostSpielerID));
	}
	//////////////////////////////////////////////////////
	// Ablauf 
	@Override
	public void SpielUndSpielerVerbinden(int spielerID, int spielID) throws SpielerZuweisenException, InstanceCreationException, NoObjectFoundException{
		Spieler.getSpieler(spielerID).zuSpielZuweisen(Spiel.getSpiel(spielID));
	}
	@Override
	public void starteSpiel(int spielID) throws NoObjectFoundException, InstanceCreationException, SpielStartException{
		Spiel.getSpiel(spielID).starteSpiel();
	}
	@Override
	public void erzeugeEinkaufsAktion(int spielerID,Positionsliste einkaufsListe) throws NoObjectFoundException, InstanceCreationException{
		EinkaufsAktion.erzeugeEinkaufsaktion(Spieler.getSpieler(spielerID).getSpiel().getAktuelleRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)), einkaufsListe);
	}
	@Override
	public void erzeugeVerkaufsAktion(int spielerID,Positionsliste verkaufsListe)throws NoObjectFoundException {
		VerkaufsAktion.erzeugeVerkaufsAktion(Spieler.getSpieler(spielerID).getSpiel().getAktuelleRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)), verkaufsListe);
		
	}
	@Override
	public void erzeugeProduktionsAktion(int spielerID,Positionsliste produktionsListe)throws NoObjectFoundException {
		ProduktionsAktion.erzeugeProdutkionsaktion(Spieler.getSpieler(spielerID).getSpiel().getAktuelleRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)), produktionsListe);
		
	}
	@Override
	public void erzeugeUnternehmensAktion(int spielerID,int mitarbeiterAenderung, int lagerAenderung, double verkaufspreis)	throws NoObjectFoundException,InstanceCreationException {
		UnternehmensAktion.erzeugeUnternehmensaktion(Spieler.getSpieler(spielerID).getSpiel().getAktuelleRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)),mitarbeiterAenderung,lagerAenderung,verkaufspreis);
	}
	@Override
	public boolean beendeRunde(int spielID, int rundenNr) throws ZugEndeException, NoObjectFoundException,WertNullException, FahrradListeException, SpielEndeException, SpielAbbruchException  {	
		try {
			Spiel.getSpiel(spielID).getEineRunde(rundenNr).beendeRunde();
			return true;
		} catch (RundeEndeException e) {
			return false;
		}
	}
	@Override
	public void beendeZug(int spielerID) throws NoObjectFoundException{
		Spieler.getSpieler(spielerID).getSpiel().getAktuelleRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).beendeZug();
	}
	//
	@Override
	public void SpielUndSpielerTrennen(int spielerID, int spielID) throws NoObjectFoundException{
		Spieler.getSpieler(spielerID).vonSpielEntfernen();
	}
	@Override
	public void beendeSpiel(int spielID) throws NoObjectFoundException {
		Spiel.getSpiel(spielID).beendeSpiel();
	}
	///////////////////////////////////////////////////////
	// Getter
	@Override
	public ArrayList<Spiel> getAlleSpiele() {
		return Spiel.getAlleSpiele();
	}
	@Override
	public ArrayList<Spiel> getOffeneSpiele() {
		return Spiel.getOffeneSpiele();
	}
	@Override
	public String getSpielName(int spielID) throws NoObjectFoundException{
		return Spiel.getSpiel(spielID).getSpielName();
	}
	@Override
	public int getSpielerAnzahl(int spielID) throws NoObjectFoundException{
		return Spiel.getSpiel(spielID).getSpieleranzahl();
	}
	@Override
	public boolean getSpielIstGestartet(int spielID)throws NoObjectFoundException {
		return Spiel.getSpiel(spielID).getIstGestartet();
	}
	@Override
	public ArrayList<Spieler> getSpielerListe(int spielID) throws NoObjectFoundException {
		return Spiel.getSpiel(spielID).getSpieler();
	}
	@Override
	public Spieler getHostDesSpiels(int spielID) throws NoObjectFoundException {
		return Spiel.getSpiel(spielID).getSpieler().get(0);
	}
	@Override
	public int getSpielAktuelleRundenNr(int spielID) throws NoObjectFoundException {
		return Spiel.getSpiel(spielID).getAktuelleRunde().getRundenNr();
	}
	@Override
	public ArrayList<Runde> getRundenDesSpiels(int spielID) throws NoObjectFoundException{
		return Spiel.getSpiel(spielID).getRunden();
	}
	@Override
	public ArrayList<Spieler> getSpielerBeendeterZug(int spielID, int rundenNr)throws NoObjectFoundException {
		ArrayList<Spieler> spielerBeendeterZug = new ArrayList<Spieler>();
		for (int i = 0;i<Spiel.getSpiel(spielID).getEineRunde(rundenNr).getBeendeteZuege().size();i++){
			spielerBeendeterZug.add(Spiel.getSpiel(spielID).getEineRunde(rundenNr).getBeendeteZuege().get(i).getSpieler());
		}
		return spielerBeendeterZug;
	}
	@Override
	public ArrayList<Spieler> getSpielerOffenerZug(int spielID, int rundenNr)throws NoObjectFoundException {
		ArrayList<Spieler> spielerOffenerZug = new ArrayList<Spieler>();
		for (int i = 0;i<Spiel.getSpiel(spielID).getEineRunde(rundenNr).getOffeneZuege().size();i++){
			spielerOffenerZug.add(Spiel.getSpiel(spielID).getEineRunde(rundenNr).getOffeneZuege().get(i).getSpieler());
		}
		return spielerOffenerZug;
	}
	@Override
	public Spieler getSpieler(int spielerID)throws NoObjectFoundException{
		return Spieler.getSpieler(spielerID);
	}
	@Override
	public ArrayList<Spieler> getAlleSpieler() {
		return Spieler.getAlleSpieler();
	}
	@Override
	public ArrayList<Spieler> getSpielerOhneSpiel() {
		return Spieler.getSpielerOhneSpiel();
	}
	@Override
	public String getSpielerName(int spielerID)throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getName();
	}
	@Override
	public String getSpielerFirma(int spielerID)throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getFirma();
	}
	@Override
	public Spiel getSpielEinesSpielers(int spielerID)throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getSpiel();
	}
	@Override
	public Positionsliste getMarktArtikel(int spielID) throws NoObjectFoundException{
		return Spiel.getSpiel(spielID).getMarkt().getMarktArtikel();
	}
	@Override
	public Konto getBilanz(int spielerID) throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getUnternehmen().getBilanz();
	}
	@Override
	public Konto getGuV(int spielerID)throws NoObjectFoundException {
		
		return Spieler.getSpieler(spielerID).getUnternehmen().getGuV();
	}
	@Override
	public int getAnzahlMitarbeiter(int spielerID)throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getUnternehmen().getAnzahlMitarbeiter();
	}
	@Override
	public int getLagerKapazitaet(int spielerID) throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getUnternehmen().getLager().getKapazitaet();
	}
	@Override
	public int getLagerBelegt(int spielerID) throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getUnternehmen().getLager().berechneBelegteKapazitaet();
	}

	@Override
	public int getLagerFrei(int spielerID) throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getUnternehmen().getLager().berechneFreieKapazitaet();
	}
	@Override
	public Positionsliste getLagerbestaende(int spielerID)throws NoObjectFoundException {
		return Spieler.getSpieler(spielerID).getUnternehmen().getLager().getArtikelliste();
	}
	
	// Getter für Statistik
	@Override
	public Positionsliste getAusschuss(int spielerID)throws NoObjectFoundException {
		Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
		return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getAusschuesse();
	}
	@Override
	public int getSpielerProduziert(int spielerID)throws NoObjectFoundException {
		Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
		return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getProduziert();
	}
	@Override
	public int getMarktVerkauft(int spielID) throws NoObjectFoundException {
		return Spiel.getSpiel(spielID).getLetzteRunde().getMarktVerkauft();
		 
	}
	@Override
	public int getSpielerVerkauft(int spielerID) throws NoObjectFoundException {
		Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
		return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getVerkauft();
	}
	@Override
	public double getSpielerAusgaben(int spielerID) throws NoObjectFoundException {
		Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
		return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getAusgaben();
	}
	@Override
	public double getSpielerEinnahmen(int spielerID)throws NoObjectFoundException {
		Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
		return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getEinnahmen();
	}
	@Override
	public double getSpielerGewinn(int spielerID) throws NoObjectFoundException {
		Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
		return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getGewinn();
	}
	@Override
	public String getMarktEreignisText(int spielID)	throws NoObjectFoundException {
		Runde eineRunde = Spiel.getSpiel(spielID).getLetzteRunde();
		try {
			return eineRunde.getMarktEreignis().getBeschreibung();
		} catch (NoObjectFoundException e){
			return "Es hat kein Marktereignis stattgefunden.";
		}
	}
	@Override
	public ArrayList<Spieler> getRangfolge(int spielID)	throws NoObjectFoundException {
		return Spiel.getSpiel(spielID).getRangfolgeSpieler();
	}
	/////////////////////////////////////////////////////
	// Setter
	@Override
	public void setSpielerName(int spielerID, String name)throws NoObjectFoundException, InstanceCreationException{
		Spieler.getSpieler(spielerID).setSpielerName(name);
	}
	@Override
	public void setSpielerFirma(int spielerID, String firma)throws NoObjectFoundException{
		Spieler.getSpieler(spielerID).setSpielerFirma(firma);
	}
	
	
}