package projekt.systemanalyse.shared.logistik;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;

@SuppressWarnings("serial")
public abstract class Artikel implements Serializable {

	private int id;
	private String bezeichnung;
	private String beschreibung;
	private int groesse;
	private int typ;
	private static int naechsteID = 200;
	private static ArrayList<Artikel> alleArtikel = new ArrayList<Artikel>();
	
	public static final int SATTEL = 500;
	public static final int LENKER = 501;
	public static final int RAHMEN = 502;
	public static final int SCHALTUNG = 503;
	public static final int RAEDER = 504;
	public static final int FAHRRAD = 600; 

	// Konstruktoren
	public Artikel() {}

	public Artikel(int groesse,int typ, String bezeichnung, String beschreibung ) {
		this.groesse = groesse;
		this.id = naechsteID;
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.typ = typ;
		alleArtikel.add(this);
		naechsteID++;
	}
	/////////////////////////////
	// set and get Methoden
	public int getId() {
		return id;
	}
	public int getGroesse() {
		return groesse;
	}
	public int getTyp() {
		return typ;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public static ArrayList<Artikel> getAlleArtikel(){
		return alleArtikel;
	}
	public static Artikel getArtikel(int artikelID) throws NoObjectFoundException {
		for(int i = 0; i<alleArtikel.size(); i++) {
			if(alleArtikel.get(i).id == artikelID) {
				return alleArtikel.get(i);
			}
		}
		throw new NoObjectFoundException("Ein Artikel mit dieser ID existiert nicht.");
	}
	public static Artikel getFahrradArtikel() throws NoObjectFoundException{
		for(int i = 0; i<alleArtikel.size(); i++) {
			if(alleArtikel.get(i).getTyp() == Artikel.FAHRRAD) {
				return alleArtikel.get(i);
			}
		}
		throw new NoObjectFoundException("Ein Fahrrad wurde nicht initialisiert.");
	}
	public static String typToString(int typ) {
		switch (typ) {
		case 500:
			return "Sattel";
		case 501:
			return "Lenker";
		case 502:
			return "Rahmen";
		case 503:
			return "Schaltung";
		case 504:
			return "Räder";
		case 505:
			return "Fahrrad";
		default:
			return "Typ";
		}
	}

	// Ende set and get Methoden
}
