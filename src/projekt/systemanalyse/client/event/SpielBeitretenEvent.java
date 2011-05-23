package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.GwtEvent;

public class SpielBeitretenEvent extends GwtEvent<SpielBeitretenEventHandler> {
	public static Type<SpielBeitretenEventHandler> TYPE = new Type<SpielBeitretenEventHandler>();
	private Spiel spiel;
	private Spieler spieler;
	
	public SpielBeitretenEvent(Spiel spiel, Spieler spieler) {
		this.spiel = spiel;
		this.spieler = spieler;
	}
	@Override
	public Type<SpielBeitretenEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SpielBeitretenEventHandler handler) {
		handler.onSpielBeitreten(this, spiel, spieler);
	}
}