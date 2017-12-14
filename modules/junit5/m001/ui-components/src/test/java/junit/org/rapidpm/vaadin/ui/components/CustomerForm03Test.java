package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.FormLayoutElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 *
 */

public class CustomerForm03Test extends CustomerFormPageObject {

  @Test
  public void test001() {
    getDriver().get(url);
    Assert.assertTrue(deleteButton().isDisplayed());
    deleteEntry();
    clickSwitchButton();
    Assert.assertTrue($(FormLayoutElement.class).$(ButtonElement.class).caption("Delete").all().isEmpty());
    saveEntry();
    clickSwitchButton();
    Assert.assertFalse($(FormLayoutElement.class).$(ButtonElement.class).caption("Delete").all().isEmpty());
  }
}