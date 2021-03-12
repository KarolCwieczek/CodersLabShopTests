package JavaClasses.PageObj;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CartAndOrderProcess {

    private WebDriver driver;

    public CartAndOrderProcess(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "a.btn")
    WebElement proceedToCheckout;

    @FindBy(css = ".btn")
    WebElement addressContinue;

    @FindBy(css = "button.continue:nth-child(2)")
    WebElement shippingContinue;

    @FindBy(id = "payment-option-1")
    WebElement payByCheck;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement termsAndConditons;

    @FindBy(css = "div.ps-shown-by-js > button:nth-child(1)")
    WebElement orderPayButton;

    @FindBy(css = ".font-weight-bold > td:nth-child(2)")
    WebElement total;

    @FindBy(className = "account")
    WebElement accountLink;

    @FindBy(xpath = "/html/body/main/section/div/div/section/section/table/tbody/tr[1]/td[2]")
    WebElement totalPriceOrderHistory;

    @FindBy(xpath = "/html/body/main/section/div/div/section/section/table/tbody/tr[1]/td[4]/span")
    WebElement paymentStatus;

    public void goToAccPage() {
        accountLink.click();
    }

    public void proceedToChekout() {
        proceedToCheckout.click();
    }

    public void addressContinue() {
       addressContinue.click();
   }

    public void shippingContinue() {
       shippingContinue.click();
   }

    public void payByCheckAndOrder() {
        payByCheck.click();
        termsAndConditons.click();
        orderPayButton.click();
    }

    public void takeScreenshot(WebDriver driver) {
       try {
        Screenshot scrShot =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        ImageIO.write(scrShot.getImage(), "png", new File("Prestascreenshot.png"));
       } catch (IOException e) {
           System.out.println("Exception occured : " + e.getMessage());
       }
    }

    public void checkOfTotalCostAndPaymentStatus() {
        String totalOrderCost = total.getText();
        goToAccPage();
        AccountPage accP = new AccountPage(driver);
        accP.goToOrderHistory();
        Assert.assertEquals(totalOrderCost,totalPriceOrderHistory.getText());
        Assert.assertEquals("Awaiting check payment",paymentStatus.getText());
    }
}
