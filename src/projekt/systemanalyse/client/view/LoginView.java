package projekt.systemanalyse.client.view;

import projekt.systemanalyse.client.presenter.LoginViewPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.text.client.IntegerRenderer;
import com.google.gwt.user.client.Timer;
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


public class LoginView extends VerticalPanel implements LoginViewPresenter.Display {
	AbsolutePanel center;
	ListBox lbSpielerListe;
	ListBox lbSpielListe;
	TextBox txtSpielerName;
	TextBox txtUnName;
	Button btnSpielBeitreten;
	Button btnSpielErstellen;
	IntegerBox intBoxSpieleranzahl;
	TextBox txtSpielname;
	HeaderWidget header = new HeaderWidget();
	
	//dialogBox
	DialogBox dbSpielErstellen = createDialogBox();
	Button btnSpielStarten;
	Button btnAbbrechen;
	
	public LoginView()  {
		setWidth("100%");
		setStyleName("MainWindow");
		this.setHorizontalAlignment(ALIGN_CENTER);
		
		center = new AbsolutePanel();
		center.setWidth("1280px");
		center.setHeight("800px");
		this.setCellHorizontalAlignment(center, ALIGN_CENTER);
		
		lbSpielerListe = new ListBox();
		lbSpielerListe.setVisibleItemCount(10);
		lbSpielerListe.setSize("154px", "200px");
		
//		center.add(new HeaderWidget());
		center.add(lbSpielerListe, 145, 371);
		
		
//		this.setSize("1280px", "100%");
		this.setTitle("BikeCity");
		this.add(header);
		this.add(center);
		
		lbSpielListe = new ListBox();
		lbSpielListe.setVisibleItemCount(10);
		center.add(lbSpielListe, 448, 371);
		lbSpielListe.setSize("154px", "200px");
		
		TextArea txtrBeschreibung = new TextArea();
		txtrBeschreibung.setStyleName("gwt-RichTextArea");
		txtrBeschreibung.setReadOnly(true);
		txtrBeschreibung.setText("Hier kannst du deinen Spielernamen anpassen. In der linken Liste siehst du die Spieler, die momentan online sind. Rechts siehst du die aktiven Spiele, die noch nicht gestartet sind.\r\n\r\nDu kannst einem Spiel beitreten oder ein neues Spiel beginnen.");
		center.add(txtrBeschreibung, 54, 112);
		txtrBeschreibung.setSize("717px", "86px");
		
		Label lblHerzlichWillkommenBei = new Label("Herzlich Willkommen bei BikeCity");
		lblHerzlichWillkommenBei.setStyleName("Spiel-Header-SpielName");
		center.add(lblHerzlichWillkommenBei, 54, 24);
		lblHerzlichWillkommenBei.setSize("600px", "49px");
		
		Label lblSpielerOnline = new Label("Spieler online:");
		center.add(lblSpielerOnline, 145, 347);
		
		Label lblSpiele = new Label("Spiele:");
		center.add(lblSpiele, 448, 347);
		lblSpiele.setSize("44px", "18px");
		
		btnSpielErstellen = new Button("Spiel erstellen");
		center.add(btnSpielErstellen, 627, 371);
		btnSpielErstellen.setSize("150px", "28px");
		
		btnSpielBeitreten = new Button("Spiel beitreten");
		center.add(btnSpielBeitreten, 627, 405);
		btnSpielBeitreten.setSize("150px", "28px");
		
		Label lblSpielername = new Label("Spielername:");
		lblSpielername.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblSpielername, 54, 245);
		lblSpielername.setSize("85px", "18px");
		
		Label lblUnternehmen = new Label("Unternehmen:");
		lblUnternehmen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblUnternehmen, 54, 282);
		lblUnternehmen.setSize("85px", "18px");
		
		txtSpielerName = new TextBox();
		center.add(txtSpielerName, 145, 242);
		
		txtUnName = new TextBox();
		center.add(txtUnName, 145, 276);
		
	}
	
	public DialogBox createDialogBox() {
		DialogBox neueDialogBox = new DialogBox();
		
		neueDialogBox.setHTML("Neues Spiel erstellen");
		neueDialogBox.setSize("423px", "288px");
		
		neueDialogBox.setGlassEnabled(true);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		neueDialogBox.setWidget(absolutePanel);
		absolutePanel.setSize("400px", "234px");
		
		Label lblSpielname = new Label("Spielname:");
		absolutePanel.add(lblSpielname, 10, 27);
		lblSpielname.setSize("111px", "28px");
		
		Label lblAnzahlSpieler = new Label("Anzahl Spieler:");
		absolutePanel.add(lblAnzahlSpieler, 10, 71);
		lblAnzahlSpieler.setSize("111px", "23px");
		
//		ListBox lbSiegbedingung= new ListBox();
//		absolutePanel.add(lbSiegbedingung, 127, 118);
//		lbSiegbedingung.setSize("126px", "18px");
//
//		Label lblSiegbedingung = new Label("Siegbedingung:");
//		absolutePanel.add(lblSiegbedingung, 10, 118);
//		lblSiegbedingung.setSize("111px", "22px");
		
		intBoxSpieleranzahl = new IntegerBox();
		absolutePanel.add(intBoxSpieleranzahl, 123, 72);
		intBoxSpieleranzahl.setSize("143px", "16px");
		
		txtSpielname = new TextBox();
		absolutePanel.add(txtSpielname, 123, 27);
		txtSpielname.setSize("139px", "16px");
		
		btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.setText("Abbrechen");
		absolutePanel.add(btnAbbrechen, 168, 195);
		btnAbbrechen.setSize("85px", "24px");
		
		btnSpielStarten = new Button("Spiel starten");
		absolutePanel.add(btnSpielStarten, 259, 195);
		btnSpielStarten.setSize("131px", "24px");
		return neueDialogBox;
	}
	
	public Widget asWidget() {
		return this;
	}

	@Override
	public TextBox getTxtSpielerName() {
		return txtSpielerName;
	}

	@Override
	public TextBox getTxtFirma() {
		return txtUnName;
	}

	@Override
	public ListBox getLbSpielerOnline() {
		return lbSpielerListe;
	}

	@Override
	public ListBox getLbSpiele() {
		return lbSpielListe;
	}

	@Override
	public Button getBtnSpielBeitreten() {
		return btnSpielBeitreten;
	}

	@Override
	public Button getBtnSpielErstellen() {
		return btnSpielErstellen;
	}

	@Override
	public Button getBtnSpielStarten() {
		return btnSpielStarten;
	}

	@Override
	public Button getBtnAbbrechen() {
		return btnAbbrechen;
	}

	@Override
	public DialogBox getDbSpielErstellen() {
		return dbSpielErstellen;
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

	@Override
	public TextBox getTxtSpielname() {
		return txtSpielname;
	}

	@Override
	public IntegerBox getTxtSpieleranzahl() {
		return intBoxSpieleranzahl;
	}

	@Override
	public Label getLblSpiel() {
		return header.getLblSpiel();
	}
}
