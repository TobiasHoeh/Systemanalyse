package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;

@SuppressWarnings("serial")
public class Spieler implements Serializable {
	
	private String name;
	private String firma;
	private Unternehmen unternehmen = null;
	private Spiel spiel = null;
	private int spielerID;
	private static int naechsteID = 0;
	private static ArrayList<Spieler> alleSpieler = new ArrayList<Spieler>();
	private static ArrayList<Spieler> spielerOhneSpiel = new ArrayList<Spieler>();
	
	private Spieler(String name, String firma) {
		this.name = name;
		this.firma = firma;
		this.spielerID = naechsteID;
		naechsteID++;
		alleSpieler.add(this);
		spielerOhneSpiel.add(this);
	}

	public Spieler() {
	}

	public static Spieler erzeugeSpieler(String name, String firma) throws InstanceCreationException {		
//		for (int i = 0;i<alleSpieler.size();i++){
//			if (alleSpieler.get(i).getName().equals(name)){
//				throw new InstanceCreationException("Ein Spieler mit dem Namen " + name + " ist bereits vorhanden!");
//			}
//		}
		return new Spieler(name, firma);
	}
	
	public void zuSpielZuweisen(Spiel einSpiel) throws SpielerZuweisenException, InstanceCreationException{
		if (this.spiel == null){
			einSpiel.spielerZuweisen(this);
			this.spiel = einSpiel;
			this.unternehmen = Unternehmen.gruendeUnternehmen(this,Spiel.LAGERKAPABEGIN,Spiel.STARTKAPITAL,Spiel.MITARBEITERANZBEGINN);
			spielerOhneSpiel.remove(this);
		}
		else {
			throw new SpielerZuweisenException ("Der Spieler ist bereits einem Spiel zugeweisen!");
		}
	}
	
	public void vonSpielEntfernen() throws NoObjectFoundException{
		if (this.spiel == null){
			throw new NoObjectFoundException("Der Spieler ist keinen Spiel zugeordnet!");
		}
		try {
			spiel.spielerEntfernen(this);
		} catch (NoObjectFoundException e) {	
		}
		this.spiel = null;
		Unternehmen.entferneUnternehmen(this.unternehmen);
		this.unternehmen = null;
		spielerOhneSpiel.add(this);
	}
	///////////////////////////////
	// Getter 
	public static ArrayList<Spieler> getAlleSpieler() {
		return alleSpieler;
	}
	public static ArrayList<Spieler> getSpielerOhneSpiel() {
		return spielerOhneSpiel;
	}
	public static Spieler getSpieler(int spielerID) throws NoObjectFoundException{
		for (int i = 0;i<alleSpieler.size();i++){
			if (alleSpieler.get(i).getSpielerID() == spielerID){
				return alleSpieler.get(i);
			}
		}
		throw new NoObjectFoundException("Ein Spieler mit dieser ID ist nicht vorhanden!");
	}	
	public String getName() {
		return name;
	}
	public String getFirma() {
		return firma;
	}
	public int getSpielerID(){
		return spielerID;
	}
	public Unternehmen getUnternehmen() {
		return unternehmen;
	}
	public Spiel getSpiel(){
		return spiel;
	}
	// Setter
	public void setSpielerName(String name) throws InstanceCreationException{
//		for (int i = 0;i<alleSpieler.size();i++){
//			if (alleSpieler.get(i).getName().equals(name)){
//				throw new InstanceCreationException("Ein Spieler mit dem Namen " + name + " ist bereits vorhanden!");
//			}
//		}
		this.name = name;
	}
	public void setSpielerFirma(String firma){
		this.firma = firma;
	}
	/////////////////////////////////////////////////
}
