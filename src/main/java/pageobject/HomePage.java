package pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@id='chrome-search']")
    private WebElement searchInput;

    @FindBy(xpath = "//span[text()='SHOP WOMEN']//ancestor::a")
    private WebElement shopWomenButton;

    @FindBy(xpath = "//span[text()='SHOP MEN']//ancestor::a")
    private WebElement shopMenButton;

    @FindBy(xpath = "//header//button[@data-testid='country-selector-btn']")
    private WebElement countryButton;

    @FindBy(xpath = "//div[@id='chrome-modal-container']")
    private WebElement countryPopup;

    @FindBy(xpath = "//select[@id='currency']")
    private WebElement currencyDropdown;

    @FindBy(xpath = "//section//button[@type='submit']")
    private WebElement submitButton;


    private static final int CURRENCY_INDEX = 1;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void searchByKeyword(String keyword) {
        searchInput.sendKeys(keyword, Keys.ENTER);
    }

    public void clickFlagButton() {
        countryButton.click();
    }

    public void selectCurrencyDropdown() {
        Select dropdown = new Select(currencyDropdown);
        dropdown.selectByIndex(CURRENCY_INDEX);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickShopWomenButton() {
        shopWomenButton.click();
    }

    public void clickShopMenButton() {
        shopMenButton.click();
    }


}
