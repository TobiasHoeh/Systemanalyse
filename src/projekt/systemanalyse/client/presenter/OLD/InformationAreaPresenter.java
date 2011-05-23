//package projekt.systemanalyse.client.presenter.OLD;
//
//import java.util.ArrayList;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//import projekt.systemanalyse.client.presenter.OLD.BodyPresenter.Display;
//import projekt.systemanalyse.client.view.KontoWidget;
//import projekt.systemanalyse.client.view.OLD.EinkaufView;
//import projekt.systemanalyse.client.view.OLD.InformationAreaView;
//import projekt.systemanalyse.client.view.components.ApplicationMenuWidget;
//import projekt.systemanalyse.shared.Konto;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.shared.SimpleEventBus;
//import com.google.gwt.i18n.client.NumberFormat;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.HasWidgets;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.events.ClickEvent;
//import com.smartgwt.client.widgets.events.ClickHandler;
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//
//public class InformationAreaPresenter{
//	public interface Display {
//		Widget asWidget();
//		HasClickHandlers getBtnZugBeenden();
//		HasValue<String> getTxtAnzahlMitarbeiter();
//		HasValue<String> getTxtLagerauslastung();
//		KontoWidget getKontoWidget();
//		void setKontoWidget(String header, ArrayList<String> columns, ArrayList<ArrayList<String>> soll, ArrayList<ArrayList<String>> haben);
//		void setRundeBeenden();
//		void setAuslastungAnzeige();
//		void setKontoWidget(KontoWidget kontoWidget);
//	}
//
//	private final SpielServiceAsync rpcService;
//	private final SimpleEventBus eventBus;
//	private final Display display;
//	private int maxLagerauslastung = 0;
//	private int aktLagerauslastung = 0;
//	private int mitarbeiterAnzahl = 0;
//	private KontoWidget bilanz;
////	private int spielerID;
//	
//	public InformationAreaPresenter(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view, int spielerID) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//
//		
//		getKontodaten(spielerID);
//		
////		if(bilanz != null) {
////			display.setKontoWidget(bilanz);
////		}
////		
////		display.setAuslastungAnzeige();
////		display.setRundeBeenden();
////		
////		getLagerauslastung(spielerID);
////		getMitarbeiterAnzahl(spielerID);
//		
////		display.setAuslastungAnzeige();
////		display.setRundeBeenden();			
//			getLagerauslastung(spielerID);
//			getMitarbeiterAnzahl(spielerID);
//		bind();
//		
//		
//	}
//	
//	public void go(final HasWidgets container) {
//		bind();
//		container.clear();
//		container.add(display.asWidget());
//	}
//
//	public void bind() {
//		display.getBtnZugBeenden().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				Window.confirm("Runde Beenden geklickt");
//				GWT.log("Runde Beenden geklickt");
//			}
//		});
//	}
//	
//	public Widget getDisplay() {
//		return display.asWidget();
//	}
//	
//	public void getMitarbeiterAnzahl(int spielerID) {
//		rpcService.getMitarbeiter(spielerID, new AsyncCallback<Integer>() {
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when fetching the MitarbeiterAnzahl.");
//			}
//
//			@Override
//			public void onSuccess(Integer result) {
//				setMitarbeiterAnzahl(result);
//			}
//		});
//		setTxtMitarbeiterAnzahl();
//	}
//	
//	// RPC-Call für Lagerauslastung
//	public void getLagerauslastung(int spielerID) {
//		rpcService.getMaxLagerauslastung(spielerID, new AsyncCallback<Integer>() {
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when fetching the MaxLagerauslastung.");
//			}
//
//			@Override
//			public void onSuccess(Integer result) {
//				setMaxLagerauslastung(result);
//			}
//		});
//		
//		rpcService.getAktLagerauslastung(spielerID, new AsyncCallback<Integer>() {
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when fetching the AktLagerauslastung.");
//			}
//
//			@Override
//			public void onSuccess(Integer result) {
//				setAktLagerauslastung(result);
//			}
//		});
//		setTxtLagerauslastung();
//	}
//	
//	// aktuelle Lagerauslastung festlegen
//	public void setAktLagerauslastung(int aktAuslastung) {
//		aktLagerauslastung = aktAuslastung;
//		
//	}
//	
//	// maximale Lagerauslastung festlegen
//	public void setMaxLagerauslastung(int maxAuslastung) {
//		maxLagerauslastung = maxAuslastung;
//	}
//	
//	public void setMitarbeiterAnzahl(int mitarbeiter) {
//		mitarbeiterAnzahl = mitarbeiter;
//	}
//	
//	public void setTxtLagerauslastung() {
//		String auslastung;
//		auslastung = Integer.toString(aktLagerauslastung) + " LE / " + Integer.toString(maxLagerauslastung) + " LE";
//		display.getTxtLagerauslastung().setValue(auslastung);
//	}
//	
//	public void setTxtMitarbeiterAnzahl() {
//		display.getTxtAnzahlMitarbeiter().setValue(Integer.toString(mitarbeiterAnzahl));
//	}
//	
//	public void getKontodaten (int spielerID) {
//		rpcService.getBilanz(spielerID, new AsyncCallback<Konto>()  {
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when fetching the Konto.");
//			}
//
//			@Override
//			public void onSuccess(Konto result) {
//				setKontoWidget(result);
//			}
//		});
//	}
//	
//	public void setKontoWidget(Konto konto) {
//		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
//		ArrayList<ArrayList<String>> soll = new ArrayList<ArrayList<String>>();
//		ArrayList<ArrayList<String>> haben = new ArrayList<ArrayList<String>>();
//		
//		ArrayList<Konto> sollKonten = konto.getUnterkontenSoll();
//		
//		for(int i = 0; i<sollKonten.size(); i++) {
//			Konto zwischenKonto;
//			ArrayList<String> zeile = new ArrayList<String>();
//			zwischenKonto = sollKonten.get(i);
//			zeile.add(zwischenKonto.getBezeichnung());
//			zeile.add(n.format(zwischenKonto.getKontosumme()));
//			soll.add(zeile);
//		}
//		
//		ArrayList<Konto> habenKonten = konto.getUnterkontenHaben();
//		
//		for(int i = 0; i<habenKonten.size(); i++) {
//			Konto zwischenKonto;
//			ArrayList<String> zeile = new ArrayList<String>();
//			zwischenKonto = habenKonten.get(i);
//			zeile.add(zwischenKonto.getBezeichnung());
//			zeile.add(n.format(zwischenKonto.getKontosumme()));
//			haben.add(zeile);
//		}
//		
//		ArrayList<String> bilanzSpalten = new ArrayList<String>();
//		bilanzSpalten.add("Aktiv");
//		bilanzSpalten.add("");
//		bilanzSpalten.add("");
//		bilanzSpalten.add("Passiv");
//		bilanz = new KontoWidget("Bilanz", bilanzSpalten, soll, haben);
//		
//		for(int i = 0; i<bilanzSpalten.size();i++) {
//			display.getKontoWidget().addColumn(bilanzSpalten.get(i));
//		}
//		
//		for(int row = 0; row<soll.size();row++) {
//			display.getKontoWidget().addSoll(soll.get(row));
//		}
//		
//		for(int row = 0; row<haben.size();row++) {
//			display.getKontoWidget().addHaben(haben.get(row));
//		}
//		display.getKontoWidget().applyDataRowStyles();
////		display.setKontoWidget(bilanz);
//
////		display.setKontoWidget("Bilanz", bilanzSpalten, soll, haben);
//		
//	}
//}
