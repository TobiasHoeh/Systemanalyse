//package projekt.systemanalyse.client.presenter.OLD;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//import projekt.systemanalyse.client.presenter.Presenter;
//import projekt.systemanalyse.client.view.OLD.EinkaufView;
//import projekt.systemanalyse.client.view.OLD.InformationAreaView;
//import projekt.systemanalyse.client.view.OLD.UnternehmenView;
//import projekt.systemanalyse.client.view.components.ApplicationMenuWidget;
//
//
//import com.google.gwt.event.shared.SimpleEventBus;
//import com.google.gwt.user.client.ui.HasWidgets;
//import com.google.gwt.user.client.ui.Widget;
//
//public class BodyPresenter implements Presenter {
//
//	public interface Display {
//		Widget asWidget();
//		void setApplicationMenu(Widget appMenu);
//		void setContextArea(Widget contextArea);
//		void setInformationArea(Widget informationArea);
//	}
//	
//	public static final String TAB_EINKAUF = "einkauf";
//	public static final String TAB_UNTERNEHMEN = "unternehmen";
//	private final SpielServiceAsync rpcService;
//	private final SimpleEventBus eventBus;
//	private final Display display;
//	private ApplicationMenuPresenter appMenuPresenter;
//	private EinkaufViewPresenter einkaufPresenter;
//	private InformationAreaPresenter informationPresenter;
//	private UnternehmenViewPresenter unternehmenPresenter;
//	
//	public BodyPresenter(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view, String tab, int spielerID) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//		
//		appMenuPresenter = new ApplicationMenuPresenter(rpcService, eventBus, new ApplicationMenuWidget());
//		view.setApplicationMenu(appMenuPresenter.getDisplay());
//		if(tab == TAB_EINKAUF) {
//			einkaufPresenter = new EinkaufViewPresenter(rpcService, eventBus, EinkaufView.getEinkaufView(), spielerID);
//			view.setContextArea(einkaufPresenter.getDisplay());
//		}
//		
//		if(tab == TAB_UNTERNEHMEN) {
//			unternehmenPresenter = new UnternehmenViewPresenter(rpcService, eventBus, new UnternehmenView());
//			view.setContextArea(unternehmenPresenter.getDisplay());
//		}
//		
//		
//		informationPresenter = new InformationAreaPresenter(rpcService, eventBus, new InformationAreaView(), spielerID);
//		view.setInformationArea(informationPresenter.getDisplay());
//		
//	}
//	
//	public void go(final HasWidgets container) {
//		bind();
//		container.clear();
//		container.add(display.asWidget());
//	}
//
//	public void bind() {
//	}
//	
//	public Widget getDisplay() {
//		return display.asWidget();
//	}
//
//}
