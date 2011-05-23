package projekt.systemanalyse.client.presenter;

import java.util.ArrayList;

import projekt.systemanalyse.client.SpielServiceAsync;
import projekt.systemanalyse.client.event.SpielBeitretenEvent;
import projekt.systemanalyse.client.event.SpielErstelltEvent;
import projekt.systemanalyse.client.view.HeaderWidget;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginViewPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		TextBox getTxtSpielerName();

		TextBox getTxtFirma();

		ListBox getLbSpielerOnline();

		ListBox getLbSpiele();

		Button getBtnSpielBeitreten();

		Button getBtnSpielErstellen();

		Button getBtnSpielStarten();

		Button getBtnAbbrechen();

		DialogBox getDbSpielErstellen();

		Label getLblSpielerName();

		Label getLblFirmenName();

		Label getLblRundenNr();

		PushButton getBtnBeenden();

		PushButton getBtnHilfe();

		TextBox getTxtSpielname();

		IntegerBox getTxtSpieleranzahl();
		public Label getLblSpiel();
	}

	private final SpielServiceAsync rpcService;
	private final SimpleEventBus eventBus;
	private final Display display;
	private Spieler spieler;
	private String spielName;
	Spiel spiel;
	private ArrayList<Spiel> spieleListe = new ArrayList<Spiel>();
	Timer timerSpielerListe;
	Timer timerSpielListe;
	HeaderWidget header;

	public LoginViewPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		setRundenNr("/");
		setSpielName("/");
		// Füge bei betreten des Screens einen neuen Spieler hinzu, der zuerst
		// im Backend angelegt wird
		// und eine Instanz auf die Klasse Spieler zurückliefert
		rpcService.erzeugeSpieler("Player", "Kleine Klitsche",
				new AsyncCallback<Spieler>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Spieler erzeugen fehlgeschlagen!");

					}

					@Override
					public void onSuccess(Spieler result) {
						GWT.log("Spieler erzeugen erfolgreich!");
						spieler = result;
						setFirmenName(result.getFirma());
						setSpielerName(result.getName());						
					}
				});
		
		// deaktiviere den Beenden Button im Header, da er noch keine Funktion erfüllt.
