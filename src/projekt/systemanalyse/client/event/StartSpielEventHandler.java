package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.EventHandler;

public interface StartSpielEventHandler extends EventHandler {
	void onStartSpiel(StartSpielEvent event, Spiel spiel, Spieler spieler);
}