package JavaClasses.PageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class AccountPage {

    WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "address-link")
    WebElement addressInformation;

    @FindBy(id = "addresses-link")
    WebElement addressesInformation;

    @FindBy(css = "#content > div.addresses-footer > a")
    WebElement addNewAddress;

    @FindAll({@FindBy(xpath = "//span[@class='link-item']")})
    List<WebElement> tiles;

    @FindBy(css = "#_desktop_logo > a:nth-child(1)")
    WebElement mainPageBtn;

    @FindBy(className = "account")
    WebElement accountLink;

    public void goToAccPage() {
        accountLink.click();
    }

    public void goToAddAddressForm() {
        if (tiles.get(1).getText().contains("FIRST")) {
            addressInformation.click();
        } else {
            addressesInformation.click();
            addNewAddress.click();
        }
    }

    public void goToAddresses() {
        addressesInformation.click();
    }

    public void goToOrderHistory() {
        tiles.get(2).click();
    }

    public void goToMainPage() {mainPageBtn.click();}
}







