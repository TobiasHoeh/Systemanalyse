package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Buchung implements Serializable {
	private ArrayList<Object[]> sollbuchungen;
	private ArrayList<Object[]> habenbuchungen;
	private Konto bilanz;

	private Buchung(ArrayList<Object[]> sollbuchungen,
			ArrayList<Object[]> habenbuchungen, Konto bilanz) {
		this.sollbuchungen = sollbuchungen;
		this.habenbuchungen = habenbuchungen;
		this.bilanz = bilanz;

	}

	public Buchung() {
	}

	public static Buchung erstelleBuchung(ArrayList<Object[]> sollbuchungen,
			ArrayList<Object[]> habenbuchungen, Konto bilanz) {
		Buchung neueBuchung = null;
		neueBuchung = new Buchung(sollbuchungen, habenbuchungen, bilanz);
		if (neueBuchung.buche()) {
			return neueBuchung;
		}
		return null;
	}

	public static Object[] erstelleEinzelbuchung(String konto, Double wert) {
		Object[] buchung = new Object[2];

		buchung[0] = konto;
		buchung[1] = wert;
		return buchung;
	}

	private boolean buche() {
		double sollbuchungenWert = 0;
		double habenbuchungenWert = 0;
		for (int i = 0; i < sollbuchungen.size(); i++) {
			Object[] temp;
			temp = (Object[]) sollbuchungen.get(i);
			sollbuchungenWert = sollbuchungenWert + (Double) temp[1];
		}
		for (int i = 0; i < habenbuchungen.size(); i++) {
			Object[] temp;
			temp = (Object[]) habenbuchungen.get(i);
			habenbuchungenWert = habenbuchungenWert + (Double) temp[1];
		}
		if (habenbuchungenWert == sollbuchungenWert) {
			for (int i = 0; i < sollbuchungen.size(); i++) {
				Object[] temp;
				Konto buchungskonto;
				temp = (Object[]) sollbuchungen.get(i);
				buchungskonto = bilanz.getKonto((String) temp[0]);
				if (!buchungskonto.buche((Double) temp[1], "Soll")) {
					System.out.println("Die Buchung auf das Konto "
							+ (String) temp[0] + " ist fehlgeschlagen");
					return false;
				} else {
					System.out.println("Die Buchung auf das Soll-Konto "
							+ (String) temp[0] + " mit dem Wert "
							+ (Double) temp[1] + " war erfolgreich");
				}
			}
			for (int i = 0; i < habenbuchungen.size(); i++) {
				Object[] temp;
				Konto buchungskonto;
				temp = (Object[]) habenbuchungen.get(i);
				buchungskonto = bilanz.getKonto((String) temp[0]);
				if (!buchungskonto.buche((Double) temp[1], "Haben")) {
					System.out.println("Die Buchung auf das Konto "
							+ (String) temp[0] + " ist fehlgeschlagen");
					return false;
				} else {
					System.out.println("Die Buchung auf das Haben-Konto "
							+ (String) temp[0] + " mit dem Wert "
							+ (Double) temp[1] + " war erfolgreich");
				}
			}
			// hier könnte noch getestet werden ob die Bilanz nach den Buchungen
			// noch ausgeglichen ist
			return true;
		}

		return false;
	}
}
