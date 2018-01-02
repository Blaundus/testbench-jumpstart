package junit.org.rapidpm.vaadin.ui.app;

import org.junit.Assert;
import org.junit.jupiter.api.TestTemplate;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.seleniumhub.VaadinCompatTest;

/**
 *
 */
@VaadinCompatTest
public class AddressBook04Test {

  @TestTemplate
  public void test001(AddressBookPageObject pageObject) {
    pageObject.loadPage();

    final CustomerFormPageObject customerFormPageObject = pageObject.selectEntryAtIndex(1);

    final CustomerFormPageObject newEntry = pageObject.createNewEntry();

    Assert.assertFalse(pageObject.dataGrid().isSelected());

  }


}
