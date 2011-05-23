package projekt.systemanalyse.client.view;

import projekt.systemanalyse.client.presenter.RundeBeendetPresenter;

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


public class RundeBeendetView extends VerticalPanel implements RundeBeendetPresenter.Display {
	AbsolutePanel center;
	HeaderWidget header = new HeaderWidget();
	private Label lblRundeBeendet;
	private TextBox txtSpielerGesamterloes;
	private TextBox txtKonkurrenzAbsatz;
	private TextBox txtSpielerFahrradAbsatz;
	private TextBox txtSpielerGewinn;
	private TextBox txtMarktAbsatz;
	private Button btnNaechsteRunde;
	private TextBox txtKonkurrenzGewinn;
	private ListBox lbKonkurrenz;
	private TextArea txtMarktereignis;
	private TextBox txtSpielerGesamtkosten;
	private ListBox lbAusschussArtikel;
	private TextBox txtAusschussMenge;
	private Label lblArtikel;
	private Label lblAusschussmenge;
	
	public RundeBeendetView()  {
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
		
		lblRundeBeendet = new Label("Runde Beendet:");
		lblRundeBeendet.setStyleName("Spiel-Header-SpielName");
		center.add(lblRundeBeendet, 54, 24);
		lblRundeBeendet.setSize("600px", "49px");
		
		Label lblVerkaufteFahrrder = new Label("Marktgeschehen:");
		lblVerkaufteFahrrder.setStyleName("Spiel-ZwischenUeberschriften");
		center.add(lblVerkaufteFahrrder, 54, 91);
		lblVerkaufteFahrrder.setSize("135px", "35px");
		
		Label lblMarktabsatz = new Label("Marktabsatz:");
		lblMarktabsatz.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblMarktabsatz, 64, 144);
		lblMarktabsatz.setSize("93px", "18px");
		
		Label Martkereignis = new Label("Marktereignis:");
		Martkereignis.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(Martkereignis, 64, 178);
		Martkereignis.setSize("93px", "18px");
		
		Label lblEigeneErgebnisse = new Label("Eigene Ergebnisse:");
		lblEigeneErgebnisse.setStyleName("Spiel-ZwischenUeberschriften");
		center.add(lblEigeneErgebnisse, 54, 257);
		lblEigeneErgebnisse.setSize("173px", "35px");
		
