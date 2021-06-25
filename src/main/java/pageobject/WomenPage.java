package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class WomenPage extends BasePage {

    @FindBy(xpath = "//button[contains(@data-id, '415ff')]//span[text()='Accessories']")
    private WebElement accessoriesHeaderWomenMenu;

    @FindBy(xpath = "//div[contains(@id, '415ff')]//a[text()='Belts']")
    private WebElement accessoriesBeltsHeaderWomenMenu;

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    public void hoverAccessories() {
        Actions action = new Actions(driver);
        action.moveToElement(accessoriesHeaderWomenMenu).perform();

    }

    public WebElement getAccessoriesBelts() {
        return accessoriesBeltsHeaderWomenMenu;
    }

    public void clickAccessoriesBelts() {

        accessoriesBeltsHeaderWomenMenu.click();

    }
}
