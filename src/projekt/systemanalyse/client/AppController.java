package projekt.systemanalyse.client;
import projekt.systemanalyse.client.event.RundeBeendetEvent;
import projekt.systemanalyse.client.event.RundeBeendetEventHandler;
import projekt.systemanalyse.client.event.SpielBeendetEvent;
import projekt.systemanalyse.client.event.SpielBeendetEventHandler;
import projekt.systemanalyse.client.event.SpielBeitretenAbbrechenEvent;
import projekt.systemanalyse.client.event.SpielBeitretenAbbrechenEventHandler;
import projekt.systemanalyse.client.event.SpielBeitretenEvent;
import projekt.systemanalyse.client.event.SpielBeitretenEventHandler;
import projekt.systemanalyse.client.event.SpielErstelltEvent;
import projekt.systemanalyse.client.event.SpielErstelltEventHandler;
import projekt.systemanalyse.client.event.StartSpielEvent;
import projekt.systemanalyse.client.event.StartSpielEventHandler;
import projekt.systemanalyse.client.event.ZugBeendetEvent;
import projekt.systemanalyse.client.event.ZugBeendetEventHandler;
import projekt.systemanalyse.client.event.ZurueckZuLobbyEvent;
import projekt.systemanalyse.client.event.ZurueckZuLobbyEventHandler;
import projekt.systemanalyse.client.presenter.LoginViewPresenter;
import projekt.systemanalyse.client.presenter.MainLayoutPresenter;
import projekt.systemanalyse.client.presenter.Presenter;
import projekt.systemanalyse.client.presenter.RundeBeendetPresenter;
import projekt.systemanalyse.client.presenter.SpielBeendetPresenter;
import projekt.systemanalyse.client.presenter.SpielStartenPresenter;
import projekt.systemanalyse.client.presenter.ZugBeendetPresenter;
import projekt.systemanalyse.client.view.LoginView;
import projekt.systemanalyse.client.view.MainLayoutView;
import projekt.systemanalyse.client.view.RundeBeendetView;
import projekt.systemanalyse.client.view.SpielBeendetView;
import projekt.systemanalyse.client.view.SpielStartenView;
import projekt.systemanalyse.client.view.ZugBeendetView;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.exceptions.InstanceCreationException;
import projekt.systemanalyse.shared.exceptions.SpielerZuweisenException;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final SimpleEventBus eventBus;
	private final SpielServiceAsync rpcService;
	private HasWidgets container;

	public AppController(SpielServiceAsync rpcService, SimpleEventBus eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	// //////////////////////////////////////////////////////////////////////////
	// //// Event Handling
	// /////////////////////////////////////////////////////////////////////////
	private void bind() {
		History.addValueChangeHandler(this);

		// /////////////////////////////////////
		// Event welches das Spiel startet
		eventBus.addHandler(StartSpielEvent.TYPE, new StartSpielEventHandler() {
			public void onStartSpiel(StartSpielEvent event, Spiel spiel, Spieler spieler) {
				History.newItem("spiel", false);
				Presenter presenter = new MainLayoutPresenter(rpcService, eventBus,
						new MainLayoutView(), spiel, spieler);
				if (presenter != null) {
					presenter.go(container);
				}
			}
		});

		// /////////////////////////////////////
		// Event welches ein Spiel erstellt
		eventBus.addHandler(SpielErstelltEvent.TYPE,
				new SpielErstelltEventHandler() {

					public void onSpielErstellt(SpielErstelltEvent event,
							Spiel spiel, Spieler spieler) {
						History.newItem("erstellen", false);
						Presenter presenter = new SpielStartenPresenter(
								rpcService, eventBus, new SpielStartenView(),
								spieler, spiel);
						if (presenter != null) {
							presenter.go(container);
						}

					}
				});
		
		// /////////////////////////////////////
		// Event welches wieder den Startbildschirm aufruft, 
		// dieses mal aber mit dem bereits existierenden Spieler
		eventBus.addHandler(SpielBeitretenAbbrechenEvent.TYPE,
				new SpielBeitretenAbbrechenEventHandler() {
					public void onSpielBeitretenAbbrechen(SpielBeitretenAbbrechenEvent event, Spieler spieler) {
						History.newItem("start", false);
						Presenter presenter = new LoginViewPresenter(
								rpcService, eventBus, new LoginView(),
								spieler);
						if (presenter != null) {
							presenter.go(container);
						}

					}
				});

		// /////////////////////////////////////
		// Event welches einem bereits gestarteten Spiel beitritt und den Screen
		// aufruft
		eventBus.addHandler(SpielBeitretenEvent.TYPE,
				new SpielBeitretenEventHandler() {

					public void onSpielBeitreten(SpielBeitretenEvent event,
							Spiel spiel, Spieler spieler) {
						History.newItem("erstellen", false);
						Presenter presenter = new SpielStartenPresenter(
								rpcService, eventBus, new SpielStartenView(),
								spieler, spiel);
						if (presenter != null) {
							presenter.go(container);
						}

					}
				});
		
		// /////////////////////////////////////
		// Event welches den Zug Beendet Screen zum warten aufruft
		// aufruft
		eventBus.addHandler(ZugBeendetEvent.TYPE,
				new ZugBeendetEventHandler() {

					public void onZugBeendet(ZugBeendetEvent event,
							Spiel spiel, Spieler spieler) {
						History.newItem("zug", false);
						Presenter presenter = new ZugBeendetPresenter(
								rpcService, eventBus, new ZugBeendetView(),
								spieler, spiel);
						if (presenter != null) {
							presenter.go(container);
						}

					}
				});
		
		// /////////////////////////////////////
		// Event welches den AuswertungsScreen aufruft, da die Runde Beendet wurde
		// aufruft
		eventBus.addHandler(RundeBeendetEvent.TYPE,
				new RundeBeendetEventHandler() {

					public void onRundeBeendet(RundeBeendetEvent event,
							Spiel spiel, Spieler spieler) {
						History.newItem("auswertung", false);
						Presenter presenter = new RundeBeendetPresenter(
								rpcService, eventBus, new RundeBeendetView(),
								spieler, spiel);
						if (presenter != null) {
							presenter.go(container);
						}

					}
				});
		
		// /////////////////////////////////////
		// Event welches den Spiel Beendet Screen aufruft, da die Siegbedingung erf�llt wurde
		// 
		eventBus.addHandler(SpielBeendetEvent.TYPE,
				new SpielBeendetEventHandler() {

					public void onSpielBeendet(SpielBeendetEvent event,
							Spiel spiel, Spieler spieler) {
						History.newItem("ende", false);
						Presenter presenter = new SpielBeendetPresenter(
								rpcService, eventBus, new SpielBeendetView(),
								spieler, spiel);
						if (presenter != null) {
							presenter.go(container);
						}

					}
				});
		
		eventBus.addHandler(ZurueckZuLobbyEvent.TYPE,
				new ZurueckZuLobbyEventHandler() {

					@Override
					public void onZurueckZuLobby(ZurueckZuLobbyEvent event,
							Spieler spieler) {
						History.newItem("start", false);
						Presenter presenter = new LoginViewPresenter(
								rpcService, eventBus, new LoginView(),
								spieler);
						if (presenter != null) {
							presenter.go(container);
						}
						
					}
				});
	}

	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("start");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("start")) {
				presenter = new LoginViewPresenter(rpcService, eventBus,
						new LoginView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
