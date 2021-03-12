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

public class AddingNewAddressTest {
    private WebDriver driver;
    private String emailAddress = "bodorat996@wedbo.net";
    private String password = "123456";

    @Before("@AddNewAddress")
    public void startMeUp() {
        System.setProperty("webdriver.gecko.driver","src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @Given("User is on MyStore site and logs in with email and password")
    public void logginIntoStore() {
        //Intialization of page object and logging into account
        LoginPageNew login = new LoginPageNew(driver);
        login.loginAs(this.emailAddress, this.password);
    }

    @When("User goes to his addresses page")
    public void goingToAddressPage() {
        //Initialization of page objects and going to address page
        MainPage mainPage = new MainPage(driver);
        AccountPage accPage = new AccountPage(driver);
        mainPage.goToAccPage();
        accPage.goToAddAddressForm();
    }

    @And("User fills form with correct {string}, {string}, {string}, {string}, {string}")
    public void addingANewAddress(String alias, String address, String postal, String city, String phoneNum) {
        //Filling an Address Form
        AddingAnAddressForm addAddress = new AddingAnAddressForm(driver);
        addAddress.fillingAddressForm(alias,address,postal,city,phoneNum);
        //Checking if the address is correct
        AddressesPage addrPage = new AddressesPage(driver);
        addrPage.checkingLastAddress(address,postal,city,phoneNum);
    }

    @Then("User sucessfully add a new address confirmed by success popup")
    public void newAddressCheck() {
        //Getting address creation popup
        AddressesPage addrPage = new AddressesPage(driver);
        addrPage.checkingUpOnSucsPopUp();
    }

    @After("@AddNewAddress")
    public void AfterAll() {
        //Deleting testing entry and checking up successful deletion Popup
        AddressesPage addrPage = new AddressesPage(driver);
        addrPage.deleteAddress();
        addrPage.checkingUpOnFailPopUp();
        driver.quit();
    }
}
