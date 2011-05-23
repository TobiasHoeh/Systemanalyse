package projekt.systemanalyse.client.view;

import projekt.systemanalyse.client.presenter.MainLayoutPresenter;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.DecoratedTabPanel;

public class MainLayoutView extends VerticalPanel implements MainLayoutPresenter.Display {
	HeaderWidget header = new HeaderWidget();
	private PositionsWidget einkaufsListe;
	
	
	// InformationArea
	private TextBox txtINFLagerauslastung;
	private TextBox txtINFMitarbeiteranzahl;
	private KontoWidget INFbilanz;
	private Button btnINFZugBeenden;
	
	// Einkauf
	
	TextBox txtEinkaufGesamtkosten;
	TextBox txtEinkaufLagerbedarf;
	private ListBox lbEinkaufArtikel;
	private ListBox lbEinkaufTyp;
	private TextBox txtEinkaufPreis;
	private TextBox txtEinkaufGesamt;
	private TextBox txtEinkaufMenge;
	private Button btnEinkaufArtikelHinzufuegen;
	
	//Unternehmen
	
	private TextBox txtUNEinstellen;
	private TextBox txtUNLagerbedarf;
	private TextBox txtUNVerkaufspreis;
	private Label lblUNLagerPlanung;
	private Label lblUNMitarbeiterPlanung;
	private PositionsWidget verkaufsListe;
	private ListBox lbVerkaufArtikel;
	private TextBox txtVerkaufMenge;
	private ListBox lbVerkaufTyp;
	private TextBox txtVerkaufGesamtErloes;
	private TextBox txtVerkaufLagerentlastung;
	private TextBox txtVerkaufGesamt;
	private TextBox txtVerkaufPreis;
	private Button btnVerkaufArtikelHinzufuegen;
	private ListBox lbProdRaeder;
	private TextBox txtProdMenge;
	private ListBox lbProdRahmen;
	private ListBox lbProdLenker;
	private ListBox lbProdSchaltung;
	private ListBox lbProdSattel;
	private TextBox txtProdStueckPreis;
	private TextBox txtProdSchaltungPreis;
	private TextBox txtProdSattelPreis;
	private TextBox txtProdRaederPreis;
	private TextBox txtProdRahmenPreis;
	private TextBox txtProdLenkerPreis;
	private TextBox txtProdSattelBestand;
	private TextBox txtProdRahmenBestand;
	private TextBox txtProdSchaltungBestand;
	private TextBox txtProdLenkerBestand;
	private TextBox txtProdRaederBestand;
	private TextBox txtVerkaufLagerbestand;
	
	
	
	
	
