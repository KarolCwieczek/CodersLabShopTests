package JavaClasses.PageObj;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddingAnAddressForm {

    private WebDriver driver;

    public AddingAnAddressForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "alias")
    WebElement aliasInput;

    @FindBy(name = "address1")
    WebElement addressInput;

    @FindBy(name = "postcode")
    WebElement postalInput;

    @FindBy(name = "city")
    WebElement cityInput;

    @FindBy(name = "phone")
    WebElement phoneInput;

    @FindBy(css = "button.btn")
    WebElement saveButton;

    @FindBy(name = "id_country")
    WebElement countryList;

    public void fillingAddressForm(String alias, String address, String postal, String city, String phoneNum) {
        aliasInput.click();
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.click();
        addressInput.clear();
        addressInput.sendKeys(address);

        postalInput.click();
        postalInput.clear();
        postalInput.sendKeys(postal);

        cityInput.click();
        cityInput.clear();
        cityInput.sendKeys(city);

        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(phoneNum);

        countryList.click();
        Select dropDownCountry = new Select(countryList);
        dropDownCountry.selectByVisibleText("United Kingdom");

        checkingTheAddress(alias,address,postal,city,phoneNum);

        saveButton.click();
    }

    public void checkingTheAddress(String alias, String address, String postal, String city, String phoneNum) {
        Assert.assertEquals(alias, aliasInput.getAttribute("value"));
        Assert.assertEquals(address, addressInput.getAttribute("value"));
        Assert.assertEquals(postal,postalInput.getAttribute("value"));
        Assert.assertEquals(city,cityInput.getAttribute("value"));
        Assert.assertEquals(phoneNum,phoneInput.getAttribute("value"));
    }
}
