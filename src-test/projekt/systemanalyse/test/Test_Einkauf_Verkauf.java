package projekt.systemanalyse.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import projekt.systemanalyse.shared.Konto;
import projekt.systemanalyse.shared.Markt;
import projekt.systemanalyse.shared.Runde;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.aktionen.EinkaufsAktion;
import projekt.systemanalyse.shared.logistik.Bauteil;
import projekt.systemanalyse.shared.logistik.Fahrrad;

public class Test_Einkauf_Verkauf extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testErzeugeSpiel() {
/*
		Spiel dasSpiel;

		// Spiel, Runde, Spieler erzeugen
		String[] spielername = new String[2];
		spielername[0] = "ErsterSpieler";
		spielername[1] = "ZweiterSpieler";
	
		String[] firma = new String[2];
		firma[0] = "Meine Firma1";
		firma[1] = "Meine Firma2";
		
		dasSpiel = Spiel.erzeugeSpiel(2, 3, "hallo", spielername, firma);
		dasSpiel.starteSpiel();
		Runde runde = Runde.erzeugeRunde(4, dasSpiel);
		//runde.starteRunde();
		Zug ersterZug = Zug.erzeugeZug(0, runde);
		
		System.out.println("#########################################");
		System.out.println("Die Kontosumme der Bilanz von Spieler 1 betr‰gt "
				+ ersterZug.getSpieler().getUnternehmen().getBilanz()
						.getKontosumme());
		
		System.out.println("#########################################");
		
		//ersterZug.ausfuehrenZug();
		ArrayList<Integer> artikelNummern = new ArrayList<Integer>();
		ArrayList<Integer> mengen = new ArrayList<Integer>();
		artikelNummern.add(1); mengen.add(10);
		artikelNummern.add(2); mengen.add(10);
		artikelNummern.add(3); mengen.add(10);
		ersterZug.beginneZug();
		System.out.println("#########################################");
		System.out.println("Einkauf");
		System.out.println("Einkaufsartikel hinzufuegen");
		ersterZug.verarbeiteDaten_EinkaufVerkauf(artikelNummern, mengen);
		ersterZug.beendeZug();
		
		System.out.println("#########################################");
		System.out.println("Bilanz abschlieﬂen");
		System.out.println("#########################################");
		System.out.println("Die Kontosumme der Bilanz betr‰gt "
				+ ersterZug.getSpieler().getUnternehmen().getBilanz()
						.getKontosumme());
		System.out.println("Die Saldo von Bauteile betr‰gt "
				+ ersterZug.getSpieler().getUnternehmen().getBilanz()
				.getKonto("Fremdbauteile").bildeSaldo());
		System.out.println("Die Saldo von Geldvermoegen betr‰gt "
				+ ersterZug.getSpieler().getUnternehmen().getBilanz()
				.getKonto("Geldvermoegen").bildeSaldo());
		assertEquals(0, ersterZug.getSpieler().getUnternehmen().getBilanz()
				.bildeSaldo(), 0);
		
		System.out.println("#########################################");
		System.out.println("Kapazit‰ten im Lager ermitteln");
		System.out.println("Die Kapazit‰t des Lagers betr‰gt: "
				+ ersterZug.getSpieler().getUnternehmen().getLager().getKapazitaet());
		System.out.println("Die freie Kapazit‰t betr‰gt: "
				+ ersterZug.getSpieler().getUnternehmen().getLager()
						.berechneFreieKapazitaet());
		System.out.println("Die belegte Kapazit‰t betr‰gt: "
				+ ersterZug.getSpieler().getUnternehmen().getLager()
						.berechneBelegteKapazitaet());
		assertEquals(4600, ersterZug.getSpieler().getUnternehmen().getLager()
				.berechneFreieKapazitaet());
		assertEquals(400, ersterZug.getSpieler().getUnternehmen().getLager()
				.berechneBelegteKapazitaet());
*/
	}
}
