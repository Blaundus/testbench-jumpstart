package junit.org.rapidpm.vaadin.ui.components;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinUnitTest;

/**
 *
 */
@VaadinUnitTest
public class CustomerForm01Test {

  public static final String LAST_NAME_ONE = "AAAA";
  public static final String LAST_NAME_TWO = "BBBB";

  @Test
  public void test001(CustomerFormPageObject pageObject) {
    pageObject.loadPage();

    pageObject.setLastName(LAST_NAME_ONE);
    pageObject.saveEntry();
    Assert.assertEquals(LAST_NAME_ONE, pageObject.textField().id(TestUI.LAST_NAME).getValue());
    pageObject.clickSwitchButton();
    Assert.assertEquals(LAST_NAME_ONE, pageObject.getLastName());
    pageObject.setLastName(LAST_NAME_TWO);
    pageObject.clickRegisterButton(); //Registrations off
    pageObject.saveEntry();
    Assert.assertEquals(LAST_NAME_ONE, pageObject.textField().id(TestUI.LAST_NAME).getValue());
    pageObject.clickSwitchButton();
    Assert.assertEquals(LAST_NAME_TWO, pageObject.getLastName());
//    setLastName(LAST_NAME_TWO); is from last time -> statefull component
    pageObject.clickRegisterButton();
    pageObject.saveEntry();
    Assert.assertEquals(LAST_NAME_TWO, pageObject.textField().id(TestUI.LAST_NAME).getValue());


  }

  @Test
  public void test002(CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    pageObject.saveEntry();

    Assert.assertEquals("2", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.deleteEntry();
    Assert.assertEquals("-1", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.saveEntry();
    Assert.assertEquals("0", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.clickRegisterButton(); // registrations off
    pageObject.deleteEntry();
    Assert.assertEquals("0", pageObject.textField().id(TestUI.ID).getValue());
    pageObject.clickSwitchButton();
    pageObject.clickRegisterButton(); // registrations on
    pageObject.deleteEntry();
    Assert.assertEquals("-1", pageObject.textField().id(TestUI.ID).getValue());

  }
}