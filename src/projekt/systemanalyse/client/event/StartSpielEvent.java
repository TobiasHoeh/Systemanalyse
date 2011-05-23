package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.GwtEvent;

public class StartSpielEvent extends GwtEvent<StartSpielEventHandler> {
	public static Type<StartSpielEventHandler> TYPE = new Type<StartSpielEventHandler>();
	private Spieler spieler;
	private Spiel spiel;
	
	
	public StartSpielEvent(Spiel spiel, Spieler spieler) {
		this.spiel = spiel;
		this.spieler = spieler;
	}
	@Override
	public Type<StartSpielEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(StartSpielEventHandler handler) {
		handler.onStartSpiel(this, spiel, spieler);
	}
}