package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingBagPage extends BasePage {

    @FindBy(xpath = "//div[@class='delivery-dropdown-holder']//span[@role='textbox']")
    private WebElement standardDeliveryOption;

    @FindBy(xpath = "//select[contains(@class,'bag-item-quantity')]")
    private WebElement qntDropdown;

    @FindBy(xpath = "//button[@class='bag-item-edit-update']")
    private WebElement updateBagButton;

    @FindBy(xpath = "//p[@class='bag-item-name']//a")
    private WebElement bagItemName;

    public WebElement getStandardDeliveryOption() {
        return standardDeliveryOption;
    }


    public String getStandardDeliveryOptionText() {
        return standardDeliveryOption.getText();
    }

    public void changeItemQnt(String quantity) {
        Select quantities = new Select(qntDropdown);
        quantities.selectByValue(quantity);
    }

    public void clickUpdateBagButton() {
        updateBagButton.click();
    }

    public ShoppingBagPage(WebDriver driver) {
        super(driver);
    }

    public String getBagItemName() {
        return bagItemName.getText();
    }
}
