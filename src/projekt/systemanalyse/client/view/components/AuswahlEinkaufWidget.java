package projekt.systemanalyse.client.view.components;

import java.util.ArrayList;

import projekt.systemanalyse.client.view.UIFactory;
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

public class AuswahlEinkaufWidget extends Canvas {
	private FlexTable layout = new FlexTable();
	private ListBox lbTyp;
	private ListBox lbArtikel;
	private TextBox txtMenge;
	private TextBox txtPreis;
	private TextBox txtGesamt;
	private Button btnHinzufuegen;
	
	public AuswahlEinkaufWidget() {

		// Create a table to layout the form options
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
		
		lbTyp = UIFactory.getListBox();
		lbArtikel = UIFactory.getListBox();
		txtMenge = UIFactory.getStyledTextBox("", false);
		txtPreis = UIFactory.getStyledTextBox("", true);
		txtGesamt = UIFactory.getStyledTextBox("", true);
		btnHinzufuegen = new Button("Artikel hinzuf&uuml;gen");
		
		
		// Add a title to the form
		layout.setHTML(0, 0, "Artikelauswahl");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		txtPreis.setWidth("150px");
		txtGesamt.setWidth("150px");
		lbTyp.setWidth("150px");
		lbArtikel.setWidth("150px");
		txtMenge.setWidth("150px");
		
	    layout.setHTML(1, 0, "Typ:");
	    layout.setWidget(1, 1, lbTyp);
	    
	    layout.setHTML(2, 0, "Artikel:");
	    layout.setWidget(2, 1, lbArtikel);
	    
	    layout.setHTML(3, 0, "Menge:");
	    layout.setWidget(3, 1, txtMenge);
	    
	    layout.setHTML(4, 0, "Preis:");
	    layout.setWidget(4, 1, txtPreis);
	    
	    layout.setHTML(5, 0, "Gesamt:");
	    layout.setWidget(5, 1, txtGesamt);
	    
	    btnHinzufuegen.setWidth("150px");
	    layout.setWidget(6, 1, btnHinzufuegen);

		// Wrap the content in a DecoratorPanel
		this.setWidth("100%");
		this.addChild(layout);
		
	}
	
	public Widget asWidget() {
		return this;
	}

	public FlexTable getLayout() {
		return layout;
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
