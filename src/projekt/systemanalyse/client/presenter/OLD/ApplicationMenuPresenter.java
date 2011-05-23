//package projekt.systemanalyse.client.presenter.OLD;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//
//import com.google.gwt.event.shared.SimpleEventBus;
//import com.google.gwt.user.client.ui.HasWidgets;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.events.ClickEvent;
//import com.smartgwt.client.widgets.events.ClickHandler;
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//
//public class ApplicationMenuPresenter {
//	public interface Display {
//		Widget asWidget();
//
//		HasClickHandlers getUnternehmenButton();
//
//		HasClickHandlers getEinkaufButton();
//
//		HasClickHandlers getStatistikButton();
//
//		HasClickHandlers getVerkaufButton();
//
//		HasClickHandlers getProduktionButton();
//	}
//
//	private final SpielServiceAsync rpcService;
//	private final SimpleEventBus eventBus;
//	private final Display display;
//
//	public ApplicationMenuPresenter(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//		bind();
//	}
//
//	public void go(final HasWidgets container) {
//		bind();
//		container.clear();
//		container.add(display.asWidget());
//	}
//
//	public void bind() {
//		display.getUnternehmenButton().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				eventBus.fireEvent(new EditUNPlanungEvent());
//			}
//		});
//
//		display.getEinkaufButton().addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				eventBus.fireEvent(new EditEinkaufEvent());
//			}
//		});
//	}
//
//	public Widget getDisplay() {
//		return display.asWidget();
//	}
//
//}
