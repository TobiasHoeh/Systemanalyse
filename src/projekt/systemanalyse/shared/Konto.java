package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;

// Tobias Klasse
@SuppressWarnings("serial")
public class Konto implements Serializable {

	private String bezeichnung;
	private Konto oberkonto = null;
	private String kontoArt;
	private ArrayList<Konto> unterkontenSoll = new ArrayList<Konto>();
	private ArrayList<Konto> unterkontenHaben = new ArrayList<Konto>();
	private ArrayList<Double> soll = new ArrayList<Double>();
	private ArrayList<Double> haben = new ArrayList<Double>();
	private boolean istAufgeloest = true;

	// private Konstruktor der Klasse Konto
	private Konto(String bezeichnung, Konto oberkonto, String kontoArt) {
		this.bezeichnung = bezeichnung;
		this.oberkonto = oberkonto;
		this.kontoArt = kontoArt;
		this.istAufgeloest = false;
	}

	public Konto() {
	}

	// erzeuge ein neues Konto und überprüfe ob es dieses Konto schon einmal gab
	public static Konto erzeugeKonto(String bezeichnung, Konto oberkonto,
			String kontoArt) {
		Konto neuesKonto = null;
		neuesKonto = new Konto(bezeichnung, oberkonto, kontoArt);
		if (neuesKonto.sucheDuplikate(bezeichnung, neuesKonto)) {
			// Werfe eine Exception
			if (neuesKonto.aufloesen()) {
				neuesKonto = null;
			}
		}
		return neuesKonto;
	}

	// füge ein Unterkonto auf der Soll oder auf der Haben-Seite hinzu
	public Konto addUnterKonto(String name, String SoderH) {
		if (!istAufgeloest()) {
			Konto neuesKonto = null;
			neuesKonto = Konto.erzeugeKonto(name, this, SoderH);
			if (neuesKonto != null) {
				if (SoderH.equals("Soll")) {
					unterkontenSoll.add(neuesKonto);
				} else {
					unterkontenHaben.add(neuesKonto);
				}
			} else {
				System.out
						.println("Es ist ein Fehler bei der Erstellung des Kontos aufgetreten.");
			}
			return neuesKonto;
		}
		// Exception schmeißen!!!
		return null;
	}

	// hole alle Unterkonten auf der Soll-Seite
	public ArrayList<Konto> getUnterkontenSoll() {
		if (unterkontenSoll.size() == 0) {
			return null;
		}
		return unterkontenSoll;
	}

	// hole alle Unterkonten auf der Haben-Seite
	public ArrayList<Konto> getUnterkontenHaben() {
		if (unterkontenHaben.size() == 0) {
			return null;
		}
		return unterkontenHaben;
	}

	// hole das Oberkonto
	public Konto getOberkonto() {
		return oberkonto;
	}

	public double getKontosumme() {
		if (!istAufgeloest()) {
			double sollwert = 0;
			double habenwert = 0;
			if (unterkontenSoll.size() > 0 || unterkontenHaben.size() > 0) {
				soll.clear();
				haben.clear();
			}
			for (int i = 0; i < unterkontenSoll.size(); i++) {
				double saldo;
				saldo = unterkontenSoll.get(i).bildeSaldo();
				if (saldo < 0) {
					haben.add(-saldo);
				} else {
					soll.add(saldo);
				}
			}

			for (int i = 0; i < unterkontenHaben.size(); i++) {
				double saldo;
				saldo = unterkontenHaben.get(i).bildeSaldo();
				if (saldo < 0) {
					soll.add(-saldo);
				} else {
					haben.add(saldo);
				}
			}
			for (int i = 0; i < soll.size(); i++) {
				sollwert = sollwert + soll.get(i);
			}

			for (int i = 0; i < haben.size(); i++) {
				habenwert = habenwert + haben.get(i);
			}
			if (habenwert > sollwert) {
				return habenwert;
			} else {
				return sollwert;
			}
		}
		return 0;
	}

