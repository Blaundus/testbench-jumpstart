package org.rapidpm.vaadin.srv;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class PropertyServiceInMemory implements PropertyService {
  private final Map<String, String> storage = new HashMap<>();

  @Override
  public String resolve(final String key) {
    return storage.get(key);
  }

  @Override
  public boolean hasKey(final String key) {
    return storage.containsKey(key);
  }

  @PostConstruct
  public void init() {


    storage.put("admin", "admin");

    storage.put("generic.ok", "Ok");
    storage.put("generic.cancel", "Cancel");

    storage.put("login.name", "Login"); // i18n
    storage.put("login.info", "Please enter your username and password"); // i18n
    storage.put("login.username", "username"); // i18n
    storage.put("login.password", "password"); // i18n
    storage.put("login.failed", "Login failed..."); // i18n
    storage.put("login.failed.description", "Login failed, please use right User / Password combination"); // i18n

    storage.put("login.language.de", "German");
    storage.put("login.language.en", "English");

    storage.put("org.rapidpm.vaadin.demo.MyUI.filterTF.placeholder", "filter by name...");
    storage.put("org.rapidpm.vaadin.demo.MyUI.clearFilterBTN.description", "Clear the current filter");
    storage.put("org.rapidpm.vaadin.demo.MyUI.newCustomerBTN.caption", "Add new customer");
    storage.put("org.rapidpm.vaadin.demo.MyUI.dataGrid.col.firstName", "First Name");
    storage.put("org.rapidpm.vaadin.demo.MyUI.dataGrid.col.lastName", "Last Name");
    storage.put("org.rapidpm.vaadin.demo.MyUI.dataGrid.col.email", "Email");


  }
}
