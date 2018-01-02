package junit.org.rapidpm.vaadin.ui.app;

import org.junit.Assert;
import org.junit.jupiter.api.TestTemplate;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.seleniumhub.VaadinCompatTest;
import org.rapidpm.vaadin.srv.CustomerServiceImpl;

/**
 *
 */
@VaadinCompatTest
public class AddressBook03Test {

  @TestTemplate
  public void test001(AddressBookPageObject pageObject) {

    //not thread save -> reload data for test
    ((CustomerServiceImpl) CustomerServiceImpl.getInstance()).resetData();

    pageObject.loadPage();
    //filter
    pageObject.filterTextField().setValue("Lara");
    Assert.assertEquals(1L, pageObject.dataGrid().getRowCount());

    pageObject.selectEntryAtIndex(0).deleteEntry();
    Assert.assertEquals(0L, pageObject.dataGrid().getRowCount());
  }

}
