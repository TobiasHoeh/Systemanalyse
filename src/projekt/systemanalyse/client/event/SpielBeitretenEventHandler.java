package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.EventHandler;

public interface SpielBeitretenEventHandler extends EventHandler {
	void onSpielBeitreten(SpielBeitretenEvent event, Spiel spiel, Spieler spieler);
}