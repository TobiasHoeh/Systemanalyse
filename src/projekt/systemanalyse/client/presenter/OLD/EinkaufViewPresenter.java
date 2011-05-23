//package projekt.systemanalyse.client.presenter.OLD;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import projekt.systemanalyse.client.SpielServiceAsync;
//import projekt.systemanalyse.client.presenter.Presenter;
//import projekt.systemanalyse.client.view.PositionsWidget;
//import projekt.systemanalyse.client.view.components.PositionsListeWidget;
//import projekt.systemanalyse.shared.Position;
//import projekt.systemanalyse.shared.Positionsliste;
//import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
//import projekt.systemanalyse.shared.logistik.Artikel;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ChangeEvent;
//import com.google.gwt.event.dom.client.ChangeHandler;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.HasChangeHandlers;
//import com.google.gwt.event.dom.client.HasClickHandlers;
//import com.google.gwt.event.logical.shared.ValueChangeEvent;
//import com.google.gwt.event.logical.shared.ValueChangeHandler;
//import com.google.gwt.event.shared.SimpleEventBus;
//import com.google.gwt.i18n.client.NumberFormat;
//
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.HasValue;
//import com.google.gwt.user.client.ui.HasWidgets;
//import com.google.gwt.user.client.ui.ListBox;
//import com.google.gwt.user.client.ui.Widget;
//
//public class EinkaufViewPresenter implements Presenter {
//
//	public interface Display {
//		Widget asWidget();
//
//		HasClickHandlers getArtikelHinzufuegenButton();
//
//		ListBox getlbTyp();
//
//		ListBox getLbArtikel();
//
//		HasValue<String> getTxtPreis();
//
//		HasValue<String> getTxtMenge();
//
//		HasValue<String> getTxtGesamt();
//
//		PositionsWidget getPositionsListe();
//
//		HasValue<String> getTxtGesamtkosten();
//
//		HasValue<String> getTxtLagerverbrauch();
//
//	}
//
//	private final SpielServiceAsync rpcService;
//	private final SimpleEventBus eventBus;
//	private final Display display;
//	private int spielerID;
//	private Positionsliste einkaufArtikel;
//	private Double gesamtwert;
//	private int lagerVerbrauch;
//
//	public EinkaufViewPresenter(SpielServiceAsync rpcService,
//			SimpleEventBus eventBus, Display view, int spielerID) {
//		this.rpcService = rpcService;
//		this.eventBus = eventBus;
//		this.display = view;
//		bind();
//		display.getTxtMenge().setValue("0");
//		setGesamtverbrauch();
//
//		getMarktEinkaufArtikel();
//		setTypValues();
//	}
//
//	public void go(final HasWidgets container) {
//		bind();
//		container.clear();
//		container.add(display.asWidget());
//	}
//
//	public Widget getDisplay() {
//		return display.asWidget();
//	}
//
//	public void bind() {
//		display.getArtikelHinzufuegenButton().addClickHandler(
//				new ClickHandler() {
//					public void onClick(ClickEvent event) {
//						Position position;
//
//						try {
//							position = einkaufArtikel.getPosition(display
//									.getLbArtikel().getItemText(
//											display.getLbArtikel()
//													.getSelectedIndex()));
//							position.setMenge(getMenge());
//							position.setWert(getWert());
//							display.getPositionsListe().addPosition(position);
//							setGesamtverbrauch();
//
//						} catch (NoObjectFoundException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IndexOutOfBoundsException e) {
//
//						}
//
//					}
//				});
//
//		display.getlbTyp().addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//
//				if (display.getlbTyp()
//						.getItemText(display.getlbTyp().getSelectedIndex())
//						.equals("Sattel")) {
//					setArtikelValues(Artikel.SATTEL);
//				}
//				if (display.getlbTyp()
//						.getItemText(display.getlbTyp().getSelectedIndex())
//						.equals("Raeder")) {
//					setArtikelValues(Artikel.RAEDER);
//				}
//				if (display.getlbTyp()
//						.getItemText(display.getlbTyp().getSelectedIndex())
//						.equals("Lenker")) {
//					setArtikelValues(Artikel.LENKER);
//				}
//				if (display.getlbTyp()
//						.getItemText(display.getlbTyp().getSelectedIndex())
//						.equals("Schaltung")) {
//					setArtikelValues(Artikel.SCHALTUNG);
//				}
//				if (display.getlbTyp()
//						.getItemText(display.getlbTyp().getSelectedIndex())
//						.equals("Rahmen")) {
//					setArtikelValues(Artikel.RAHMEN);
//				}
//				Position position;
//				try {
//					position = einkaufArtikel.getPosition(display
//							.getLbArtikel().getItemText(
//									display.getLbArtikel().getSelectedIndex()));
//					setTextValues(position.getGut().getId());
//					setTxtGesamt();
//				} catch (NoObjectFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IndexOutOfBoundsException e) {
//
//				}
//			}
//		});
//
//		display.getLbArtikel().addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				Position position;
//				try {
//					position = einkaufArtikel.getPosition(display
//							.getLbArtikel().getItemText(
//									display.getLbArtikel().getSelectedIndex()));
//					setTextValues(position.getGut().getId());
//					setTxtGesamt();
//				} catch (NoObjectFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//
//		display.getTxtMenge().addValueChangeHandler(
//				new ValueChangeHandler<String>() {
//					@Override
//					public void onValueChange(ValueChangeEvent<String> event) {
//						setTxtGesamt();
//					}
//				});
//	}
//
//	public void getMarktEinkaufArtikel() {
//		rpcService.getMarktEinkaufArtikel(new AsyncCallback<Positionsliste>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Einkaufsartikel holen fehlgeschlagen");
//
//			}
//
//			@Override
//			public void onSuccess(Positionsliste result) {
//				einkaufArtikel = result;
//			}
//		});
//	}
//
//	public void setTypValues() {
//		display.getlbTyp().clear();
//		display.getlbTyp().addItem("Sattel");
//		display.getlbTyp().addItem("Lenker");
//		display.getlbTyp().addItem("Rahmen");
//		display.getlbTyp().addItem("Schaltung");
//		display.getlbTyp().addItem("Raeder");
//	}
//
//	public void setArtikelValues(int typ) {
//		display.getLbArtikel().clear();
//		for (int i = 0; i < einkaufArtikel.size(); i++) {
//			try {
//				if (einkaufArtikel.getPosition(i).getGut().getTyp() == typ) {
//					display.getLbArtikel().addItem(
//							einkaufArtikel.getPosition(i).getGut()
//									.getBezeichnung());
//				}
//			} catch (NoObjectFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void setTextValues(int artikelID) {
//		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
//		for (int i = 0; i < einkaufArtikel.size(); i++) {
//			try {
//				if (einkaufArtikel.getPosition(i).getGut().getId() == artikelID) {
//					display.getTxtPreis().setValue(
//							n.format(einkaufArtikel.getPosition(i).getWert()));
//				}
//			} catch (NoObjectFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void setTxtGesamt() {
//		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
//		if ((display.getTxtPreis().getValue() == null || display.getTxtPreis()
//				.getValue().equals(""))
//				&& (display.getTxtMenge().getValue() == null || display
//						.getTxtMenge().getValue().equals(""))) {
//
//		} else {
//			// Double preis = n.parse(display.getTxtPreis().getValue());
//			// int menge = Integer.parseInt(display.getTxtMenge().getValue());
//
//			double gesamt;
//			double preis = getWert();
//			int menge = getMenge();
//			gesamt = preis * menge;
//
//			display.getTxtGesamt().setValue(n.format(gesamt));
//		}
//
//	}
//
//	public int getMenge() {
//		try {
//			return Integer.parseInt(display.getTxtMenge().getValue());
//		} catch (NumberFormatException e) {
//			Window.alert("In das Feld Menge muss eine Zahl eingegeben werden");
//			return 0;
//		}
//
//	}
//
//	public double getWert() {
//		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
//		try {
//
//			return n.parse(display.getTxtPreis().getValue());
//		} catch (NumberFormatException e) {
////			Window.alert("In das Feld muss eine Zahl eingegeben werden");
//			return 0;
//		}
//	}
//
//	public double getGesamt() {
//		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
//		try {
//			return n.parse(display.getTxtGesamt().getValue());
//		} catch (NumberFormatException e) {
//			Window.alert("In das Feld muss eine Zahl eingegeben werden");
//			return 0;
//		}
//
//	}
//
//	public void setGesamtverbrauch() {
//		NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
//		List<Position> einkaufsListe = display.getPositionsListe()
//				.getDisplayedItems();
//		gesamtwert = 0.0;
//		lagerVerbrauch = 0;
//		for (int i = 0; i < einkaufsListe.size(); i++) {
//			gesamtwert = gesamtwert + einkaufsListe.get(i).getWert()
//					* einkaufsListe.get(i).getMenge();
//			lagerVerbrauch = lagerVerbrauch
//					+ einkaufsListe.get(i).getGut().getGroesse()
//					* einkaufsListe.get(i).getMenge();
//		}
//
//		display.getTxtGesamtkosten().setValue(n.format(gesamtwert));
//		display.getTxtLagerverbrauch().setValue(
//				Integer.toString(lagerVerbrauch));
//	}
//
//}
