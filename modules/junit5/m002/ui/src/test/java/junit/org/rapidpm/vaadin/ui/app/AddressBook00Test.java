package junit.org.rapidpm.vaadin.ui.app;


import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.FormLayoutElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.seleniumhub.VaadinCompatTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@VaadinCompatTest
public class AddressBook00Test {


  @TestTemplate
  public void test001(AddressBookPageObject pageObject) {
    pageObject.loadPage();
    pageObject.clearFilterBTN().showTooltip();
    WebElement ttip = pageObject.findElement(By.className("v-tooltip"));
    assertEquals(ttip.getText(), "Clear the current filter");

  }

  @TestTemplate
  public void test002(AddressBookPageObject pageObject) {
    pageObject.loadPage();
    final String placeholder = pageObject.filterTextField().getAttribute("placeholder");
    assertEquals(placeholder, "filter by name...");

  }


  @TestTemplate
  public void test003(AddressBookPageObject pageObject) {
    pageObject.loadPage();
    final List<FormLayoutElement> allA = pageObject.$(FormLayoutElement.class).recursive(true).all();
    Assert.assertEquals(0, allA.size());
    final CustomerFormPageObject  customerFormPageObject = pageObject.selectEntryAtIndex(1);
    final List<FormLayoutElement> allB                   = pageObject.$(FormLayoutElement.class).recursive(true).all();
    Assert.assertEquals(1, allB.size());
    customerFormPageObject.saveEntry();
    final List<FormLayoutElement> allC = pageObject.$(FormLayoutElement.class).recursive(true).all();
    Assert.assertEquals(0, allC.size());
  }


}
