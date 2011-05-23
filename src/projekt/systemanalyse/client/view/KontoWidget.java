package projekt.systemanalyse.client.view;

import java.util.ArrayList;


import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class KontoWidget extends FlexTable {


	private static final int HeaderRowIndex = 0;
	int sollIndex = 1;
	int habenIndex = 1;
	
	public KontoWidget(String header, ArrayList<String> columns, ArrayList<ArrayList<String>> soll, ArrayList<ArrayList<String>> haben) {
		this.insertRow(HeaderRowIndex);
		this.setStyleName("Spiel-Konto");
		this.getRowFormatter().addStyleName(HeaderRowIndex,
				"header");
		
		setWidth("200px");
		setHeight("500px");

//		for (int row = 0; row < rowData.size(); row++) {
//			addRow(rowData.get(row));
//		}
		
		for(int row = 0; row<soll.size();row++) {
			addSoll(soll.get(row));
		}
		
		for(int row = 0; row<haben.size();row++) {
			addHaben(haben.get(row));
		}
		
		for(int i=0; i<columns.size(); i++) {
			addColumn(columns.get(i));
		}
		
		applyDataRowStyles();

		this.setCellSpacing(0);
		this.addStyleName("FlexTable");
	}
	
	public KontoWidget() {
		this.insertRow(HeaderRowIndex);
		this.setStyleName("Spiel-Konto");
		this.getRowFormatter().addStyleName(HeaderRowIndex,
				"header");
		setHeight("300px");
		setWidth("400px");
		
		
		applyDataRowStyles();

		this.setCellSpacing(0);
		this.addStyleName("FlexTable");
	}


	public void addColumn(Object columnHeading) {
		Widget widget = createCellWidget(columnHeading);
		int cell = this.getCellCount(HeaderRowIndex);

		widget.setWidth("40px");
		widget.addStyleName("FlexTable-ColumnLabel");

		this.setWidget(HeaderRowIndex, cell, widget);

		this.getCellFormatter().addStyleName(HeaderRowIndex, cell,
				"FlexTable-ColumnLabelCell");
	}
	
	public void addSoll(ArrayList<String> cellObjects) {

		for (int cell = 0; cell < cellObjects.size(); cell++) {
			Widget widget = createCellWidget(cellObjects.get(cell));
			this.setWidget(sollIndex, cell, widget);
			this.getCellFormatter().addStyleName(sollIndex, cell,
					"FlexTable-Cell");
		}
		sollIndex++;
	}
	
	public void addHaben(ArrayList<String> cellObjects) {

		for (int cell = 0; cell < cellObjects.size(); cell++) {
			Widget widget = createCellWidget(cellObjects.get(cell));
			this.setWidget(habenIndex, cell+2, widget);
			this.getCellFormatter().addStyleName(habenIndex, cell+2,
					"FlexTable-Cell");
		}
		habenIndex++;
	}
	
	public void auffuellen() {
		do {
			Widget widget = createCellWidget("");
			this.setWidget(habenIndex, 2, widget);
			this.getCellFormatter().addStyleName(habenIndex, 2,
					"FlexTable-FillIn");
			widget = createCellWidget("");
			this.setWidget(habenIndex, 3, widget);
			this.getCellFormatter().addStyleName(habenIndex, 3,
					"FlexTable-FillIn");
			habenIndex++;
		} while (habenIndex < sollIndex);
	}
	
	public void addSumme(ArrayList<String> cellObjects) {
		habenIndex++;
		for (int cell = 0; cell < cellObjects.size(); cell++) {
			Widget widget = createCellWidget(cellObjects.get(cell));
			this.setWidget(habenIndex, cell, widget);
			this.getCellFormatter().addStyleName(habenIndex, cell,
					"FlexTable-Sum");
		}
	}
	
	private Widget createCellWidget(Object cellObject) {
		Widget widget = null;

		if (cellObject instanceof Widget)
			widget = (Widget) cellObject;
		else {
			widget = UIFactory.getStyledLabel((String)cellObject, 50, 10);
//			widget = new Label((String) cellObject);
//			widget.setHeight("20px");
		}
		return widget;
	}

	int rowIndex = 1;

	private void addRow(ArrayList<String> cellObjects) {

		for (int cell = 0; cell < cellObjects.size(); cell++) {
			Widget widget = createCellWidget(cellObjects.get(cell));
			this.setWidget(rowIndex, cell, widget);
			this.getCellFormatter().addStyleName(rowIndex, cell,
					"FlexTable-Cell");
		}
		rowIndex++;
	}

	public void applyDataRowStyles() {
		HTMLTable.RowFormatter rf = this.getRowFormatter();

		for (int row = 1; row < this.getRowCount(); ++row) {
			if ((row % 2) != 0) {
				rf.addStyleName(row, "FlexTable-OddRow");
			} else {
				rf.addStyleName(row, "FlexTable-EvenRow");
			}
		}
	}
}
