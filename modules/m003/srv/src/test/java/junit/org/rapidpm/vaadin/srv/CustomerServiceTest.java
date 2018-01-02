package junit.org.rapidpm.vaadin.srv;

import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.shared.Customer;
import org.rapidpm.vaadin.srv.CustomerService;

import java.util.List;

/**
 *
 */
public class CustomerServiceTest {


  @Test
  void test001() {

    final CustomerService instance = CustomerService.getInstance();
    final List<Customer>  all      = instance.findAll();

    //how to test this ?


  }
}
