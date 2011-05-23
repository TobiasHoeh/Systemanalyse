package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.EventHandler;

public interface SpielBeendetEventHandler extends EventHandler {
	void onSpielBeendet(SpielBeendetEvent event, Spiel spiel, Spieler spieler);
}