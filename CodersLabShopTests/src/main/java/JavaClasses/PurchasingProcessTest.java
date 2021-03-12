package JavaClasses;

import JavaClasses.PageObj.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class PurchasingProcessTest {
    private WebDriver driver;

    @Before("@PurchasingProcess")
    public void startMeUp() {
        //Driver set up
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @Given("User is logging on MyStore")
    public void searchingForProduct() {
        //Logging into previously created account and adding a dummy address
        LoginPageNew login = new LoginPageNew(driver);
        MainPage main = new MainPage(driver);
        AccountPage accPage = new AccountPage(driver);
        AddingAnAddressForm addressForm = new AddingAnAddressForm(driver);
        login.loginAs("bodorat996@wedbo.net", "123456");
        main.goToAccPage();
        accPage.goToAddAddressForm();
        addressForm.fillingAddressForm("12","Street Fighting Man","02-223","Sandia","112322031");
        accPage.goToMainPage();
    }

    @When("User search for {string}")
    public void placingAnOrder(String product) {
        // initialization of MainPage obj and search for wanted product
        MainPage main = new MainPage(driver);
        main.searchFor(product);
        main.chooseThisOne(product);
    }

    @And("User add to cart {string} of product in size {string}")
    public void addingToCart(String number, String size) {
        //Initialization of ProductPage and placing an order
        ProductPage prodPage = new ProductPage(driver);
        prodPage.checkDiscount(20);
        prodPage.getSize(size);
        prodPage.quantityOfProduct(number);
        prodPage.addToCart();
    }

    @Then("Order is placed successfully confirmed by screenshot")
    public void successfulOrder() throws InterruptedException {
        //Going to cart after adding there a product to finish ordering process
        ProductPage prodPage = new ProductPage(driver);
        prodPage.goToCartAfterProductAdding();
        //Finishing an order form cart
        CartAndOrderProcess cartAndOrderProcess = new CartAndOrderProcess(driver);
        cartAndOrderProcess.proceedToChekout();
        cartAndOrderProcess.addressContinue();
        cartAndOrderProcess.shippingContinue();
        cartAndOrderProcess.payByCheckAndOrder();
        // Taking screenshot of successfully placed order
        // Also checking order history to make sure that amount is the same in order history
        cartAndOrderProcess.takeScreenshot(driver);
        cartAndOrderProcess.checkOfTotalCostAndPaymentStatus();
    }

    @After("@PurchasingProcess")
    public void afterAll() {
        //Deleting testing entry
        AddressesPage addressP = new AddressesPage(driver);
        AccountPage accP = new AccountPage(driver);
        accP.goToAccPage();
        accP.goToAddresses();
        addressP.deleteAddress();
        //Quiting browser
        driver.quit();
    }
}
