package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.client.event.SpielBeitretenEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class SpielBeitretenAbbrechenEvent extends GwtEvent<SpielBeitretenAbbrechenEventHandler> {
	public static Type<SpielBeitretenAbbrechenEventHandler> TYPE = new Type<SpielBeitretenAbbrechenEventHandler>();
	private Spieler spieler;
	
	
	public SpielBeitretenAbbrechenEvent(Spieler spieler) {
		this.spieler = spieler;
	}
	@Override
	public Type<SpielBeitretenAbbrechenEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SpielBeitretenAbbrechenEventHandler handler) {
		handler.onSpielBeitretenAbbrechen(this, spieler);
	}
}