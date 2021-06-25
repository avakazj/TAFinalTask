package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductDetailPage extends BasePage {

    @FindBy(xpath = "//button[@data-test-id='add-button']")
    private WebElement addToBagButton;

    @FindBy(xpath = "//select[@data-id='sizeSelect']")
    private WebElement selectDropdown;

    @FindBy(xpath = "//div[@class='product-hero']//h1")
    private WebElement productTitle;

    @FindBy(xpath = "//span[@id='selectSizeError']")
    private WebElement selectSizeAlert;

    @FindBy(xpath = "//button[@data-testid='miniBagIcon']")
    private WebElement bagDropdown;

    @FindBy(xpath = "//a[@data-test-id='bag-link']")
    private WebElement viewBagButton;

    @FindBy(xpath = "//div[@class='product-code']//p")
    private WebElement productCode;

    @FindBy(xpath = "//button[@id='fit-upper']")
    private WebElement fitAssistantLink;

    @FindBy(xpath = "//div[@id='uclw']")
    private WebElement fitAssistantPopup;

    @FindBy(xpath = "//input[@aria-label='Height in Centimeters']")
    private WebElement heightInCmField;

    @FindBy(xpath = "//input[@aria-label='Weight in Kilograms']")
    private WebElement weightInKgField;

    @FindBy(xpath = "//button[text()='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@id='uclw_age_element']//input")
    private WebElement ageInput;

    @FindBy(xpath = "//span[@data-key='1']")
    private WebElement secondBrandTile;

    @FindBy(xpath = "//div[text()='Trousers type']//following-sibling::div[text()='Select']")
    private WebElement trousersTypeDropdown;

    @FindBy(xpath = "//span[text()='Regular Jeans']/..")
    private WebElement regularJeansTrousersTypeDropdownOption;

    @FindBy(xpath = "//button[text()='Skip this question']")
    private WebElement skipButton;

    @FindBy(xpath = "//span[@aria-label='L']")
    private WebElement jeansLSize;

    @FindBy(xpath = "//button[text()=' Continue shopping ']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='uclw_headline']")
    private WebElement fitResult;

    @FindBy(xpath = "//div[@data-ref='primaryLabel']//div[@class='uclw_label_inner']")
    private WebElement fitResultSize;

    @FindBy(xpath = "//span[text()='Rounder']/parent::span")
    private WebElement radioButtonRounder;

    @FindBy(xpath = "//div[contains(@class,'handle')]")
    private WebElement handleFitPreference;


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToBag() {
        addToBagButton.click();
    }

    public void openNotEmptyBag() {
        Actions actions = new Actions(driver);
        actions.moveToElement(bagDropdown).build().perform();
        viewBagButton.click();
    }

    public String getProductCode() {
        return productCode.getText();
    }

    public void clickFitAssistant() {
        fitAssistantLink.click();
    }

    public void fillHeightAndWeight(String height, String weight) {
        heightInCmField.sendKeys(height);
        weightInKgField.sendKeys(weight);

    }

    public void clickContinueFitPopupButton() {
        continueButton.click();
    }

    public WebElement getContinueFitPopupButton() {
        return continueButton;
    }

    public void fillAge(String age) {
        ageInput.sendKeys(age);
    }

    public WebElement getAgeInput() {
        return ageInput;
    }

    public void clickSecondBrandTile() {
        secondBrandTile.click();
    }

    public WebElement getSecondBrandTile() {
        return secondBrandTile;
    }

    public WebElement getHeightField() {
        return heightInCmField;
    }

    public void selectRegularJeansType() {
        trousersTypeDropdown.click();
        regularJeansTrousersTypeDropdownOption.click();
    }

    public WebElement getTrousersTypeDropdown() {
        return trousersTypeDropdown;
    }

    public void clickSkipQuestion() {
        skipButton.click();
    }

    public WebElement getSkipQuestion() {
        return skipButton;
    }

    public void selectLSize() {
        jeansLSize.click();
    }

    public WebElement getJeansLSize() {
        return jeansLSize;
    }

    public WebElement getFitResult() {
        return fitResult;
    }

    public String expectedFitResult() {
        String result;
        if (fitResult.getText().equalsIgnoreCase("No matching size")) {
            result = "Find your Fit Assistant size";
        } else result = fitResultSize.getText();
        return result;
    }

    public WebElement getFitAdviseText() {
        return fitAssistantLink;
    }

    public void selectSize() {
        Select dropdown = new Select(selectDropdown);
        dropdown.selectByIndex(1);
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

    public WebElement getContinueShoppingButton() {
        return continueShoppingButton;
    }

    public void isVisible() {
        radioButtonRounder.isDisplayed();
    }

    public WebElement getRadioButtonRounder() {
        return radioButtonRounder;
    }

    public void setFitPreference() {
        Actions action = new Actions(driver);
        action.dragAndDropBy(handleFitPreference, 67, 0).build().perform();
    }

    public WebElement getHandleFitPreference() {
        return handleFitPreference;
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public WebElement getSelectSizeAlertMessage() {
        return selectSizeAlert;
    }
}
