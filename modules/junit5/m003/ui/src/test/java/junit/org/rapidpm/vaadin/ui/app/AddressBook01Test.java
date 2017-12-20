package junit.org.rapidpm.vaadin.ui.app;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.seleniumhub.VaadinCompatTest;

/**
 *
 */
@VaadinCompatTest
public class AddressBook01Test {

  public static final String FIRST_NAME = "Alfred";
  public static final String LAST_NAME = "Mueller";

  @TestTemplate
  public void test001(AddressBookPageObject pageObject) {
    pageObject.loadPage();

    final CustomerFormPageObject newEntry = pageObject.createNewEntry();
    newEntry.setFirstName(FIRST_NAME);
    newEntry.setLastName(LAST_NAME);
    newEntry.saveEntry();

    pageObject.dataGrid().getCell(6 , 0).click();
    Assert.assertNotEquals(FIRST_NAME , pageObject.activeCustomerForm().getFirstName());
    Assert.assertNotEquals(LAST_NAME , pageObject.activeCustomerForm().getLastName());

    pageObject.dataGrid().getCell(0 , 0).click();

    Assert.assertFalse(pageObject.dataGrid().isSelected());

    Assert.assertEquals(FIRST_NAME , pageObject.dataGrid().getCell(0 , 0).getText());
    Assert.assertEquals(LAST_NAME , pageObject.dataGrid().getCell(0 , 1).getText());
    Assert.assertEquals(FIRST_NAME , pageObject.activeCustomerForm().getFirstName());
    Assert.assertEquals(LAST_NAME , pageObject.activeCustomerForm().getLastName());
  }


}
