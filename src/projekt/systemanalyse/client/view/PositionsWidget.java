package projekt.systemanalyse.client.view;

import java.util.ArrayList;
import java.util.List;

import projekt.systemanalyse.shared.Position;
import projekt.systemanalyse.shared.Positionsliste;
import projekt.systemanalyse.shared.exceptions.NoObjectFoundException;
import projekt.systemanalyse.shared.logistik.Artikel;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class PositionsWidget extends CellTable<Position> {
	/**
	 * The key provider that allows us to identify Contacts even if a field
	 * changes. We identify contacts by their unique ID.
	 */
	private Column<Position, String> entfColumn;
	
	public Column<Position, String> getEntfColumn() {
		return entfColumn;
	}
	private static final ProvidesKey<Position> KEY_PROVIDER = new ProvidesKey<Position>() {
		public Object getKey(Position item) {
			return item.getGut().getId();
		}
	};

	private Positionsliste positionen = new Positionsliste();

	public PositionsWidget() {
		this.setWidth("100%");
		this.setHeight("90%");
		// Create a CellTable.
		this.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the name.
		TextColumn<Position> typColumn = new TextColumn<Position>() {
			@Override
			public String getValue(Position object) {
				return Artikel.typToString(object.getGut().getTyp());
			}
		};
		this.addColumn(typColumn, "Typ");

		// Add a text column to show the name.
		TextColumn<Position> artikelColumn = new TextColumn<Position>() {
			@Override
			public String getValue(Position object) {
				return object.getGut().getBezeichnung();
			}
		};
		this.addColumn(artikelColumn, "Artikel");

		// SelectionCell für den Artikel
		// final SelectionCell bezeichnungCell = new
		// SelectionCell(positionToOptions(positionen));
		// Column<Position<Artikel>, String> selectionColumn = new
		// Column<Position<Artikel>, String>(bezeichnungCell) {
		// @Override
		// public String getValue(Position<Artikel> object) {
		// return object.getGut().getBezeichnung();
		// }
		// };
		// this.addColumn(selectionColumn, "Artikel");
		//
		//
		// selectionColumn.setFieldUpdater(new FieldUpdater<Position<Artikel>,
		// String>() {
		// public void update(int index, Position<Artikel> object, String value)
		// {
		// // Validate the data.
		// List<Position<Artikel>> angezeigte = getDisplayedItems();
		// for(int i = 0; i<angezeigte.size();i++) {
		// // if(angezeigte.get(i).getGut().getId()
		// }
		// Artikel neuerArtikel;
		// try {
		// neuerArtikel = Bauteil.erzeugeBauteil(504, 20, 3, 2, "dritter",
		// "schlecht");
		// positionen.erfassePosition(neuerArtikel, 800, 1000);
		// setRowCount(positionen.size(), true);
		// // Push the data into the widget.
		// setRowData(0, positionToList(positionen));
		// redraw();
		// } catch (InstanceCreationException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });
		// Add a date column to show the birthday.
		// DateCell dateCell = new DateCell();
		// Column<Position<Artikel>, Date> dateColumn = new
		// Column<Position<Artikel>, Date>(dateCell) {
		// @Override
		// public Date getValue(Contact object) {
		// return object.birthday;
		// }
		// };
		// this.addColumn(dateColumn, "Birthday");

		// Add a text input column to edit the name.
//		final EditTextCell mengeCell = new EditTextCell();
//		Column<Position, String> mengeColumn = new Column<Position, String>(
//				mengeCell) {
//			@Override
//			public String getValue(Position object) {
//				return Integer.toString(object.getMenge());
//			}
//		};
//		this.addColumn(mengeColumn, "Menge");
//
//		mengeColumn.setFieldUpdater(new FieldUpdater<Position, String>() {
//			public void update(int index, Position object, String value) {
//				// Validate the data.
//
//				try {
//					object.setMenge(Integer.parseInt(value));
//					object.getGut().setBezeichnung("test");
//					redraw();
//				} catch (NumberFormatException e) {
//					Window.alert("Hier muss ein ganzzahliger Wert eingegeben werden.");
//					// mengeCell.setViewData(KEY_PROVIDER.getKey(object), new
//					// TextInputCell.ViewData("0"));
//					// mengeCell.clearViewData(KEY_PROVIDER.getKey(object));
//					object.setMenge(0);
//					return;
//				}
//			}
//
//		});

		// Add a text column to show the address.
		TextColumn<Position> mengeColumn = new TextColumn<Position>() {
			@Override
			public String getValue(Position object) {
				return Integer.toString(object.getMenge());
			}
		};
		this.addColumn(mengeColumn, "Menge");
		
		// Add a text column to show the address.
		TextColumn<Position> preisColumn = new TextColumn<Position>() {
			@Override
			public String getValue(Position object) {
				NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
				return n.format(object.getWert());
			}
		};
		this.addColumn(preisColumn, "Preis");

		// Add a text column to show the address.
		TextColumn<Position> gesamtColumn = new TextColumn<Position>() {
			@Override
			public String getValue(Position object) {
				NumberFormat n = NumberFormat.getCurrencyFormat("EUR");
				return n.format(object.getWert() * object.getMenge());
			}
		};
		this.addColumn(gesamtColumn, "Gesamt");

		// Add a text column to show the name.
		final ButtonCell entfCell = new ButtonCell();

		entfColumn = new Column<Position, String>(
				entfCell) {
			@Override
			public String getValue(Position object) {
				return "Entfernen";
			}
		};
		this.addColumn(entfColumn, "");

//		entfColumn.setFieldUpdater(new FieldUpdater<Position, String>() {
//			public void update(int index, Position object, String value) {
//				positionen.entfernePosition(object);
//				setRowCount(positionen.getSize(), true);
//				// Push the data into the widget.
//				setRowData(0, positionToList(positionen));
//				redraw();
//			}
//
//		});

		// addColumn(new SelectionCell(positionToOptions(positionen)),
		// "Selection", new GetValue<String>() {
		// public String getValue(Position<Artikel> artikel) {
		// return artikel.getGut().getBezeichnung();
		// }
		// }, new FieldUpdater<Position<Artikel>, String>() {
		// public void update(int index, Position<Artikel> object, String value)
		// {
		// for (Category category : categories) {
		// if (category.getDisplayName().equals(value)) {
		// pendingChanges.add(new CategoryChange(object, category));
		// break;
		// }
		// }
		// }
		// });

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Position> selectionModel = new SingleSelectionModel<Position>();
		this.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Position selected = selectionModel.getSelectedObject();
						if (selected != null) {
							// Window.alert("You selected: " +
							// selected.getMenge());
						}
					}
				});

		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		this.setRowCount(positionen.getSize(), true);
		// Push the data into the widget.
		this.setRowData(0, positionToList(positionen));
	}
	
	public void updateListe(Position object) {
		positionen.entfernePosition(object);
		setRowCount(positionen.getSize(), true);
		// Push the data into the widget.
		setRowData(0, positionToList(positionen));
		redraw();
	}

	public List<Position> positionToList(Positionsliste liste) {
		List<Position> neueListe = new ArrayList<Position>();
		for (int i = 0; i < liste.getSize(); i++) {
			neueListe.add(liste.getEinzelPositionNr(i));
		}
		return neueListe;
	}

	public List<String> positionToOptions(Positionsliste liste) {
		List<String> options = new ArrayList<String>();
		for (int i = 0; i < liste.getSize(); i++) {
			options.add(liste.getEinzelPositionNr(i).getGut().getBezeichnung());
		}
		return options;
	}

	public void addPosition(Position position) {

		Position vergleichsPosition;
		boolean existiert = false;
		
		for (int i = 0; i < positionen.getSize(); i++) {
			vergleichsPosition = positionen.getEinzelPositionNr(i);
			if (vergleichsPosition.getGut().getId() == position.getGut()
					.getId()) {
				existiert = true;
				vergleichsPosition.setMenge(position.getMenge());
				// Push the data into the widget.
				setRowData(0, positionToList(positionen));
				redraw();
			}
		}
		if(existiert == false) {
			positionen.erfassePosition(position);
			setRowCount(positionen.getSize(), true);
			// Push the data into the widget.
			setRowData(0, positionToList(positionen));
			redraw();
		}

	}
}
