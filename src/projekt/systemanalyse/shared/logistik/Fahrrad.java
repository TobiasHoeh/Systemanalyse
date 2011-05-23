package projekt.systemanalyse.shared.logistik;

import java.io.Serializable;

import projekt.systemanalyse.shared.exceptions.InstanceCreationException;

@SuppressWarnings("serial")
public class Fahrrad extends Artikel implements Serializable {
	
	// Konstruktoren
	public Fahrrad() {	}

	private Fahrrad(int groesse,int typ, String bezeichnung, String beschreibung ) {
		super( groesse, typ, bezeichnung, beschreibung);
	}
	public static Fahrrad erzeugeFahrrad(int groesse,int typ, String bezeichnung, String beschreibung) throws InstanceCreationException {
		for (int i = 0;i<Artikel.getAlleArtikel().size();i++){
			if (Artikel.getAlleArtikel().get(i).getBezeichnung().equals(bezeichnung)){
				throw new InstanceCreationException("Das Fahrrad exisitert bereits!");
			}
		}
		return new Fahrrad(groesse, typ, bezeichnung, beschreibung);
	}
	////////////////////////////////
	
}
