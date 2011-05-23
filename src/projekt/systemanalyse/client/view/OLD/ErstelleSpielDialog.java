//package projekt.systemanalyse.client.view.OLD;
//
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.AbsolutePanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.ValueListBox;
//import com.google.gwt.text.client.IntegerRenderer;
//import com.google.gwt.user.client.ui.IntegerBox;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.Button;
//
//public class ErstelleSpielDialog extends DialogBox {
//
//	public ErstelleSpielDialog() {
//		setHTML("Neues Spiel erstellen");
//		setSize("423px", "288px");
//		setGlassEnabled(true);
//		
//		AbsolutePanel absolutePanel = new AbsolutePanel();
//		setWidget(absolutePanel);
//		absolutePanel.setSize("400px", "234px");
//		
//		Label lblSpielname = new Label("Spielname:");
//		absolutePanel.add(lblSpielname, 10, 27);
//		lblSpielname.setSize("111px", "28px");
//		
//		Label lblAnzahlSpieler = new Label("Anzahl Spieler:");
//		absolutePanel.add(lblAnzahlSpieler, 10, 71);
//		lblAnzahlSpieler.setSize("111px", "23px");
//		
//		ValueListBox valueListBox = new ValueListBox(IntegerRenderer.instance());
//		absolutePanel.add(valueListBox, 127, 118);
//		valueListBox.setSize("126px", "18px");
//		
//		Label lblSiegbedingung = new Label("Siegbedingung:");
//		absolutePanel.add(lblSiegbedingung, 10, 118);
//		lblSiegbedingung.setSize("111px", "22px");
//		
//		IntegerBox integerBox = new IntegerBox();
//		absolutePanel.add(integerBox, 123, 72);
//		integerBox.setSize("143px", "16px");
//		
//		TextBox textBox = new TextBox();
//		absolutePanel.add(textBox, 123, 27);
//		textBox.setSize("139px", "16px");
//		
//		Button btnAbbrechen = new Button("Abbrechen");
//		btnAbbrechen.setText("Abbrechen");
//		absolutePanel.add(btnAbbrechen, 168, 195);
//		btnAbbrechen.setSize("85px", "24px");
//		
//		Button btnSpielStarten = new Button("Spiel starten");
//		absolutePanel.add(btnSpielStarten, 259, 195);
//		btnSpielStarten.setSize("131px", "24px");
//	}
//}
