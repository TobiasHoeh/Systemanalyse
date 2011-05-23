package projekt.systemanalyse.client.presenter;

import projekt.systemanalyse.client.SpielServiceAsync;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class HeaderPresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		// Header
		Label getLblSpielerName();

		Label getLblFirmenName();

		Label getLblRundenNr();

		PushButton getBtnBeenden();

		PushButton getBtnHilfe();
	}

	private final SpielServiceAsync rpcService;
	private final SimpleEventBus eventBus;
	private final Display display;

	public HeaderPresenter(SpielServiceAsync rpcService,
			SimpleEventBus eventBus, Display view, int spielerID) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
//		getSpielerName(spielerID);
//		getFirma(spielerID);
//		getRunde();
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	public void bind() {

	}

	public Widget getDisplay() {
		return display.asWidget();
	}

//	public void getSpielerName(int spielerID) {
//		rpcService.getSpielerName(spielerID, new AsyncCallback<String>() {
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when fetching the Name.");
//			}
//
//			@Override
//			public void onSuccess(String result) {
//				display.getLblSpielerName().setText(result);
//			}
//		});
//	}
//
//	public void getFirma(int spielerID) {
//		rpcService.getFirma(spielerID, new AsyncCallback<String>() {
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when fetching the FirmenName.");
//			}
//
//			@Override
//			public void onSuccess(String result) {
//				display.getLblFirmenName().setText(result);
//			}
//		});
//	}
//
//	public void getRunde() {
//		rpcService.getRundenNr(new AsyncCallback<Integer>() {
//			public void onFailure(Throwable caught) {
//				Window.alert("Error when fetching the Runde.");
//			}
//
//			@Override
//			public void onSuccess(Integer result) {
//				display.getLblRundenNr().setText(Integer.toString(result));
//
//			}
//		});
//	}
}
