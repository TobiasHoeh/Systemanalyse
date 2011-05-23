package projekt.systemanalyse.client.presenter;

import java.util.ArrayList;

import projekt.systemanalyse.client.SpielServiceAsync;
import projekt.systemanalyse.client.event.StartSpielEvent;
import projekt.systemanalyse.client.view.HeaderWidget;
import projekt.systemanalyse.shared.Position;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RundeBeendetPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		Label getLblSpielerName();

		Label getLblFirmenName();

		Label getLblRundenNr();
		public Label getLblSpiel();
		
		public Label getLblWarten();
		public TextBox getTxtSpielerGesamterloes();
		public TextBox getTxtKonkurrenzAbsatz();
		public TextBox getTxtSpielerFahrradAbsatz();
		public TextBox getTxtSpielerGewinn();
		public TextBox getTxtMarktAbsatz();
		public Button getBtnNaechsteRunde();
		public TextBox getTxtKonkurrenzGewinn();
		public ListBox getLbKonkurrenz();
		public TextArea getTxtMarktereignis();
		public TextBox getTxtSpielerGesamtkosten();
		public TextBox getTxtAusschussMenge();
		public ListBox getLbAusschussArtikel();
	}

	private final SpielServiceAsync rpcService;
	private final SimpleEventBus eventBus;
	private final Display display;
	private Spieler spieler;
	private Spiel spiel;
	private ArrayList<Spieler> mitspielerListe = new ArrayList<Spieler>();
	HeaderWidget header;
	private int aktSpielerAnzahl = 0;
	private int maxSpielerAnzahl;
	private Timer timer;
	private Positionsliste ausschussListe = new Positionsliste();

	public RundeBeendetPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view, Spieler spieler, Spiel spiel) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		
		this.spieler = spieler;
		this.spiel = spiel;
		
		ArrayList<Spieler> spielerListe = spiel.getSpieler();
		for(int i =0; i<spielerListe.size(); i++) {
			Spieler temp = spielerListe.get(i);
			if(!(temp.getSpielerID() == spieler.getSpielerID())) {
				mitspielerListe.add(temp);
			}
		}
		
		setSpielerName(spieler.getName());
		setFirmenName(spieler.getFirma());
		setSpielName(spiel.getSpielName());
		
