package JavaClasses.PageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingANewUser {

    private WebDriver driver;

    public CreatingANewUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "firstname")
    WebElement firstNameField;

    @FindBy(name = "lastname")
    WebElement lastNameField;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(css = "button.btn:nth-child(2)")
    WebElement saveThisUser;

    public void CreatingNewUser(String firstName, String lastName, String email, String password) {
        firstNameField.click();
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);

        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);

        saveThisUser.click();
    }
}
