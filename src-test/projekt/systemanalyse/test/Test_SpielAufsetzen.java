package projekt.systemanalyse.test;

import junit.framework.TestCase;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;

public class Test_SpielAufsetzen extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testErzeugeSpiel() {
	Spieler s1 = Spieler.erzeugeSpieler("Spieler 1", "Firma 1");
	Spieler s2 = Spieler.erzeugeSpieler("Spieler 2", "Firma 2");
	Spieler s11 = Spieler.erzeugeSpieler("Spieler 11", "Firma 11");
	Spieler s12 = Spieler.erzeugeSpieler("Spieler 12", "Firma 12");
	
	
	Spiel testSpiel;
	Spiel testSpiel2;
	try {
		testSpiel = Spiel.erzeugeSpiel("Spiel 1", 2, "fertig", s1);
		//s1.zuSpielZuweisen(testSpiel);
		s2.zuSpielZuweisen(testSpiel);
		testSpiel.starteSpiel();
		System.out.println(testSpiel.getAktuelleRunde().getRundenNr());
		System.out.println(testSpiel.getAktuelleRunde().ausgebenLagerbestaendeAlle());
		s1.vonSpielEntfernen();
		System.out.println(testSpiel.getAktuelleRunde().getRundenNr());
		System.out.println(testSpiel.getAktuelleRunde().ausgebenLagerbestaendeAlle());
		
		testSpiel2 = Spiel.erzeugeSpiel("Spiel 2", 2, "fertig", s11);
		s12.zuSpielZuweisen(testSpiel);
		testSpiel2.starteSpiel();
		System.out.println(testSpiel2.getAktuelleRunde().getRundenNr());
		System.out.println(testSpiel2.getAktuelleRunde().ausgebenLagerbestaendeAlle());
		
	} catch (InstanceCreationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SpielerZuweisenException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoObjectFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}

		

}
