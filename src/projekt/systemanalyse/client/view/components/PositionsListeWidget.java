package projekt.systemanalyse.client.view.components;

import projekt.systemanalyse.client.view.PositionsWidget;
import projekt.systemanalyse.client.view.UIFactory;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.logistik.Artikel;
import projekt.systemanalyse.shared.logistik.Bauteil;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.EdgedCanvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class PositionsListeWidget extends HLayout {
	
	VLayout links;
	VLayout rechts;
	PositionsWidget einkaufsTabelle;
	TextBox txtGesamtkosten;
	TextBox txtLagerbedarf;
	AuswahlEinkaufWidget auswahl;
	
	
	public PositionsListeWidget(){
		this.setWidth("100%");
		this.setHeight("100%");
		
		links = UIFactory.getVLayout("70%", "500px");
		rechts = UIFactory.getVLayout("30%", "100%");
		this.setBackgroundColor("#CCFFCC");
		
		Positionsliste einkaufArtikel = new Positionsliste();
		Artikel neuerArtikel;
		try {
			neuerArtikel = Bauteil.erzeugeBauteil(20, 3, 2, "zweiterTyp", "schlecht");
			einkaufArtikel.erfassePosition(neuerArtikel, 500, 1000);
			neuerArtikel = Bauteil.erzeugeBauteil(20, 3, 2, "dritter", "schlecht");
			einkaufArtikel.erfassePosition(neuerArtikel, 500, 1000);
		} catch (InstanceCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		einkaufsTabelle = new PositionsWidget();
		
	    // Create a table to layout the form options
	    FlexTable layout = new FlexTable();
	    layout.setCellSpacing(6);
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

	    // Add a title to the form
	    layout.setHTML(0, 0, "Gesamtverbrauch");
	    cellFormatter.setColSpan(0, 0, 2);
	    cellFormatter.setHorizontalAlignment(
	        0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    txtGesamtkosten = UIFactory.getStyledTextBox("TB1", true);
	    txtLagerbedarf = UIFactory.getStyledTextBox("TB1", true);
	    
	    // Add some standard form options
	    layout.setHTML(1, 0, "Gesamtkosten:");
	    layout.setWidget(1, 1, txtGesamtkosten);
	    layout.setHTML(2, 0, "Lagerbedarf:");
	    layout.setWidget(2, 1, txtLagerbedarf);

	    // Wrap the content in a DecoratorPanel
//	    DecoratorPanel gesamt = new DecoratorPanel();
	    Canvas gesamt = new Canvas();
	    gesamt.setWidth("100%");
//	    gesamt.setWidget(layout);
	    gesamt.addChild(layout);

	    auswahl = new AuswahlEinkaufWidget();
		
		// add Members to left and right side
	    links.addMember(UIFactory.getStyledLabel("Einkaufsartikel", 30, 30));
		links.addMember(einkaufsTabelle);
		
		rechts.addMember(auswahl);
		rechts.addMember(gesamt);
		
		this.addMember(links);
		this.addMember(rechts);
	}
	

	
	public Widget asWidget() {
		return this;
	}
	
	public VLayout getLinks() {
		return links;
	}

	public VLayout getRechts() {
		return rechts;
	}

	public PositionsWidget getEinkaufsTabelle() {
		return einkaufsTabelle;
	}

	public TextBox getTxtGesamtkosten() {
		return txtGesamtkosten;
	}

	public TextBox getTxtLagerbedarf() {
		return txtLagerbedarf;
	}

	public AuswahlEinkaufWidget getAuswahl() {
		return auswahl;
	}
}
