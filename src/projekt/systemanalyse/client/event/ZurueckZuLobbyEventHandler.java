package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.EventHandler;

public interface ZurueckZuLobbyEventHandler extends EventHandler {
	void onZurueckZuLobby(ZurueckZuLobbyEvent event, Spieler spieler);
}