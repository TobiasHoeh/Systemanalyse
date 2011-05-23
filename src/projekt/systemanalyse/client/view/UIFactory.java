package projekt.systemanalyse.client.view;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.EdgedCanvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class UIFactory {

	public static final int BUTTON_WIDTH = 50;
	public static final int BUTTON_HEIGHT = 30;
	public static final String BUTTON_STYLE = "Spiel-Button-Style";
	public static final String LABEL_STYLE = "Spiel-Label-Style";

	public static HLayout getHLayout(String width, String height,
			int membersMargin, Alignment alignment, VerticalAlignment vAlignment) {
		HLayout neuesLayout = new HLayout(membersMargin);
		neuesLayout.setSize(width, height);
		neuesLayout.setAlign(alignment);
		neuesLayout.setAlign(vAlignment);
		return neuesLayout;
	}

	public static HLayout getHLayout(String width, String height) {
		HLayout neuesLayout = new HLayout();
		neuesLayout.setSize(width, height);
		return neuesLayout;
	}

	public static VLayout getVLayout(String width, String height,
			int membersMargin, Alignment alignment, VerticalAlignment vAlignment) {
		VLayout neuesLayout = new VLayout(membersMargin);
		neuesLayout.setSize(width, height);
		neuesLayout.setAlign(alignment);
		neuesLayout.setAlign(vAlignment);
		return neuesLayout;
	}

	public static VLayout getVLayout(String width, String height) {
		VLayout neuesLayout = new VLayout();
		neuesLayout.setSize(width, height);
		return neuesLayout;
	}

	public static Label getLabel(String content, int left, int top, int width,
			int height, String styleName) {
		Label neuesLabel = new Label(content);
		neuesLabel.setRect(left, top, width, height);
		neuesLabel.setStyleName(styleName);
		return neuesLabel;
	}

	public static Label getStyledLabel(String content, int left, int top,
			int width, int height) {
		Label neuesLabel = new Label(content);
		neuesLabel.setRect(left, top, width, height);
		neuesLabel.setStyleName(LABEL_STYLE);
		return neuesLabel;
	}

	public static Label getStyledLabel(String content, int width, int height) {
		Label neuesLabel = new Label(content);
		neuesLabel.setWidth(width);
		neuesLabel.setHeight(height);
		neuesLabel.setStyleName(LABEL_STYLE);
		return neuesLabel;
	}

	public static Label getStyledLabel(String content, String width,
			String height) {
		Label neuesLabel = new Label(content);
		neuesLabel.setWidth(width);
		neuesLabel.setHeight(height);
		neuesLabel.setStyleName(LABEL_STYLE);
		return neuesLabel;
	}

	public static Label getStyledLabel(String content, String width, int height) {
		Label neuesLabel = new Label(content);
		neuesLabel.setWidth(width);
		neuesLabel.setHeight(height);
		neuesLabel.setStyleName(LABEL_STYLE);
		return neuesLabel;
	}

	public static Label getLabel(String content, String styleName) {
		Label neuesLabel = new Label(content);
		neuesLabel.setStyleName(styleName);
		return neuesLabel;
	}

	public static Img getImg(String source, String styleName,
			Alignment alignment, VerticalAlignment vAlignment) {
		Img neuesImg = new Img(source);
		neuesImg.setStyleName(styleName);
		neuesImg.setAlign(alignment);
		return neuesImg;
	}

	public static Img getImg(String source, String styleName) {
		Img neuesImg = new Img(source);
		neuesImg.setStyleName(styleName);
		return neuesImg;
	}

	public static Canvas getCanvas(String width, String height) {
		Canvas neuesCanvas = new Canvas();
		neuesCanvas.setSize(width, height);
		return neuesCanvas;
	}

	public static ImgButton getImgButton(int left, int top, int width,
			int height, String icon) {
		ImgButton neuerImgButton = new ImgButton();
		neuerImgButton.setRect(left, top, width, height);
		neuerImgButton.setIcon(icon);
		return neuerImgButton;
	}

	public static Button getButton(int left, int top, int width, int height,
			String title, String styleName) {
		Button neuerButton = new Button(title);
		neuerButton.setRect(left, top, width, height);
		neuerButton.setStyleName(styleName);
		return neuerButton;
	}
	
	public static Button getButton(String title, String styleName) {
		Button neuerButton = new Button(title);
		neuerButton.setStyleName(styleName);
		return neuerButton;
	}
	
	public static Button getStyledButton(String title) {
		Button neuerButton = new Button(title);
		neuerButton.setStyleName(BUTTON_STYLE);
		return neuerButton;
	}

	public static Button getStyledButton(int left, int top, String title) {
		Button neuerButton = new Button(title);
		neuerButton.setRect(left, top, BUTTON_WIDTH, BUTTON_HEIGHT);
		neuerButton.setStyleName(BUTTON_STYLE);
		return neuerButton;
	}

	public static LayoutSpacer getLayoutSpacer(String width, String height) {
		LayoutSpacer neuerLayoutSpacer = new LayoutSpacer();
		neuerLayoutSpacer.setWidth(width);
		neuerLayoutSpacer.setHeight(height);
		return neuerLayoutSpacer;
	}

	public static TextBox getStyledTextBox(String value, boolean isReadOnly) {
		TextBox neueTextBox = new TextBox();
		if (isReadOnly) {
			neueTextBox.isReadOnly();
			neueTextBox.setEnabled(false);

		}
		neueTextBox.setValue(value);
		neueTextBox.setWidth("80px");
		return neueTextBox;
	}

	public static FlexTable getFlexTable() {
		FlexTable neueFlexTable = new FlexTable();
		return neueFlexTable;
	}

	public static EdgedCanvas getEdgedCanvas(String width, String height) {
		EdgedCanvas neuesCanvas = new EdgedCanvas();
		neuesCanvas.setSize(width, height);
		return neuesCanvas;
	}

	public static ListBox getListBox() {
		ListBox neueListBox = new ListBox();
		neueListBox.setVisibleItemCount(1);
		return neueListBox;
	}

}
