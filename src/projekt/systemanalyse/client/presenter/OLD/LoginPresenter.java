//package projekt.systemanalyse.client.presenter.OLD;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//import projekt.systemanalyse.client.event.EditUNPlanungEvent;
//import projekt.systemanalyse.client.event.StartSpielEvent;
//import projekt.systemanalyse.client.presenter.Presenter;
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
//
//public class LoginPresenter implements Presenter {
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
//	public LoginPresenter(SpielServiceAsync rpcService,
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
//				startSpiel();
//				eventBus.fireEvent(new StartSpielEvent());
////				eventBus.fireEvent(new EditUNPlanungEvent());
//			}
//		});
//	}
//
//	private void startSpiel() {
//		rpcService.startSpiel(new AsyncCallback<Spiel>() {
//			public void onSuccess(Spiel result) {
//				Window.confirm("Das Spiel wurde gestartet.");
//			}
//
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when starting the game");
//			}
//
//		});
//	}
//}
