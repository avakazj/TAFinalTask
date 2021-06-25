package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//input[@id='emailAddress']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addEmailButton;

    @FindBy(xpath = "//span[@class='validation-error']")
    private WebElement emailValidateAlert;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void inputEmailField(String inputStr) {
        emailInput.sendKeys(inputStr);
        addEmailButton.click();
    }

    public String getEmailValidatorAlert() {
        return emailValidateAlert.getText();
    }
}
