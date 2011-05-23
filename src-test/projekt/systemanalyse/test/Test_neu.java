package projekt.systemanalyse.test;


import junit.framework.TestCase;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.aktionen.EinkaufsAktion;
import projekt.systemanalyse.shared.exceptions.FahrradListeException;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.RundeEndeException;
import projekt.systemanalyse.shared.exceptions.SpielEndeException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;
import projekt.systemanalyse.shared.exceptions.WertNullException;
import projekt.systemanalyse.shared.exceptions.ZugEndeException;
import projekt.systemanalyse.shared.logistik.Artikel;


public class Test_neu extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testErzeugeSpiel() {
	Spieler s1 = Spieler.erzeugeSpieler("Spieler 1", "Firma 1");
	
	try {
	Spiel testSpiel = Spiel.erzeugeSpiel("Spiel 1", 1, "fertig", s1);
	testSpiel.starteSpiel();
	System.out.println(testSpiel.getAktuelleRunde().getRundenNr());
	System.out.println(testSpiel.getAktuelleRunde().ausgebenLagerbestaendeAlle());
	Positionsliste einkaufsListe = new Positionsliste();
	einkaufsListe.erfassePosition(Artikel.getArtikel(201), 1000, 0);
  	EinkaufsAktion.erzeugeEinkaufsaktion(testSpiel.getAktuelleRunde().getZugDesSpielers(s1), einkaufsListe);
  	testSpiel.getAktuelleRunde().getZugDesSpielers(s1).beendeZug();
  	testSpiel.getAktuelleRunde().beendeRunde();
  	System.out.println(testSpiel.getMarkt().getMarktArtikel().listeAusgeben());
  	testSpiel.getAktuelleRunde().getZugDesSpielers(s1).beendeZug();
  	testSpiel.getAktuelleRunde().beendeRunde();
  	System.out.println(testSpiel.getMarkt().getMarktArtikel().listeAusgeben());
	} catch (InstanceCreationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SpielerZuweisenException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoObjectFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ZugEndeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WertNullException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FahrradListeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SpielEndeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (RundeEndeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	}

		

}
