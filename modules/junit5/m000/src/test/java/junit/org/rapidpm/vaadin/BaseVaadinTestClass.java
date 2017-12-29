package junit.org.rapidpm.vaadin;

import com.vaadin.testbench.TestBenchTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.frp.Transformations;
import org.rapidpm.frp.functions.CheckedExecutor;
import org.rapidpm.frp.functions.CheckedSupplier;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;
import org.rapidpm.vaadin.CustomerService;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.function.Supplier;

import static org.rapidpm.frp.StringFunctions.notEmpty;
import static org.rapidpm.frp.StringFunctions.notStartsWith;
import static org.rapidpm.frp.Transformations.not;

/**
 *
 */
public abstract class BaseVaadinTestClass extends TestBenchTestCase {

  protected String url;

  @BeforeEach
  public void setUp()
      throws Exception {
    System.setProperty(MainUndertow.REST_PORT_PROPERTY, new PortUtils().nextFreePortForTest() + "");
    System.setProperty(MainUndertow.SERVLET_PORT_PROPERTY, new PortUtils().nextFreePortForTest() + "");
    System.setProperty(MainUndertow.SERVLET_HOST_PROPERTY, ipSupplierLocalIP.get());
    System.setProperty(MainUndertow.REST_HOST_PROPERTY, ipSupplierLocalIP.get());
    url = "http://" + ipSupplierLocalIP.get() + ":" + System.getProperty(MainUndertow.SERVLET_PORT_PROPERTY) + MainUndertow.MYAPP; //from Annotation Servlet
    System.out.println("url = " + url);

    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages(this.getClass());
    DI.activateDI(this);
    Main.deploy();


//    final URL             url             = new URL("http://" + ipSupplierLocalIP.get() + ":4444/wd/hub");
//    final RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
//    final WebDriver       webDriver       = TestBench.createDriver(remoteWebDriver);
//    setDriver(webDriver);

    System.setProperty("webdriver.chrome.driver", "_data/webdrivers/chromedriver-mac-64bit");
    System.setProperty("webdriver.gecko.driver", "_data/webdrivers/geckodriver-mac-64bit");
//    setDriver(new ChromeDriver());
//    setDriver(new SafariDriver());
    setDriver(new FirefoxDriver());
    getDriver().manage().window().setSize(new Dimension(1920, 1080));

    //data init -> depending on the Singleton
    CustomerService.getInstance().resetData();
  }

  @AfterEach
  public void tearDown()
      throws Exception {
    ((CheckedExecutor) () -> getDriver().quit()).execute();
    ((CheckedExecutor) Main::stop).execute();
    ((CheckedExecutor) DI::clearReflectionModel).execute();
  }

  //TODO inheritance is slowing down without benefits
//  @Test
//  public void testAddressBook() {
//    getDriver().get(url);
//    Assert.assertTrue($(GridElement.class).exists());
//  }


  Supplier<String> ipSupplierLocalIP = () -> {
    final CheckedSupplier<Enumeration<NetworkInterface>> checkedSupplier = NetworkInterface::getNetworkInterfaces;

    return Transformations.<NetworkInterface>enumToStream()
        .apply(checkedSupplier.getOrElse(Collections::emptyEnumeration))
        .map(NetworkInterface::getInetAddresses)
        .flatMap(iaEnum -> Transformations.<InetAddress>enumToStream().apply(iaEnum))
        .filter(inetAddress -> inetAddress instanceof Inet4Address)
        .filter(not(InetAddress::isMulticastAddress))
        .map(InetAddress::getHostAddress)
        .filter(notEmpty())
        .filter(adr -> notStartsWith().apply(adr, "127"))
        .filter(adr -> notStartsWith().apply(adr, "169.254"))
        .filter(adr -> notStartsWith().apply(adr, "255.255.255.255"))
        .filter(adr -> notStartsWith().apply(adr, "255.255.255.255"))
        .filter(adr -> notStartsWith().apply(adr, "0.0.0.0"))
        //            .filter(adr -> range(224, 240).noneMatch(nr -> adr.startsWith(valueOf(nr))))
        .findFirst().orElse("localhost");
  };

}
