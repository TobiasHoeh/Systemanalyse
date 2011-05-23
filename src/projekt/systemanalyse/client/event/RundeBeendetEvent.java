package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.Spiel;

import com.google.gwt.event.shared.GwtEvent;

public class RundeBeendetEvent extends GwtEvent<RundeBeendetEventHandler> {
	public static Type<RundeBeendetEventHandler> TYPE = new Type<RundeBeendetEventHandler>();
	
	Spiel spiel;
	Spieler spieler;
	
	public RundeBeendetEvent(Spiel spiel, Spieler spieler) {
		this.spiel = spiel;
		this.spieler = spieler;
	}
	@Override
	public Type<RundeBeendetEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RundeBeendetEventHandler handler) {
		handler.onRundeBeendet(this,spiel,spieler);
	}
}