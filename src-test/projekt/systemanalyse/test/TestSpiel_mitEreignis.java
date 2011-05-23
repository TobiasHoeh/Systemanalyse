package projekt.systemanalyse.test;

import junit.framework.TestCase;
import projekt.systemanalyse.shared.Konto;
import projekt.systemanalyse.shared.Markt;
import projekt.systemanalyse.shared.Runde;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Zug;
import projekt.systemanalyse.shared.aktionen.EinkaufsAktion;
import projekt.systemanalyse.shared.ereignisse.EinkaufsEreignis;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.logistik.Bauteil;
import projekt.systemanalyse.shared.logistik.Fahrrad;

public class TestSpiel_mitEreignis extends TestCase {

	// public TestSpiel(String name) {
	// super(name);
	// }

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
		String[] spielername = new String[4];
		spielername[0] = "ErsterSpieler";
		spielername[1] = "ZweiterSpieler";
		spielername[2] = "DritterSpieler";
		spielername[3] = "VierterSpieler";
		String[] firma = new String[4];
		firma[0] = "Meine Firma1";
		firma[1] = "Meine Firma2";
		firma[2] = "Meine Firma3";
		firma[3] = "Meine Firma4";

		dasSpiel = Spiel.erzeugeSpiel(4, 3, "hallo", spielername, firma);
		Runde runde = Runde.erzeugeRunde(4, dasSpiel);

		// Markt erstellen
		Markt markt;
		markt = Markt.erzeugeMarkt(1000, dasSpiel);
		try {
			markt.hinzufuegenArtikel(
					Bauteil.erzeugeBauteil(500, 10, 1, 1, "ersterTyp", "gut"),
					5000, 10);

		markt.hinzufuegenArtikel(
				Bauteil.erzeugeBauteil(501, 10, 2, 3, "zweiterTyp", "schlecht"),
				5000, 10);
		markt.hinzufuegenArtikel(
				Bauteil.erzeugeBauteil(502, 20, 3, 2, "zweiterTyp", "schlecht"),
				5000, 10);
		} catch (InstanceCreationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// runde.starteRunde();
		Zug ersterZug = Zug.erzeugeZug(0, runde);
		Zug zweiterZug = Zug.erzeugeZug(1, runde);
		Zug dritterZug = Zug.erzeugeZug(2, runde);
		Zug vierterZug = Zug.erzeugeZug(3, runde);
		System.out.println("#########################################");
		System.out.println("Vemoegenswerte zu Beginn");
		System.out.println("#########################################");
		System.out.println("Die Kontosumme der Bilanz beträgt "
				+ ersterZug.getSpieler().getUnternehmen().getBilanz()
						.getKontosumme());
		Konto geldvermoegen;
		geldvermoegen = ersterZug.getSpieler().getUnternehmen().getBilanz()
				.getKonto("Geldvermoegen");
		Konto bauteile;
		bauteile = ersterZug.getSpieler().getUnternehmen().getBilanz()
				.getKonto("Fremdbauteile");
		System.out.println("Die Saldo von Bauteile beträgt "
				+ bauteile.bildeSaldo());
		System.out.println("Die Saldo von Geldvermoegen beträgt "
				+ geldvermoegen.bildeSaldo());

		EinkaufsAktion einkauf = EinkaufsAktion.erzeugen(ersterZug);
		System.out.println("#########################################");
		System.out.println("Einkauf");
		System.out.println("#########################################");
		System.out.println("Einkaufsartikel hinzufuegen");
		try {
			einkauf.hinzufuegenEinkaufsArtikel(markt.getArtikel(500), 10);

			einkauf.hinzufuegenEinkaufsArtikel(markt.getArtikel(501), 10);
			einkauf.hinzufuegenEinkaufsArtikel(markt.getArtikel(502), 10);
		} catch (NoObjectFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ereignis ausführen");
		System.out.println();
		EinkaufsEreignis ereignis = EinkaufsEreignis.erzeugeEinkaufsereignis(
				"test", einkauf, 0.9, 0.9);
		ereignis.ausfuehren();
		System.out.println("Einkauf ausführen");
		System.out.println();
		einkauf.ausfuehren();
		System.out.println("#########################################");
		System.out.println("Bilanz abschließen");
		System.out.println("#########################################");
		System.out.println("Die Kontosumme der Bilanz beträgt "
				+ ersterZug.getSpieler().getUnternehmen().getBilanz()
						.getKontosumme());
		System.out.println("Die Saldo von Bauteile beträgt "
				+ bauteile.bildeSaldo());
		System.out.println("Die Saldo von Geldvermoegen beträgt "
				+ geldvermoegen.bildeSaldo());
		assertEquals(0, ersterZug.getSpieler().getUnternehmen().getBilanz()
				.bildeSaldo(), 0);
		System.out.println("#########################################");
		System.out.println("Kapazitäten im Lager ermitteln");
		System.out.println("#########################################");
		System.out.println("Die freie Kapazität beträgt: "
				+ ersterZug.getSpieler().getUnternehmen().getLager()
						.berechneFreieKapazitaet());
		System.out.println("Die belegte Kapazität beträgt: "
				+ ersterZug.getSpieler().getUnternehmen().getLager()
						.berechneBelegteKapazitaet());
		assertEquals(4640, ersterZug.getSpieler().getUnternehmen().getLager()
				.berechneFreieKapazitaet());
		assertEquals(360, ersterZug.getSpieler().getUnternehmen().getLager()
				.berechneBelegteKapazitaet());
*/
	}

}
