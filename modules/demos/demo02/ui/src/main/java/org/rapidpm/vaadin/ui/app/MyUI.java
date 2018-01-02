package org.rapidpm.vaadin.ui.app;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.rapidpm.frp.functions.CheckedExecutor;
import org.rapidpm.vaadin.shared.Customer;
import org.rapidpm.vaadin.srv.CustomerService;
import org.rapidpm.vaadin.srv.CustomerServiceImpl;
import org.rapidpm.vaadin.ui.components.CustomerForm;

import javax.servlet.annotation.WebServlet;
import java.util.List;

public class MyUI extends UI {

  public static final String          FILTER_TF          = "filterTF";
  public static final String          DATA_GRID          = "dataGrid";
  public static final String          CLEAR_FILTER_BTN   = "clearFilterBTN";
  private final       Grid<Customer>  grid               = new Grid<>();
  private final       TextField       filterText         = new TextField();
  private final       Button          clearFilterTextBtn = new Button(FontAwesome.TIMES);
  private final       CustomerForm    customerForm       = new CustomerForm();
  private             CustomerService service            = CustomerServiceImpl.getInstance();
  private CustomerForm.Registration deleteRegistration;
  private CustomerForm.Registration saveRegistration;

  @Override
  protected void init(VaadinRequest vaadinRequest) {

    filterText.setId(FILTER_TF);
    grid.setId(DATA_GRID);
    clearFilterTextBtn.setId(CLEAR_FILTER_BTN);

    final VerticalLayout layout = new VerticalLayout();

    filterText.setPlaceholder("filter by name...");
    filterText.addValueChangeListener(e -> updateList());
    filterText.setValueChangeMode(ValueChangeMode.LAZY);

    clearFilterTextBtn.setDescription("Clear the current filter");
    clearFilterTextBtn.addClickListener(e -> filterText.clear());

    CssLayout filtering = new CssLayout();
    filtering.addComponents(filterText, clearFilterTextBtn);
    filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

    Button addCustomerBtn = new Button("Add new customer");
    addCustomerBtn.addClickListener(e -> {
      grid.asSingleSelect().clear();
      customerForm.setCustomer(new Customer());
    });

    HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);

    grid.addColumn(Customer::getFirstName).setCaption("First Name");
    grid.addColumn(Customer::getLastName).setCaption("Last Name");
    grid.addColumn(Customer::getEmail).setCaption("Email");

    HorizontalLayout main = new HorizontalLayout(grid, customerForm);
    main.setSizeFull();
    grid.setSizeFull();
    main.setExpandRatio(grid, 1);

    layout.addComponents(toolbar, main);

    // fetch list of Customers from service and assign it to Grid
    updateList();

    setContent(layout);

    customerForm.setVisible(false);

    grid.asSingleSelect().addValueChangeListener(event -> {
      if (event.getValue() == null) {
        customerForm.setVisible(false);
      } else {
        customerForm.setCustomer(event.getValue());
      }
    });

    deleteRegistration = customerForm.registerDeleteListener(customer -> {
      service.delete(customer);
      updateList();
    });
    saveRegistration = customerForm.registerSaveListener(customer -> {
      service.save(customer);
      updateList();
    });
  }

  @Override
  public void detach() {
    super.detach();
    ((CheckedExecutor) () -> deleteRegistration.remove()).execute();
    ((CheckedExecutor) () -> saveRegistration.remove()).execute();
  }

  private void updateList() {
    List<Customer> customers = service.findAll(filterText.getValue());
    grid.setItems(customers);
  }

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
  @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {
  }
}
