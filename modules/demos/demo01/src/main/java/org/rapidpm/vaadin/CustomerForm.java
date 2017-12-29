package org.rapidpm.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.rapidpm.dependencies.core.logger.HasLogger;

public class CustomerForm extends FormLayout implements HasLogger {

  private final TextField                    firstName = new TextField("First name");
  private final TextField                    lastName  = new TextField("Last name");
  private final TextField                    email     = new TextField("Email");
  private final NativeSelect<CustomerStatus> status    = new NativeSelect<>("Status");
  private final DateField                    birthdate = new DateField("Birthday");
  private final Button                       save      = new Button("Save");
  private final Button                       delete    = new Button("Delete");

  private final MyUI myUI;
  private final Binder<Customer> beanBinder = new Binder<>(Customer.class);

  private CustomerService service = CustomerService.getInstance();
  private Customer customer;


  public CustomerForm(MyUI myUI) {
    this.myUI = myUI;

    setSizeUndefined();
    HorizontalLayout buttons = new HorizontalLayout(save, delete);
    addComponents(firstName, lastName, email, status, birthdate, buttons);

    status.setItems(CustomerStatus.values());
    save.setStyleName(ValoTheme.BUTTON_PRIMARY);
    save.setClickShortcut(KeyCode.ENTER);

    beanBinder.bindInstanceFields(this);

    save.addClickListener(e -> this.save());
    delete.addClickListener(e -> this.delete());
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
    beanBinder.setBean(customer);

    // Show delete button for only customers already in the database
    boolean visible = (customer != null) && customer.isPersisted();
    delete.setVisible(visible);
    setVisible(true);
    firstName.selectAll();
  }

  private void delete() {
    service.delete(customer);
    myUI.updateList();
    setVisible(false);
  }

  private void save() {
    service.save(customer);
    myUI.updateList();
    setVisible(false);
  }
}
