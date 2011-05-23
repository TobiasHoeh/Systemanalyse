package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.GwtEvent;

public class SpielBeendetEvent extends GwtEvent<SpielBeendetEventHandler> {
	public static Type<SpielBeendetEventHandler> TYPE = new Type<SpielBeendetEventHandler>();
	private Spieler spieler;
	private Spiel spiel;
	
	
	public SpielBeendetEvent(Spiel spiel, Spieler spieler) {
		this.spiel = spiel;
		this.spieler = spieler;
	}
	@Override
	public Type<SpielBeendetEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SpielBeendetEventHandler handler) {
		handler.onSpielBeendet(this, spiel, spieler);
	}
}