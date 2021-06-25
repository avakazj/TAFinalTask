package pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListingPage extends BasePage {

    @FindBy(xpath = "//div[text()='Brand']")
    private WebElement brandDropdown;

    @FindBy(xpath = "//li[@data-dropdown-id='brand']//input[@type='search']")
    private WebElement brandDropdownSearchInput;

    @FindBy(xpath = "//li[@data-dropdown-id='brand']//li")
    private List<WebElement> brandDropdownElements;

    @FindBy(xpath = "//li[@data-dropdown-id='currentprice']//button")
    private WebElement priceDropdown;

    @FindBy(xpath = "//div[@data-testid='minIndicator']")
    private WebElement minValueIndicator;

    @FindBy(xpath = "//div[@data-testid='maxIndicator']")
    private WebElement maxValueIndicator;

    @FindBy(xpath = "//div[text()='Sort']//ancestor::button")
    private WebElement sortDropdown;

    @FindBy(xpath = "//div[text()='Leather/Non Leather']//ancestor::button")
    private WebElement sortByLeatherDropdown;

    @FindBy(xpath = "//div[text()='Non Leather']")
    private WebElement sortByLeatherDropdownNonLeather;

    @FindBy(xpath = "//div[text()='Colour']//ancestor::button")
    private WebElement sortByColourDropdown;

    @FindBy(xpath = "//div[text()='Black']")
    private WebElement sortByColourDropdownBlack;

    @FindBy(xpath = "//div[text()='Style']//ancestor::button")
    private WebElement sortByStyleDropdown;

    @FindBy(xpath = "//div[text()='Waist']")
    private WebElement sortByStyleDropdownWaist;

    @FindBy(xpath = "//div[text()='Sale / New Season']//ancestor::button")
    private WebElement sortBySaleDropdown;

    @FindBy(xpath = "//div[text()='Sale']")
    private WebElement sortBySaleDropdownSale;

    @FindBy(xpath = "//li[text()='Price low to high']")
    private WebElement sortDropdownPriceLowToHigh;

    @FindBy(xpath = "//article[contains(@id, 'product')]//a")
    private List<WebElement> productTiles;

    @FindBy(xpath = "//article[@data-auto-id='productTile']//a//div//p")
    private List<WebElement> searchProductTitles;

    @FindBy(xpath = "//span[@data-auto-id='productTilePrice']//span") //--
    private List<WebElement> searchProductPrices;

    @FindBy(xpath = "//button[@data-auto-id='saveForLater']")  //--
    private List<WebElement> saveForLaterButtons;

    @FindBy(xpath = "//a[@aria-label='Saved Items']")
    private WebElement savedButton;

    @FindBy(xpath = "//a[@data-testid='asoslogo']")
    private WebElement mainLogo;

    public ProductListingPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnMainLogo() {
        mainLogo.click();
    }

    public void sortByBrand(String brand) {
        brandDropdown.click();
        brandDropdownSearchInput.clear();
        brandDropdownSearchInput.sendKeys(brand, Keys.ENTER);
        brandDropdownElements.get(0).click();
    }

    public void sortByLeather() {
        sortByLeatherDropdown.click();
        sortByLeatherDropdownNonLeather.click();
    }

    public void sortByColor() {
        sortByColourDropdown.click();
        sortByColourDropdownBlack.click();
    }

    public void sortByStyle() {
        sortByStyleDropdown.click();
        sortByStyleDropdownWaist.click();
    }

    public void sortBySale() {
        sortBySaleDropdown.click();
        sortBySaleDropdownSale.click();
    }

    public void inputProductsPriceRange(int minValue, int maxValue) {
        priceDropdown.click();
        Actions action = new Actions(driver);
        action.dragAndDropBy(minValueIndicator, minValue, 0);
        priceDropdown.click();
        action.dragAndDropBy(maxValueIndicator, maxValue, 0);
    }

    public void sortProducts(String sortingOrder) {
        sortDropdown.click();
        sortDropdownPriceLowToHigh.click();
    }

    public void clickFirstProduct() {
        productTiles.get(0).click();
    }

    public List<WebElement> getProductTitles() {
        return searchProductTitles;
    }

    public void clickOnProductByTitle(String title) {
        searchProductTitles
                .stream()
                .filter(x -> x.getText().equals(title))
                .findFirst().orElseThrow(NotFoundException::new)
                .click();
    }


    public char getFirstProductPriceCurrency() {
        return searchProductPrices
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .get(0).charAt(0);
    }

    public List<WebElement> getSaveForLaterButtons() {
        return saveForLaterButtons;
    }

    public void addSaveForLater(String indexesString) {
        Arrays.stream(indexesString.split(", ?"))
                .map(Integer::parseInt)
                .forEach(x -> getSaveForLaterButtons().get(x).click());
    }

    public void openSavedForLater() {
        savedButton.click();
    }

    public List<String> getListOfSelectedTitlesByIndexes(String indexesString) {
        return Arrays.stream(indexesString.split(", ?"))
                .map(Integer::parseInt)
                .map(x -> getProductTitles()
                        .get(x)
                        .getText())
                .sorted()
                .collect(Collectors.toList());
    }

    public boolean checkTitlesContainsKeyword(List<WebElement> list, String keywords) {
        for (WebElement element : list) {
            for (String s : keywords.split(" ")) {
                if (!element.getText().toLowerCase()
                        .contains(s.toLowerCase())) return false;
            }
        }
        return true;
    }
}

