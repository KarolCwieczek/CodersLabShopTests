package JavaClasses.PageObj;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class AddressesPage {

    private WebDriver driver;

    public AddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindAll({@FindBy(css = ".address-body > address:nth-child(2)")})
    List<WebElement> addresses;

    @FindBy(css = ".alert > ul:nth-child(1) > li:nth-child(1)")
    WebElement popUp;

    @FindBy(xpath = "//a[@data-link-action='delete-address']")
    WebElement deleteBtn;

    @FindBy(css = "#_desktop_logo > a:nth-child(1)")
    WebElement mainPageBtn;

    @FindBy(css = ".addresses-footer > a:nth-child(1)")
    WebElement createNewAddress;

    @FindBy(className = "account")
    WebElement accountLink;

    public void goToAccPage() {
        accountLink.click();
    }

    public void checkingLastAddress(String address, String postal, String city, String phoneNum) {
        Assert.assertTrue(addresses.get(addresses.size() - 1).getText().contains(address));
        Assert.assertTrue(addresses.get(addresses.size() - 1).getText().contains(postal));
        Assert.assertTrue(addresses.get(addresses.size() - 1).getText().contains(city));
        Assert.assertTrue(addresses.get(addresses.size() - 1).getText().contains(phoneNum));
    }

    public void checkingUpOnSucsPopUp() {
        Assert.assertTrue(popUp.getText().contains("Address successfully added!"));
    }

    public void checkingUpOnFailPopUp() {
        Assert.assertTrue(popUp.getText().contains("Address successfully deleted!"));
    }

    public void deleteAddress() {
        deleteBtn.click();
    }

    public void createNewAddress() {
        createNewAddress.click();
    }

    public void goToMainPage() {mainPageBtn.click();}
}
