//package projekt.systemanalyse.client.view.OLD;
//
//
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.widgets.Canvas;
//import com.smartgwt.client.widgets.Img;
//import com.smartgwt.client.widgets.ImgButton;
//import com.smartgwt.client.widgets.Label;
//import com.smartgwt.client.widgets.layout.HLayout;
//import com.smartgwt.client.widgets.layout.LayoutSpacer;
//
//import projekt.systemanalyse.client.presenter.OLD.HeaderPresenter;
//import projekt.systemanalyse.client.view.UIFactory;
//
//public class HeaderWidget extends HLayout implements HeaderPresenter.Display {
//	private Label lblSpielerName;
//	private Label lblFirma;
//	private Label lblRundeNr;
//	private ImgButton btnBeenden;
//	private ImgButton btnHilfe;
//	
//	public HeaderWidget() {
//		// TODO Farbe entfernen
////		this.setBackgroundColor("#3399FF");
//		
//		// Einstellungen für den Header
//		setHeight("70px");
//		setWidth("100%");
//		setStyleName("Spiel-Header-Head");
//		
//		// Links und Mitte des Header
//		Img imgLogo = UIFactory.getImg("/images/logo.png", "Spiel-Header-Logo");
//		imgLogo.setWidth("709px");
//		imgLogo.setHeight("71px");
////		Label lblSpielName = UIFactory.getLabel("BikeCity", "Spiel-Header-SpielName");
////		lblSpielName.setWidth100();
//		LayoutSpacer spacer = UIFactory.getLayoutSpacer("100%", "70px");
//		
//		
//		// rechte Seite mit Labels
//		Canvas rightControls = UIFactory.getCanvas("335px", "70px");
//		
//		Label lblSpieler = UIFactory.getLabel("Spieler:", 0,15,73,19, "Spiel-Header-Description");
//		Label lblUnternehmen = UIFactory.getLabel("Unternehmen:", 0,40,73,19, "Spiel-Header-Description");
//		Label lblRunde = UIFactory.getLabel("Runde:", 201,15,45,19, "Spiel-Header-Description");
//		
//		// TODO add Parameter
//		lblSpielerName = UIFactory.getStyledLabel("", 89,15,106,19);
//		lblFirma = UIFactory.getStyledLabel("", 89,40,106,19);
//		lblRundeNr = UIFactory.getStyledLabel("", 252, 15, 73, 19);
//		
//		// TODO add Parameter
//		btnBeenden = UIFactory.getImgButton(262, 40, 22, 19, null);
//		btnHilfe = UIFactory.getImgButton(290, 40, 22, 19, null);
//		
//		
//		rightControls.addChild(lblSpieler);
//		rightControls.addChild(lblUnternehmen);
//		rightControls.addChild(lblRunde);
//		rightControls.addChild(lblRundeNr);
//		rightControls.addChild(lblSpielerName);
//		rightControls.addChild(lblFirma);
//		rightControls.addChild(btnBeenden);
//		rightControls.addChild(btnHilfe);
//		
//		// add Elements to Header
//		this.addMember(imgLogo);
//		this.addMember(spacer);
//		this.addMember(rightControls);
//		
//	}
//	
//	public Widget asWidget() {
//		return this;
//	}
//	@Override
//	public Label getLblSpielerName() {
//		return lblSpielerName;
//	}
//	
//	public Label getLblFirmenName() {
//		return lblFirma;
//	}
//	
//	public Label getLblRundenNr() {
//		return lblRundeNr;
//	}
//	
//	public ImgButton getBtnBeenden() {
//		return btnBeenden;
//	}
//	
//	public ImgButton getBtnHilfe() {
//		return btnHilfe;
//	}
//}
