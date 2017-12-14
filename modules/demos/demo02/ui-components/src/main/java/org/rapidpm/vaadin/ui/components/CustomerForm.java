package org.rapidpm.vaadin.ui.components;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.vaadin.ui.*;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.vaadin.shared.Customer;
import org.rapidpm.vaadin.shared.CustomerStatus;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends Composite implements HasLogger {

  private final TextField firstName = new TextField("First name");
  private final TextField lastName = new TextField("Last name");
  private final TextField email = new TextField("Email");
  private final NativeSelect<CustomerStatus> status = new NativeSelect<>("Status");
  private final DateField birthday = new DateField("Birthday");
  private final Button save = new Button("Save");
  private final Button delete = new Button("Delete");

  private final Binder<Customer> beanBinder = new Binder<>(Customer.class);

  private Customer customer;

  private final Set<UpdateEvent> saveListeners = ConcurrentHashMap.newKeySet();
  private final Set<UpdateEvent> deleteListeners = ConcurrentHashMap.newKeySet();


  public CustomerForm() {
    final FormLayout layout = new FormLayout();
    final HorizontalLayout buttons = new HorizontalLayout(save , delete);
    layout.addComponents(firstName , lastName , email , status , birthday , buttons);

    status.setItems(CustomerStatus.values());
    save.setStyleName(ValoTheme.BUTTON_PRIMARY);
    save.setClickShortcut(KeyCode.ENTER);

    beanBinder.bindInstanceFields(this);

    save.addClickListener(e -> this.save());
    delete.addClickListener(e -> this.delete());

    setCompositionRoot(layout);
    setSizeUndefined();
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
    beanBinder.setBean(customer);

    // Show delete button for only customers already in the database
    delete.setVisible(customer.isPersisted());
    setVisible(true);
    firstName.selectAll();
  }

  private void delete() {
    setVisible(false);
    deleteListeners.forEach(listener -> listener.update(customer));
  }

  private void save() {
    setVisible(false);
    saveListeners.forEach(listener -> listener.update(customer));
  }

  public Registration registerSaveListener(UpdateEvent updateEvent) {
    saveListeners.add(updateEvent);
    return () -> saveListeners.remove(updateEvent);
  }

  public Registration registerDeleteListener(UpdateEvent updateEvent) {
    deleteListeners.add(updateEvent);
    return () -> deleteListeners.remove(updateEvent);
  }


  public static interface UpdateEvent {
    public void update(Customer customer);
  }

  @FunctionalInterface
  public interface Registration extends Serializable {
    boolean remove();
  }

}
