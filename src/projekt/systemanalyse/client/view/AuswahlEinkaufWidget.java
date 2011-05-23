package projekt.systemanalyse.client.view;

import java.util.ArrayList;

import projekt.systemanalyse.shared.Positionsliste;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Canvas;

public class AuswahlEinkaufWidget extends FlexTable {
//	private FlexTable this = new FlexTable();
	private ListBox lbTyp;
	private ListBox lbArtikel;
	private TextBox txtMenge;
	private TextBox txtPreis;
	private TextBox txtGesamt;
	private Button btnHinzufuegen;
	
	public AuswahlEinkaufWidget() {

		// Create a table to this the form options
		this.setCellSpacing(6);
		FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
		
		lbTyp = UIFactory.getListBox();
		lbArtikel = UIFactory.getListBox();
		txtMenge = UIFactory.getStyledTextBox("", false);
		txtPreis = UIFactory.getStyledTextBox("", true);
		txtGesamt = UIFactory.getStyledTextBox("", true);
		btnHinzufuegen = new Button("Artikel hinzuf&uuml;gen");
		
		
		// Add a title to the form
		this.setHTML(0, 0, "Artikelauswahl");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		txtPreis.setWidth("150px");
		txtGesamt.setWidth("150px");
		lbTyp.setWidth("150px");
		lbArtikel.setWidth("150px");
		txtMenge.setWidth("150px");
		
	    this.setHTML(1, 0, "Typ:");
	    this.setWidget(1, 1, lbTyp);
	    
	    this.setHTML(2, 0, "Artikel:");
	    this.setWidget(2, 1, lbArtikel);
	    
	    this.setHTML(3, 0, "Menge:");
	    this.setWidget(3, 1, txtMenge);
	    
	    this.setHTML(4, 0, "Preis:");
	    this.setWidget(4, 1, txtPreis);
	    
	    this.setHTML(5, 0, "Gesamt:");
	    this.setWidget(5, 1, txtGesamt);
	    
	    btnHinzufuegen.setWidth("150px");
	    this.setWidget(6, 1, btnHinzufuegen);

		
	}
	
	public Widget asWidget() {
		return this;
	}

	public ListBox getLbTyp() {
		return lbTyp;
	}

	public ListBox getLbArtikel() {
		return lbArtikel;
	}

	public TextBox getTxtMenge() {
		return txtMenge;
	}

	public TextBox getTxtPreis() {
		return txtPreis;
	}

	public TextBox getTxtGesamt() {
		return txtGesamt;
	}
	
	public Button getBtnHinzufuegen() {
		return btnHinzufuegen;
	}
	
	
}
