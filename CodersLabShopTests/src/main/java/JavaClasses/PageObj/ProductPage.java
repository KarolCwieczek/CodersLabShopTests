package JavaClasses.PageObj;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".regular-price")
    WebElement regPrice;

    @FindBy(css = ".current-price > span:nth-child(1)")
    WebElement discountedPrice;

    @FindBy(id = "group_1")
    WebElement sizeList;

    @FindBy(id = "quantity_wanted")
    WebElement quantity;

    @FindBy(css = "div.add")
    WebElement addToCart;

    @FindBy(id = "_desktop_cart")
    WebElement goToCart;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/div[2]/div/div/a")
    WebElement goToCartAlert;

    public void checkDiscount(double discountPercent) {
        String regular = regPrice.getText().substring(1,6);
        double regPrice = Double.parseDouble(regular);
        String discounted = discountedPrice.getText().substring(1,6);
        double disPrice = Double.parseDouble(discounted);
        Assert.assertTrue(((100 - discountPercent)/100) * regPrice == disPrice);
    }

    public void getSize(String size) {
        sizeList.click();
        Select dropSizeList = new Select(sizeList);
        dropSizeList.selectByVisibleText(size);
    }

    public void quantityOfProduct(String number) {
        quantity.click();
        quantity.clear();
        quantity.sendKeys(number);
    }

    public void addToCart() {
        addToCart.click();
    }

    public void goToCart() {
        goToCart.click();
    }

    public void goToCartAfterProductAdding() throws InterruptedException {
        Thread.sleep(2000);
        goToCartAlert.click();
    }
}
