package org.rapidpm.vaadin.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.rapidpm.vaadin.srv.Customer;
import org.rapidpm.vaadin.srv.CustomerService;

import javax.servlet.annotation.WebServlet;
import java.util.List;

public class MyUI extends UI {

  private final Grid<Customer>  grid       = new Grid<>();
  private final TextField       filterText = new TextField();
  private final CustomerForm    form       = new CustomerForm(this);
  private       CustomerService service    = CustomerService.getInstance();

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    final VerticalLayout layout = new VerticalLayout();

    filterText.setPlaceholder("filter by name...");
    filterText.addValueChangeListener(e -> updateList());
    filterText.setValueChangeMode(ValueChangeMode.LAZY);

    Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
    clearFilterTextBtn.setDescription("Clear the current filter");
    clearFilterTextBtn.addClickListener(e -> filterText.clear());

    CssLayout filtering = new CssLayout();
    filtering.addComponents(filterText, clearFilterTextBtn);
    filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

    Button addCustomerBtn = new Button("Add new customer");
    addCustomerBtn.addClickListener(e -> {
      grid.asSingleSelect().clear();
      form.setCustomer(new Customer());
    });

    HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);

    grid.addColumn(Customer::getFirstName).setCaption("First Name");
    grid.addColumn(Customer::getLastName).setCaption("Last Name");
    grid.addColumn(Customer::getEmail).setCaption("Email");

    HorizontalLayout main = new HorizontalLayout(grid, form);
    main.setSizeFull();
    grid.setSizeFull();
    main.setExpandRatio(grid, 1);

    layout.addComponents(toolbar, main);

    // fetch list of Customers from service and assign it to Grid
    updateList();

    setContent(layout);

    form.setVisible(false);

    grid.asSingleSelect().addValueChangeListener(event -> {
      if (event.getValue() == null) {
        form.setVisible(false);
      } else {
        form.setCustomer(event.getValue());
      }
    });
  }

  public void updateList() {
    List<Customer> customers = service.findAll(filterText.getValue());
    grid.setItems(customers);
  }

  @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
  @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
  public static class MyUIServlet extends VaadinServlet {
  }
}
