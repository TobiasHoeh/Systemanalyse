//package projekt.systemanalyse.client.presenter.OLD;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//import projekt.systemanalyse.client.event.EditEinkaufEvent;
//import projekt.systemanalyse.client.event.EditUNPlanungEvent;
//import projekt.systemanalyse.client.event.StartSpielEvent;
//import projekt.systemanalyse.client.presenter.Presenter;
//import projekt.systemanalyse.client.presenter.OLD.LoginPresenter.Display;
////import projekt.systemanalyse.client.view.components.HeaderWidget;
////import projekt.systemanalyse.client.view.HeaderWidget;
//import projekt.systemanalyse.client.view.OLD.EinkaufView;
//import projekt.systemanalyse.client.view.OLD.MainLayoutView;
//import projekt.systemanalyse.client.view.components.HeaderWidget;
//import projekt.systemanalyse.shared.Spiel;
//
//import com.google.gwt.event.dom.client.HasChangeHandlers;
//import com.google.gwt.event.shared.SimpleEventBus;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.HasWidgets;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.ImgButton;
//import com.smartgwt.client.widgets.Label;
//import com.smartgwt.client.widgets.events.ClickEvent;
//import com.smartgwt.client.widgets.events.ClickHandler;
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//import com.smartgwt.client.widgets.events.ValueChangedEvent;
//
//public class MainLayoutPresenterOLD implements Presenter {
//
//	public interface Display {
//		Widget asWidget();
//		
//		// ApplicationMenu
////		HasClickHandlers getUnternehmenButton();
////		HasClickHandlers getEinkaufButton();
////		HasClickHandlers getStatistikButton();
////		HasClickHandlers getVerkaufButton();
////		HasClickHandlers getProduktionButton();
//		
//		//EinkaufView
//		
////		HasClickHandlers getArtikelHinzufuegenButton();
////		HasChangeHandlers getlbTyp();
////		HasValue<String> getTxtPreis();
////		HasValue<String> getTxtMenge();
////		HasValue<String> getTxtGesamt();
//		
//		void setHeader(Widget header);
//		void setContextArea(Widget contextArea);
//	}
//
//	private final SpielServiceAsync rpcService;
//	private final SimpleEventBus eventBus;
//	private final Display display;
//	private int spielerID;
//	private static HeaderPresenter headerPresenter;
//	private BodyPresenter bodyPresenter;
//	HeaderWidget header;
//	
//	public MainLayoutPresenterOLD(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view, int spielerID, String tab) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//		
//		headerPresenter = new HeaderPresenter(rpcService, eventBus, new HeaderWidget(), spielerID);
//		view.setHeader(headerPresenter.getDisplay());
//		
//		bodyPresenter = new BodyPresenter(rpcService, eventBus, new BodyView(), tab, spielerID);
//		view.setContextArea(bodyPresenter.getDisplay());
//	}
//	
//	public MainLayoutPresenterOLD(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view, int spielerID, String tab, boolean body) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//	
//		view.setHeader(headerPresenter.getDisplay());
//		
//		bodyPresenter = new BodyPresenter(rpcService, eventBus, new BodyView(), tab, spielerID);
//		view.setContextArea(bodyPresenter.getDisplay());
//	}
//	
////	public void setBodyPresenter(String tab) {
////		bodyPresenter = new BodyPresenter(rpcService, eventBus, new BodyView(), tab);
////		display.setContextArea(bodyPresenter.getDisplay());
////	}
//	
//	
//	public void go(final HasWidgets container) {
//		bind();
//		container.clear();
//		container.add(display.asWidget());
//	}
//	
//
//	
//	public void bind() {
//
//	}
//
//}
