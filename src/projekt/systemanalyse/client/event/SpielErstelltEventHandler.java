package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.Spiel;

import com.google.gwt.event.shared.EventHandler;

public interface SpielErstelltEventHandler extends EventHandler {
	void onSpielErstellt(SpielErstelltEvent event, Spiel spiel, Spieler spieler);
}