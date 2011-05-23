//package projekt.systemanalyse.client.view.OLD;
//
//import projekt.systemanalyse.client.view.UIFactory;
//import projekt.systemanalyse.client.view.components.HelpBoxWidget;
//import projekt.systemanalyse.client.presenter.OLD.UnternehmenViewPresenter;
//
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DecoratorPanel;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HasHorizontalAlignment;
//import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.Widget;
////import com.smartgwt.client.widgets.Button;
//
////import com.smartgwt.client.widgets.events.HasClickHandlers;
//import com.smartgwt.client.widgets.Canvas;
//import com.smartgwt.client.widgets.layout.VLayout;
//
//public class UnternehmenView extends VLayout implements UnternehmenViewPresenter.Display{
//	private TextBox txtEinstellen = UIFactory.getStyledTextBox("", false);
//	private TextBox txtLagerbedarf = UIFactory.getStyledTextBox("", false);
//	private TextBox txtVerkaufspreis = UIFactory.getStyledTextBox("", false);
//	private Button btnUebernehmen = new Button("&Uuml;bernehmen");
//	public UnternehmenView() {
//		// add Members
//		addMember(new HelpBoxWidget(
//				"Auf dieser Seite kannst du deine Unternehmensplanung vornehmen. Du kannst Mitarbeiter einstellen oder entlassen. Die Kosten f&uuml;r einen Mitarbeiter betragen 2.000 Euro pro Monat. In der N&auml;he deines Standortes sind viele leerstehende Hallen zu finden, sodass du jederzeit deine Laerkapazit&auml;t zu einem Preis von 10% pro Lagereinheit erweitern oder verringern kannst. Au&szlig;erdem legst du auf dieser Seite den Verkaufspreis deiner Fahrr&auml;der fest.","Hilfe zur Unternehmensplanung"));
//		
//	    // Create a table to layout the form options
//	    FlexTable layout = new FlexTable();
//	    layout.setCellSpacing(6);
//	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
//
//	    // Add a title to the form
//	    layout.setHTML(0, 0, "Planung");
//	    cellFormatter.setColSpan(0, 0, 2);
//	    cellFormatter.setHorizontalAlignment(
//	        0, 0, HasHorizontalAlignment.ALIGN_CENTER);
//
//	    // Add some standard form options
//	    layout.setHTML(1, 0, "Mitarbeiter einstellen/entlassen:");
//	    layout.setWidget(1, 1, txtEinstellen);
//	    
//	    layout.setHTML(2, 0, "Lagerbedarf erweitern/verringern:");
//	    layout.setWidget(2, 1, txtLagerbedarf);
//	    
//	    layout.setHTML(3, 0, "Verkaufspreis f&uuml;r ein Fahrrad:");
//	    layout.setWidget(3, 1, txtVerkaufspreis);
////	    btnUebernehmen = UIFactory.getButton("&Uuml;bernehmen", UIFactory.BUTTON_STYLE);
//	    layout.setWidget(4, 1, btnUebernehmen);
//
//	    // Wrap the content in a DecoratorPanel
//	    DecoratorPanel unPlanung = new DecoratorPanel();
////	    Canvas unPlanung = new Canvas();
//	    unPlanung.setWidth("100%");
////	    unPlanung.addChild(layout);
//	    unPlanung.setWidget(layout);
//	    
//	    this.setWidth("100%");
//	    
//	    addMember(unPlanung);
//	}
//	
//	public Widget asWidget() {
//		return this;
//	}
//
//	@Override
//	public HasClickHandlers getBtnUebernehmen() {
//		return btnUebernehmen;
//	}
//
//	@Override
//	public HasValue<String> getTxtEinstellen() {
//		return txtEinstellen;
//	}
//
//	@Override
//	public HasValue<String> getTxtLagerbedarf() {
//		return txtLagerbedarf;
//	}
//
//	@Override
//	public HasValue<String> getTxtVerkaufspreis() {
//		return txtVerkaufspreis;
//	}
//	
//
//}
