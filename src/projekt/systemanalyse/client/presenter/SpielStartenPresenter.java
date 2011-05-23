package projekt.systemanalyse.client.presenter;

import java.util.ArrayList;

import projekt.systemanalyse.client.SpielServiceAsync;
import projekt.systemanalyse.client.event.SpielBeitretenAbbrechenEvent;
import projekt.systemanalyse.client.event.StartSpielEvent;
import projekt.systemanalyse.client.view.HeaderWidget;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class SpielStartenPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		ListBox getLbSpielerOnline();

		Button getBtnSpielStarten();

		Button getBtnAbbrechen();

		Label getLblSpielerName();

		Label getLblFirmenName();

		Label getLblRundenNr();

		PushButton getBtnBeenden();

		PushButton getBtnHilfe();

		public Label getLblMitspieler();

		public Label getLblWarten();
		public Label getLblSpiel();
	}

	private final SpielServiceAsync rpcService;
	private final SimpleEventBus eventBus;
	private final Display display;
	private Spieler spieler;
	private Spiel spiel;
	HeaderWidget header;
	private int aktSpielerAnzahl = 0;
	private int maxSpielerAnzahl;
	private int hostID = -1;
	private Timer timer;

	public SpielStartenPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view, Spieler spieler, Spiel spiel) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		
		this.spieler = spieler;
		this.spiel = spiel;
		
//		setSpielerHinzufuegen(spieler, spiel);
		setSpielerName(spieler.getName());
		setFirmenName(spieler.getFirma());
		setSpielName(spiel.getSpielName());
		getHost(spiel.getSpielID());
		
		display.getBtnSpielStarten().setEnabled(false);
		maxSpielerAnzahl = spiel.getMaxSpielerAnzahl();
		display.getLblRundenNr().setText("/");
		
		// deaktiviere den Beenden Button im Header, da er noch keine Funktion erfüllt.
