package projekt.systemanalyse.shared;

import java.io.Serializable;

import projekt.systemanalyse.shared.logistik.Artikel;

@SuppressWarnings("serial")
public class Position implements Serializable {

	private double wert;
	private int menge;
	private Artikel gut;

	// Konstruktoren
	public Position() {}
	
	public Position (Artikel referenzGut, int menge, double wert) {
		this.wert = wert;
		this.menge = menge;
		this.gut = referenzGut;
	}
	/////////////////////////////////
	// Logik
	public int berechneBelegterPlatz() {
		return this.menge * gut.getGroesse();
	}
	public double berechneGesamtWert(){
		return this.menge * this.wert;
	}
	public void updatePosition(int aenderungMenge, double einzelWert) {
		double zwischensumme = berechneGesamtWert() + aenderungMenge * einzelWert;
		this.menge = this.menge + aenderungMenge;
		if(this.menge == 0){
			this.wert = 0;
		}
		else{
			this.wert = zwischensumme / this.menge;
		}
	}	
	/////////////////////////////////
	// Get Methoden
	public double getWert() {
		return wert;
	}
	public int getMenge() {
		return menge;
	}
	public Artikel getGut() {
		return gut;
	}
	// Set Methoden
	public void setWert(double neuerWert){
		this.wert = neuerWert;
	}
	public void setMenge(int neueMenge){
		this.menge = neueMenge;
	}
	// ToString
	public String toString(){
		return (Artikel.typToString(gut.getTyp()) + " - " + gut.getBezeichnung() + " - " + menge + " - " + wert);
	}
}
