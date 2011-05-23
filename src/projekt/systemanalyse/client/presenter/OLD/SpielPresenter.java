//package projekt.systemanalyse.client.presenter.OLD;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//import projekt.systemanalyse.client.event.StartSpielEvent;
//import projekt.systemanalyse.client.presenter.Presenter;
//import projekt.systemanalyse.client.presenter.OLD.LoginPresenter.Display;
//import projekt.systemanalyse.shared.Spiel;
//
//import com.google.gwt.event.shared.SimpleEventBus;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.HasWidgets;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.events.ClickEvent;
//import com.smartgwt.client.widgets.events.ClickHandler;
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//import com.smartgwt.client.widgets.events.ValueChangedEvent;
//
//public class SpielPresenter implements Presenter {
//
//	public interface Display {
//		Widget asWidget();
//
//		HasClickHandlers getStartButton();
//	}
//
//	private final SpielServiceAsync rpcService;
//	private final SimpleEventBus eventBus;
//	private final Display display;
//	
//	public SpielPresenter(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//	}
//	
//	public void go(final HasWidgets container) {
//		bind();
//		container.clear();
//		container.add(display.asWidget());
//	}
//
//	public void bind() {
//		display.getStartButton().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				eventBus.fireEvent(new StartSpielEvent());
//			}
//		});
//		
//		
//	}
//
//}
