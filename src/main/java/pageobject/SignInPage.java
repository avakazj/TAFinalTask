package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//a[@id='new-to-asos-tab']")
    private WebElement joinTab;

    @FindBy(xpath = "//input[@value='Continue to checkout']")
    private WebElement continueToCheckoutButton;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void clickJoinTab() {
        joinTab.click();
    }

    public void clickContinueToCheckout() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", continueToCheckoutButton);
    }
}
