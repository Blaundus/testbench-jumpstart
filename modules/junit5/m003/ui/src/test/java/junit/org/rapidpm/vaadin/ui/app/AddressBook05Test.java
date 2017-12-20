package junit.org.rapidpm.vaadin.ui.app;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.seleniumhub.VaadinCompatTest;
import org.rapidpm.vaadin.srv.CustomerServiceImpl;

/**
 *
 */
@VaadinCompatTest
public class AddressBook05Test {



  @TestTemplate
  public void test001(AddressBookPageObject pageObject) throws Exception {
    pageObject.loadPage();
    Assert.assertEquals(pageObject.dataGrid().getRowCount() , CustomerServiceImpl.getInstance().findAll().size());

  }


}
