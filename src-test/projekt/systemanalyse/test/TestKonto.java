package projekt.systemanalyse.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import projekt.systemanalyse.shared.Buchung;
import projekt.systemanalyse.shared.Konto;

public class TestKonto extends TestCase {

	// public TestKonto(String name) {
	// super(name);
	// }

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testErzeugeKonto() {
		Konto bilanz = Konto.erzeugeKonto("Bilanz", null, "Soll");
		Konto taMasch = bilanz.addUnterKonto("Techn. Anlagen und Maschinen",
				"Soll");
		Konto eigenkapital = bilanz.addUnterKonto("Eigenkapital", "Haben");
		Konto gezKapital = eigenkapital.addUnterKonto("Gezeichnetes Kapital",
				"Haben");
		Konto guv = eigenkapital.addUnterKonto("GuV", "Haben");
		Konto maschine = taMasch.addUnterKonto("Maschine", "Soll");
		Konto aufwand = guv.addUnterKonto("Aufwand", "Soll");
		Konto Ertrag = guv.addUnterKonto("Ertrag", "Haben");
		Konto bank = bilanz.addUnterKonto("Bank", "Soll");

		// pruefe ob duplikate erkannt werden
		Konto Ertrag2 = guv.addUnterKonto("Ertrag", "Haben");
		Konto Ertrag3 = bilanz.addUnterKonto("Ertrag", "Soll");
		assertNull(Ertrag2);
		assertNull(Ertrag3);

		// buche Zugänge
		Ertrag.buche(10000, "Haben");
		Ertrag.buche(20000, "Haben");
		gezKapital.buche(50000, "Haben");
		maschine.buche(80000, "Soll");

		// pruefe Saldo und buchungen
		assertEquals(30000, Ertrag.bildeSaldo(), 0);
		assertEquals(30000, Ertrag.bildeSaldo(), 0);
		assertEquals(false, maschine.buche(1000000, "Haben")); // Saldo muss > 0
																// sein
		assertEquals(false, bilanz.buche(10000, "Haben")); // das buchen ist nur
															// auf der
															// niedrigsten Stufe
															// erlaubt
		assertEquals(false, guv.buche(10000, "Haben")); // das buchen ist nur
														// auf der niedrigsten
														// Stufe erlaubt
		assertEquals(30000, guv.bildeSaldo(), 0);
		assertEquals(30000, guv.bildeSaldo(), 0);
		assertEquals(0, bilanz.bildeSaldo(), 0);

		// buche Aufwände
		aufwand.buche(50000, "Soll");
		assertEquals(50000, aufwand.bildeSaldo(), 0);
		assertEquals(-20000, guv.bildeSaldo(), 0);
		assertEquals(30000, eigenkapital.bildeSaldo(), 0);
		assertEquals(50000, bilanz.bildeSaldo(), 0);
		maschine.buche(80000, "Soll");
		assertEquals(130000, bilanz.bildeSaldo(), 0);
		aufwand.buche(30000, "Soll");
		assertEquals(-50000, guv.bildeSaldo(), 0);
		assertEquals(0, eigenkapital.bildeSaldo(), 0);
		assertEquals(160000, bilanz.bildeSaldo(), 0);
		Ertrag.buche(160000, "Haben");
		assertEquals(110000, guv.bildeSaldo(), 0);
		assertEquals(0, bilanz.bildeSaldo(), 0);

		ArrayList<Object[]> sollbuchung = new ArrayList<Object[]>();
		ArrayList<Object[]> habenbuchung = new ArrayList<Object[]>();

		sollbuchung.add(Buchung.erstelleEinzelbuchung("Aufwand", 10000.0));
		habenbuchung.add(Buchung.erstelleEinzelbuchung("Maschine", 10000.0));

		sollbuchung.add(Buchung.erstelleEinzelbuchung("Bank", 10000.0));
		habenbuchung.add(Buchung.erstelleEinzelbuchung("Maschine", 10000.0));
		sollbuchung.add(Buchung.erstelleEinzelbuchung("Bank", 5000.0));
		habenbuchung.add(Buchung.erstelleEinzelbuchung("Ertrag", 5000.0));

		Buchung buchung;
		buchung = Buchung.erstelleBuchung(sollbuchung, habenbuchung, bilanz);
		assertEquals(105000, guv.bildeSaldo(), 0);
		assertEquals(0, bilanz.bildeSaldo(), 0);
		assertEquals(155000, bilanz.getKontosumme(), 0);

	}

	// public void testAddUnterKonto() {
	// fail("Not yet implemented");
	// }
	//
	// public void testGetUnterkontenSoll() {
	// fail("Not yet implemented");
	// }
	//
	// public void testGetUnterkontenHaben() {
	// fail("Not yet implemented");
	// }
	//
	// public void testGetOberkonto() {
	// fail("Not yet implemented");
	// }
	//
	// public void testUpdate() {
	// fail("Not yet implemented");
	// }
	//
	// public void testBildeSaldo() {
	// fail("Not yet implemented");
	// }

}