//		display.getBtnBeenden().setVisible(false);
		
		// aktualisiert die ListBox mit der aktuellen Anzahl der Spieler
		timer = new Timer() {

			@Override
			public void run() {
				getAktSpielerAnzahl();
				getAktSpieler();
				getSpielGestartet();
			}
		};
		timer.scheduleRepeating(2000);
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
		display.getBtnAbbrechen().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				rpcService.SpielUndSpielerTrennen(spieler.getSpielerID(), spiel.getSpielID(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						GWT.log("Spiel von Spieler entfernen erfolgreich!");
						rpcService.getSpieler(spieler.getSpielerID(), new AsyncCallback<Spieler>() {
							
							@Override
							public void onSuccess(Spieler result) {
								GWT.log("Neue Spielerversion holen erfolgreich!");
								eventBus.fireEvent(new SpielBeitretenAbbrechenEvent(result));		
							}
							
							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Neue Spielerversion holen fehlgeschlagen!");
								
							}
						});
											
					}
					
					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Spiel von Spieler entfernen fehlgeschlagen!");
						
					}
				});
			}
		});

		display.getBtnSpielStarten().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.getBtnSpielStarten().setEnabled(false);
				display.getBtnAbbrechen().setEnabled(false);
				rpcService.starteSpiel(spiel.getSpielID(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						GWT.log("Spiel erfolgreich gestartet!");
//						Window.confirm("Das Spiel wurde gestartet.");
						timer.cancel();
						rpcService.getSpielEinesSpielers(spieler.getSpielerID(), new AsyncCallback<Spiel>() {
							
							@Override
							public void onSuccess(Spiel result) {
								GWT.log("neue Spielversion holen erfolgreich!");
								eventBus.fireEvent(new StartSpielEvent(result, spieler));
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								GWT.log("neue Spielversion holen fehlgeschlagen!");
								display.getBtnSpielStarten().setEnabled(true);
							}
						});
						
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error when starting the game");
						
					}
				});
			}
		});
	}
	
	// //////////////////////////////////////////////////////////////////////////
	// //// get Methoden für neue Informationen vom Server
	// /////////////////////////////////////////////////////////////////////////
	
	public void getAktSpielerAnzahl() {
		rpcService.getSpielerAnzahl(spiel.getSpielID(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				aktSpielerAnzahl = result;
				setSpielerAnzahl(aktSpielerAnzahl, maxSpielerAnzahl);
				if(aktSpielerAnzahl == maxSpielerAnzahl && hostID == spieler.getSpielerID()) {
					display.getBtnSpielStarten().setEnabled(true);
				} else {
					display.getBtnSpielStarten().setEnabled(false);
				}
				GWT.log("Aktuelle Spieleranzahl holen erfolgreich!");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fehler beim abrufen der Spieleranzahl; SpielStartenPresenter");
			}
		});
	}
	
	public void getHost(int spielID) {
		rpcService.getHostDesSpiels(spiel.getSpielID(), new AsyncCallback<Spieler>() {
			
			@Override
			public void onSuccess(Spieler result) {
				GWT.log("Get Host ID erfolgreich");
				hostID = result.getSpielerID();				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Get Host ID fehlgeschlagen");
				
			}
		});
	}
	
	public void getSpielGestartet() {
		rpcService.getSpielIstGestartet(spiel.getSpielID(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if(result == true) {
					GWT.log("Spiel erfolgreich gestartet!");
//					Window.confirm("Das Spiel wurde gestartet.");
					timer.cancel();
					rpcService.getSpielEinesSpielers(spieler.getSpielerID(), new AsyncCallback<Spiel>() {
						
						@Override
						public void onSuccess(Spiel result) {
							GWT.log("neue Spielversion holen erfolgreich!");
							eventBus.fireEvent(new StartSpielEvent(result, spieler));
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							GWT.log("neue Spielversion holen fehlgeschlagen!");
							
						}
					});
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Spiel ist gestartet abfrage fehlgeschlagen!");
			}
		});
	}
	
	public void getAktSpieler() {
		rpcService.getSpielerListe(spiel.getSpielID(), new AsyncCallback<ArrayList<Spieler>>() {
			
			@Override
			public void onSuccess(ArrayList<Spieler> result) {
				GWT.log("Aktuelle Spielerliste holen erfolgreich!");
				setLbSpieler(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Aktuelle Spielerliste holen fehlgeschlagen!");
			}
		});
	}
	
//	public void setSpielerHinzufuegen(Spieler spieler, Spiel spiel) {
////		rpcService.spielerZuSpielZuweisen(spieler.getSpielerID(), spiel.getSpielID(), new AsyncCallback<Void>() {
////			
////			@Override
////			public void onSuccess(Void result) {
////				GWT.log("Spieler zu Spiel hinzufuegen erfolgreich!");
////				
////			}
////			
////			@Override
////			public void onFailure(Throwable caught) {
////				GWT.log("Spieler zu Spiel hinzufügen fehlgeschlagen!");
////				
////			}
////		});
//		
//		// nicht nötig
////		rpcService.spielZuSpielerZuweisen(spiel.getSpielID(), spieler.getSpielerID(), new AsyncCallback<Void>() {
////			
////			@Override
////			public void onSuccess(Void result) {
////				GWT.log("Spiel zu Spieler hinzufuegen erfolgreich!");
////			}
////			
////			@Override
////			public void onFailure(Throwable caught) {
////				GWT.log("Spiel zu Spieler hinzufuegen fehlgeschlagen!");
////				
////			}
////		});
//	}
	
	// //////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	public void setRundenNr(String runde) {
		display.getLblRundenNr().setText(runde);
	}
	
	public void setSpielerAnzahl(int aktSpielerAnzahl, int maxSpielerAnzahl) {
		display.getLblMitspieler().setText("Mitspieler (" + aktSpielerAnzahl + "/" + maxSpielerAnzahl +"):");
	}
	
	public void setLbSpieler(ArrayList<Spieler> spielerListe) {
		display.getLbSpielerOnline().clear();
		for(int i = 0; i<spielerListe.size(); i++) {
			display.getLbSpielerOnline().addItem(spielerListe.get(i).getName());
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
	

}
