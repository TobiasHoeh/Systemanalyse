package projekt.systemanalyse.shared;

import java.io.Serializable;
import java.util.ArrayList;

import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.logistik.Artikel;

@SuppressWarnings("serial")
public class Positionsliste implements Serializable {
	
	private ArrayList<Position> positionen = new ArrayList<Position>();
	
	// Konstruktoren
	public Positionsliste(){}
	//////////////////////////////////////////
	// Logik
	public void erfassePosition(Artikel referenzGut, int menge, double wert) {
		Position neuePosition = new Position(referenzGut, menge, wert);
		positionen.add(neuePosition);
	}
	
	public void erfassePosition(Position position) {
		positionen.add(position);
	}
	
	public void entfernePosition(Position position) {
		positionen.remove(position);
	}
	
	public Position getEinzelPositionNr(int positionsNr)  {
		return positionen.get(positionsNr);
	}
	public Position getEinzelPositionID(int gutID) throws NoObjectFoundException{
		for(int i = 0; i<positionen.size(); i++) {
			if(positionen.get(i).getGut().getId() == gutID){
				return positionen.get(i);
			}
		}
		throw new NoObjectFoundException("In der Liste ist kein Gut mit der ID \"" + gutID + "\".");
	}
	
	public int getPositionsNr(int gutID) throws NoObjectFoundException {
		for(int i = 0; i<positionen.size(); i++) {
			if(positionen.get(i).getGut().getId() == gutID) {
				return i;
			}
		}
		throw new NoObjectFoundException("In der Liste ist kein Gut mit der ID \"" + gutID + "\".");
	}
	public Artikel getGut(int gutID) throws NoObjectFoundException {
		for(int i = 0; i<positionen.size(); i++) {
			if(positionen.get(i).getGut().getId() == gutID) {
				return positionen.get(i).getGut();
			}
		}
		throw new NoObjectFoundException("In der Liste ist kein Gut mit der ID \"" + gutID + "\".");
	}
	
	public Position getPosition(String bezeichnung) throws NoObjectFoundException {
		for(int i = 0; i<positionen.size(); i++) {
			if(positionen.get(i).getGut().getBezeichnung().equals(bezeichnung)) {
				return positionen.get(i);
			}
		}
		throw new NoObjectFoundException("Die Position mit der Bezeichnung \"" + bezeichnung + "\" konnte nicht gefunden werden.");
	}
	
	/////////////////////////////
	// Get Methoden
	public ArrayList<Position> getPositionen() {
		return positionen;
	}
	
	public int getSize() {
		return positionen.size();
	}
	// ToString
	public String listeAusgeben(){
		String rueckgabe = "";
		for (int i = 0 ;i<positionen.size();i++){
			rueckgabe = rueckgabe + positionen.get(i).toString() +  "\n" ;
		}
		return rueckgabe;
	}

}
