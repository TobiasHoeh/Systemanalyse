package projekt.systemanalyse.client.view;

import projekt.systemanalyse.client.presenter.SpielBeendetPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.text.client.IntegerRenderer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.widgets.Img;
import com.google.gwt.user.client.ui.Image;


public class SpielBeendetView extends VerticalPanel implements SpielBeendetPresenter.Display {
	AbsolutePanel center;
	HeaderWidget header = new HeaderWidget();
	private Label lblWarten;
	private Button btnZurueckZurLobby;
	private Label lblErster;
	private ListBox lbRest;
	private Label lblDritter;
	private Label lblZweiter;
	
	public SpielBeendetView()  {
		setWidth("100%");
		setStyleName("MainWindow");
		this.setHorizontalAlignment(ALIGN_CENTER);
		
		center = new AbsolutePanel();
		center.setSize("1280px", "600px");
		this.setCellHorizontalAlignment(center, ALIGN_CENTER);
		
		
//		this.setSize("1280px", "100%");
		this.setTitle("BikeCity");
		this.add(header);
		header.setWidth("1280px");
		this.add(center);
		
		lblWarten = new Label("Spielende!");
		lblWarten.setStyleName("Spiel-Header-SpielName");
		center.add(lblWarten, 54, 24);
		lblWarten.setSize("600px", "49px");
		
		Image image = new Image("images/treppe.png");
		center.add(image, 64, 91);
		image.setSize("400px", "400px");
		
		lbRest = new ListBox();
		lbRest.setEnabled(false);
		lbRest.setVisibleItemCount(3);
		center.add(lbRest, 470, 374);
		lbRest.setSize("177px", "61px");
		
		btnZurueckZurLobby = new Button("Zur\u00FCck zur Lobby");
		center.add(btnZurueckZurLobby, 532, 493);
		btnZurueckZurLobby.setSize("124px", "24px");
		
		lblErster = new Label("1.");
		lblErster.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblErster.setStyleName("Spiel-ZwischenUeberschriften");
		center.add(lblErster, 205, 122);
		lblErster.setSize("122px", "23px");
		
		lblZweiter = new Label("2.");
		lblZweiter.setStyleName("Spiel-ZwischenUeberschriften");
		lblZweiter.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		center.add(lblZweiter, 54, 176);
		lblZweiter.setSize("122px", "23px");
		
		lblDritter = new Label("3.");
		lblDritter.setStyleName("Spiel-ZwischenUeberschriften");
		lblDritter.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		center.add(lblDritter, 354, 227);
		lblDritter.setSize("122px", "23px");
		
	}
	
	public Widget asWidget() {
		return this;
	}

	@Override
	public Label getLblSpielerName() {
		return header.getLblSpielerName();
	}

	@Override
	public Label getLblFirmenName() {
		return header.getLblFirmenName();
	}

	@Override
	public Label getLblRundenNr() {
		return header.getLblRundenNr();
	}

	public Label getLblWarten() {
		return lblWarten;
	}
	@Override
	public Label getLblSpiel() {
		return header.getLblSpiel();
	}
	public Button getBtnZurueckZurLobby() {
		return btnZurueckZurLobby;
	}
	public Label getLblErster() {
		return lblErster;
	}
	public ListBox getLbRest() {
		return lbRest;
	}
	public Label getLblDritter() {
		return lblDritter;
	}
	public Label getLblZweiter() {
		return lblZweiter;
	}
}
