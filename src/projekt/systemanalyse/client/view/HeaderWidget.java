package projekt.systemanalyse.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

import projekt.systemanalyse.client.presenter.HeaderPresenter;


import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class HeaderWidget extends HorizontalPanel implements HeaderPresenter.Display {
	Label lblSpielername;
	Label lblFirma;
	Label lblRundenNr;
	PushButton pshbtnBeeenden;
	PushButton pshbtnHilfe;
	private Label lblSpiel;
	
	public HeaderWidget() {
		setBorderWidth(0);
		setSize("1280px", "71px");
		setHorizontalAlignment(ALIGN_CENTER);
		
		Image image = new Image("images/logo.png");
		image.setStyleName("Spiel-Header-Logo");
		add(image);
		image.setSize("709px", "71px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		add(absolutePanel);
		absolutePanel.setSize("571px", "71px");
		
		Label lblSpieler = new Label("Spieler:");
		lblSpieler.setStyleName("Spiel-Header-Description");
		lblSpieler.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		absolutePanel.add(lblSpieler, 208, 10);
		lblSpieler.setSize("92px", "18px");
		
		Label lblUnternehmen = new Label("Unternehmen:");
		lblUnternehmen.setStyleName("Spiel-Header-Description");
		lblUnternehmen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		absolutePanel.add(lblUnternehmen, 208, 38);
		lblUnternehmen.setSize("92px", "14px");
		
		lblSpielername = new Label("");
		lblSpielername.setStyleName("Spiel-Header-SpielerName");
		absolutePanel.add(lblSpielername, 310, 10);
		lblSpielername.setSize("81px", "18px");
		
		lblFirma = new Label("");
		lblFirma.setStyleName("Spiel-Header-SpielerName");
		absolutePanel.add(lblFirma, 310, 34);
		lblFirma.setSize("81px", "18px");
		
		Label lblRunde = new Label("Runde:");
		lblRunde.setStyleName("Spiel-Header-Description");
		lblRunde.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		absolutePanel.add(lblRunde, 417, 10);
		lblRunde.setSize("47px", "18px");
		
		lblRundenNr = new Label("");
		lblRundenNr.setStyleName("Spiel-Header-SpielerName");
		absolutePanel.add(lblRundenNr, 470, 10);
		lblRundenNr.setSize("81px", "18px");
		
		Label lblSpielLabel = new Label("Spiel:");
		lblSpielLabel.setStyleName("Spiel-Header-Description");
		lblSpielLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		absolutePanel.add(lblSpielLabel, 417, 38);
		lblSpielLabel.setSize("47px", "18px");
		
		lblSpiel = new Label("");
		lblSpiel.setStyleName("Spiel-Header-SpielerName");
		absolutePanel.add(lblSpiel, 470, 38);
		lblSpiel.setSize("81px", "18px");
		
		this.setStyleName("Spiel-Header-Head");
	}

	@Override
	public Label getLblSpielerName() {
		return lblSpielername;
	}

	@Override
	public Label getLblFirmenName() {
		return lblFirma;
	}

	@Override
	public Label getLblRundenNr() {
		return lblRundenNr;
	}

	@Override
	public PushButton getBtnBeenden() {
		return pshbtnBeeenden;
	}

	@Override
	public PushButton getBtnHilfe() {
		return pshbtnHilfe;
	}
	public Label getLblSpiel() {
		return lblSpiel;
	}
}
