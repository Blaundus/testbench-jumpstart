package junit.org.rapidpm.vaadin.ui.components;

import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinUnitTest;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.PageObject;

/**
 *
 */
@VaadinUnitTest
public class CustomerForm07Test {

  @Test
  public void test001(@PageObject CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    pageObject.clickRegisterButton();



  }
}