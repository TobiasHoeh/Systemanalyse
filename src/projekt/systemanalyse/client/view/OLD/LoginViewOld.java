//package projekt.systemanalyse.client.view.OLD;
//
////import com.google.gwt.user.client.ui.Widget;
//import com.google.gwt.user.client.ui.HTMLPanel;
//import com.google.gwt.user.client.ui.PasswordTextBox;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.Widget;
//import com.smartgwt.client.types.Alignment;
//import com.smartgwt.client.widgets.events.HasClickHandlers;
//import com.smartgwt.client.widgets.layout.Layout;
//import com.smartgwt.client.widgets.layout.VLayout;
//
//import projekt.systemanalyse.client.presenter.OLD.LoginPresenter;
//
//import com.smartgwt.client.widgets.Button;
//import com.smartgwt.client.widgets.Window;
//import com.smartgwt.client.widgets.tile.TileLayout;
//import com.google.gwt.user.client.ui.AbsolutePanel;
//
//
//public class LoginViewOld extends Window implements LoginPresenter.Display {
//	private static String html = "<div>\n"
//			+ "<table align=\"center\">\n"
//			+ "  <tr>\n"
//			+ "<td> </td>\n"
//			+ "<td> </td>\n"
//			+ "</tr>\n"
//			+ "  <tr>\n"
//			+ "<td> </td>\n"
//			+ "<td> </td>\n"
//			+ "</tr>\n"
//			+ "  <tr>\n"
//			+ "<td> </td>\n"
//			+ "<td> </td>\n"
//			+ "</tr>\n"
//			+ "  <tr>\n"
//			+ "    <td colspan=\"2\" style=\"font-weight:bold;\">Sign In</td>\n"
//			+ "  </tr>\n"
//			+ "  <tr>\n"
//			+ "    <td>User name</td>\n"
//			+ "    <td id=\"userNameFieldContainer\"></td>\n"
//			+ "  </tr>\n"
//			+ "  <tr>\n"
//			+ "    <td>Password</td>\n"
//			+ "    <td id=\"passwordFieldContainer\"></td>\n"
//			+ "  </tr>\n"
//			+ "  <tr>\n"
//			+ "    <td id=\"signInButtonContainer\"></td>\n"
//			+ "    <td id=\"registerButtonContainer\"></td>\n"
//			+ "  </tr>\n"
//			+ "  <tr>\n"
//			+ "<td> </td>\n"
//			+ "<td> </td>\n"
//			+ "</tr>\n"
//			+ "  <tr>\n"
//			+ "    <td colspan=\"2\">Forget your password?</td>\n"
//			+ "  </tr>\n"
//			+ "  <tr>\n"
//			+ "    <td colspan=\"2\">Contact your Serendipity administrator.</td>\n"
//			+ "  </tr>\n" + "</table>\n" + "</div>\n";
//
//	HTMLPanel panel = new HTMLPanel(html);
//
//	private final TextBox userNameField;
//	private final PasswordTextBox passwordField;
//	private final Button signInButton;
//	private final Button registerButton;
//
//	public LoginViewOld() {
//		userNameField = new TextBox();
//		passwordField = new PasswordTextBox();
//		signInButton = new Button("Sign in");
//		registerButton = new Button("Register");
//
//		userNameField.setText("administrator");
//
//		panel.add(userNameField, "userNameFieldContainer");
//		panel.add(passwordField, "passwordFieldContainer");
//		panel.add(signInButton, "signInButtonContainer");
//		panel.add(registerButton, "registerButtonContainer");
//		addMember(panel);
//	}
//
//	public HasClickHandlers getStartButton() {
//		return signInButton;
//	}
//
//	public Widget asWidget() {
//		return panel;
//	}
//}
