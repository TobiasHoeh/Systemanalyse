package projekt.systemanalyse.client.presenter;

import java.util.ArrayList;
import java.util.List;

import projekt.systemanalyse.client.SpielServiceAsync;
import projekt.systemanalyse.client.view.HeaderWidget;
import projekt.systemanalyse.client.view.KontoWidget;
import projekt.systemanalyse.client.view.PositionsWidget;
import projekt.systemanalyse.shared.Konto;
import projekt.systemanalyse.shared.Position;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.Spiel;
import projekt.systemanalyse.shared.Spieler;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.logistik.Artikel;
import projekt.systemanalyse.client.event.ZugBeendetEvent;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MainLayoutPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		// Header
		Label getLblSpielerName();

		Label getLblFirmenName();

		Label getLblRundenNr();

		PushButton getBtnBeenden();

		PushButton getBtnHilfe();

		public Label getLblSpiel();

		// Information Area
		KontoWidget getINFBilanz();

		Button getINFBtnZugBeenden();

		TextBox getINFTxtLagerauslastung();

		TextBox getINFTxtMitarbeiteranzahl();

		// Einkauf
		ListBox getEinkaufLbTyp();

		ListBox getEinkaufLbArtikel();

		TextBox getEinkaufTxtLagerbedarf();

		TextBox getEinkaufTxtMenge();

		TextBox getEinkaufTxtPreis();

		TextBox getEinkaufTxtGesamt();

		TextBox getEinkaufTxtGesamtKosten();

		Button getEinkaufBtnHinzufuegen();

		PositionsWidget getEinkaufPositionsWidget();

		// Unternehmen
		TextBox getUNTxtEinstellen();

		TextBox getUNTxtVerkaufspreis();

		TextBox getUNTxtLagerbedarf();

		Label getLblUNLagerPlanung();

		Label getLblUNMitarbeiterPlanung();

		// Produktion
		public ListBox getLbProdRaeder();

		public TextBox getTxtProdMenge();

		public ListBox getLbProdRahmen();

		public ListBox getLbProdLenker();

		public ListBox getLbProdSchaltung();

		public ListBox getLbProdSattel();

		public TextBox getTxtProdStueckPreis();

		public TextBox getTxtProdSchaltungPreis();

		public TextBox getTxtProdSattelPreis();

		public TextBox getTxtProdRaederPreis();

		public TextBox getTxtProdRahmenPreis();

		public TextBox getTxtProdLenkerPreis();

		public TextBox getTxtProdSattelBestand();

		public TextBox getTxtProdRahmenBestand();

		public TextBox getTxtProdSchaltungBestand();

		public TextBox getTxtProdLenkerBestand();

		public TextBox getTxtProdRaederBestand();

		// Verkauf

		public PositionsWidget getVerkaufsListe();

		public ListBox getLbVerkaufArtikel();

		public TextBox getTxtVerkaufMenge();

		public ListBox getLbVerkaufTyp();

		public TextBox getTxtVerkaufGesamtErloes();

		public TextBox getVerkaufLagerentlastung();

		public TextBox getTxtVerkaufGesamt();

		public TextBox getTxtVerkaufPreis();

		public Button getBtnVerkaufArtikelHinzufuegen();

		public TextBox getTxtVerkaufLagerbestand();

	}

	private final SpielServiceAsync rpcService;
	private final SimpleEventBus eventBus;
	private final Display display;
	HeaderWidget header;

	// Information Area
	private int maxLagerauslastung = 0;
	private int aktLagerauslastung = 0;
	private int mitarbeiterAnzahl = 0;
	private KontoWidget bilanz;

	// Einkauf
	private Positionsliste einkaufArtikel;
	private Double gesamtwert;
	private int lagerVerbrauch;

	// Unternehmen
	private int mitarbeiterPlanung = 0;
	private int lagerPlanung = 0;
	private double verkaufspreisPlanung = 100;

	// Produktion
	private Positionsliste lagerArtikel;
	private Positionsliste produktionsArtikelNachVerkauf = new Positionsliste();
	private Positionsliste produktionsListe;

	// Verkauf
	private List<Position> verkaufsListe;
	private Positionsliste verkaufsArtikel = new Positionsliste();
	private int lagerEinsparung;

	// allgemeine Variablen
	private Spieler spieler;
	private Spiel spiel;
	private double liquiditaet = 0;

	public MainLayoutPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view, Spiel spiel, Spieler spieler) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;

		this.spiel = spiel;
		this.spieler = spieler;

		// Header
		getSpielerName();
		getRunde();
		getFirma();
		setSpielName(spiel.getSpielName());

		// Information Area
		getKontodaten();
		getLagerauslastung();
		getMitarbeiterAnzahl();

		// Einkauf
		setTypValues();
		display.getEinkaufTxtMenge().setValue("0");
		setGesamtverbrauch();
		getMarktEinkaufArtikel();

		// Unternehmen
		setLblLagerPlanung(0);
		setLblMitarbeiterPlanung(0);
		setUNLagerplanung(0);
		setUNMitarbeiterPlanung(0);
		setUNVerkaufspreis(200);

		// Verkauf
		display.getTxtVerkaufMenge().setValue("0");
		setVerkaufTypValues();
		setVerkaufGesamterloes();
		getLagerArtikel();

		// Produktion
		setProdTxtMenge(0);
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	// //////////////////////////////////////////////////////////////////////////
	// //// ClickHandlers und ChangeHandlers
	// /////////////////////////////////////////////////////////////////////////
	public void bind() {
		Window.addWindowClosingHandler(new ClosingHandler() {
			@Override
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Das bereits begonnene Spiel wird dann als verloren gewertet.");
				rpcService.SpielUndSpielerTrennen(spieler.getSpielerID(), spiel.getSpielID(), new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						GWT.log("Spieler von Spiel entfernen bei verlassen den Browsers erfolgreich.");
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Spieler von Spiel entfernen bei verlassen den Browsers fehlgeschlagen.");
						
					}
				});
			}
		});
		// ///////////////////////////////////////////////////////////////
		// Header
		// ///////////////////////////////////////////////////////////////

		// display.getBtnBeenden().addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// boolean verlassen = Window
		// .confirm("Möchtest du wirklich aufgeben und das Spiel verlassen?");
		// if (verlassen == true) {
		// eventBus.fireEvent(new SpielBeendetEvent(spiel, spieler));
		// }
		// }
		// });

		// ///////////////////////////////////////////////////////////////
		// InformationArea
		// ///////////////////////////////////////////////////////////////
		display.getINFBtnZugBeenden().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.getINFBtnZugBeenden().setEnabled(false);
				// Window.alert("Zug beenden gedrückt, es müssen hier noch mehr Informationen übergeben werden.");
				List<Position> einkaufsListe = display
						.getEinkaufPositionsWidget().getDisplayedItems();
				int mitarbeiterPlanung = getMitarbeiterPlanung();
				int lagerplanung = getLagerPlanung();
				double verkaufsPreis = getVerkaufspreisPlanung();
				verkaufsListe = display.getVerkaufsListe().getDisplayedItems();
				einkaufsListe = display.getEinkaufPositionsWidget()
						.getDisplayedItems();

				Positionsliste einkaufsListeAktion = new Positionsliste();
				for (int i = 0; i < einkaufsListe.size(); i++) {
					einkaufsListeAktion.erfassePosition(einkaufsListe.get(i));
				}

				Positionsliste verkaufsListeAktion = new Positionsliste();
				for (int i = 0; i < verkaufsListe.size(); i++) {
					Position temp = verkaufsListe.get(i);
					temp.setWert(temp.getWert() * 2);
					verkaufsListeAktion.erfassePosition(temp);
				}

				Positionsliste produktionsListeAktion = new Positionsliste();
				produktionsListeAktion
						.erfassePosition(getProdSchaltungPosition());
				produktionsListeAktion.erfassePosition(getProdLenkerPosition());
				produktionsListeAktion.erfassePosition(getProdRahmenPosition());
				produktionsListeAktion.erfassePosition(getProdRaederPosition());
				produktionsListeAktion.erfassePosition(getProdSattelPosition());

				GWT.log("Zug wird beenden");


				rpcService.erzeugeUnternehmensAktion(spieler.getSpielerID(),
						mitarbeiterPlanung, lagerplanung, verkaufsPreis,
						new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								GWT.log("Erzeuge UnternehmensAktion erfolgreich.");

							}

							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Erzeuge UnternehmensAktion fehlgeschlagen.");
								Window.alert(caught.getMessage());

							}
						});
				
				rpcService.erzeugeProduktionsAktion(spieler.getSpielerID(),
						produktionsListeAktion, new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								GWT.log("Erzeuge Produktionsaktion erfolgreich.");

							}

							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Erzeuge Produktionsaktion fehlgeschlagen.");
								Window.alert(caught.getMessage());

							}
						});

				rpcService.erzeugeVerkaufsAktion(spieler.getSpielerID(),
						verkaufsListeAktion, new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								GWT.log("Erzeuge Verkaufsaktion erfolgreich");

							}

							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Erzeuge Verkaufsaktion fehlgeschlagen.");
								Window.alert(caught.getMessage());

							}
						});
				
				rpcService.erzeugeEinkaufsAktion(spieler.getSpielerID(),
						einkaufsListeAktion, new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								GWT.log("Erzeuge Einkaufsaktion erfolgreich.");
								rpcService.beendeZug(spieler.getSpielerID(),
										new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												GWT.log("Beende Zug erfolgreich");
												eventBus.fireEvent(new ZugBeendetEvent(spiel,
														spieler));

											}

											@Override
											public void onFailure(Throwable caught) {
												GWT.log("Beende Zug fehlgeschlagen.");

											}
										});
							}

							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Erzeuge Einkaufsaktion fehlgeschlagen.");
								Window.alert(caught.getMessage());
								display.getINFBtnZugBeenden().setEnabled(true);
								
							}
						});




				// Daten von der Produktion und vom Verkauf holen

				// Informationen an Zug-Beenden Schnittstelle geben

			}
		});

		// ///////////////////////////////////////////////////////////////
		// Unternehmen
		// ///////////////////////////////////////////////////////////////
		display.getUNTxtEinstellen().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				mitarbeiterPlanung = getMitarbeiterPlanung();
				setLblMitarbeiterPlanung(mitarbeiterPlanung);
			}
		});

		display.getUNTxtLagerbedarf().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				lagerPlanung = getLagerPlanung();
				setLblLagerPlanung(lagerPlanung);
			}
		});

		display.getUNTxtVerkaufspreis().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				verkaufspreisPlanung = getVerkaufspreisPlanung();
				setUNVerkaufspreis(verkaufspreisPlanung);
			}
		});

		// ///////////////////////////////////////////////////////////////
		// Einkauf
		// ///////////////////////////////////////////////////////////////
		display.getEinkaufBtnHinzufuegen().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Position position;

				try {
					if (getMenge() > 0) {
						position = einkaufArtikel.getPosition(display
								.getEinkaufLbArtikel().getItemText(
										display.getEinkaufLbArtikel()
												.getSelectedIndex()));
						position.setMenge(getMenge());
						position.setWert(getWert());
						List<Position> einkaufsListe = display
								.getEinkaufPositionsWidget()
								.getDisplayedItems();
						double einkaufswert = 0;
						for (int i = 0; i < einkaufsListe.size(); i++) {
							einkaufswert = einkaufsListe.get(i).getWert()
									* einkaufsListe.get(i).getMenge()
									+ einkaufswert;
						}
						if (einkaufswert + position.getWert()
								* position.getMenge() < liquiditaet) {
//							+
							if(getLagerPlanung()+maxLagerauslastung-aktLagerauslastung-lagerVerbrauch+lagerEinsparung > position.getMenge()*position.getGut().getGroesse()) {
								display.getEinkaufPositionsWidget().addPosition(
										position);
								setGesamtverbrauch();
							} else {
								Window.alert("Der Lagerplatz reicht aktuell " + (getLagerPlanung()+maxLagerauslastung-aktLagerauslastung-lagerVerbrauch+lagerEinsparung) + " LE nicht aus um den Einkauf zu tätigen.");
							}
								
						} else {
							Window.alert("Es sind nicht genügend liquide Mittel vorhanden.");
						}
					} else {
						Window.alert("Die eingestellte Menge muss größer als 0 sein.");
					}

				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}

			}
		});

		display.getEinkaufLbTyp().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (display
						.getEinkaufLbTyp()
						.getItemText(
								display.getEinkaufLbTyp().getSelectedIndex())
						.equals("Sattel")) {
					setArtikelValues(Artikel.SATTEL);
				}
				if (display
						.getEinkaufLbTyp()
						.getItemText(
								display.getEinkaufLbTyp().getSelectedIndex())
						.equals("Räder")) {
					setArtikelValues(Artikel.RAEDER);
				}
				if (display
						.getEinkaufLbTyp()
						.getItemText(
								display.getEinkaufLbTyp().getSelectedIndex())
						.equals("Lenker")) {
					setArtikelValues(Artikel.LENKER);
				}
				if (display
						.getEinkaufLbTyp()
						.getItemText(
								display.getEinkaufLbTyp().getSelectedIndex())
						.equals("Schaltung")) {
					setArtikelValues(Artikel.SCHALTUNG);
				}
				if (display
						.getEinkaufLbTyp()
						.getItemText(
								display.getEinkaufLbTyp().getSelectedIndex())
						.equals("Rahmen")) {
					setArtikelValues(Artikel.RAHMEN);
				}
				Position position;
				try {
					position = einkaufArtikel.getPosition(display
							.getEinkaufLbArtikel().getItemText(
									display.getEinkaufLbArtikel()
											.getSelectedIndex()));
					setTextValues(position.getGut().getId());
					setTxtGesamt();
				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {

				}
			}
		});

		display.getEinkaufLbArtikel().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Position position;
				try {
					position = einkaufArtikel.getPosition(display
							.getEinkaufLbArtikel().getItemText(
									display.getEinkaufLbArtikel()
											.getSelectedIndex()));
					setTextValues(position.getGut().getId());
					setTxtGesamt();
				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				}
			}
		});

		display.getEinkaufTxtMenge().addValueChangeHandler(
				new ValueChangeHandler<String>() {
					@Override
					public void onValueChange(ValueChangeEvent<String> event) {
						setTxtGesamt();
					}
				});

		display.getEinkaufPositionsWidget().getEntfColumn()
				.setFieldUpdater(new FieldUpdater<Position, String>() {

					@Override
					public void update(int index, Position object, String value) {
						display.getEinkaufPositionsWidget().updateListe(object);
						setGesamtverbrauch();
					}
				});

		// ///////////////////////////////////////////////////////////////
		// Verkauf
		// ///////////////////////////////////////////////////////////////
		display.getBtnVerkaufArtikelHinzufuegen().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						Position position;

						try {
							if (getVerkaufMenge() > 0) {
								position = verkaufsArtikel.getPosition(display
										.getLbVerkaufArtikel().getItemText(
												display.getLbVerkaufArtikel()
														.getSelectedIndex()));
								position.setMenge(getVerkaufMenge());
								position.setWert(getVerkaufWert());

								int lagerPositionsNr = verkaufsArtikel
										.getPositionsNr(position.getGut()
												.getId());

								if (lagerArtikel.getEinzelPositionNr(
										lagerPositionsNr).getMenge() < position
										.getMenge()) {
									Window.alert("Es können nicht mehr Artikel verkauft werden als zur Zeit auf Lager sind.");
								} else {
									display.getVerkaufsListe().addPosition(
											position);
									verkaufsListe = display.getVerkaufsListe()
											.getDisplayedItems();
									setVerkaufGesamterloes();
									getProdListeNachVerkauf();
								}
							} else {
								Window.alert("Die eingestellte Menge muss größer als 0 sein.");
							}

						} catch (NoObjectFoundException e) {
							e.printStackTrace();
						} catch (IndexOutOfBoundsException e) {
							e.printStackTrace();
						}

					}
				});

		display.getLbVerkaufTyp().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				if (display
						.getLbVerkaufTyp()
						.getItemText(
								display.getLbVerkaufTyp().getSelectedIndex())
						.equals("Sattel")) {
					setVerkaufArtikelValues(Artikel.SATTEL);
				}
				if (display
						.getLbVerkaufTyp()
						.getItemText(
								display.getLbVerkaufTyp().getSelectedIndex())
						.equals("Räder")) {
					setVerkaufArtikelValues(Artikel.RAEDER);
				}
				if (display
						.getLbVerkaufTyp()
						.getItemText(
								display.getLbVerkaufTyp().getSelectedIndex())
						.equals("Lenker")) {
					setVerkaufArtikelValues(Artikel.LENKER);
				}
				if (display
						.getLbVerkaufTyp()
						.getItemText(
								display.getLbVerkaufTyp().getSelectedIndex())
						.equals("Schaltung")) {
					setVerkaufArtikelValues(Artikel.SCHALTUNG);
				}
				if (display
						.getLbVerkaufTyp()
						.getItemText(
								display.getLbVerkaufTyp().getSelectedIndex())
						.equals("Rahmen")) {
					setVerkaufArtikelValues(Artikel.RAHMEN);
				}
				Position position;
				try {
					position = lagerArtikel.getPosition(display
							.getLbVerkaufArtikel().getItemText(
									display.getLbVerkaufArtikel()
											.getSelectedIndex()));
					setVerkaufTextPreis(position.getGut().getId());
					setVerkaufTextLagerBestand(position.getGut().getId());
					setVerkaufTxtGesamt();
				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		});

		display.getLbVerkaufArtikel().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Position position;
				try {
					position = lagerArtikel.getPosition(display
							.getLbVerkaufArtikel().getItemText(
									display.getLbVerkaufArtikel()
											.getSelectedIndex()));
					setVerkaufTextPreis(position.getGut().getId());
					setVerkaufTextLagerBestand(position.getGut().getId());
					setVerkaufTxtGesamt();
				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				}
			}
		});

		display.getTxtVerkaufMenge().addValueChangeHandler(
				new ValueChangeHandler<String>() {
					@Override
					public void onValueChange(ValueChangeEvent<String> event) {
						setVerkaufTxtGesamt();
					}
				});

		display.getVerkaufsListe().getEntfColumn()
				.setFieldUpdater(new FieldUpdater<Position, String>() {

					@Override
					public void update(int index, Position object, String value) {
						display.getVerkaufsListe().updateListe(object);
						verkaufsListe = display.getVerkaufsListe()
								.getDisplayedItems();
						getProdListeNachVerkauf();
						setVerkaufGesamterloes();
					}
				});

		// ///////////////////////////////////////////////////////////////
		// Produktion
		// ///////////////////////////////////////////////////////////////

		display.getLbProdLenker().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Position position;
				try {
					position = produktionsArtikelNachVerkauf
							.getPosition(display.getLbProdLenker().getItemText(
									display.getLbProdLenker()
											.getSelectedIndex()));
					display.getTxtProdLenkerPreis().setText(
							Double.toString(position.getWert()));
					berechneProdGesamtWert();
					setProdLenkerBestand();

				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				}
			}
		});
		//
		display.getLbProdSattel().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Position position;
				try {
					position = produktionsArtikelNachVerkauf
							.getPosition(display.getLbProdSattel().getItemText(
									display.getLbProdSattel()
											.getSelectedIndex()));
					display.getTxtProdSattelPreis().setText(
							Double.toString(position.getWert()));
					berechneProdGesamtWert();
					setProdSattelBestand();

				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				}
			}
		});

		display.getLbProdRahmen().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Position position;
				try {
					position = produktionsArtikelNachVerkauf
							.getPosition(display.getLbProdRahmen().getItemText(
									display.getLbProdRahmen()
											.getSelectedIndex()));
					display.getTxtProdRahmenPreis().setText(
							Double.toString(position.getWert()));
					berechneProdGesamtWert();
					setProdRahmenBestand();

				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				}
			}
		});

		display.getLbProdSchaltung().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Position position;
				try {
					position = produktionsArtikelNachVerkauf
							.getPosition(display.getLbProdSchaltung()
									.getItemText(
											display.getLbProdSchaltung()
													.getSelectedIndex()));
					display.getTxtProdSchaltungPreis().setText(
							Double.toString(position.getWert()));
					berechneProdGesamtWert();
					setProdSchaltungBestand();

				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				}
			}
		});

		display.getLbProdRaeder().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				Position position;
				try {
					position = produktionsArtikelNachVerkauf
							.getPosition(display.getLbProdRaeder().getItemText(
									display.getLbProdRaeder()
											.getSelectedIndex()));
					display.getTxtProdRaederPreis().setText(
							Double.toString(position.getWert()));
					berechneProdGesamtWert();
					setProdRaederBestand();

				} catch (NoObjectFoundException e) {
					e.printStackTrace();
				}
			}
		});

		display.getTxtProdMenge().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				pruefeProdMenge();
				berechneProdGesamtWert();
			}
		});

	}

	// ///////////////////////////////////////////////////////////////
	// Header
	// ///////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	public void setRundenNr(String runde) {
		display.getLblRundenNr().setText(runde);
	}

	public void setSpielName(String spielName) {
		display.getLblSpiel().setText(spielName);
	}

	// /////////////////////////////////////////////////////////////////////////
	// //// RPC Calls zum beschaffen von Daten
	// /////////////////////////////////////////////////////////////////////////
	public void getSpielerName() {
		rpcService.getSpielerName(spieler.getSpielerID(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error when fetching the Name.");
					}

					@Override
					public void onSuccess(String result) {
						display.getLblSpielerName().setText(result);
					}
				});
	}

	public void getFirma() {
		rpcService.getSpielerFirma(spieler.getSpielerID(),
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error when fetching the FirmenName.");
					}

					@Override
					public void onSuccess(String result) {
						display.getLblFirmenName().setText(result);
					}
				});
	}

	public void getRunde() {
		rpcService.getSpielAktuelleRundenNr(spiel.getSpielID(),
				new AsyncCallback<Integer>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error when fetching the Runde.");
					}

					@Override
					public void onSuccess(Integer result) {
						display.getLblRundenNr().setText(
								Integer.toString(result));

					}
				});
	}

	// ///////////////////////////////////////////////////////////////
	// Information Area
	// ///////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// //// RPC Calls zum beschaffen von Daten
	// /////////////////////////////////////////////////////////////////////////
	public void getMitarbeiterAnzahl() {
		rpcService.getAnzahlMitarbeiter(spieler.getSpielerID(),
				new AsyncCallback<Integer>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error when fetching the MitarbeiterAnzahl.");
					}

					@Override
					public void onSuccess(Integer result) {
						setMitarbeiterAnzahl(result);
						setTxtMitarbeiterAnzahl();
					}
				});

	}

	// RPC-Call für Lagerauslastung
	public void getLagerauslastung() {
		rpcService.getLagerKapazitaet(spieler.getSpielerID(),
				new AsyncCallback<Integer>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error when fetching the MaxLagerauslastung.");
					}

					@Override
					public void onSuccess(Integer result) {
						setMaxLagerauslastung(result);
					}
				});

		rpcService.getLagerBelegt(spieler.getSpielerID(),
				new AsyncCallback<Integer>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error when fetching the AktLagerauslastung.");
					}

					@Override
					public void onSuccess(Integer result) {
						setAktLagerauslastung(result);
						setTxtLagerauslastung();
					}
				});

	}

	public void getKontodaten() {
		rpcService.getBilanz(spieler.getSpielerID(),
				new AsyncCallback<Konto>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error when fetching the Konto.");
					}

					@Override
					public void onSuccess(Konto result) {
						Konto kasse = result.getKonto("Kasse");
						liquiditaet = kasse.bildeSaldo();
						setKontoWidget(result);
					}
				});
	}

	// /////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	// aktuelle Lagerauslastung festlegen
	public void setAktLagerauslastung(int aktAuslastung) {
		aktLagerauslastung = aktAuslastung;

	}

	// maximale Lagerauslastung festlegen
	public void setMaxLagerauslastung(int maxAuslastung) {
		maxLagerauslastung = maxAuslastung;
	}

	public void setMitarbeiterAnzahl(int mitarbeiter) {
		mitarbeiterAnzahl = mitarbeiter;
	}

	public void setTxtLagerauslastung() {
		String auslastung;
		auslastung = Integer.toString(aktLagerauslastung) + " LE / "
				+ Integer.toString(maxLagerauslastung) + " LE";
		display.getINFTxtLagerauslastung().setValue(auslastung);
	}

	public void setTxtMitarbeiterAnzahl() {
		display.getINFTxtMitarbeiteranzahl().setValue(
				Integer.toString(mitarbeiterAnzahl));
	}

	public void setKontoWidget(Konto konto) {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		ArrayList<ArrayList<String>> soll = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> haben = new ArrayList<ArrayList<String>>();
		double habenwert = 0;
		double sollwert = 0;

		ArrayList<Konto> sollKonten = konto.getUnterkontenSoll();

		for (int i = 0; i < sollKonten.size(); i++) {
			Konto zwischenKonto;
			ArrayList<String> zeile = new ArrayList<String>();
			zwischenKonto = sollKonten.get(i);
			// zwischenKonto.bildeSaldo();
			zeile.add(zwischenKonto.getBezeichnung());
			zeile.add(n.format(zwischenKonto.bildeSaldo()));
			soll.add(zeile);
			sollwert = sollwert + zwischenKonto.bildeSaldo();
		}

		ArrayList<Konto> habenKonten = konto.getUnterkontenHaben();

		for (int i = 0; i < habenKonten.size(); i++) {
			Konto zwischenKonto;
			ArrayList<String> zeile = new ArrayList<String>();
			zwischenKonto = habenKonten.get(i);

			zeile.add(zwischenKonto.getBezeichnung());
			zeile.add(n.format(zwischenKonto.bildeSaldo()));
			haben.add(zeile);
			habenwert = habenwert + zwischenKonto.bildeSaldo();
		}

		ArrayList<String> bilanzSpalten = new ArrayList<String>();
		bilanzSpalten.add("Aktiv");
		bilanzSpalten.add("");
		bilanzSpalten.add("");
		bilanzSpalten.add("Passiv");
		bilanz = new KontoWidget("Bilanz", bilanzSpalten, soll, haben);

		ArrayList<String> bilanzSumme = new ArrayList<String>();
		bilanzSumme.add("");
		bilanzSumme.add(n.format(sollwert));
		bilanzSumme.add("");
		bilanzSumme.add(n.format(habenwert));

		for (int i = 0; i < bilanzSpalten.size(); i++) {
			display.getINFBilanz().addColumn(bilanzSpalten.get(i));
		}

		for (int row = 0; row < soll.size(); row++) {
			display.getINFBilanz().addSoll(soll.get(row));
		}

		for (int row = 0; row < haben.size(); row++) {
			display.getINFBilanz().addHaben(haben.get(row));
		}
		display.getINFBilanz().auffuellen();
		display.getINFBilanz().applyDataRowStyles();
		display.getINFBilanz().addSumme(bilanzSumme);

	}

	// ///////////////////////////////////////////////////////////////
	// ENDE InformationArea
	// ///////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////
	// Einkauf
	// ///////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// //// RPC Calls zum beschaffen von Daten
	// /////////////////////////////////////////////////////////////////////////

	public void getMarktEinkaufArtikel() {
		rpcService.getMarktArtikel(spiel.getSpielID(),
				new AsyncCallback<Positionsliste>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Einkaufsartikel holen fehlgeschlagen");

					}

					@Override
					public void onSuccess(Positionsliste result) {
						einkaufArtikel = result;
						// set first selection
						display.getEinkaufLbTyp().setSelectedIndex(0);
						setArtikelValues(Artikel.SATTEL);
						Position position;
						try {
							position = einkaufArtikel.getPosition(display
									.getEinkaufLbArtikel().getItemText(
											display.getEinkaufLbArtikel()
													.getSelectedIndex()));
							setTextValues(position.getGut().getId());
							setTxtGesamt();
						} catch (NoObjectFoundException e) {
							e.printStackTrace();
						} catch (IndexOutOfBoundsException e) {

						}
					}
				});
	}

	// /////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////

	public void setGesamtverbrauch() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		List<Position> einkaufsListe = display.getEinkaufPositionsWidget()
				.getDisplayedItems();
		gesamtwert = 0.0;
		lagerVerbrauch = 0;
		for (int i = 0; i < einkaufsListe.size(); i++) {
			gesamtwert = gesamtwert + einkaufsListe.get(i).getWert()
					* einkaufsListe.get(i).getMenge();
			lagerVerbrauch = lagerVerbrauch
					+ einkaufsListe.get(i).getGut().getGroesse()
					* einkaufsListe.get(i).getMenge();
		}

		display.getEinkaufTxtGesamtKosten().setValue(n.format(gesamtwert));
		display.getEinkaufTxtLagerbedarf().setValue(
				Integer.toString(lagerVerbrauch));
	}

	public void setTypValues() {
		display.getEinkaufLbTyp().clear();
		display.getEinkaufLbTyp().addItem("Sattel");
		display.getEinkaufLbTyp().addItem("Lenker");
		display.getEinkaufLbTyp().addItem("Rahmen");
		display.getEinkaufLbTyp().addItem("Schaltung");
		display.getEinkaufLbTyp().addItem("Räder");
	}

	public void setArtikelValues(int typ) {
		display.getEinkaufLbArtikel().clear();
		for (int i = 0; i < einkaufArtikel.getSize(); i++) {
			if (einkaufArtikel.getEinzelPositionNr(i).getGut().getTyp() == typ) {
				display.getEinkaufLbArtikel().addItem(
						einkaufArtikel.getEinzelPositionNr(i).getGut()
								.getBezeichnung());
			}
		}
	}

	public void setTextValues(int artikelID) {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		for (int i = 0; i < einkaufArtikel.getSize(); i++) {
			if (einkaufArtikel.getEinzelPositionNr(i).getGut().getId() == artikelID) {
				display.getEinkaufTxtPreis().setValue(
						n.format(einkaufArtikel.getEinzelPositionNr(i)
								.getWert()));
			}
		}
	}

	public void setTxtGesamt() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		if ((display.getEinkaufTxtPreis().getValue() == null || display
				.getEinkaufTxtPreis().getValue().equals(""))
				&& (display.getEinkaufTxtMenge().getValue() == null || display
						.getEinkaufTxtMenge().getValue().equals(""))) {

		} else {
			// Double preis = n.parse(display.getTxtPreis().getValue());
			// int menge = Integer.parseInt(display.getTxtMenge().getValue());

			double gesamt;
			double preis = getWert();
			int menge = getMenge();
			gesamt = preis * menge;

			display.getEinkaufTxtGesamt().setValue(n.format(gesamt));
		}

	}

	public int getMenge() {
		try {
			return Integer.parseInt(display.getEinkaufTxtMenge().getValue());
		} catch (NumberFormatException e) {
			Window.alert("In das Feld Menge muss eine Zahl eingegeben werden.");
			return 0;
		}

	}

	public double getWert() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {

			return n.parse(display.getEinkaufTxtPreis().getValue());
		} catch (NumberFormatException e) {
			// Window.alert("In das Feld muss eine Zahl eingegeben werden");
			return 0;
		}
	}

	public double getGesamt() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			return n.parse(display.getEinkaufTxtGesamt().getValue());
		} catch (NumberFormatException e) {
			Window.alert("In das Feld muss eine Zahl eingegeben werden.");
			return 0;
		}

	}

	// ///////////////////////////////////////////////////////////////
	// ENDE Einkauf
	// ///////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////
	// Unternehmen
	// ///////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// //// set und get Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////
	public void setLblLagerPlanung(int lagerPlanung) {
		if (lagerPlanung < 0) {
			display.getLblUNLagerPlanung().setText(
					"+ " + -1 * Spiel.LAGERKOSTENEINHEIT * lagerPlanung
							+ " Euro");
			// display.getLblUNLagerPlanung().setText("+ " +
			// spiel.getLagerkosten * lagerPlanung + " €");
		} else if (lagerPlanung == 0) {
			display.getLblUNLagerPlanung().setText(
					"+ " + Spiel.LAGERKOSTENEINHEIT * lagerPlanung + " Euro");
		} else {
			display.getLblUNLagerPlanung().setText(
					-1 * Spiel.LAGERKOSTENEINHEIT * lagerPlanung + " Euro");
			// display.getLblUNLagerPlanung().setText("- " +
			// spiel.getLagerkosten * lagerPlanung + " €");
		}
	}

	public void setLblMitarbeiterPlanung(int mitarbeiterPlanung) {
		if (mitarbeiterPlanung >= 0) {
			// display.getLblUNMitarbeiterPlanung().setText("+ " +
			// spiel.getMitarbeiterkosten * mitarbeiterPlanung + "€ / Monat");
			display.getLblUNMitarbeiterPlanung().setText(
					"+ " + Spiel.MITARBEITERGEHALT * mitarbeiterPlanung
							+ " Euro / Monat");
		} else {
			// display.getLblUNMitarbeiterPlanung().setText("- " +
			// spiel.getMitarbeiterkosten * mitarbeiterPlanung + "€ / Monat");
			display.getLblUNMitarbeiterPlanung().setText(
					Spiel.MITARBEITERGEHALT * mitarbeiterPlanung
							+ " Euro / Monat");
		}
	}

	public void setUNMitarbeiterPlanung(int mitarbeiter) {
		display.getUNTxtEinstellen().setText(Integer.toString(mitarbeiter));
	}

	public void setUNLagerplanung(int lager) {
		display.getUNTxtLagerbedarf().setText(Integer.toString(lager));
	}

	public void setUNVerkaufspreis(double preis) {
		if (preis <= 0) {
			Window.alert("Der Verkaufspreis muss größer als 0 Euro sein.");
		} else {
			display.getUNTxtVerkaufspreis().setText(Double.toString(preis));
			verkaufspreisPlanung = preis;
		}
	}

	public int getMitarbeiterPlanung() {
		try {
			int planung = Integer.parseInt(display.getUNTxtEinstellen()
					.getText());
			if (planung < 0 && (mitarbeiterAnzahl + planung) < 0) {
				Window.alert("Es können nicht mehr Mitarbeiter entlassen werden, als man bisher schon hat.");
				setUNMitarbeiterPlanung(mitarbeiterPlanung);
				return mitarbeiterPlanung;
			} else {
				return planung;
			}
		} catch (NumberFormatException e) {
			Window.alert("In das Feld Mitarbeiter einstellen / entlassen muss eine ganzahlige positive oder negative Zahl eingegeben werden");
			return mitarbeiterPlanung;
		}
	}

	public int getLagerPlanung() {
		try {
			int planung = Integer.parseInt(display.getUNTxtLagerbedarf()
					.getText());
			if (planung < 0 && (maxLagerauslastung + planung) < 0) {
				Window.alert("Es können nicht mehr Lagereinheiten verkauft werden, als man besitzt hat.");
				setUNLagerplanung(lagerPlanung);
				return lagerPlanung;
			} else {
				return planung;
			}
		} catch (NumberFormatException e) {
			Window.alert("In das Feld Lagerkapazität erhöhen / verringern muss eine ganzahlige positive oder negative Zahl eingegeben werden");
			return lagerPlanung;
		}
	}

	public double getVerkaufspreisPlanung() {
		try {
			double verkaufspreis = Double.parseDouble(display
					.getUNTxtVerkaufspreis().getText());
			if (verkaufspreis <= 0) {
				Window.alert("Der Verkaufspreis muss größer als 0");
				setUNVerkaufspreis(this.verkaufspreisPlanung);
				return this.verkaufspreisPlanung;
			}
			return verkaufspreis;
		} catch (NumberFormatException e) {
			Window.alert("In das Feld Verkaufspreis muss eine Zahl eingegeben werden.");
			return this.verkaufspreisPlanung;
		}
	}

	// ///////////////////////////////////////////////////////////////
	// ENDE Unternehmen
	// ///////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////
	// Produktion
	// ///////////////////////////////////////////////////////////////

	public void getProdListeNachVerkauf() {
		List<Position> verkaufsArtikel = verkaufsListe;
		produktionsArtikelNachVerkauf = new Positionsliste();
		for (int i = 0; i < lagerArtikel.getSize(); i++) {
			produktionsArtikelNachVerkauf.erfassePosition(new Position(
					lagerArtikel.getEinzelPositionNr(i).getGut(), lagerArtikel
							.getEinzelPositionNr(i).getMenge(), lagerArtikel
							.getEinzelPositionNr(i).getWert()));
		}

		for (int i = 0; i < verkaufsArtikel.size(); i++) {
			for (int j = 0; j < produktionsArtikelNachVerkauf.getSize(); j++) {
				if (verkaufsArtikel
						.get(i)
						.getGut()
						.getBezeichnung()
						.equals(produktionsArtikelNachVerkauf
								.getEinzelPositionNr(j).getGut()
								.getBezeichnung())) {
					int alteMenge = produktionsArtikelNachVerkauf
							.getEinzelPositionNr(j).getMenge();
					int abzugMenge = verkaufsArtikel.get(i).getMenge();
					int restMenge = alteMenge - abzugMenge;
					produktionsArtikelNachVerkauf.getEinzelPositionNr(j)
							.setMenge(restMenge);
				}
			}
		}
		updateProduktion();
	}

	public void pruefeProdMenge() {
		int zwischenVar = getProdMenge();
		if (getProdLenkerBestand() < getProdMenge()) {
			setProdTxtMenge(getProdLenkerBestand());
		}
		if (getProdSattelBestand() < getProdMenge()) {
			setProdTxtMenge(getProdSattelBestand());
		}
		if (getProdSchaltungBestand() < getProdMenge()) {
			setProdTxtMenge(getProdSchaltungBestand());
		}
		if (getProdRaederBestand() < getProdMenge()) {
			setProdTxtMenge(getProdRaederBestand());
		}
		if (getProdRahmenBestand() < getProdMenge()) {
			setProdTxtMenge(getProdRahmenBestand());
		}
		if (zwischenVar > getProdMenge()) {
			Window.alert("Es sind nicht genug Bauteile auf Lager um die gewünschte Anzahl an Teilen zu produzieren");
		}
		zwischenVar = getProdMenge();
		if (zwischenVar > (mitarbeiterAnzahl + getMitarbeiterPlanung())
				* Spiel.MITARBEITERPRODUKTION) {
			Window.alert("Nach aktueller Planung lassen sich mit einer Mitarbeiteranzahl von: "
					+ (mitarbeiterAnzahl
					+ getMitarbeiterPlanung())
					+ " lassen sich maximal "
					+ (mitarbeiterAnzahl + getMitarbeiterPlanung())
					* Spiel.MITARBEITERPRODUKTION + " Fahrräder herstellen");
			setProdTxtMenge((mitarbeiterAnzahl + getMitarbeiterPlanung())
					* Spiel.MITARBEITERPRODUKTION);
		}

	}

	public void updateProduktion() {
		setProdSattelPreis();
		setProdLenkerPreis();
		setProdRahmenPreis();
		setProdSchaltungPreis();
		setProdRaederPreis();
		setProdLenkerBestand();
		setProdSattelBestand();
		setProdRahmenBestand();
		setProdSchaltungBestand();
		setProdRaederBestand();
		pruefeProdMenge();
	}

	public void setProduktionsListBoxes(Positionsliste lagerArtikel) {
		for (int i = 0; i < lagerArtikel.getSize(); i++) {
			Position position = lagerArtikel.getEinzelPositionNr(i);
			switch (position.getGut().getTyp()) {
			case Artikel.SATTEL:
				display.getLbProdSattel().addItem(
						position.getGut().getBezeichnung());

				break;

			case Artikel.LENKER:
				display.getLbProdLenker().addItem(
						position.getGut().getBezeichnung());

				break;
			case Artikel.RAEDER:
				display.getLbProdRaeder().addItem(
						position.getGut().getBezeichnung());

				break;

			case Artikel.RAHMEN:
				display.getLbProdRahmen().addItem(
						position.getGut().getBezeichnung());

				break;

			case Artikel.SCHALTUNG:
				display.getLbProdSchaltung().addItem(
						position.getGut().getBezeichnung());

				break;

			default:
				break;
			}
		}
	}

	// / SET Methoden für die UI Elemente
	public void setProdSattelPreis() {
		Position position;
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			position = lagerArtikel.getPosition(display.getLbProdSattel()
					.getItemText(display.getLbProdSattel().getSelectedIndex()));
			display.getTxtProdSattelPreis().setText(
					n.format(position.getWert()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdSattelPreis().setText("0");
		}

	}

	public void setProdRahmenPreis() {
		Position position;
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			position = lagerArtikel.getPosition(display.getLbProdRahmen()
					.getItemText(display.getLbProdRahmen().getSelectedIndex()));
			display.getTxtProdRahmenPreis().setText(
					n.format(position.getWert()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdRahmenPreis().setText("0");
		}

	}

	public void setProdRaederPreis() {
		Position position;
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			position = lagerArtikel.getPosition(display.getLbProdRaeder()
					.getItemText(display.getLbProdRaeder().getSelectedIndex()));
			display.getTxtProdRaederPreis().setText(
					n.format(position.getWert()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdRaederPreis().setText("0");
		}

	}

	public void setProdSchaltungPreis() {
		Position position;
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			position = lagerArtikel.getPosition(display.getLbProdSchaltung()
					.getItemText(
							display.getLbProdSchaltung().getSelectedIndex()));
			display.getTxtProdSchaltungPreis().setText(
					n.format(position.getWert()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdSchaltungPreis().setText("0");
		}

	}

	public void setProdLenkerPreis() {
		Position position;
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			position = lagerArtikel.getPosition(display.getLbProdLenker()
					.getItemText(display.getLbProdLenker().getSelectedIndex()));
			display.getTxtProdLenkerPreis().setText(
					n.format(position.getWert()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdLenkerPreis().setText("0");
		}

	}

	public void setProdLenkerBestand() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdLenker().getItemText(
							display.getLbProdLenker().getSelectedIndex()));
			display.getTxtProdLenkerBestand().setText(
					Integer.toString(position.getMenge()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdLenkerBestand().setText("0");
		}

	}

	public void setProdSattelBestand() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdSattel().getItemText(
							display.getLbProdSattel().getSelectedIndex()));
			display.getTxtProdSattelBestand().setText(
					Integer.toString(position.getMenge()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdSattelBestand().setText("0");
		}

	}

	public void setProdRahmenBestand() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdRahmen().getItemText(
							display.getLbProdRahmen().getSelectedIndex()));
			display.getTxtProdRahmenBestand().setText(
					Integer.toString(position.getMenge()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdRahmenBestand().setText("0");
		}

	}

	public void setProdRaederBestand() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdRaeder().getItemText(
							display.getLbProdRaeder().getSelectedIndex()));
			display.getTxtProdRaederBestand().setText(
					Integer.toString(position.getMenge()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdRaederBestand().setText("0");
		}

	}

	public void setProdSchaltungBestand() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdSchaltung().getItemText(
							display.getLbProdSchaltung().getSelectedIndex()));
			display.getTxtProdSchaltungBestand().setText(
					Integer.toString(position.getMenge()));
			berechneProdGesamtWert();
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			display.getTxtProdSchaltungBestand().setText("0");
		}

	}

	public double berechneProdGesamtWert() {
		pruefeProdMenge();
		int menge = getProdMenge();
		double stueckKosten = getStueckKosten();
		return menge * stueckKosten;
	}

	public double getStueckKosten() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		double lenkerPreis = getProdLenkerPreis();
		double sattelPreis = getProdSattelPreis();
		double rahmenPreis = getProdRahmenPreis();
		double schaltungPreis = getProdSchaltungPreis();
		double raederPreis = getProdRaederPreis();
		display.getTxtProdStueckPreis().setText(
				n.format(lenkerPreis + sattelPreis + rahmenPreis
						+ schaltungPreis + raederPreis));
		return lenkerPreis + sattelPreis + rahmenPreis + schaltungPreis
				+ raederPreis;
	}

	public int getProdMenge() {
		try {
			int menge = Integer.parseInt(display.getTxtProdMenge().getText());
			if (menge < 0) {
				Window.alert("Hier kann keine negative Zahl eingegeben werden!");
				setProdTxtMenge(0);
				return 0;
			} else {
				return menge;
			}
		} catch (NumberFormatException e) {
			return 0;
		}

	}

	public void setProdTxtMenge(int menge) {
		display.getTxtProdMenge().setText(Integer.toString(menge));
	}

	// get Positionen in der Produktion

	public Position getProdSchaltungPosition() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdSchaltung().getItemText(
							display.getLbProdSchaltung().getSelectedIndex()));
			position.setMenge(getProdMenge());
			position.setWert(getProdSchaltungPreis());
			return position;
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Position getProdRahmenPosition() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdRahmen().getItemText(
							display.getLbProdRahmen().getSelectedIndex()));
			position.setMenge(getProdMenge());
			position.setWert(getProdRahmenPreis());
			return position;
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Position getProdRaederPosition() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdRaeder().getItemText(
							display.getLbProdRaeder().getSelectedIndex()));
			position.setMenge(getProdMenge());
			position.setWert(getProdRaederPreis());
			return position;
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Position getProdSattelPosition() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdSattel().getItemText(
							display.getLbProdSattel().getSelectedIndex()));
			position.setMenge(getProdMenge());
			position.setWert(getProdSattelPreis());
			return position;
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Position getProdLenkerPosition() {
		Position position;
		try {
			position = produktionsArtikelNachVerkauf.getPosition(display
					.getLbProdLenker().getItemText(
							display.getLbProdLenker().getSelectedIndex()));
			position.setMenge(getProdMenge());
			position.setWert(getProdLenkerPreis());
			return position;
		} catch (NoObjectFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Hole die Preise
	public double getProdLenkerPreis() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			return n.parse(display.getTxtProdLenkerPreis().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public double getProdSattelPreis() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			return n.parse(display.getTxtProdSattelPreis().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public double getProdSchaltungPreis() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			return n.parse(display.getTxtProdSchaltungPreis()
					.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public double getProdRaederPreis() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			return n.parse(display.getTxtProdRaederPreis().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public double getProdRahmenPreis() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			return n.parse(display.getTxtProdRahmenPreis().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	// Hole die Bestandswerte
	public int getProdSattelBestand() {
		try {
			return Integer
					.parseInt(display.getTxtProdSattelBestand().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public int getProdRahmenBestand() {
		try {
			return Integer
					.parseInt(display.getTxtProdRahmenBestand().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public int getProdSchaltungBestand() {
		try {
			return Integer.parseInt(display.getTxtProdSchaltungBestand()
					.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public int getProdLenkerBestand() {
		try {
			return Integer
					.parseInt(display.getTxtProdLenkerBestand().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public int getProdRaederBestand() {
		try {
			return Integer
					.parseInt(display.getTxtProdRaederBestand().getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	// ///////////////////////////////////////////////////////////////
	// ENDE Produktion
	// ///////////////////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////////////
	// Verkauf
	// ///////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////////////
	// //// RPC Calls zum beschaffen von Daten
	// /////////////////////////////////////////////////////////////////////////

	public void getLagerArtikel() {
		rpcService.getLagerbestaende(spieler.getSpielerID(),
				new AsyncCallback<Positionsliste>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Lagerartikel holen fehlgeschlagen");

					}

					@Override
					public void onSuccess(Positionsliste result) {
						lagerArtikel = result;
						for (int i = 0; i < result.getSize(); i++) {
							verkaufsArtikel.erfassePosition(new Position(result
									.getEinzelPositionNr(i).getGut(), result
									.getEinzelPositionNr(i).getMenge(), result
									.getEinzelPositionNr(i).getWert() / 2));
						}

						for (int i = 0; i < result.getSize(); i++) {
							produktionsArtikelNachVerkauf
									.erfassePosition(new Position(result
											.getEinzelPositionNr(i).getGut(),
											result.getEinzelPositionNr(i)
													.getMenge(), result
													.getEinzelPositionNr(i)
													.getWert() / 2));
						}
						setProduktionsListBoxes(produktionsArtikelNachVerkauf);
						updateProduktion();

						// set first selection
						display.getLbVerkaufArtikel().setSelectedIndex(0);
						setVerkaufArtikelValues(Artikel.SATTEL);
						Position position;
						try {
							position = verkaufsArtikel.getPosition(display
									.getLbVerkaufArtikel().getItemText(
											display.getLbVerkaufArtikel()
													.getSelectedIndex()));
							setVerkaufTextPreis(position.getGut().getId());
							setVerkaufTextLagerBestand(position.getGut()
									.getId());
							setVerkaufTxtGesamt();
						} catch (NoObjectFoundException e) {
							e.printStackTrace();
						} catch (IndexOutOfBoundsException e) {

						}
					}
				});
	}

	// /////////////////////////////////////////////////////////////////////////
	// //// set Methoden für die Elemente des UI
	// /////////////////////////////////////////////////////////////////////////

	public void setVerkaufGesamterloes() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		List<Position> verkaufsListe = display.getVerkaufsListe()
				.getDisplayedItems();
		gesamtwert = 0.0;
		lagerEinsparung = 0;
		for (int i = 0; i < verkaufsListe.size(); i++) {
			gesamtwert = gesamtwert + verkaufsListe.get(i).getWert()
					* verkaufsListe.get(i).getMenge();
			lagerEinsparung = lagerEinsparung
					+ verkaufsListe.get(i).getGut().getGroesse()
					* verkaufsListe.get(i).getMenge();
		}

		display.getTxtVerkaufGesamtErloes().setValue(n.format(gesamtwert));
		display.getVerkaufLagerentlastung().setValue(
				Integer.toString(lagerEinsparung));
	}

	public void setVerkaufTypValues() {
		display.getLbVerkaufTyp().clear();
		display.getLbVerkaufTyp().addItem("Sattel");
		display.getLbVerkaufTyp().addItem("Lenker");
		display.getLbVerkaufTyp().addItem("Rahmen");
		display.getLbVerkaufTyp().addItem("Schaltung");
		display.getLbVerkaufTyp().addItem("Räder");
	}

	//
	public void setVerkaufArtikelValues(int typ) {
		display.getLbVerkaufArtikel().clear();
		for (int i = 0; i < verkaufsArtikel.getSize(); i++) {
			if (verkaufsArtikel.getEinzelPositionNr(i).getGut().getTyp() == typ) {
				display.getLbVerkaufArtikel().addItem(
						verkaufsArtikel.getEinzelPositionNr(i).getGut()
								.getBezeichnung());
			}
		}
	}

	//
	public void setVerkaufTextPreis(int artikelID) {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		for (int i = 0; i < verkaufsArtikel.getSize(); i++) {
			if (verkaufsArtikel.getEinzelPositionNr(i).getGut().getId() == artikelID) {
				display.getTxtVerkaufPreis().setValue(
						n.format(verkaufsArtikel.getEinzelPositionNr(i)
								.getWert()));
			}
		}
	}

	public void setVerkaufTextLagerBestand(int artikelID) {
		for (int i = 0; i < lagerArtikel.getSize(); i++) {
			if (lagerArtikel.getEinzelPositionNr(i).getGut().getId() == artikelID) {
				display.getTxtVerkaufLagerbestand().setValue(
						Integer.toString(lagerArtikel.getEinzelPositionNr(i)
								.getMenge()));
			}
		}
	}

	//
	public void setVerkaufTxtGesamt() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		if ((display.getTxtVerkaufPreis().getValue() == null || display
				.getTxtVerkaufPreis().getValue().equals(""))
				&& (display.getTxtVerkaufMenge().getValue() == null || display
						.getTxtVerkaufMenge().getValue().equals(""))) {

		} else {
			// Double preis = n.parse(display.getTxtPreis().getValue());
			// int menge = Integer.parseInt(display.getTxtMenge().getValue());

			double gesamt;
			double preis = getVerkaufWert();
			int menge = getVerkaufMenge();
			gesamt = preis * menge;

			display.getTxtVerkaufGesamt().setValue(n.format(gesamt));
		}

	}

	//
	public int getVerkaufMenge() {
		try {
			return Integer.parseInt(display.getTxtVerkaufMenge().getValue());
		} catch (NumberFormatException e) {
			Window.alert("In das Feld Menge muss eine Zahl eingegeben werden");
			return 0;
		}

	}

	//
	public double getVerkaufWert() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {

			return n.parse(display.getTxtVerkaufPreis().getValue());
		} catch (NumberFormatException e) {
			// Window.alert("In das Feld muss eine Zahl eingegeben werden");
			return 0;
		}
	}

	//
	public double getVerkaufGesamt() {
		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
		try {
			return n.parse(display.getTxtVerkaufGesamt().getValue());
		} catch (NumberFormatException e) {
			Window.alert("In das Feld muss eine Zahl eingegeben werden");
			return 0;
		}

	}

	// ///////////////////////////////////////////////////////////////
	// ENDE Verkauf
	// ///////////////////////////////////////////////////////////////
}