		Label lblFahrradAbsatz = new Label("Fahrrad Absatz:");
		lblFahrradAbsatz.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblFahrradAbsatz, 64, 310);
		
		Label lblGesamterlse = new Label("Gesamterl\u00F6se:");
		lblGesamterlse.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblGesamterlse, 64, 345);
		lblGesamterlse.setSize("93px", "28px");
		
		Label lblAusschussDerProduktion = new Label("Ausschuss der Produktion:");
		lblAusschussDerProduktion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblAusschussDerProduktion, 64, 457);
		lblAusschussDerProduktion.setSize("232px", "18px");
		
		Label lblErgebnisseDerKonkurrenz = new Label("Ergebnisse der Konkurrenz:");
		lblErgebnisseDerKonkurrenz.setStyleName("Spiel-ZwischenUeberschriften");
		center.add(lblErgebnisseDerKonkurrenz, 553, 91);
		lblErgebnisseDerKonkurrenz.setSize("230px", "35px");
		
		txtMarktereignis = new TextArea();
		txtMarktereignis.setReadOnly(true);
		center.add(txtMarktereignis, 173, 182);
		txtMarktereignis.setSize("302px", "63px");
		
		txtMarktAbsatz = new TextBox();
		txtMarktAbsatz.setReadOnly(true);
		center.add(txtMarktAbsatz, 173, 144);
		
		txtSpielerFahrradAbsatz = new TextBox();
		txtSpielerFahrradAbsatz.setReadOnly(true);
		center.add(txtSpielerFahrradAbsatz, 173, 310);
		txtSpielerFahrradAbsatz.setSize("155px", "20px");
		
		txtSpielerGesamterloes = new TextBox();
		txtSpielerGesamterloes.setReadOnly(true);
		center.add(txtSpielerGesamterloes, 173, 345);
		txtSpielerGesamterloes.setSize("155px", "20px");
		
		txtSpielerGewinn = new TextBox();
		txtSpielerGewinn.setReadOnly(true);
		center.add(txtSpielerGewinn, 173, 413);
		txtSpielerGewinn.setSize("155px", "20px");
		
		Label lblGewinn = new Label("Gewinn:");
		lblGewinn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblGewinn, 64, 413);
		lblGewinn.setSize("93px", "28px");
		
		Label lblGesamtkosten = new Label("Gesamtkosten:");
		lblGesamtkosten.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblGesamtkosten, 64, 379);
		lblGesamtkosten.setSize("93px", "28px");
		
		txtSpielerGesamtkosten = new TextBox();
		txtSpielerGesamtkosten.setReadOnly(true);
		center.add(txtSpielerGesamtkosten, 173, 379);
		txtSpielerGesamtkosten.setSize("155px", "20px");
		
		Label label = new Label("Gewinn:");
		label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(label, 563, 217);
		label.setSize("93px", "28px");
		
		txtKonkurrenzGewinn = new TextBox();
		txtKonkurrenzGewinn.setReadOnly(true);
		center.add(txtKonkurrenzGewinn, 672, 217);
		txtKonkurrenzGewinn.setSize("155px", "20px");
		
		Label label_1 = new Label("Fahrrad Absatz:");
		label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(label_1, 563, 179);
		label_1.setSize("93px", "28px");
		
		txtKonkurrenzAbsatz = new TextBox();
		txtKonkurrenzAbsatz.setReadOnly(true);
		center.add(txtKonkurrenzAbsatz, 672, 179);
		txtKonkurrenzAbsatz.setSize("155px", "20px");
		
		Label lblMitspieler = new Label("Mitspieler:");
		lblMitspieler.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblMitspieler, 563, 144);
		lblMitspieler.setSize("103px", "18px");
		
		btnNaechsteRunde = new Button("New button");
		btnNaechsteRunde.setText("N\u00E4chste Runde");
		center.add(btnNaechsteRunde, 553, 41);
		btnNaechsteRunde.setSize("232px", "28px");
		
		lbKonkurrenz = new ListBox();
		center.add(lbKonkurrenz, 672, 144);
		lbKonkurrenz.setSize("163px", "24px");
		lbKonkurrenz.setVisibleItemCount(1);
		
		lbAusschussArtikel = new ListBox();
		lbAusschussArtikel.setVisibleItemCount(1);
		center.add(lbAusschussArtikel, 175, 481);
		lbAusschussArtikel.setSize("163px", "24px");
		
		txtAusschussMenge = new TextBox();
		txtAusschussMenge.setReadOnly(true);
		center.add(txtAusschussMenge, 175, 509);
		txtAusschussMenge.setSize("155px", "20px");
		
		lblArtikel = new Label("Artikel:");
		lblArtikel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblArtikel, 64, 481);
		lblArtikel.setSize("103px", "18px");
		
		lblAusschussmenge = new Label("Ausschuss:");
		lblAusschussmenge.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		center.add(lblAusschussmenge, 64, 509);
		lblAusschussmenge.setSize("93px", "28px");
		
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
	
//	@Override
//	public PushButton getBtnBeenden() {
//		return header.getBtnBeenden();
//	}
//
//	@Override
//	public PushButton getBtnHilfe() {
//		return header.getBtnHilfe();
//	}

	public Label getLblWarten() {
		return lblRundeBeendet;
	}
	public TextBox getTxtSpielerGesamterloes() {
		return txtSpielerGesamterloes;
	}
	public TextBox getTxtKonkurrenzAbsatz() {
		return txtKonkurrenzAbsatz;
	}
	public TextBox getTxtSpielerFahrradAbsatz() {
		return txtSpielerFahrradAbsatz;
	}
	public TextBox getTxtSpielerGewinn() {
		return txtSpielerGewinn;
	}
	public TextBox getTxtMarktAbsatz() {
		return txtMarktAbsatz;
	}
	public Button getBtnNaechsteRunde() {
		return btnNaechsteRunde;
	}
	public TextBox getTxtKonkurrenzGewinn() {
		return txtKonkurrenzGewinn;
	}
	public ListBox getLbKonkurrenz() {
		return lbKonkurrenz;
	}
	public TextArea getTxtMarktereignis() {
		return txtMarktereignis;
	}
	public TextBox getTxtSpielerGesamtkosten() {
		return txtSpielerGesamtkosten;
	}
	
	@Override
	public Label getLblSpiel() {
		return header.getLblSpiel();
	}
	public TextBox getTxtAusschussMenge() {
		return txtAusschussMenge;
	}
	public ListBox getLbAusschussArtikel() {
		return lbAusschussArtikel;
	}
}
