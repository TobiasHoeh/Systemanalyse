package projekt.systemanalyse.shared.logistik;

import java.io.Serializable;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;


@SuppressWarnings("serial")
public class Bauteil extends Artikel implements Serializable {
	
	public static final int QUAL_SCHLECHT = 10;
	public static final int QUAL_MITTEL = 20;
	public static final int QUAL_GUT = 30;
	
	private int qualitaet;

	// Konstruktoren
	public Bauteil() {	}
	
	private Bauteil(int groesse, int typ, int qualitaet, String bezeichnung, String beschreibung) {
		super(groesse, typ,  bezeichnung, beschreibung );
		this.qualitaet = qualitaet;
	}
	public static Bauteil erzeugeBauteil(int groesse, int typ,int qualitaet, String bezeichnung, String beschreibung)
			throws InstanceCreationException {
		for (int i = 0;i<Artikel.getAlleArtikel().size();i++){
			if (Artikel.getAlleArtikel().get(i).getBezeichnung().equals(bezeichnung)){
				throw new InstanceCreationException("Der Artikel exisitert bereits!", Artikel.getAlleArtikel().get(i));
			}
		}
		return new Bauteil(groesse, typ, qualitaet, bezeichnung,beschreibung);
	}
	//////////////////////////////////////
	// Get Methoden
	public int getQualitaet() {
		return qualitaet;
	}
	
}

	