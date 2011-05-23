package projekt.systemanalyse.client.event;

import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;

import com.google.gwt.event.shared.EventHandler;

public interface SpielBeitretenAbbrechenEventHandler extends EventHandler {
	void onSpielBeitretenAbbrechen(SpielBeitretenAbbrechenEvent event, Spieler spieler);
}