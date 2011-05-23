package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.GwtEvent;

public class ZurueckZuLobbyEvent extends GwtEvent<ZurueckZuLobbyEventHandler> {
	public static Type<ZurueckZuLobbyEventHandler> TYPE = new Type<ZurueckZuLobbyEventHandler>();
	private Spieler spieler;
	
	
	public ZurueckZuLobbyEvent(Spieler spieler) {
		this.spieler = spieler;
	}
	@Override
	public Type<ZurueckZuLobbyEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ZurueckZuLobbyEventHandler handler) {
		handler.onZurueckZuLobby(this, spieler);
	}
}