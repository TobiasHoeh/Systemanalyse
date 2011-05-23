//package projekt.systemanalyse.client.view.OLD;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.Button;
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//import com.smartgwt.client.widgets.layout.HLayout;
//import com.smartgwt.client.widgets.layout.LayoutSpacer;
//
//import projekt.systemanalyse.client.presenter.OLD.ApplicationMenuPresenter;
//import projekt.systemanalyse.client.view.UIFactory;
//
//
//public class ApplicationMenuWidget extends HLayout implements ApplicationMenuPresenter.Display {
//
//	private static final int APPLICATION_MENU_HEIGHT = 30;
//	private Button btnUnternehmen;
//	private Button btnEinkauf;
//	private Button btnVerkauf;
//	private Button btnProduktion;
//	private Button btnStatistik;
//
//	public ApplicationMenuWidget() {
//		super();
//		GWT.log("init Application Menu()...", null);
//		
//		// SetProperties
//		this.setWidth("100%");
//		this.setHeight(APPLICATION_MENU_HEIGHT);
//		// add Buttons
//		btnUnternehmen = UIFactory.getStyledButton(0, 0, "Unternehmen");
//		btnEinkauf = UIFactory.getStyledButton(0, UIFactory.BUTTON_WIDTH+5, "Einkauf");
//		btnVerkauf = UIFactory.getStyledButton(0, UIFactory.BUTTON_WIDTH+5, "Verkauf");
//		btnProduktion = UIFactory.getStyledButton(0, UIFactory.BUTTON_WIDTH+5, "Produktion");
//		btnStatistik = UIFactory.getStyledButton(0, UIFactory.BUTTON_WIDTH+5, "Statistik");
//		
//		
//		LayoutSpacer auffuellungRechts = UIFactory.getLayoutSpacer("*", "100%");
//		
//		// add Elements to Menu
//		this.addMember(btnUnternehmen);
//		this.addMember(btnEinkauf);
//		this.addMember(btnVerkauf);
//		this.addMember(btnProduktion);
//		this.addMember(btnStatistik);
//		this.addMember(auffuellungRechts);
//		this.setStyleName("Spiel-ApplicationMenu");
//	}
//	
//	public Widget asWidget() {
//		return this;
//	}
//
//	public HasClickHandlers getUnternehmenButton() {
//		return btnUnternehmen;
//	}
//
//
//	public HasClickHandlers getEinkaufButton() {
//		return btnEinkauf;
//	}
//
//
//	public HasClickHandlers getStatistikButton() {
//		return btnStatistik;
//	}
//
//
//	public HasClickHandlers getVerkaufButton() {
//		return btnVerkauf;
//	}
//
//
//	public HasClickHandlers getProduktionButton() {
//		return btnProduktion;
//	}
//}
