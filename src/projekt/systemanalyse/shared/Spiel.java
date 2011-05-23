package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.SpielStartException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;
import projekt.systemanalyse.shared.logistik.Artikel;
import projekt.systemanalyse.shared.logistik.Bauteil;
import projekt.systemanalyse.shared.logistik.Fahrrad;

@SuppressWarnings("serial")
public class Spiel implements Serializable {
	
	private String name;
	private int spielID;
	private String siegbedingung;
	private int spieleranzahl;
	private ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	private Markt markt;
	private ArrayList<Runde> runden = new ArrayList<Runde>();
	private static int naechsteID = 0;
	private static ArrayList<Spiel> alleSpiele = new ArrayList<Spiel>();
	private static ArrayList<Spiel> offeneSpiele = new ArrayList<Spiel>();
	
	public static final double STARTKAPITAL = 100000;
	public static final int MARKTVOLUMEN = 5000;
	public static final int LAGERKAPABEGIN = 5000;
	public static final int MITARBEITERANZBEGINN = 3;
	public static final double LAGERKOSTENEINHEIT = 0.5;
	public static final double LAGERUNTERHALTLEEREINHEIT = 0.15;
	public static final double LAGERUNTERHALTVOLLEINHEIT = 0.35;
	public static final double MITARBEITERGEHALT = 2000;
	public static final int MITARBEITERPRODUKTION = 50;
	public static final int ARTIKELMENGEMARKT = 100000;
	
	// Konstruktoren
	public Spiel(){}

	private Spiel(String name, int spieleranzahl, String siegbedingung, Spieler hostSpieler)throws InstanceCreationException, SpielerZuweisenException {
		this.name  = name;
		this.spielID = naechsteID;
		this.siegbedingung = siegbedingung;
		this.spieleranzahl = spieleranzahl;
		naechsteID++;
		alleSpiele.add(this);
		offeneSpiele.add(this);
		hostSpieler.zuSpielZuweisen(this);
	}

	public static Spiel erzeugeSpiel(String name, int spieleranzahl, String siegbedingung, Spieler hostSpieler) throws InstanceCreationException ,SpielerZuweisenException{	
		for (int i=0; i<alleSpiele.size();i++){
			if (alleSpiele.get(i).getSpielName().equals(name)){
				throw new InstanceCreationException("Ein Spiel mit diesem Namen ist bereits vorhanden!");
			}
		}
		if (!(spieleranzahl > 0 && siegbedingung != "" )) {
			throw new InstanceCreationException("Ein Spiel muss min. einen Spieler besitzen und eine Siegbendingung haben.");
		}
		return new Spiel(name, spieleranzahl, siegbedingung, hostSpieler);
	}
	
	//////////////////////
	// Spieler zuweisen / entfernen
	public void spielerZuweisen(Spieler einSpieler) throws SpielerZuweisenException, InstanceCreationException{
		try{
			getEinSpielerDesSpiels(einSpieler.getSpielerID());
			throw new SpielerZuweisenException ("Der Spieler ist dem Spiel bereits zugeweisen!");
		}
		catch (NoObjectFoundException e){
			if(spieler.size() < spieleranzahl){
				this.spieler.add(einSpieler);
			}
			else{
				throw new SpielerZuweisenException("Das Spiel ist voll!");
			}
		}
	}
	
	public void spielerEntfernen(Spieler einSpieler) throws NoObjectFoundException{
			this.getEinSpielerDesSpiels(einSpieler.getSpielerID());
			if (this.getIstGestartet()){
				getAktuelleRunde().getZugDesSpielers(einSpieler).beendeZug();
			}
			this.spieler.remove(einSpieler);
			if (spieler.size() == 0){
				this.beendeSpiel();
			}
	}
	
	///////////////////////////////
	// Ablauf
	public void starteSpiel() throws InstanceCreationException, SpielStartException{
		if (this.spieler.size() != spieleranzahl){	
			throw new SpielStartException ("Die angegebene Spieleranzahl ist noch nicht erreicht.");
		}
		offeneSpiele.remove(this);
		this.markt = Markt.erzeugeMarkt(Spiel.MARKTVOLUMEN, this);
		this.initialisierenArtikelMarkt();
		Runde.erzeugeRunde(this);
	}
	
