package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.FormLayoutElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinUnitTest;

/**
 *
 */
@VaadinUnitTest
public class CustomerForm03Test {

  @Test
  public void test001(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    Assert.assertTrue(pageObject.deleteButton().isDisplayed());
    pageObject.deleteEntry();
    pageObject.clickSwitchButton();
    Assert.assertTrue(pageObject.$(FormLayoutElement.class).$(ButtonElement.class).caption("Delete").all().isEmpty());
    pageObject.saveEntry();
    pageObject.clickSwitchButton();
    Assert.assertFalse(pageObject.$(FormLayoutElement.class).$(ButtonElement.class).caption("Delete").all().isEmpty());
  }
}