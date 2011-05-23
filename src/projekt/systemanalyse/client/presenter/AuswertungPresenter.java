package projekt.systemanalyse.client.presenter;

import java.util.ArrayList;

import projekt.systemanalyse.client.SpielServiceAsync;
import projekt.systemanalyse.client.event.RundeBeendetEvent;
import projekt.systemanalyse.client.event.SpielBeendetEvent;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class AuswertungPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		ListBox getLbSpielerOnline();

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
	private Timer timer;

	public AuswertungPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view, Spieler spieler, Spiel spiel) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		
		this.spieler = spieler;
		this.spiel = spiel;
		
		setSpielerName(spieler.getName());
		setFirmenName(spieler.getFirma());
		setSpielName(spiel.getSpielName());
		
//		display.getBtnSpielStarten().setEnabled(false);
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

	}
	
	// //////////////////////////////////////////////////////////////////////////
	// //// get Methoden für neue Informationen vom Server
	// /////////////////////////////////////////////////////////////////////////
	
	public void getAktSpielerAnzahl() {
		rpcService.getSpielerAnzahl(spiel.getSpielID(), new AsyncCallback<Integer>() {
			
			@Override
			public void onSuccess(Integer result) {
				aktSpielerAnzahl = result;
//				setSpielerAnzahl(aktSpielerAnzahl, maxSpielerAnzahl);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fehler beim abrufen der Spieleranzahl; SpielStartenPresenter");
			}
		});
	}
	
	public void getAktSpieler() {
		rpcService.getSpielerListe(spiel.getSpielID(), new AsyncCallback<ArrayList<Spieler>>() {
			
			@Override
			public void onSuccess(ArrayList<Spieler> result) {
				setLbSpieler(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("GetSpielerListe fehlgeschlagen!");
				
			}
		});
	}
	
	// //////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	public void setRundenNr(String runde) {
		display.getLblRundenNr().setText(runde);
	}
	
//	public void setSpielerAnzahl(int aktSpielerAnzahl, int maxSpielerAnzahl) {
//		display.getLblMitspieler().setText("Mitspieler (" + aktSpielerAnzahl + "/" + maxSpielerAnzahl +"):");
//		if(aktSpielerAnzahl == maxSpielerAnzahl) {
////			rpcService.beendeRunde(spiel.getSpielID(), new AsyncCallback<Void>() {
////				
////				@Override
////				public void onSuccess(Void result) {
////					GWT.log("Runde beendet erfolgreich");
////					eventBus.fireEvent(new RundeBeendetEvent(spiel, spieler));
////					
////				}
////				
////				@Override
////				public void onFailure(Throwable caught) {
////					GWT.log("Runde beendet Fehler");
////					
////				}
//			});
//			
//		}
//	}
	
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
