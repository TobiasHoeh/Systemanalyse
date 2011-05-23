//package projekt.systemanalyse.client.presenter.OLD;
//
//
//import projekt.systemanalyse.client.view.components.ApplicationMenuWidget;
//
//
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.layout.HLayout;
//import com.smartgwt.client.widgets.layout.VLayout;
//
//
//public class BodyView extends HLayout implements BodyPresenter.Display {
//	
////	public VLayout informationArea = new InformationAreaView();
//	public VLayout contextContainer = new VLayout();
//	
//	
//	
////	public VLayout contextArea;
////	public HLayout navigation = new ApplicationMenuWidget();
//	public Widget navigation = new ApplicationMenuWidget();
//	public Widget contextArea;
//	public Widget informationArea;
//	
//	public BodyView() {
//		setWidth100();
//		setHeight100();
////		contextContainer.addMember(navigation);
////		if(tab == "unternehmen") {
////			contextArea = new UnternehmenView();
////		}
////		if(tab == "einkauf") {
////			contextArea = EinkaufView.getEinkaufView();
////		}
////		contextContainer.addMember(contextArea);
//		contextContainer.setStyleName("Spiel-Container");
//		
//		this.addMember(contextContainer);
////		this.addMember(informationArea);
//	}
//	
//	public ApplicationMenuWidget getApplicationMenu() {
//		return (ApplicationMenuWidget) navigation;
//	}
//	
//	@Override
//	public void setApplicationMenu(Widget appMenu) {
//		this.navigation = appMenu;
//		contextContainer.addMember(navigation);
//	}
//	
//	@Override
//	public void setContextArea(Widget contextArea) {
//		this.contextArea = contextArea;
//		contextContainer.addMember(contextArea);
//	}
//
//	@Override
//	public void setInformationArea(Widget informationArea) {
//		this.informationArea = informationArea;
//		this.addMember(informationArea);
//	}
//}
