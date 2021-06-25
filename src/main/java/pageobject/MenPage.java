package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenPage extends BasePage {

    public MenPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='JEANS']//ancestor::a")
    private WebElement saleJeansTile;

    public void clickSaleJeansTile() {
        saleJeansTile.click();
    }

}
