package JavaClasses.PageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "account")
    WebElement accountLink;

    @FindBy(name = "s")
    WebElement searchBar;

    //Search for all products name
    @FindAll({@FindBy(css = "article.product-miniature:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h2:nth-child(1) > a:nth-child(1)")})
    List<WebElement> tiles;

    public void goToAccPage() {
        accountLink.click();
    }

    public void searchFor(String search) {
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(search);
        searchBar.submit();
    }

    public void chooseThisOne(String name) {
        for (int i = 0; i < tiles.size() ; i++) {
            if (name.equalsIgnoreCase(tiles.get(i).getText())) {
                tiles.get(i).click();
            }
        }
    }
}
