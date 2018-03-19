package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elements.FormLayoutElement;
import com.vaadin.testbench.elements.TextFieldElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinUnitTest;

/**
 *
 */
@VaadinUnitTest
public class CustomerForm02Test {

  @Test
  public void test001(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    final FormLayoutElement customerForm = pageObject.$(FormLayoutElement.class).id(TestUI.CUSTOMER_FORM);
    Assert.assertTrue(customerForm.isDisplayed());
    pageObject.deleteEntry();
    Assert.assertEquals(0, pageObject.getDriver().findElements(new By.ById(TestUI.CUSTOMER_FORM)).size());

    final String id = pageObject.$(TextFieldElement.class).id(TestUI.ID).getValue();
    Assert.assertEquals("-1", id);
  }


  @Test
  public void test002(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    final FormLayoutElement customerForm = pageObject.$(FormLayoutElement.class).id(TestUI.CUSTOMER_FORM);
    Assert.assertTrue(customerForm.isDisplayed());
    pageObject.saveEntry();
    Assert.assertEquals(0, pageObject.getDriver().findElements(new By.ById(TestUI.CUSTOMER_FORM)).size());

    final String id = pageObject.$(TextFieldElement.class).id(TestUI.ID).getValue();
    Assert.assertEquals("2", id);
  }


}