//		display.getBtnSpielStarten().setEnabled(false);
		maxSpielerAnzahl = spiel.getMaxSpielerAnzahl();
		display.getLblRundenNr().setText(Integer.toString(spiel.getAktuelleRunde().getRundenNr()));
		
		getMarktdaten();
		getSpielerDaten();
		setLbMitspieler(mitspielerListe);
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

	}

	
	// //////////////////////////////////////////////////////////////////////////
	// //// ClickHandlers für die Buttons
	// /////////////////////////////////////////////////////////////////////////
	public void bind() {
		Window.addWindowClosingHandler(new ClosingHandler() {
			@Override
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Das bereits begonnene Spiel wird dann als verloren gewertet.");
				rpcService.SpielUndSpielerTrennen(spieler.getSpielerID(), spiel.getSpielID(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						GWT.log("Spieler von Spiel entfernen bei verlassen den Browsers erfolgreich.");
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Spieler von Spiel entfernen bei verlassen den Browsers fehlgeschlagen.");
						
					}
				});
			}
		});
		display.getBtnNaechsteRunde().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rpcService.getSpielEinesSpielers(spieler.getSpielerID(), new AsyncCallback<Spiel>() {
					
					@Override
					public void onSuccess(Spiel result) {
						GWT.log("Neue Spielversion holen erfolgreich. Neue Runde wird gestartet.");
						eventBus.fireEvent(new StartSpielEvent(result, spieler));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						caught.getCause().printStackTrace();
						GWT.log("Neue Spielversion holen fehlgeschlagen.");
					}	
				});
				
			}	
		});
		
		display.getLbKonkurrenz().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				setMitspielerDaten(display.getLbKonkurrenz().getSelectedIndex());
				
			}
		});
		
		display.getLbAusschussArtikel().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				setTxtAusschuss();
				
			}
		});
			
	}
	
	// //////////////////////////////////////////////////////////////////////////
	// //// get Methoden für neue Informationen vom Server
	// /////////////////////////////////////////////////////////////////////////
	
	public void getMarktdaten() {
		rpcService.getMarktVerkauft(spiel.getSpielID(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				GWT.log("MarktVerkauft Informationen abrufen erfolgreich");
				display.getTxtMarktAbsatz().setText(Integer.toString(result) + " Stück");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("MarktVerkauft Informationen abrufen fehlgeschlagen");
				
			}
		});
		
		rpcService.getMarktEreignisText(spiel.getSpielID(), new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				GWT.log("MarktEreignis Informationen abrufen erfolgreich");
				setMarktEreignisText(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("MarktEreignis Informationen abrufen fehlgeschlagen");
				
			}
		});
	}
	
	public void getSpielerDaten() {
		
		rpcService.getAusschuss(spieler.getSpielerID(), new AsyncCallback<Positionsliste>() {
			
			@Override
			public void onSuccess(Positionsliste result) {
				GWT.log("Ausschuss holen erfolgreich!");
				
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Ausschuss holen fehlgeschlagen!");
				
			}
		});
		
		rpcService.getSpielerEinnahmen(spieler.getSpielerID(), new AsyncCallback<Double>() {
			
			@Override
			public void onSuccess(Double result) {
				GWT.log("Spieler Einnahmen holen erfolgreich!");
				NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
				display.getTxtSpielerGesamterloes().setText(n.format(result));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Spieler Einnahmen holen fehlgeschlagen!");
				
			}
		});
		
		rpcService.getSpielerProduziert(spieler.getSpielerID(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
//				display.get
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("GetSpielerProduziert fehlgeschlagen!");
			}
		});
		
		rpcService.getSpielerVerkauft(spieler.getSpielerID(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				GWT.log("GetSpielerVerkauft erfolgreich!");
				display.getTxtSpielerFahrradAbsatz().setText(Integer.toString(result) + " Stück");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("GetSpielerVerkauft fehlgeschlagen!");
				
			}
		});
		
		rpcService.getSpielerEinnahmen(spieler.getSpielerID(), new AsyncCallback<Double>() {
			
			@Override
			public void onSuccess(Double result) {
				GWT.log("GetSpielerEinnahmen erfolgreich!");
				NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
				display.getTxtSpielerGesamterloes().setText(n.format(result));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("GetSpielerEinnahmen fehlgeschlagen!");
				
			}
		});
		
		rpcService.getSpielerAusgaben(spieler.getSpielerID(), new AsyncCallback<Double>() {
			
			@Override
			public void onSuccess(Double result) {
				GWT.log("GetSpielerAusgaben erfolgreich!");
				NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
				display.getTxtSpielerGesamtkosten().setText(n.format(result));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("GetSpielerAusgaben fehlgeschlagen!");
				
			}
		});
		
		rpcService.getSpielerGewinn(spieler.getSpielerID(), new AsyncCallback<Double>() {
			
			@Override
			public void onSuccess(Double result) {
				GWT.log("GetSpielerGewinn erfolgreich!");
				NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
				display.getTxtSpielerGewinn().setText(n.format(result));
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("GetSpielerGewinn fehlgeschlagen!");
				
			}
		});
		
		rpcService.getAusschuss(spieler.getSpielerID(), new AsyncCallback<Positionsliste>() {
			
			@Override
			public void onSuccess(Positionsliste result) {
				GWT.log("Get Ausschuss erfolgreich!");
				ausschussListe = result;
				setLbSpielerArtikelAusschuss(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Get Ausschuss fehlgeschlagen!");
				
			}
		});
		
//		public Positionsliste getAusschuss(int spielerID)throws NoObjectFoundException {
//			Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
//			return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getAusschuesse();
//		}
//		@Override
//		public int getSpielerProduziert(int spielerID)throws NoObjectFoundException {
//			Spiel seinSpiel = Spieler.getSpieler(spielerID).getSpiel();
//			return seinSpiel.getLetzteRunde().getZugDesSpielers(Spieler.getSpieler(spielerID)).getProduziert();
//		}

	}
	
	public void setMitspielerDaten(int spielerNummer) {
		Spieler temp = mitspielerListe.get(spielerNummer);
		rpcService.getSpielerGewinn(temp.getSpielerID(), new AsyncCallback<Double>() {
			
			@Override
			public void onSuccess(Double result) {
				GWT.log("getSpielerGewinn Konkurrenz erfolgreich!");
				NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
				display.getTxtKonkurrenzGewinn().setText(n.format(result));
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("getSpielerGewinn Konkurrenz fehlgeschlagen!");
				
			}
		});
		
		rpcService.getSpielerVerkauft(temp.getSpielerID(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				GWT.log("GetSpielerVerkauft Konkurrenz erfolgreich!");
				display.getTxtKonkurrenzAbsatz().setText(Integer.toString(result) + " Stück");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("GetSpielerVerkauft Konkurrenz fehlgeschlagen!");
				
			}
		});
	}
	
	
	// //////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	public void setRundenNr(String runde) {
		display.getLblRundenNr().setText(runde);
	}
	
	public void setLbMitspieler(ArrayList<Spieler> mitspielerListe) {
		for(int i = 0; i<mitspielerListe.size(); i++) {
			int zaehler = i +1;
			display.getLbKonkurrenz().addItem(zaehler + ". " + mitspielerListe.get(i).getName());
		}
		if(mitspielerListe.size() > 0) {
			display.getLbKonkurrenz().setItemSelected(0, true);
			setMitspielerDaten(display.getLbKonkurrenz().getSelectedIndex());
		}
	}
	
	public void setSpielerName(String spielerName) {
		display.getLblSpielerName().setText(spielerName);
	}

	public void setFirmenName(String firmenName) {
		display.getLblFirmenName().setText(firmenName);
	}
	public void setSpielName(String spielName) {
		display.getLblSpiel().setText(spielName);
	}
	
	public void setLbSpielerArtikelAusschuss(Positionsliste ausschussListe) {
		display.getLbAusschussArtikel().clear();
		for(int i = 0; i<ausschussListe.getSize(); i++) {
			Position position = ausschussListe.getEinzelPositionNr(i);
			
			display.getLbAusschussArtikel().addItem(position.getGut().getBezeichnung());
			
		}
		setTxtAusschuss();
	}
	public void setTxtAusschuss() {
		String auswahl = display.getLbAusschussArtikel().getItemText(display.getLbAusschussArtikel().getSelectedIndex());
		try {
			Position ausschuss = ausschussListe.getPosition(auswahl);
			display.getTxtAusschussMenge().setText(Integer.toString(ausschuss.getMenge()));
		} catch (NoObjectFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// //////////////////////////////////////////////////////////////////////////
	// //// UI-Elemente des Markt
	public void setMarktEreignisText(String ereignisText) {
		display.getTxtMarktereignis().setText(ereignisText);
	}
	

}
