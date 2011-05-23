//package projekt.systemanalyse.client.view.OLD;
//
//import projekt.systemanalyse.client.presenter.OLD.MainLayoutPresenterOLD;
//import projekt.systemanalyse.client.view.UIFactory;
//
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//import com.smartgwt.client.widgets.layout.LayoutSpacer;
//import com.smartgwt.client.widgets.layout.VLayout;
//import com.smartgwt.client.widgets.layout.HLayout;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.Button;
//import com.smartgwt.client.widgets.ImgButton;
//import com.smartgwt.client.widgets.Label;
//
//public class MainLayoutView extends HLayout implements MainLayoutPresenterOLD.Display {
//	public Button startButton = new Button();
////	public HeaderWidget header;
//	public Widget header;
//	public Widget body;
//	VLayout center;
//	
//	public MainLayoutView(String bodyView) {
//		LayoutSpacer leftSide = UIFactory.getLayoutSpacer("20%","*");
//		
//		center = UIFactory.getVLayout("1280px", "*");
////		center.setSize("60%", "100%");
//		
//		LayoutSpacer rightSide = UIFactory.getLayoutSpacer("20%","*");
//		
////		header = new HeaderWidget();
//		
////		body = new BodyView(bodyView);
//		
////		center.addMember(header);
////		center.addMember(UIFactory.getLayoutSpacer("100%", "15px"));
////		center.addMember(body);
//		
//		this.setSize("1280px", "100%");
//		this.addMember(leftSide);
//		this.addMember(center);
//		this.addMember(rightSide);
//		this.setTitle("BikeCity");
//		this.setCanSelectText(true);
//		this.setShowHover(false);
//		
//	}
//	
//	public HasClickHandlers getStartButton() {
//		return startButton;
//	}
//	
//	public Widget asWidget() {
//		return this;
//	}
//
//	@Override
//	public void setContextArea(Widget contextArea) {
//		this.body = contextArea;
//		center.addMember(body);
//	}
//	
//	@Override
//	public void setHeader(Widget header) {
//		this.header = header;
//		center.addMember(header);
//	}
//
//}
