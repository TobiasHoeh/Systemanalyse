//package projekt.systemanalyse.client.view.OLD;
//
//import java.util.ArrayList;
//
//import projekt.systemanalyse.client.view.KontoWidget;
//import projekt.systemanalyse.client.view.UIFactory;
//import projekt.systemanalyse.client.view.components.RundeBeendenWidget;
//import projekt.systemanalyse.client.presenter.OLD.InformationAreaPresenter;
//
//import com.google.gwt.user.client.ui.CaptionPanel;
//import com.google.gwt.user.client.ui.DecoratorPanel;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HasHorizontalAlignment;
//import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.smartgwt.client.types.Alignment;
//import com.smartgwt.client.types.VerticalAlignment;
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//import com.smartgwt.client.widgets.layout.HLayout;
//import com.smartgwt.client.widgets.layout.LayoutSpacer;
//import com.smartgwt.client.widgets.layout.VLayout;
//import com.smartgwt.client.widgets.Button;
//import com.smartgwt.client.widgets.Canvas;
//import com.smartgwt.client.widgets.Label;
//import com.smartgwt.client.widgets.Progressbar;
//
//public class InformationAreaView extends VLayout implements
//		InformationAreaPresenter.Display {
//
//	private Button btnZugBeenden;
//	private TextBox txtLagerauslastung = UIFactory.getStyledTextBox("", true);
//	private TextBox txtMitarbeiteranzahl = UIFactory.getStyledTextBox("", true);
//	private KontoWidget kontoWidget = new KontoWidget();
//	private HLayout bilanz = new HLayout();
//
//	public InformationAreaView() {
//		// setBackgroundColor("#FFCC33");
//		setWidth("30%");
//		setHeight("100%");
//		// setHorizontalAlignment(ALIGN_RIGHT);
//		// setBorder("solid");
//		// bilanz = UIFactory.getHLayout("100%", "30%");
//		// auslastung = UIFactory.getCanvas("100%", "10%");
//		// spacer = UIFactory.getCanvas("100%", "100%");
//		// beenden = UIFactory.getCanvas("100%", "20%");
//
//		// addMember(new KontoWidget("Bilanz", bilanzSpalten, zeilen));
//		// addMember(gesamt);
//		// kontoWidget = new KontoWidget("Bilanz", bilanzSpalten, soll, haben);
//		// bilanz.addChild(kontoWidget);
//		// auslastung.addChild(gesamt);
//		// beenden.addChild(new RundeBeendenWidget());
//
//		// addMember(new KontoWidget("Bilanz", bilanzSpalten, zeilen));
//		// addMember(gesamt);
//		// addMember(new RundeBeendenWidget());
//		addMember(kontoWidget);
//		setAuslastungAnzeige();
//		setRundeBeenden();
//
//	}
//
//	@Override
//	public HasClickHandlers getBtnZugBeenden() {
//		return btnZugBeenden;
//	}
//
//	@Override
//	public HasValue<String> getTxtAnzahlMitarbeiter() {
//		return txtMitarbeiteranzahl;
//	}
//
//	@Override
//	public HasValue<String> getTxtLagerauslastung() {
//		return txtLagerauslastung;
//	}
//
//	@Override
//	public KontoWidget getKontoWidget() {
//		return kontoWidget;
//	}
//
//	@Override
//	public void setKontoWidget(String header, ArrayList<String> columns,
//			ArrayList<ArrayList<String>> soll,
//			ArrayList<ArrayList<String>> haben) {
//		this.kontoWidget = new KontoWidget(header, columns, soll, haben);
//		// bilanz.addMember(kontoWidget);
////		bilanz = new HLayout();
////		bilanz.addMember(kontoWidget);
////		this.addMember(bilanz);
//	}
//	
//	@Override
//	public void setKontoWidget(KontoWidget kontoWidget) {
//		bilanz.addMember(kontoWidget);
//		bilanz.setWidth("100%");
//		bilanz.setHeight("100%");
//		this.addMember(bilanz);
//	}
//
//	public void setAuslastungAnzeige() {
//		FlexTable layout = new FlexTable();
//		layout.setCellSpacing(6);
//		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
//
//		// Add a title to the form
//		layout.setHTML(0, 0, "Unternehmensdaten");
//		cellFormatter.setColSpan(0, 0, 2);
//		cellFormatter.setHorizontalAlignment(0, 0,
//				HasHorizontalAlignment.ALIGN_CENTER);
//
//		// Add some standard form options
//
//		layout.setHTML(1, 0, "Lagerauslastung:");
//		layout.setWidget(1, 1, txtLagerauslastung);
//		layout.setHTML(3, 0, "Anzahl Mitarbeiter:");
//		layout.setWidget(3, 1, txtMitarbeiteranzahl);
//		// Wrap the content in a DecoratorPanel
////		DecoratorPanel gesamt = new DecoratorPanel();
//		Canvas gesamt = new Canvas();
//		gesamt.setWidth("300px");
////		gesamt.setWidget(layout);
//		gesamt.addChild(layout);
//
//		this.addMember(gesamt);
//	}
//
//	public void setRundeBeenden() {
//		Canvas rundeBeenden = new Canvas();
//
//		Label lblErledigt = UIFactory.getLabel("Alles erledigt?", 0, 0, 100,
//				40, "Spiel-Label-Style");
//		btnZugBeenden = UIFactory.getButton(50, 50, 100, 30, "Zug Beenden",
//				"Spiel-Button-Style");
//
//		rundeBeenden.addChild(lblErledigt);
//		rundeBeenden.addChild(btnZugBeenden);
//		
//		this.addMember(rundeBeenden);
//	}
//
//}
