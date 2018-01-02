package junit.org.rapidpm.vaadin.ui.app;

import com.vaadin.testbench.elements.GridElement;
import org.junit.Assert;
import org.junit.jupiter.api.TestTemplate;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.seleniumhub.VaadinCompatTest;
import org.rapidpm.vaadin.srv.CustomerServiceImpl;

/**
 *
 */
@VaadinCompatTest
public class AddressBook06Test {

  @TestTemplate
  public void test001(AddressBookPageObject pageObject) throws Exception {
    final int firstCount = CustomerServiceImpl.getInstance().findAll().size();
    pageObject.loadPage();

    final CustomerFormPageObject newEntry = pageObject.createNewEntry();
    newEntry.setLastName("X");
    newEntry.setFirstName("Y");
    newEntry.saveEntry();
    final GridElement gridElement = pageObject.dataGrid();
    final long        rowCount    = gridElement.getRowCount();
    Assert.assertEquals(rowCount, firstCount + 1);

  }
}