//		display.getBtnBeenden().setVisible(false);
		
		
		// füge einen Timer hinzu, welcher regelmäßig die Listboxen updatet
		timerSpielerListe = new Timer() {

			@Override
			public void run() {
				getSpielerListe();
			}
		};
		timerSpielerListe.scheduleRepeating(1000);
		
		timerSpielListe = new Timer() {

			@Override
			public void run() {
				getSpielListe();
			}
		};
		timerSpielListe.scheduleRepeating(5000);
	}
	
	public LoginViewPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view, Spieler spieler) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		this.spieler = spieler;
		
		setFirmenName(spieler.getFirma());
		setRundenNr("/");
		setSpielName("/");
		setSpielerName(spieler.getName());
		
		// füge einen Timer hinzu, welcher regelmäßig die Listboxen updatet
		timerSpielerListe = new Timer() {

			@Override
			public void run() {
				getSpielerListe();
			}
		};
		timerSpielerListe.scheduleRepeating(1000);
		
		timerSpielListe = new Timer() {

			@Override
			public void run() {
				getSpielListe();
			}
		};
		timerSpielListe.scheduleRepeating(5000);
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	public void bind() {
		// /////////////////////////////////////////////////////////////////////////
		// //// Elemente auf dem Header
		// /////////////////////////////////////////////////////////////////////////
		Window.addWindowClosingHandler(
				new ClosingHandler(){
					@Override
					public void onWindowClosing(ClosingEvent event) {
//						event.setMessage("Möchten Sie die Seite wirklich verlassen?");
					}
				}
		);
		// //////////////////////////////////////////////////////////////////////////
		// //// Elemente auf dem LoginView
		// /////////////////////////////////////////////////////////////////////////

		// setzt den Text in das Label im Head, falls der Spieler seinen
		// Spielernamen ändert,
		// zuerst wird aber ein RPC-Call gemacht um zu prüfen ob der Spieler
		// seinen Spielernamen ändern kann
		display.getTxtSpielerName().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				rpcService.setSpielerName(spieler.getSpielerID(), display
						.getTxtSpielerName().getText(),
						new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								setSpielerName(display.getTxtSpielerName()
										.getText());
								try {
									spieler.setSpielerName(display.getTxtSpielerName().getText());
								} catch (InstanceCreationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Fehler beim setzen des Spielernamens; LoginViewPresenter");

							}
						});
			}
		});

		// setzt den Text in das Label im Head, falls der Spieler seinen
		// Firmennamen ändert,
		// zuerst wird aber ein RPC-Call gemacht um zu prüfen ob der Spieler
		// seinen Firmennamen ändern kann
		display.getTxtFirma().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				rpcService.setSpielerFirma(spieler.getSpielerID(), display
						.getTxtFirma().getText(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Fehler beim setzen des Firmennamens; LoginViewPresenter");
					}

					@Override
					public void onSuccess(Void result) {
						setFirmenName(display.getTxtFirma().getText());
						spieler.setSpielerFirma(display.getTxtFirma().getText());
					}
				});
			}
		});

		// zeigt das Dialog Feld um ein Spiel zu erstellen
		display.getBtnSpielErstellen().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.getDbSpielErstellen().center();
				display.getDbSpielErstellen().show();
			}
		});
		display.getBtnSpielBeitreten().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selectedGame = display.getLbSpiele().getSelectedIndex();
				if (selectedGame == -1) {
					Window.alert("Es muss ein Spiel selektiert werden bevor einem Spiel beigetreten werden kann.");
				} else {
					spiel = spieleListe.get(display.getLbSpiele().getSelectedIndex());
					rpcService.SpielUndSpielerVerbinden(spieler.getSpielerID(), spiel.getSpielID(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							GWT.log("Spieler zu Spiel hinzufuegen erfolgreich!");
							eventBus.fireEvent(new SpielBeitretenEvent(spiel, spieler));
						}
						
						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Spieler zu Spiel hinzufügen fehlgeschlagen!");
//							if(caught instanceof SpielerZuweisenException) {
//								Window.alert(caught.getMessage());
//							}		
							Window.alert(caught.getMessage());
						}
					});					
				}
			}
		});

		// //////////////////////////////////////////////////////////////////////////
		// //// Dialog zum Spiel
		// /////////////////////////////////////////////////////////////////////////

		display.getBtnAbbrechen().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.getDbSpielErstellen().hide();
			}
		});

		display.getBtnSpielStarten().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String spielName = getSpielName();
				int spielerAnzahl = getSpielerAnzahl();
				if(spielName != null && spielerAnzahl > 0) {
					rpcService.erzeugeSpiel(spielName, spielerAnzahl, "",spieler.getSpielerID(), new AsyncCallback<Spiel>() {
						public void onSuccess(Spiel result) {
//							Window.confirm("Das Spiel wurde gestartet.");
							timerSpielListe.cancel();			
							timerSpielerListe.cancel();
							eventBus.fireEvent(new SpielErstelltEvent(result,
									spieler));
						}

						public void onFailure(Throwable caught) {
							GWT.log("Spiel erzeugen fehlgeschlagen.");
							Window.alert(caught.getMessage());
//							if(caught instanceof InstanceCreationException) {
//								Window.alert(caught.getMessage());
//							}
						}

					});
				} else {
					
					Window.alert("Das Spiel konnte aufgrund falscher Parametereingaben nicht gestartet werden!");
				}
			}
		});
		
		display.getTxtSpielname().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				spielName = getSpielName();
			}
		});
				
		display.getTxtSpieleranzahl().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				getSpielerAnzahl();
			}
		});
		
	}

	// //////////////////////////////////////////////////////////////////////////
	// //// get Methoden um Informationen vom Server zu besorgen
	// /////////////////////////////////////////////////////////////////////////
	public void getSpielerListe() {
		LoginViewPresenter.this.rpcService.getSpielerOhneSpiel(new AsyncCallback<ArrayList<Spieler>>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Fehler beim abrufen der Spielerliste; LoginViewPresenter");

					}

					@Override
					public void onSuccess(ArrayList<Spieler> result) {
//						spielerListe = result;
						setSpielerListe(result);
					}
				});
	}

	public void getSpielListe() {
		rpcService.getOffeneSpiele(new AsyncCallback<ArrayList<Spiel>>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fehler beim abrufen der Spielliste; LoginViewPresenter");

			}

			@Override
			public void onSuccess(ArrayList<Spiel> result) {
				spieleListe = result;
				setSpielListe(result);
			}
		});
	}

	// //////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////

	public void setSpielerName(String spielerName) {
		display.getTxtSpielerName().setValue(spielerName);
		display.getLblSpielerName().setText(spielerName);
	}

	public void setFirmenName(String firmenName) {
		display.getTxtFirma().setValue(firmenName);
		display.getLblFirmenName().setText(firmenName);
	}

	public void setRundenNr(String runde) {
		display.getLblRundenNr().setText(runde);
	}

	public void setSpielerListe(ArrayList<Spieler> spielerListe) {
		display.getLbSpielerOnline().clear();
		for (int i = 0; i < spielerListe.size(); i++) {
			display.getLbSpielerOnline().addItem(spielerListe.get(i).getName());
		}

	}

	public void setSpielListe(ArrayList<Spiel> spieleListe) {
		display.getLbSpiele().clear();
		for (int i = 0; i < spieleListe.size(); i++) {
			display.getLbSpiele().addItem(spieleListe.get(i).getSpielName());
		}
	}
	
	public void setSpielName(String spielName) {
		display.getLblSpiel().setText(spielName);
	}
	
	// //////////////////////////////////////////////////////////////////////////
	// //// get Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	
	public int getSpielerAnzahl() {
		try {
			int spielerAnzahl;
			spielerAnzahl = Integer.parseInt(display.getTxtSpieleranzahl().getText());
			if(spielerAnzahl < 1) {
				throw  new NumberFormatException();
			} else {
				return spielerAnzahl;
			}
		} catch (NumberFormatException e) {
			Window.alert("In das Feld SpielerAnzahl muss eine ganze Zahl größer als 0 eingegeben werden.");
			return 0;
		}
	}
	
	public String getSpielName() {
		try {
			String spielName = display.getTxtSpielname().getText();
			if(spielName != "" && spielName != null) {
				return spielName;
			} else {
				throw new NumberFormatException();
			}
			
		} catch (NumberFormatException e) {
			Window.alert("In das Feld Spiel muss ein gültiger Wert eingegeben werden.");
			return null;
		}
	}
}
