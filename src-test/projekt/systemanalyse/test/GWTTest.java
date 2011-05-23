package projekt.systemanalyse.test;

import com.google.gwt.junit.client.GWTTestCase;

public class GWTTest extends GWTTestCase {

	public String getModuleName() {
		return "";
	}
	// private ContactsPresenter contactsPresenter;
	// private ContactsServiceAsync rpcService;
	// private HandlerManager eventBus;
	// private ContactsPresenter.Display display;
	//
	// public String getModuleName() {
	// return "com.google.gwt.sample.contacts.Contacts";
	// }
	//
	// public void gwtSetUp() {
	// rpcService = GWT.create(ContactsService.class);
	// eventBus = new HandlerManager(null);
	// display = new ContactsView();
	// contactsPresenter = new ContactsPresenter(rpcService, eventBus, display);
	// }
	//
	// public void testContactSort(){
	// ArrayList<ContactDetails> contactDetails = new
	// ArrayList<ContactDetails>();
	// contactDetails.add(new ContactDetails("0", "c_contact"));
	// contactDetails.add(new ContactDetails("1", "b_contact"));
	// contactDetails.add(new ContactDetails("2", "a_contact"));
	// contactsPresenter.setContactDetails(contactDetails);
	// contactsPresenter.sortContactDetails();
	// assertTrue(contactsPresenter.getContactDetail(0).getDisplayName().equals("a_contact"));
	// assertTrue(contactsPresenter.getContactDetail(1).getDisplayName().equals("b_contact"));
	// assertTrue(contactsPresenter.getContactDetail(2).getDisplayName().equals("c_contact"));
	// }
}