	public boolean buche(double wert, String art) {
		if (!istAufgeloest()) {
			if (unterkontenSoll.size() > 0 && unterkontenHaben.size() > 0) {
				// Hier eine Exception schmeißen
				System.out
						.println("Das buchen ist nur auf der niedrigsten Stufe der Unterkonten erlaubt!");
				return false;
			}
			if (wert < 0) {
				// Achtung hier eine Exception schmeißen!!!

				System.out.println("Der Wert darf nicht kleiner 0 sein");
				return false;
			}

			if (art.equals("Soll")) {
				if (bildeSaldo() - wert < 0 && kontoArt.equals("Haben")) {
					System.out
							.println("Der Saldo darf nach der Buchung nicht 0 unterschreiten");
					return false;
				}
				soll.add(wert);
				return true;
			}
			if (art.equals("Haben")) {
				if (bildeSaldo() - wert < 0 && kontoArt.equals("Soll")) {
					System.out
							.println("Der Saldo darf nach der Buchung nicht 0 unterschreiten");
					return false;
				}
				haben.add(wert);
				return true;
			}
		}
		return false;
	}

	public double bildeSaldo() {
		if (!istAufgeloest()) {
			double sollwert = 0;
			double habenwert = 0;
			if (unterkontenSoll.size() > 0 || unterkontenHaben.size() > 0) {
				soll.clear();
				haben.clear();
			}
			for (int i = 0; i < unterkontenSoll.size(); i++) {
				double saldo;
				saldo = unterkontenSoll.get(i).bildeSaldo();
				if (saldo < 0) {
					haben.add(-saldo);
				} else {
					soll.add(saldo);
				}
			}

			for (int i = 0; i < unterkontenHaben.size(); i++) {
				double saldo;
				saldo = unterkontenHaben.get(i).bildeSaldo();
				if (saldo < 0) {
					soll.add(-saldo);
				} else {
					haben.add(saldo);
				}
			}

			for (int i = 0; i < soll.size(); i++) {
				sollwert = sollwert + soll.get(i);
			}

			for (int i = 0; i < haben.size(); i++) {
				habenwert = habenwert + haben.get(i);
			}
			if (kontoArt.equals("Soll"))
				return sollwert - habenwert;
			if (kontoArt.equals("Haben"))
				return habenwert - sollwert;
		}
		return 0;
	}

	// Hole ein beliebiges Konto unterhalb der Bilanz
	public Konto getKonto(String bezeichnung) {
		if (this.bezeichnung.equals(bezeichnung)) {
			return this;
		}
		for (int i = 0; i < unterkontenSoll.size(); i++) {
			Konto pruefKonto = null;
			pruefKonto = unterkontenSoll.get(i).getKonto(bezeichnung);
			if (pruefKonto != null)
				return pruefKonto;
		}
		for (int i = 0; i < unterkontenHaben.size(); i++) {
			Konto pruefKonto = null;
			pruefKonto = unterkontenHaben.get(i).getKonto(bezeichnung);
			if (pruefKonto != null)
				return pruefKonto;
		}

		return null;
	}

	// suche Duplikate um eine doppelte Bezeichnung zu vermeiden
	public boolean sucheDuplikate(String bezeichnung, Konto original) {
		Konto duplikat = null;
		if (this.oberkonto == null) {
			for (int i = 0; i < unterkontenSoll.size(); i++) {
				duplikat = unterkontenSoll.get(i).getKonto(bezeichnung);
				if (duplikat == original) {
					duplikat = null;
				}
			}
			for (int i = 0; i < unterkontenHaben.size(); i++) {
				duplikat = unterkontenHaben.get(i).getKonto(bezeichnung);
				if (duplikat == original) {
					duplikat = null;
				}
			}
			if (duplikat == null) {
				return false;
			} else {
				System.out.println("Ein Konto mit dem Namen " + bezeichnung
						+ " existiert bereits.");
				return true;
			}
		} else {
			return oberkonto.sucheDuplikate(bezeichnung, original);
		}
	}

	// pruefe ob dieses Konto bereits aufgeloest wurde
	private boolean istAufgeloest() {
		return istAufgeloest;
	}

	public boolean aufloesen() {
		boolean alleAufgeloest = true;

		for (int i = 0; i < unterkontenSoll.size(); i++) {
			boolean aufgeloest;
			aufgeloest = unterkontenSoll.get(i).istAufgeloest();
			if (aufgeloest == false) {
				alleAufgeloest = false;
			}
		}

		if (alleAufgeloest == false) {
			// schmeiß hier eine Exception
			return false;
		}

		for (int i = 0; i < unterkontenHaben.size(); i++) {
			boolean aufgeloest;
			aufgeloest = unterkontenHaben.get(i).aufloesen();
			if (aufgeloest == false) {
				alleAufgeloest = false;
			}
		}

		if (alleAufgeloest == false) {
			// schmeiß hier eine Exception
			return false;
		}

		this.istAufgeloest = true;
		this.bezeichnung = null;
		return true;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}

}
