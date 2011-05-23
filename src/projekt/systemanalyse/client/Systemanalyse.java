package projekt.systemanalyse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class Systemanalyse implements EntryPoint {

	public void onModuleLoad() {
		SpielServiceAsync rpcService = GWT.create(SpielService.class);
		SimpleEventBus eventBus = new SimpleEventBus();
		AppController appViewer = new AppController(rpcService, eventBus);
		// get rid of scroll bars, and clear out the window's built-in margin,
		// because we want to take advantage of the entire client area
		Window.enableScrolling(true);
		Window.setMargin("0px");
		Window.setTitle("BikeCity");
		
		appViewer.go(RootPanel.get());
	}
}
