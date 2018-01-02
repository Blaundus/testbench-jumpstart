package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.FormLayoutElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinUnitTest;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.PageObject;

import java.util.List;

/**
 *
 */
@VaadinUnitTest
public class CustomerForm05Test {

  @Test
  public void test001(@PageObject CustomerFormPageObject pageObject) {
    pageObject.loadPage();

    final List<WebElement> elements = pageObject.getDriver().findElements(new By.ById(TestUI.CUSTOMER_FORM));
    Assert.assertEquals(1, elements.size());

    // test save Shortcut
    pageObject.firstnameTF().sendKeys(Keys.chord(Keys.BACK_SPACE));
    pageObject.getDriver().findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.ENTER));
    Assert.assertEquals(0, pageObject.getDriver().findElements(new By.ById(TestUI.CUSTOMER_FORM)).size());

    pageObject.clickSwitchButton();
    Assert.assertFalse(pageObject.$(FormLayoutElement.class).$(ButtonElement.class).caption("Delete").all().isEmpty());
    Assert.assertTrue(pageObject.getFirstName().isEmpty());

  }
}