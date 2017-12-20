package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.ui.themes.ValoTheme;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinUnitTest;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.PageObject;

/**
 *
 */
@VaadinUnitTest
public class CustomerForm06Test {

  @Test
  public void test001(@PageObject CustomerFormPageObject pageObject) {
    pageObject.loadPage();
    Assert.assertTrue(pageObject.saveButton()
                          .getClassNames()
                          .contains("v-button-" + ValoTheme.BUTTON_PRIMARY));
  }
}