package projekt.systemanalyse.client.view;

import projekt.systemanalyse.client.presenter.AuswertungPresenter;

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


public class AuswertungView extends VerticalPanel implements AuswertungPresenter.Display {
	AbsolutePanel center;
	ListBox lbSpielerListe;
	HeaderWidget header = new HeaderWidget();
	private Label lblMitspieler;
	private Label lblWarten;
	
	public AuswertungView()  {
		setWidth("100%");
		setStyleName("MainWindow");
		this.setHorizontalAlignment(ALIGN_CENTER);
		
		center = new AbsolutePanel();
		center.setSize("1280px", "600px");
		this.setCellHorizontalAlignment(center, ALIGN_CENTER);
		
		lbSpielerListe = new ListBox();
		lbSpielerListe.setVisibleItemCount(10);
		lbSpielerListe.setSize("154px", "200px");
		
//		center.add(new HeaderWidget());
		center.add(lbSpielerListe, 142, 140);
		
		
//		this.setSize("1280px", "100%");
		this.setTitle("BikeCity");
		this.add(header);
		header.setWidth("1280px");
		this.add(center);
		
		lblWarten = new Label("Warten auf Rundenende ...");
		lblWarten.setStyleName("Spiel-Header-SpielName");
		center.add(lblWarten, 54, 24);
		lblWarten.setSize("600px", "49px");
		
		lblMitspieler = new Label("Mitspieler (0 / x)");
		center.add(lblMitspieler, 142, 116);
		lblMitspieler.setSize("103px", "18px");
		
	}
	
	public Widget asWidget() {
		return this;
	}

	@Override
	public ListBox getLbSpielerOnline() {
		return lbSpielerListe;
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

	@Override
	public PushButton getBtnBeenden() {
		return header.getBtnBeenden();
	}

	@Override
	public PushButton getBtnHilfe() {
		return header.getBtnHilfe();
	}
	public Label getLblMitspieler() {
		return lblMitspieler;
	}
	public Label getLblWarten() {
		return lblWarten;
	}
	
	@Override
	public Label getLblSpiel() {
		return header.getLblSpiel();
	}

}
