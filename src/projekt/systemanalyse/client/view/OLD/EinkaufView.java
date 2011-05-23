//package projekt.systemanalyse.client.view.OLD;
//
//import java.util.ArrayList;
//import projekt.systemanalyse.shared.Positionsliste;
//import projekt.systemanalyse.client.presenter.OLD.EinkaufViewPresenter;
//import projekt.systemanalyse.client.view.PositionsWidget;
//import projekt.systemanalyse.client.view.components.HelpBoxWidget;
//import projekt.systemanalyse.client.view.components.PositionsListeWidget;
//import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
//
//import com.smartgwt.client.types.Alignment;
//import com.smartgwt.client.widgets.Canvas;
//import com.smartgwt.client.widgets.EdgedCanvas;
//import com.smartgwt.client.widgets.Label;
//import com.smartgwt.client.widgets.layout.HLayout;
//import com.smartgwt.client.widgets.layout.VLayout;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.HasChangeHandlers;
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.ListBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//
//public class EinkaufView extends VLayout implements
//		EinkaufViewPresenter.Display {
//	
//	PositionsListeWidget positionsListe;
//	private static EinkaufView einkaufView = null;
//
//	private EinkaufView() {
//		GWT.log("init Einkauf()...", null);
//
//		// set Parameters
//		setWidth("100%");
//		setHeight("100%");
//
//		// create Elements
//		positionsListe = new PositionsListeWidget();
//
//		// add Members
//		addMember(new HelpBoxWidget(
//				"Auf dieser Seite kannst du deine Eink&auml;ufe planen. Von jedem Bauteil gibt es verschiedene angebotene Produkte in unterschiedlichen Qualit&auml;tsstufen. Je nachdem, in welcher Qualit&auml;t du deine Bauteile kaufst, wird sich diese Entscheidung auf den Ausschuss und somit auf deine Produktion auswirken. Teurere Artikel haben grunds&auml;tzlich eine h&ouml;here Qualit&auml;t.",
//				"Hilfe zum Einkauf"));
//		addMember(positionsListe);
//		einkaufView = this;
//	}
//
//	public static EinkaufView getEinkaufView() {
//		if (einkaufView == null) {
//			new EinkaufView();
//		}
//		return einkaufView;
//	}
//	
//	public Widget asWidget() {
//		return this;
//	}
//
//	@Override
//	public HasClickHandlers getArtikelHinzufuegenButton() {
//		return positionsListe.getAuswahl().getBtnHinzufuegen();
//	}
//
//	@Override
//	public ListBox getlbTyp() {
//		return positionsListe.getAuswahl().getLbTyp();
//	}
//
//	@Override
//	public HasValue<String> getTxtPreis() {
//		return positionsListe.getAuswahl().getTxtPreis();
//	}
//
//	@Override
//	public HasValue<String> getTxtMenge() {
//		return positionsListe.getAuswahl().getTxtMenge();
//	}
//
//	@Override
//	public HasValue<String> getTxtGesamt() {
//		return positionsListe.getAuswahl().getTxtGesamt();
//	}
//
//	@Override
//	public PositionsWidget getPositionsListe() {
//		return positionsListe.getEinkaufsTabelle();
//	}
//
//	@Override
//	public ListBox getLbArtikel() {
//		return positionsListe.getAuswahl().getLbArtikel();
//	}
//
//	@Override
//	public HasValue<String> getTxtGesamtkosten() {
//		return positionsListe.getTxtGesamtkosten();
//	}
//
//	@Override
//	public HasValue<String> getTxtLagerverbrauch() {
//		return positionsListe.getTxtLagerbedarf();
//	}
//
//}
