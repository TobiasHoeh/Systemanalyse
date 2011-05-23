package projekt.systemanalyse.client.presenter;

import java.util.ArrayList;

import projekt.systemanalyse.client.SpielServiceAsync;
import projekt.systemanalyse.client.event.RundeBeendetEvent;
import projekt.systemanalyse.client.event.SpielBeendetEvent;
import projekt.systemanalyse.client.event.SpielBeitretenAbbrechenEvent;
import projekt.systemanalyse.client.event.StartSpielEvent;
import projekt.systemanalyse.client.event.ZurueckZuLobbyEvent;
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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class SpielBeendetPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		Label getLblSpielerName();

		Label getLblFirmenName();

		Label getLblRundenNr();
		
		Label getLblSpiel();
		
		public Button getBtnZurueckZurLobby();
		public Label getLblErster();
		public ListBox getLbRest();
		public Label getLblDritter();
		public Label getLblZweiter();
	}

	private final SpielServiceAsync rpcService;
	private final SimpleEventBus eventBus;
	private final Display display;
	private Spieler spieler;
	private Spiel spiel;
	HeaderWidget header;

	public SpielBeendetPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view, Spieler spieler, Spiel spiel) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		
		this.spieler = spieler;
		this.spiel = spiel;
		
		setSpielerName(spieler.getName());
		setFirmenName(spieler.getFirma());
		setRundenNr();
		setSpielName(spiel.getSpielName());
		getRangfolge();
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
		display.getBtnZurueckZurLobby().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				rpcService.SpielUndSpielerTrennen(spieler.getSpielerID(), spiel.getSpielID(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						GWT.log("Spieler von Spiel entfernen erfolgreich!");
						rpcService.getSpieler(spieler.getSpielerID(), new AsyncCallback<Spieler>() {
							
							@Override
							public void onSuccess(Spieler result) {
								GWT.log("Neue Spielerversion holen erfolgreich!");
								eventBus.fireEvent(new ZurueckZuLobbyEvent(result));
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Neue Spielerversion holen fehlgeschlagen!");
								Window.alert(caught.getMessage());
								
							}
						});
						
						
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Spieler von Spiel entfernen fehlgeschlagen!");
						Window.alert(caught.getMessage());
					}
				});
			}
		});
	}
	
	// //////////////////////////////////////////////////////////////////////////
	// //// get Methoden für neue Informationen vom Server
	// /////////////////////////////////////////////////////////////////////////
	
	
	
	// //////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	public void setRundenNr() {
		display.getLblRundenNr().setText("Spielende!");
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
	
	public void setSpielerSieger(String spielerName) {
		display.getLblErster().setText(spielerName);
	}
	
	public void setSpielerZweiter(String spielerName) {
		display.getLblZweiter().setText(spielerName);
	}
	
	public void setSpielerDritter(String spielerName) {
		display.getLblDritter().setText(spielerName);
	}
	
	public void getRangfolge() {
		rpcService.getRangfolge(spiel.getSpielID(), new AsyncCallback<ArrayList<Spieler>>() {
			
			@Override
			public void onSuccess(ArrayList<Spieler> result) {
				
				for(int i=0; i<result.size();i++) {
					Spieler spieler = result.get(i);
					switch (i) {
					case 0:
						setSpielerSieger(spieler.getName());
						break;
					case 1:
						setSpielerZweiter(spieler.getName());
					break;
					case 2:
						setSpielerDritter(spieler.getName());
					break;
					default:
						int platz = i +1;
						display.getLbRest().addItem(platz + ". - " + spieler.getName());
						break;
					}
					
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Holen der Rangfolge fehlgeschlagen.");
				Window.alert(caught.getMessage());				
			}
		});
	}
	

}