	public MainLayoutView()  {
		setWidth("100%");
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setStyleName("MainWindow");
		
		
//		this.setSize("1280px", "100%");
		this.setTitle("BikeCity");
		this.add(header);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(2);
		horizontalPanel.setBorderWidth(0);
		add(horizontalPanel);
		horizontalPanel.setSize("1280px", "595px");
		
		DecoratedTabPanel tabLayoutPanel = new DecoratedTabPanel();
		horizontalPanel.add(tabLayoutPanel);
		tabLayoutPanel.setSize("880px", "595px");
		
		VerticalPanel vpUnternehmen = new VerticalPanel();
		tabLayoutPanel.add(vpUnternehmen, "Unternehmen", false);
		vpUnternehmen.setSize("5cm", "545px");
		
		TextArea txtrAufDieserSeite_1 = new TextArea();
		txtrAufDieserSeite_1.setStyleName("gwt-RichTextArea");
		txtrAufDieserSeite_1.setText("Auf dieser Seite kannst du deine Unternehmensplanung vornehmen. Du kannst Mitarbeiter einstellen oder entlassen. Die Kosten für einen Mitarbeiter betragen 2.000 Euro pro Monat. In der Nähe deines Standortes sind viele leerstehende Hallen zu finden, sodass du jederzeit deine Lagerkapazität zu einem Preis von 10% pro Lagereinheit erweitern oder verringern kannst. Außerdem legst du auf dieser Seite den Verkaufspreis deiner Fahrräder fest.");
		txtrAufDieserSeite_1.setReadOnly(true);
		vpUnternehmen.add(txtrAufDieserSeite_1);
		txtrAufDieserSeite_1.setSize("861px", "80px");
		
		AbsolutePanel absolutePanel_6 = new AbsolutePanel();
		vpUnternehmen.add(absolutePanel_6);
		absolutePanel_6.setSize("100%", "443px");
		
		Label lblPlanung = new Label("Planung");
		lblPlanung.setStyleName("Spiel-ZwischenUeberschriften");
		absolutePanel_6.add(lblPlanung, 10, 0);
		lblPlanung.setSize("327px", "32px");
		
		Label label_1 = new Label("Mitarbeiter einstellen / entlassen:");
		absolutePanel_6.add(label_1, 10, 50);
		label_1.setSize("215px", "24px");
		
		txtUNEinstellen = UIFactory.getStyledTextBox("TB1", true);
		txtUNEinstellen.setEnabled(true);
		txtUNEinstellen.setText("");
		absolutePanel_6.add(txtUNEinstellen, 231, 50);
		txtUNEinstellen.setSize("112px", "16px");
		
		txtUNLagerbedarf = UIFactory.getStyledTextBox("TB1", true);
		txtUNLagerbedarf.setEnabled(true);
		txtUNLagerbedarf.setText("");
		absolutePanel_6.add(txtUNLagerbedarf, 231, 80);
		txtUNLagerbedarf.setSize("112px", "19px");
		
		Label lblLagerkapazittErweitern = new Label("Lagerkapazit\u00E4t erweitern / verringern:");
		lblLagerkapazittErweitern.setStyleName("body");
		absolutePanel_6.add(lblLagerkapazittErweitern, 10, 83);
		lblLagerkapazittErweitern.setSize("215px", "24px");
		
		Label label_3 = new Label("Verkaufspreis f\u00FCr ein Fahrrad:");
		absolutePanel_6.add(label_3, 10, 116);
		label_3.setSize("215px", "24px");
		
		txtUNVerkaufspreis = UIFactory.getStyledTextBox("TB1", true);
		txtUNVerkaufspreis.setEnabled(true);
		txtUNVerkaufspreis.setText("");
		absolutePanel_6.add(txtUNVerkaufspreis, 231, 113);
		txtUNVerkaufspreis.setSize("112px", "19px");
		
		lblUNMitarbeiterPlanung = new Label("+- 0 \u20AC / Monat");
		lblUNMitarbeiterPlanung.setStyleName("body");
		absolutePanel_6.add(lblUNMitarbeiterPlanung, 357, 50);
		lblUNMitarbeiterPlanung.setSize("215px", "24px");
		
		lblUNLagerPlanung = new Label("+- 0 \u20AC");
		absolutePanel_6.add(lblUNLagerPlanung, 357, 80);
		lblUNLagerPlanung.setSize("215px", "24px");
		
		Label lblEuro = new Label("Euro");
		absolutePanel_6.add(lblEuro, 357, 116);
		lblEuro.setSize("215px", "24px");
		
		
		
		// Einkauf 
		VerticalPanel vpEinkauf = new VerticalPanel();
		tabLayoutPanel.add(vpEinkauf, "Einkauf", false);
		tabLayoutPanel.selectTab(0);
		vpEinkauf.setHeight("545px");
		
		TextArea txtrAufDieserSeite = new TextArea();
		txtrAufDieserSeite.setStyleName("gwt-RichTextArea");
		txtrAufDieserSeite.setReadOnly(true);
		txtrAufDieserSeite.setText("Auf dieser Seite kannst du deine Eink\u00E4ufe planen. Von jedem Bauteil gibt es verschiedene angebotene Produkte in unterschiedlichen Qualit\u00E4tsstufen. Je nachdem, in welcher Qualit\u00E4t du deine Bauteile kaufst, wird sich diese Entscheidung auf den Ausschuss und somit auf deine Produktion auswirken. Teurere Artikel haben grunds\u00E4tzlich eine h\u00F6here Qualit\u00E4t.");
		vpEinkauf.add(txtrAufDieserSeite);
		txtrAufDieserSeite.setSize("861px", "80px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		vpEinkauf.add(absolutePanel);
		absolutePanel.setSize("100%", "443px");
	    
	    einkaufsListe = new PositionsWidget();
	    absolutePanel.add(einkaufsListe, 0, 50);
	    einkaufsListe.setSize("594px", "32px");
	    
	    Label lblEinkaufsliste = new Label("Einkaufsliste");
	    lblEinkaufsliste.setStyleName("Spiel-ZwischenUeberschriften");
	    absolutePanel.add(lblEinkaufsliste, 10, 0);
	    lblEinkaufsliste.setSize("327px", "32px");
	    
	    AbsolutePanel absolutePanel_4 = new AbsolutePanel();
	    absolutePanel_4.setStyleName("Spiel-Container");
	    absolutePanel.add(absolutePanel_4, 599, 299);
	    absolutePanel_4.setSize("260px", "117px");
	    
	    Label lblGesamtverbrauch = new Label("Gesamtverbrauch");
	    lblGesamtverbrauch.setStyleName("Spiel-ZwischenUeberschriften");
	    absolutePanel_4.add(lblGesamtverbrauch, 63, 0);
	    lblGesamtverbrauch.setSize("152px", "32px");
	    
	    Label lblGesamtkosten = new Label("Gesamtkosten:");
	    absolutePanel_4.add(lblGesamtkosten, 10, 50);
	    lblGesamtkosten.setSize("118px", "24px");
	    
	    Label lblLagerbedarf = new Label("Lagerbedarf:");
	    absolutePanel_4.add(lblLagerbedarf, 10, 80);
	    lblLagerbedarf.setSize("118px", "25px");
	    
	    txtEinkaufGesamtkosten = UIFactory.getStyledTextBox("TB1", true);
	    absolutePanel_4.add(txtEinkaufGesamtkosten, 134, 50);
	    txtEinkaufGesamtkosten.setSize("112px", "16px");
	    
	    txtEinkaufLagerbedarf = UIFactory.getStyledTextBox("TB1", true);
	    absolutePanel_4.add(txtEinkaufLagerbedarf, 134, 80);
	    txtEinkaufLagerbedarf.setSize("112px", "17px");
	    
	    AbsolutePanel absolutePanel_5 = new AbsolutePanel();
	    absolutePanel_5.setStyleName("Spiel-Container");
	    absolutePanel.add(absolutePanel_5, 599, 50);
	    absolutePanel_5.setSize("260px", "241px");
	    
	    Label lblArtikelauswahl = new Label("Artikelauswahl");
	    lblArtikelauswahl.setStyleName("Spiel-ZwischenUeberschriften");
	    absolutePanel_5.add(lblArtikelauswahl, 74, 0);
	    lblArtikelauswahl.setSize("136px", "32px");
	    
	    Label lblTyp = new Label("Typ:");
	    absolutePanel_5.add(lblTyp, 10, 50);
	    lblTyp.setSize("68px", "24px");
	    
	    Label lblArtikel = new Label("Artikel:");
	    absolutePanel_5.add(lblArtikel, 10, 80);
	    lblArtikel.setSize("68px", "25px");
	    
	    lbEinkaufTyp = new ListBox();
	    absolutePanel_5.add(lbEinkaufTyp, 84, 50);
	    lbEinkaufTyp.setSize("170px", "24px");
	    lbEinkaufTyp.setVisibleItemCount(1);
	    
	    lbEinkaufArtikel = new ListBox();
	    lbEinkaufArtikel.setVisibleItemCount(1);
	    absolutePanel_5.add(lbEinkaufArtikel, 84, 80);
	    lbEinkaufArtikel.setSize("170px", "24px");
	    
	    Label lblMenge = new Label("Menge:");
	    absolutePanel_5.add(lblMenge, 10, 111);
	    lblMenge.setSize("68px", "25px");
	    
	    Label lblPreis = new Label("Preis:");
	    absolutePanel_5.add(lblPreis, 10, 142);
	    lblPreis.setSize("68px", "25px");
	    
	    Label lblGesamt = new Label("Gesamt:");
	    absolutePanel_5.add(lblGesamt, 10, 173);
	    lblGesamt.setSize("68px", "25px");
	    
	    txtEinkaufMenge = new TextBox();
	    absolutePanel_5.add(txtEinkaufMenge, 84, 110);
	    txtEinkaufMenge.setSize("162px", "16px");
	    
	    txtEinkaufPreis = new TextBox();
	    txtEinkaufPreis.setReadOnly(true);
	    absolutePanel_5.add(txtEinkaufPreis, 84, 142);
	    txtEinkaufPreis.setSize("162px", "16px");
	    
	    txtEinkaufGesamt = new TextBox();
	    txtEinkaufGesamt.setReadOnly(true);
	    absolutePanel_5.add(txtEinkaufGesamt, 84, 174);
	    txtEinkaufGesamt.setSize("162px", "16px");
	    
	    btnEinkaufArtikelHinzufuegen = new Button("New button");
	    btnEinkaufArtikelHinzufuegen.setText("Artikel hinzuf\u00FCgen");
	    absolutePanel_5.add(btnEinkaufArtikelHinzufuegen, 10, 204);
	    btnEinkaufArtikelHinzufuegen.setSize("244px", "28px");
	    
	    VerticalPanel vpVerkauf = new VerticalPanel();
	    tabLayoutPanel.add(vpVerkauf, "Verkauf", false);
	    vpVerkauf.setSize("5cm", "545px");
	    
	    TextArea txtrAufDieserSeite_2 = new TextArea();
	    txtrAufDieserSeite_2.setStyleName("gwt-RichTextArea");
	    txtrAufDieserSeite_2.setText("Auf dieser Seite kannst du deine Verk\u00E4ufe von fr\u00FCher gekauften Bauteilen planen. Die Vertr\u00E4ge mit deinen Zulieferern erlauben es dir, jederzeit Bauteile f\u00FCr die H\u00E4lfte des aktuellen Marktpreises zur\u00FCckgeben zu k\u00F6nnen. So kannst du deine liquiden Mittel zum Kauf anderer Bauteile wieder aufstocken.");
	    txtrAufDieserSeite_2.setReadOnly(true);
	    vpVerkauf.add(txtrAufDieserSeite_2);
	    txtrAufDieserSeite_2.setSize("861px", "80px");
	    
	    AbsolutePanel absolutePanel_1 = new AbsolutePanel();
	    vpVerkauf.add(absolutePanel_1);
	    absolutePanel_1.setSize("100%", "443px");
	    
	    Label lblVerkaufsliste = new Label("Verkaufsliste");
	    lblVerkaufsliste.setStyleName("Spiel-ZwischenUeberschriften");
	    absolutePanel_1.add(lblVerkaufsliste, 10, 0);
	    lblVerkaufsliste.setSize("327px", "32px");
	    
	    verkaufsListe = new PositionsWidget();
	    absolutePanel_1.add(verkaufsListe, 0, 50);
	    verkaufsListe.setSize("594px", "32px");
	    
	    AbsolutePanel absolutePanel_7 = new AbsolutePanel();
	    absolutePanel_7.setStyleName("Spiel-Container");
	    absolutePanel_1.add(absolutePanel_7, 599, 334);
	    absolutePanel_7.setSize("260px", "107px");
	    
	    Label lblGesamt_1 = new Label("Gesamtzuwachs");
	    lblGesamt_1.setStyleName("Spiel-ZwischenUeberschriften");
	    absolutePanel_7.add(lblGesamt_1, 65, 0);
	    lblGesamt_1.setSize("136px", "22px");
	    
	    Label Gesamterloes = new Label("Gesamterl\u00F6s:");
	    absolutePanel_7.add(Gesamterloes, 10, 40);
	    Gesamterloes.setSize("118px", "24px");
	    
	    Label lblLagerentlastung = new Label("Lagerentlastung:");
	    absolutePanel_7.add(lblLagerentlastung, 10, 70);
	    lblLagerentlastung.setSize("118px", "25px");
	    
	    txtVerkaufGesamtErloes = UIFactory.getStyledTextBox("TB1", true);
	    txtVerkaufGesamtErloes.setText("");
	    absolutePanel_7.add(txtVerkaufGesamtErloes, 134, 40);
	    txtVerkaufGesamtErloes.setSize("112px", "16px");
	    
	    txtVerkaufLagerentlastung = UIFactory.getStyledTextBox("TB1", true);
	    txtVerkaufLagerentlastung.setText("");
	    absolutePanel_7.add(txtVerkaufLagerentlastung, 134, 70);
	    txtVerkaufLagerentlastung.setSize("112px", "19px");
	    
	    AbsolutePanel absolutePanel_8 = new AbsolutePanel();
	    absolutePanel_8.setStyleName("Spiel-Container");
	    absolutePanel_1.add(absolutePanel_8, 599, 50);
	    absolutePanel_8.setSize("260px", "276px");
	    
	    Label label_6 = new Label("Artikelauswahl");
	    label_6.setStyleName("Spiel-ZwischenUeberschriften");
	    absolutePanel_8.add(label_6, 74, 0);
	    label_6.setSize("136px", "32px");
	    
	    Label label_7 = new Label("Typ:");
	    absolutePanel_8.add(label_7, 10, 50);
	    label_7.setSize("68px", "24px");
	    
	    Label label_8 = new Label("Artikel:");
	    absolutePanel_8.add(label_8, 10, 80);
	    label_8.setSize("68px", "25px");
	    
	    lbVerkaufTyp = new ListBox();
	    lbVerkaufTyp.setVisibleItemCount(1);
	    absolutePanel_8.add(lbVerkaufTyp, 84, 50);
	    lbVerkaufTyp.setSize("170px", "24px");
	    
	    lbVerkaufArtikel = new ListBox();
	    lbVerkaufArtikel.setVisibleItemCount(1);
	    absolutePanel_8.add(lbVerkaufArtikel, 84, 80);
	    lbVerkaufArtikel.setSize("170px", "24px");
	    
	    Label label_9 = new Label("Menge:");
	    absolutePanel_8.add(label_9, 10, 111);
	    label_9.setSize("68px", "25px");
	    
	    Label label_10 = new Label("Preis:");
	    absolutePanel_8.add(label_10, 10, 176);
	    label_10.setSize("68px", "25px");
	    
	    Label label_11 = new Label("Gesamt:");
	    absolutePanel_8.add(label_11, 10, 207);
	    label_11.setSize("68px", "25px");
	    
	    txtVerkaufMenge = new TextBox();
	    absolutePanel_8.add(txtVerkaufMenge, 84, 110);
	    txtVerkaufMenge.setSize("162px", "16px");
	    
	    txtVerkaufPreis = new TextBox();
	    txtVerkaufPreis.setEnabled(false);
	    txtVerkaufPreis.setReadOnly(true);
	    absolutePanel_8.add(txtVerkaufPreis, 84, 176);
	    txtVerkaufPreis.setSize("162px", "16px");
	    
	    txtVerkaufGesamt = new TextBox();
	    txtVerkaufGesamt.setEnabled(false);
	    txtVerkaufGesamt.setReadOnly(true);
	    absolutePanel_8.add(txtVerkaufGesamt, 84, 208);
	    txtVerkaufGesamt.setSize("162px", "16px");
	    
	    btnVerkaufArtikelHinzufuegen = new Button("New button");
	    btnVerkaufArtikelHinzufuegen.setText("Artikel hinzuf\u00FCgen");
	    absolutePanel_8.add(btnVerkaufArtikelHinzufuegen, 10, 238);
	    btnVerkaufArtikelHinzufuegen.setSize("244px", "28px");
	    
	    txtVerkaufLagerbestand = new TextBox();
	    txtVerkaufLagerbestand.setReadOnly(true);
	    txtVerkaufLagerbestand.setEnabled(false);
	    absolutePanel_8.add(txtVerkaufLagerbestand, 84, 142);
	    txtVerkaufLagerbestand.setSize("162px", "16px");
	    
	    Label lblLagerbest = new Label("Lagerbest.:");
	    absolutePanel_8.add(lblLagerbest, 10, 142);
	    lblLagerbest.setSize("68px", "25px");
	    
	    VerticalPanel vpProduktion = new VerticalPanel();
	    tabLayoutPanel.add(vpProduktion, "Produktion", false);
	    vpProduktion.setSize("5cm", "545px");
	    
	    TextArea txtrAufDieserSeite_3 = new TextArea();
	    txtrAufDieserSeite_3.setText("Auf dieser Seite kannst du deine Produktion planen. In jeder Runde kannst du die Fahrr\u00E4der aus einem bestimmten Satz von Bauteilen produzieren. Je nach Qualit\u00E4t der Bauteile kann der Ausschuss unterschiedlich gro\u00DF sein. Sollten nicht genug Teile auf Lager sein, kann ein zu hoher Ausschuss deine Produktion beeintr\u00E4chtigen.\r\nEs wird immer versucht, so viele Fahrr\u00E4der wie m\u00F6glich abzusetzen.");
	    txtrAufDieserSeite_3.setStyleName("gwt-RichTextArea");
	    txtrAufDieserSeite_3.setReadOnly(true);
	    vpProduktion.add(txtrAufDieserSeite_3);
	    txtrAufDieserSeite_3.setSize("861px", "80px");
	    
	    AbsolutePanel absolutePanel_9 = new AbsolutePanel();
	    vpProduktion.add(absolutePanel_9);
	    absolutePanel_9.setSize("100%", "443px");
	    
	    Label lblProduktion = new Label("Produktion");
	    lblProduktion.setStyleName("Spiel-ZwischenUeberschriften");
	    absolutePanel_9.add(lblProduktion, 10, 0);
	    lblProduktion.setSize("327px", "32px");
	    
	    TextBox txtbxSattel = new TextBox();
	    txtbxSattel.setEnabled(false);
	    txtbxSattel.setStyleName("Spiel-TextBox");
	    txtbxSattel.setReadOnly(true);
	    txtbxSattel.setText("Sattel");
	    absolutePanel_9.add(txtbxSattel, 10, 78);
	    txtbxSattel.setSize("89px", "16px");
	    
	    TextBox txtbxLenker = new TextBox();
	    txtbxLenker.setEnabled(false);
	    txtbxLenker.setStyleName("Spiel-TextBox");
	    txtbxLenker.setReadOnly(true);
	    txtbxLenker.setText("Lenker");
	    absolutePanel_9.add(txtbxLenker, 10, 108);
	    txtbxLenker.setSize("89px", "16px");
	    
	    TextBox txtbxRahmen = new TextBox();
	    txtbxRahmen.setEnabled(false);
	    txtbxRahmen.setStyleName("Spiel-TextBox");
	    txtbxRahmen.setReadOnly(true);
	    txtbxRahmen.setText("Rahmen");
	    absolutePanel_9.add(txtbxRahmen, 10, 138);
	    txtbxRahmen.setSize("89px", "16px");
	    
	    TextBox txtbxSchaltung = new TextBox();
	    txtbxSchaltung.setEnabled(false);
	    txtbxSchaltung.setStyleName("Spiel-TextBox");
	    txtbxSchaltung.setReadOnly(true);
	    txtbxSchaltung.setText("Schaltung");
	    absolutePanel_9.add(txtbxSchaltung, 10, 168);
	    txtbxSchaltung.setSize("89px", "16px");
	    
	    TextBox txtbxRder = new TextBox();
	    txtbxRder.setStyleName("Spiel-TextBox");
	    txtbxRder.setEnabled(false);
	    txtbxRder.setReadOnly(true);
	    txtbxRder.setText("R\u00E4der");
	    absolutePanel_9.add(txtbxRder, 10, 198);
	    txtbxRder.setSize("89px", "16px");
	    
	    TextBox txtbxVerwendeteBauteile = new TextBox();
	    txtbxVerwendeteBauteile.setEnabled(false);
	    txtbxVerwendeteBauteile.setReadOnly(true);
	    txtbxVerwendeteBauteile.setStyleName("Spiel-TextBox");
	    txtbxVerwendeteBauteile.setText("Verwendete Bauteile");
	    absolutePanel_9.add(txtbxVerwendeteBauteile, 10, 48);
	    txtbxVerwendeteBauteile.setSize("398px", "16px");
	    
	    lbProdSattel = new ListBox();
	    absolutePanel_9.add(lbProdSattel, 113, 78);
	    lbProdSattel.setSize("299px", "22px");
	    lbProdSattel.setVisibleItemCount(1);
	    
	    lbProdLenker = new ListBox();
	    lbProdLenker.setVisibleItemCount(1);
	    absolutePanel_9.add(lbProdLenker, 113, 108);
	    lbProdLenker.setSize("299px", "22px");
	    
	    lbProdRahmen = new ListBox();
	    lbProdRahmen.setVisibleItemCount(1);
	    absolutePanel_9.add(lbProdRahmen, 113, 138);
	    lbProdRahmen.setSize("299px", "22px");
	    
	    lbProdSchaltung = new ListBox();
	    lbProdSchaltung.setVisibleItemCount(1);
	    absolutePanel_9.add(lbProdSchaltung, 113, 168);
	    lbProdSchaltung.setSize("299px", "22px");
	    
	    lbProdRaeder = new ListBox();
	    lbProdRaeder.setVisibleItemCount(1);
	    absolutePanel_9.add(lbProdRaeder, 113, 198);
	    lbProdRaeder.setSize("299px", "22px");
	    
	    TextBox txtbxPreis = new TextBox();
	    txtbxPreis.setEnabled(false);
	    txtbxPreis.setStyleName("Spiel-TextBox");
	    txtbxPreis.setReadOnly(true);
	    txtbxPreis.setText("Preis");
	    absolutePanel_9.add(txtbxPreis, 517, 48);
	    txtbxPreis.setSize("89px", "16px");
	    
	    txtProdSattelPreis = new TextBox();
	    txtProdSattelPreis.setEnabled(false);
	    txtProdSattelPreis.setStyleName("Spiel-TextBox");
	    txtProdSattelPreis.setReadOnly(true);
	    absolutePanel_9.add(txtProdSattelPreis, 517, 78);
	    txtProdSattelPreis.setSize("89px", "16px");
	    
	    txtProdLenkerPreis = new TextBox();
	    txtProdLenkerPreis.setEnabled(false);
	    txtProdLenkerPreis.setStyleName("Spiel-TextBox");
	    txtProdLenkerPreis.setReadOnly(true);
	    absolutePanel_9.add(txtProdLenkerPreis, 517, 108);
	    txtProdLenkerPreis.setSize("89px", "16px");
	    
	    txtProdRahmenPreis = new TextBox();
	    txtProdRahmenPreis.setEnabled(false);
	    txtProdRahmenPreis.setStyleName("Spiel-TextBox");
	    txtProdRahmenPreis.setReadOnly(true);
	    absolutePanel_9.add(txtProdRahmenPreis, 517, 138);
	    txtProdRahmenPreis.setSize("89px", "16px");
	    
	    txtProdSchaltungPreis = new TextBox();
	    txtProdSchaltungPreis.setEnabled(false);
	    txtProdSchaltungPreis.setStyleName("Spiel-TextBox");
	    txtProdSchaltungPreis.setReadOnly(true);
	    absolutePanel_9.add(txtProdSchaltungPreis, 517, 168);
	    txtProdSchaltungPreis.setSize("89px", "16px");
	    
	    txtProdRaederPreis = new TextBox();
	    txtProdRaederPreis.setEnabled(false);
	    txtProdRaederPreis.setStyleName("Spiel-TextBox");
	    txtProdRaederPreis.setReadOnly(true);
	    absolutePanel_9.add(txtProdRaederPreis, 517, 198);
	    txtProdRaederPreis.setSize("89px", "16px");
	    
	    txtProdStueckPreis = new TextBox();
	    txtProdStueckPreis.setEnabled(false);
	    txtProdStueckPreis.setStyleName("Spiel-TextBox");
	    txtProdStueckPreis.setReadOnly(true);
	    absolutePanel_9.add(txtProdStueckPreis, 517, 238);
	    txtProdStueckPreis.setSize("89px", "16px");
	    
	    txtProdMenge = new TextBox();
	    absolutePanel_9.add(txtProdMenge, 517, 268);
	    txtProdMenge.setSize("85px", "16px");
	    
	    Label lblZuProduzierendeFahrrder = new Label("Zu produzierende Fahrr\u00E4der:");
	    lblZuProduzierendeFahrrder.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	    absolutePanel_9.add(lblZuProduzierendeFahrrder, 342, 268);
	    lblZuProduzierendeFahrrder.setSize("169px", "24px");
	    
	    Label lblKostenProFahrrad = new Label("Kosten pro Fahrrad:");
	    lblKostenProFahrrad.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	    lblKostenProFahrrad.setDirectionEstimator(true);
	    absolutePanel_9.add(lblKostenProFahrrad, 342, 236);
	    lblKostenProFahrrad.setSize("169px", "24px");
	    
	    TextBox txtbxLagerbestand = new TextBox();
	    txtbxLagerbestand.setText("Lagerbestand");
	    txtbxLagerbestand.setStyleName("Spiel-TextBox");
	    txtbxLagerbestand.setReadOnly(true);
	    txtbxLagerbestand.setEnabled(false);
	    absolutePanel_9.add(txtbxLagerbestand, 418, 48);
	    txtbxLagerbestand.setSize("89px", "16px");
	    
	    txtProdSattelBestand = new TextBox();
	    txtProdSattelBestand.setStyleName("Spiel-TextBox");
	    txtProdSattelBestand.setReadOnly(true);
	    txtProdSattelBestand.setEnabled(false);
	    absolutePanel_9.add(txtProdSattelBestand, 418, 78);
	    txtProdSattelBestand.setSize("89px", "16px");
	    
	    txtProdLenkerBestand = new TextBox();
	    txtProdLenkerBestand.setStyleName("Spiel-TextBox");
	    txtProdLenkerBestand.setReadOnly(true);
	    txtProdLenkerBestand.setEnabled(false);
	    absolutePanel_9.add(txtProdLenkerBestand, 418, 108);
	    txtProdLenkerBestand.setSize("89px", "16px");
	    
	    txtProdRahmenBestand = new TextBox();
	    txtProdRahmenBestand.setStyleName("Spiel-TextBox");
	    txtProdRahmenBestand.setReadOnly(true);
	    txtProdRahmenBestand.setEnabled(false);
	    absolutePanel_9.add(txtProdRahmenBestand, 418, 138);
	    txtProdRahmenBestand.setSize("89px", "16px");
	    
	    txtProdSchaltungBestand = new TextBox();
	    txtProdSchaltungBestand.setStyleName("Spiel-TextBox");
	    txtProdSchaltungBestand.setReadOnly(true);
	    txtProdSchaltungBestand.setEnabled(false);
	    absolutePanel_9.add(txtProdSchaltungBestand, 418, 168);
	    txtProdSchaltungBestand.setSize("89px", "16px");
	    
	    txtProdRaederBestand = new TextBox();
	    txtProdRaederBestand.setStyleName("Spiel-TextBox");
	    txtProdRaederBestand.setReadOnly(true);
	    txtProdRaederBestand.setEnabled(false);
	    absolutePanel_9.add(txtProdRaederBestand, 418, 198);
	    txtProdRaederBestand.setSize("89px", "16px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);
		verticalPanel.setWidth("400px");
		
		AbsolutePanel absolutePanel_10 = new AbsolutePanel();
		absolutePanel_10.setHeight("27px");
		verticalPanel.add(absolutePanel_10);
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		verticalPanel.add(absolutePanel_3);
		absolutePanel_3.setStyleName("body-bordered");
		absolutePanel_3.setSize("100%", "124px");
		
		Label lblAllesErledit = new Label("Alles erledigt?");
		lblAllesErledit.setStyleName("Spiel-Header-SpielName");
		absolutePanel_3.add(lblAllesErledit, 10, 10);
		lblAllesErledit.setSize("338px", "39px");
		
		btnINFZugBeenden = new Button("Zug Beenden");
		absolutePanel_3.add(btnINFZugBeenden, 168, 82);
		btnINFZugBeenden.setSize("165px", "29px");
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		absolutePanel_2.setStyleName("body-bordered");
		absolutePanel_2.setSize("100%", "115px");
		verticalPanel.add(absolutePanel_2);
		txtINFLagerauslastung = new TextBox();
		txtINFLagerauslastung.setReadOnly(true);
		absolutePanel_2.add(txtINFLagerauslastung, 134, 50);
		txtINFMitarbeiteranzahl = new TextBox();
		txtINFMitarbeiteranzahl.setReadOnly(true);
		absolutePanel_2.add(txtINFMitarbeiteranzahl, 134, 80);
		
		Label lblAuslastung = new Label("Auslastung");
		lblAuslastung.setStyleName("Spiel-ZwischenUeberschriften");
		absolutePanel_2.add(lblAuslastung, 151, 0);
		lblAuslastung.setSize("91px", "32px");
		
		Label lblLagerauslastung = new Label("Lagerauslastung:");
		absolutePanel_2.add(lblLagerauslastung, 10, 50);
		lblLagerauslastung.setSize("118px", "24px");
		
		Label lblMitarbeiteranzahl = new Label("Mitarbeiteranzahl:");
		absolutePanel_2.add(lblMitarbeiteranzahl, 10, 80);
		lblMitarbeiteranzahl.setSize("118px", "25px");
						
		// InformationArea
		
		INFbilanz = new KontoWidget();
		INFbilanz.setStyleName("FlexTable");
		verticalPanel.add(INFbilanz);
		INFbilanz.setSize("100%", "300px");
		
	}
	
	
	
	public Widget asWidget() {
		return this;
	}
	
	// Header get-Methoden
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
	
	
	// Information Area
	public KontoWidget getINFBilanz() {
		return INFbilanz;
	}
	public Button getINFBtnZugBeenden() {
		return btnINFZugBeenden;
	}
	
	public TextBox getINFTxtLagerauslastung() {
		return txtINFLagerauslastung;
	}
	public TextBox getINFTxtMitarbeiteranzahl() {
		return txtINFMitarbeiteranzahl;
	}

	
	// Einkauf
	public TextBox getEinkaufTxtLagerbedarf() {
		return txtEinkaufLagerbedarf;
	}

	
	public TextBox getEinkaufTxtGesamtKosten() {
		return txtEinkaufGesamtkosten;
	}

	public PositionsWidget getEinkaufPositionsWidget() {
		return einkaufsListe;
	}
	
	@Override
	public ListBox getEinkaufLbTyp() {
		return lbEinkaufTyp;
	}


	@Override
	public ListBox getEinkaufLbArtikel() {
		return lbEinkaufArtikel;
	}


	@Override
	public TextBox getEinkaufTxtMenge() {
		return txtEinkaufMenge;
	}


	@Override
	public TextBox getEinkaufTxtPreis() {
		return txtEinkaufPreis;
	}


	@Override
	public TextBox getEinkaufTxtGesamt() {
		return txtEinkaufGesamt;
	}


	@Override
	public Button getEinkaufBtnHinzufuegen() {
		return btnEinkaufArtikelHinzufuegen;
	}
	
	// Unternehmen
	public TextBox getUNTxtEinstellen() {
		return txtUNEinstellen;
	}

	public TextBox getUNTxtVerkaufspreis() {
		return txtUNVerkaufspreis;
	}
	
	public TextBox getUNTxtLagerbedarf() {
		return txtUNLagerbedarf;
	}




	public TextBox getTxtUNMitarbeiter() {
		return txtUNEinstellen;
	}
	public Label getLblUNLagerPlanung() {
		return lblUNLagerPlanung;
	}
	public Label getLblUNMitarbeiterPlanung() {
		return lblUNMitarbeiterPlanung;
	}
	public PositionsWidget getVerkaufsListe() {
		return verkaufsListe;
	}
	public ListBox getLbVerkaufArtikel() {
		return lbVerkaufArtikel;
	}
	public TextBox getTxtVerkaufMenge() {
		return txtVerkaufMenge;
	}
	public ListBox getLbVerkaufTyp() {
		return lbVerkaufTyp;
	}
	public TextBox getTxtVerkaufGesamtErloes() {
		return txtVerkaufGesamtErloes;
	}
	public TextBox getVerkaufLagerentlastung() {
		return txtVerkaufLagerentlastung;
	}
	public TextBox getTxtVerkaufGesamt() {
		return txtVerkaufGesamt;
	}
	public TextBox getTxtVerkaufPreis() {
		return txtVerkaufPreis;
	}
	public Button getBtnVerkaufArtikelHinzufuegen() {
		return btnVerkaufArtikelHinzufuegen;
	}
	public ListBox getLbProdRaeder() {
		return lbProdRaeder;
	}
	public TextBox getTxtProdMenge() {
		return txtProdMenge;
	}
	public ListBox getLbProdRahmen() {
		return lbProdRahmen;
	}
	public ListBox getLbProdLenker() {
		return lbProdLenker;
	}
	public ListBox getLbProdSchaltung() {
		return lbProdSchaltung;
	}
	public ListBox getLbProdSattel() {
		return lbProdSattel;
	}
	public TextBox getTxtProdStueckPreis() {
		return txtProdStueckPreis;
	}
	public TextBox getTxtProdSchaltungPreis() {
		return txtProdSchaltungPreis;
	}
	public TextBox getTxtProdSattelPreis() {
		return txtProdSattelPreis;
	}
	public TextBox getTxtProdRaederPreis() {
		return txtProdRaederPreis;
	}
	public TextBox getTxtProdRahmenPreis() {
		return txtProdRahmenPreis;
	}
	public TextBox getTxtProdLenkerPreis() {
		return txtProdLenkerPreis;
	}
	public TextBox getTxtProdSattelBestand() {
		return txtProdSattelBestand;
	}
	public TextBox getTxtProdRahmenBestand() {
		return txtProdRahmenBestand;
	}
	public TextBox getTxtProdSchaltungBestand() {
		return txtProdSchaltungBestand;
	}
	public TextBox getTxtProdLenkerBestand() {
		return txtProdLenkerBestand;
	}
	public TextBox getTxtProdRaederBestand() {
		return txtProdRaederBestand;
	}
	public TextBox getTxtVerkaufLagerbestand() {
		return txtVerkaufLagerbestand;
	}
	
	@Override
	public Label getLblSpiel() {
		return header.getLblSpiel();
	}
}
