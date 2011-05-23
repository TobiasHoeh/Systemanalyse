package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.Spiel;

import com.google.gwt.event.shared.GwtEvent;

public class SpielErstelltEvent extends GwtEvent<SpielErstelltEventHandler> {
	public static Type<SpielErstelltEventHandler> TYPE = new Type<SpielErstelltEventHandler>();
	
	Spiel spiel;
	Spieler spieler;
	
	public SpielErstelltEvent(Spiel spiel, Spieler spieler) {
		this.spiel = spiel;
		this.spieler = spieler;
	}
	@Override
	public Type<SpielErstelltEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SpielErstelltEventHandler handler) {
		handler.onSpielErstellt(this,spiel,spieler);
	}
}