	public void beendeSpiel() throws NoObjectFoundException {
		for (int i = 0;i<spieler.size();i++){
			spieler.get(i).vonSpielEntfernen();
		}
		//TODO Rundenobjekte entfernen
		this.runden = null;
		//TODO Marktobjekt entfernen
		this.markt = null;
		alleSpiele.remove(this);
		System.out.println("Das Spiel wurde beendet");
	}
	
	//////////////////////////
	//Hilfsmethoden
	// wird vom Konstruktor der Runde benutzt
	public void rundeHinzufuegen(Runde eineRunde){
		if(!runden.contains(eineRunde)){
			runden.add(eineRunde);
		}
	}
	// Artikel erzeugen und auf Markt anbieten
	private void initialisierenArtikelMarkt() throws InstanceCreationException{
		// Fahrrad
		try{
		Fahrrad.erzeugeFahrrad(10,Artikel.FAHRRAD,"City-Bike","Dieses Fahrrad besticht durch sein überaus leichtgängiges Getriebe");
		} catch (InstanceCreationException e){	}
		// Sattel
		Artikel einArtikel;
		try{
			einArtikel = Bauteil.erzeugeBauteil(1,Artikel.SATTEL, Bauteil.QUAL_SCHLECHT, "Pauls Standardsattel S200", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 20);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(),ARTIKELMENGEMARKT, 20);
		}
		try{
			einArtikel = Bauteil.erzeugeBauteil(1,Artikel.SATTEL, Bauteil.QUAL_GUT, "Flo Flauschis superweiche Plüschsattel", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 40);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(),ARTIKELMENGEMARKT, 40);
		}
		// Lenker
		try{
			einArtikel = Bauteil.erzeugeBauteil(1,Artikel.LENKER, Bauteil.QUAL_SCHLECHT, "China Lenker \"Fählt lichtig\"", "Beschreibung"); ;
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 40);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel() ,ARTIKELMENGEMARKT, 40);
		}
		try{
			einArtikel = Bauteil.erzeugeBauteil(1,Artikel.LENKER, Bauteil.QUAL_GUT, "Susis Super-Lenker", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 80);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(), ARTIKELMENGEMARKT, 80);
		}
		// Rahmen		
		try{
			einArtikel = Bauteil.erzeugeBauteil(5,Artikel.RAHMEN, Bauteil.QUAL_SCHLECHT, "Knick-Knack Hartplastik-Rahmen", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 75);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(), ARTIKELMENGEMARKT, 75);
		}
		try{
			einArtikel = Bauteil.erzeugeBauteil(5,Artikel.RAHMEN, Bauteil.QUAL_GUT, "Aluminium-Rahmen \"Stabilio\"", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 80);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(), ARTIKELMENGEMARKT, 150);
		}
		// Schaltung
		try{
			einArtikel = Bauteil.erzeugeBauteil(1,Artikel.SCHALTUNG, Bauteil.QUAL_SCHLECHT, "Knick-Knack 3-Gang-Schaltung", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 60);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(), ARTIKELMENGEMARKT, 60);
		}
		try{
			einArtikel = Bauteil.erzeugeBauteil(1,Artikel.SCHALTUNG, Bauteil.QUAL_GUT, "Smooth-Tooth-21-Gang-Schaltung", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 120);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(), ARTIKELMENGEMARKT, 120);
		}
		// Reifen
		try{
			einArtikel = Bauteil.erzeugeBauteil(2,Artikel.RAEDER, Bauteil.QUAL_SCHLECHT, "China Reifen \"Lollt helum\"", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 45);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(), ARTIKELMENGEMARKT, 45);
		}
		try{
			einArtikel = Bauteil.erzeugeBauteil(2,Artikel.RAEDER, Bauteil.QUAL_GUT, "Rollin' Tyre Turbo-Reifen", "Beschreibung");
			markt.hinzufuegenArtikel(einArtikel,ARTIKELMENGEMARKT, 90);
		} catch (InstanceCreationException e){
			markt.hinzufuegenArtikel(e.getUebergabeArtikel(),ARTIKELMENGEMARKT, 90);
		}
		//
		for (int i = 0;i<spieler.size();i++){
			for (int j = 0;j<Artikel.getAlleArtikel().size();j++){
				spieler.get(i).getUnternehmen().getLager().einlagernArtikel(Artikel.getAlleArtikel().get(j),0, 0.0);
			}
		}	
	}
	// ueberpruefe die Siegbedingung
	public boolean pruefeSiegbedingung() {
		if (getAktuelleRunde().getRundenNr() == 12) {
			return true;
		}
		return false;
	}
	////////////////////////
	// Get Methoden
	public static Spiel getSpiel(int spielID) throws NoObjectFoundException{
		for (int i = 0;i<alleSpiele.size();i++){
			if (alleSpiele.get(i).getSpielID() == spielID){
				return alleSpiele.get(i);
			}
		}
		throw new NoObjectFoundException("Ein Spiel mit dieser ID ist nicht vorhanden!");
	}
	public static ArrayList<Spiel> getAlleSpiele() {
		return alleSpiele;
	}
	public static ArrayList<Spiel> getOffeneSpiele() {
		return offeneSpiele;
	}
	public String getSpielName(){
		return name;
	}
	public int getSpielID(){
		return spielID;
	}
	public int getSpieleranzahl() {
		return spieler.size();
	}
	public boolean getIstGestartet(){
		for (int i=0;i<Spiel.getOffeneSpiele().size();i++){
			if (Spiel.getOffeneSpiele().get(i).getSpielID() == spielID){
				return false;
			}
		}
		return true;
	}
	public ArrayList<Spieler> getSpieler(){
		return this.spieler;
	}
	public ArrayList<Spieler> getRangfolgeSpieler(){
		ArrayList<Spieler> rangfolge = new ArrayList<Spieler>();
		for (int i=0;i<spieler.size();i++){
			if (rangfolge.size() == 0){
				rangfolge.add(spieler.get(i));
			}
			else{
				double bilanzsumme = 0;
				int indexToAdd = 0;
				bilanzsumme = spieler.get(i).getUnternehmen().getBilanz().getKontosumme();
				for (int j = 0;j<rangfolge.size(); j++){
					if (rangfolge.get(j).getUnternehmen().getBilanz().getKontosumme() > bilanzsumme){
						if (rangfolge.size() == j){
							rangfolge.add(spieler.get(i));
						}
						else continue;
						}
					else {
						indexToAdd = j;
						break;
					}
				}
				rangfolge.add(indexToAdd, spieler.get(i));
			}
		}
		return rangfolge;
	}
	public Spieler getEinSpielerDesSpiels(int spielerID) throws NoObjectFoundException{
		for (int i=0;i<spieler.size();i++){
			if (spieler.get(i).getSpielerID() == spielerID){
				return spieler.get(i);
			}
		}
		throw new NoObjectFoundException("Der Spieler mit der ID " + spielerID + " ist dem Spiel nicht zugewiesen");
	}
	public Markt getMarkt(){
		return markt;
	}
	public ArrayList<Runde> getRunden() {
		return runden;
	}
	public Runde getAktuelleRunde(){
		return runden.get(runden.size()-1);
	}
	public Runde getEineRunde(int rundenNr) throws NoObjectFoundException{
		for (int i = 0; i<runden.size();i++){
			if( runden.get(i).getRundenNr() == rundenNr){
				return runden.get(i);
			}
		}
		throw new NoObjectFoundException("Es ist keine Runde mit der Rundennummer " + rundenNr + " vorhanden.");
	}
	public Runde getLetzteRunde(){
		return runden.get(runden.size()-2);
	}
	public int getMaxSpielerAnzahl() {
		return spieleranzahl;
	}
	////////////////////////////////
	
}
