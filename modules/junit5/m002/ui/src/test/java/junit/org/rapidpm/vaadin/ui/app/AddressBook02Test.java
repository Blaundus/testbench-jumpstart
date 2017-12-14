package junit.org.rapidpm.vaadin.ui.app;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.seleniumhub.VaadinCompatTest;

/**
 *
 */
@VaadinCompatTest
public class AddressBook02Test  {

  @TestTemplate
  public void test001(AddressBookPageObject pageObject) {
    pageObject.loadPage();

    //filter
    pageObject.filterTextField().setValue("Lara");

    Assert.assertEquals("Lara" , pageObject.getFirstNameAtIndex(0));
    Assert.assertEquals("Martin" , pageObject.getLastNameAtIndex(0));

    Assert.assertEquals(1L , pageObject.dataGrid().getRowCount());

    pageObject.clearFilterBTN().click();

    Assert.assertTrue(pageObject.dataGrid().getRowCount() > 1);
  }

}
