//package projekt.systemanalyse.client.presenter.OLD;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//import projekt.systemanalyse.client.presenter.Presenter;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.event.shared.SimpleEventBus;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.HasWidgets;
//import com.google.gwt.user.client.ui.Widget;
//
//public class UnternehmenViewPresenter implements Presenter {
//
//	public interface Display {
//		Widget asWidget();
//		HasClickHandlers getBtnUebernehmen();
//		HasValue<String> getTxtEinstellen();
//		HasValue<String> getTxtLagerbedarf();
//		HasValue<String> getTxtVerkaufspreis();
//	}
//
//	private final SpielServiceAsync rpcService;
//	private final SimpleEventBus eventBus;
//	private final Display display;
//	
//	public UnternehmenViewPresenter(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//		bind();
//		
//	}
//	
//	public void go(final HasWidgets container) {
//		bind();
//		container.clear();
//		container.add(display.asWidget());
//	}
//	
//	public Widget getDisplay() {
//		return display.asWidget();
//	}
//
//	public void bind() {
//		display.getBtnUebernehmen().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				Window.confirm("Uebernehmen geklickt");
//				GWT.log("Uebernehmen geklickt");
//			}
//		});
//	}
//
//}
