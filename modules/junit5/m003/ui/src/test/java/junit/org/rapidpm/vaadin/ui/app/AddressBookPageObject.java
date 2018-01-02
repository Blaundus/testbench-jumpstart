package junit.org.rapidpm.vaadin.ui.app;

import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.TextFieldElement;
import org.openqa.selenium.WebDriver;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.AbstractVaadinPageObject;

import static org.rapidpm.vaadin.ui.app.MyUI.*;

public class AddressBookPageObject extends AbstractVaadinPageObject {

  public AddressBookPageObject(WebDriver webDriver) {
    super(webDriver);
  }


  public String getLastNameAtIndex(int index) {
    return dataGrid()
        .getCell(index, 1) //TODO reorder problem
        .getText();
  }

  public String getFirstNameAtIndex(int index) {
    return dataGrid()
        .getCell(index, 0) //TODO reorder problem
        .getText();
  }

  public CustomerFormPageObject selectEntryAtIndex(int index) {
    dataGrid()
        .getCell(index, 0) //TODO reorder problem
        .click();
    return new CustomerFormPageObject(getDriver());
  }

  public CustomerFormPageObject createNewEntry() {
    newCustomerBTN().click();
    return new CustomerFormPageObject(getDriver());
  }

  public CustomerFormPageObject activeCustomerForm() {
    return new CustomerFormPageObject(getDriver());
  }


  public TextFieldElement filterTextField() {
    return textField().id(FILTER_TF_ID);
  }

  public GridElement dataGrid() {
    return grid().id(DATA_GRID_ID);
  }

  public ButtonElement clearFilterBTN() {
    return btn().id(CLEAR_FILTER_BTN_ID);
  }

  public ButtonElement newCustomerBTN() {
    return btn().id(NEW_CUSTOMER_BTN_ID);
  }

}
