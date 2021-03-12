package JavaClasses.PageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageNew {

    private WebDriver driver;

    public LoginPageNew(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "email")
    WebElement emailInputField;

    @FindBy(name = "password")
    WebElement passwordInputField;

    @FindBy(id = "submit-login")
    WebElement loginButton;

    @FindBy(css = "#content > div > a")
    WebElement noAccount;

    public void loginAs(String email, String password) {
        emailInputField.click();
        emailInputField.clear();
        emailInputField.sendKeys(email);

        passwordInputField.click();
        passwordInputField.clear();
        passwordInputField.sendKeys(password);

        loginButton.click();
    }

    public void noAccountClick() {
        noAccount.click();
    }
}
