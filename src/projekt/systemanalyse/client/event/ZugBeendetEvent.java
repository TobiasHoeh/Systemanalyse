package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.Spiel;

import com.google.gwt.event.shared.GwtEvent;

public class ZugBeendetEvent extends GwtEvent<ZugBeendetEventHandler> {
	public static Type<ZugBeendetEventHandler> TYPE = new Type<ZugBeendetEventHandler>();
	
	Spiel spiel;
	Spieler spieler;
	
	public ZugBeendetEvent(Spiel spiel, Spieler spieler) {
		this.spiel = spiel;
		this.spieler = spieler;
	}
	@Override
	public Type<ZugBeendetEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ZugBeendetEventHandler handler) {
		handler.onZugBeendet(this,spiel,spieler);
	}
}