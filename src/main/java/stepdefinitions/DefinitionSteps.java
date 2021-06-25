package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.*;

import java.util.Collections;
import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {
    private static final long DEFAULT_TIMEOUT = 60;
    private static final long LONG_TIMEOUT = 150;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    CheckoutPage checkoutPage;
    HomePage homePage;
    ProductDetailPage productDetailPage;
    SaveForLaterPage saveForLaterPage;
    ShoppingBagPage shoppingBagPage;
    ProductListingPage productListingPage;
    WomenPage womenPage;
    MenPage menPage;

    List<String> expected;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        expected = null;

    }

    @Given("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @And("User makes search by keyword {string}")
    public void enterKeywordToSearchField(final String keyword) {
        homePage.searchByKeyword(keyword);
        productListingPage = pageFactoryManager.getProductListingPage();
    }

    @And("User filters products by {string}")
    public void filterByBrand(final String brand) {
        productListingPage.sortByBrand(brand);
    }

    @And("User filters products by price in range {int}, {int}")
    public void filterByPrice(int minValue, int maxValue) {
        productListingPage.inputProductsPriceRange(minValue, maxValue);
    }

    @And("User set sort order {string}")
    public void chooseAscendingSortOrder(String sortingOrder) {
        productListingPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        productListingPage.sortProducts(sortingOrder);
    }

    @When("User clicks first product")
    public void clickFirstProduct() {
        productListingPage = pageFactoryManager.getProductListingPage();
        productListingPage.waitForPageLoadingComplete(DEFAULT_TIMEOUT);
        productListingPage.clickFirstProduct();
        productListingPage.waitForPageLoadingComplete(DEFAULT_TIMEOUT);
    }

    @And("User clicks 'Put to Bag' button")
    public void clickAddToBagButton() {
        productDetailPage = pageFactoryManager.getProductDetailPage();
        productDetailPage.waitForPageLoadingComplete(LONG_TIMEOUT);
        productDetailPage.clickAddToBag();
    }

    @And("User opens shopping cart")
    public void openShoppingCart() {
        productDetailPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        productDetailPage.openNotEmptyBag();
    }

    @And("User checks that delivery payment is added")
    public void checkDeliveryPaymentAdded() {
        shoppingBagPage = pageFactoryManager.getShoppingBagPage();
        shoppingBagPage.waitVisibilityOfElement(LONG_TIMEOUT, shoppingBagPage.getStandardDeliveryOption());
        Assert.assertNotEquals("Standard Delivery (Free)", shoppingBagPage.getStandardDeliveryOptionText());
    }

    @And("User set quantity of items to {string}")
    public void changeItemQnt(String quantity) {
        shoppingBagPage.changeItemQnt(quantity);
    }

    @Then("User checks that there is no delivery payment added")
    public void checkDeliveryPaymentNotAdded() {
        shoppingBagPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        Assert.assertEquals("Standard Delivery (Free)", shoppingBagPage.getStandardDeliveryOptionText());
    }

    @And("User saves several products {string} by clicking Heart icon on product image")
    public void addToSaveList(String string) {
        productListingPage.addSaveForLater(string);
        List<String> expected = productListingPage.getListOfSelectedTitlesByIndexes(string);
        productListingPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
    }

    @When("User opens 'Save for later' page by clicking 'Heart' icon in header")
    public void openSaveList() {
        productListingPage.openSavedForLater();
    }

    @Then("User checks if items in list are the same that he added {string}")
    public void checkAddedItemsToSaveList() {
        saveForLaterPage = pageFactoryManager.getSaveForLaterPage();
        Assert.assertEquals(expected, saveForLaterPage.getSaveForLaterProductTitles());
    }

    @Then("User checks if items shown contains search keywords {string}")
    public void checkItemsContainsKeyword(String keyword) {
        Assert.assertTrue(productListingPage.checkTitlesContainsKeyword(
                productListingPage.getProductTitles(), keyword));
    }

    @And("User clicks Flag icon")
    public void clickFlagIcon() {
        homePage.clickFlagButton();
    }

    @When("User selects USD currency")
    public void selectUSDCurrency() {
        homePage.selectCurrencyDropdown();
    }

    @And("User clicks Update button")
    public void clickUpdateButton() {
        homePage.clickSubmitButton();
    }

    @Then("User checks that currency in products price is USD")
    public void checkThatCurrencyInProductsPriceIsUSD() {
        Assert.assertEquals('$', productListingPage.getFirstProductPriceCurrency());
    }

    @And("User clicks Update bag button")
    public void clicksUpdateBagButton() {
        shoppingBagPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        shoppingBagPage.clickUpdateBagButton();
    }

    @And("User clicks 'Shop Women'")
    public void userClicksShopWomen() {
        homePage.clickShopWomenButton();
        womenPage = pageFactoryManager.getWomenPage();
    }

    @And("User clicks dropdown menu Accessories -> Belts")
    public void userClicksDropdownMenuAccessoriesBelts() {
        womenPage.waitForPageLoadingComplete(DEFAULT_TIMEOUT);
        womenPage.hoverAccessories();
        womenPage.waitElementToBeClickable(DEFAULT_TIMEOUT, womenPage.getAccessoriesBelts());
        womenPage.clickAccessoriesBelts();
        productListingPage = pageFactoryManager.getProductListingPage();
    }

    @And("User filters products by 'Sale-New Season' -> 'Sale'")
    public void userFiltersProductsBySaleNewSeason() {
        productListingPage.sortBySale();
    }

    @And("User filters products by 'Style' -> 'Waist'")
    public void userFiltersProductsByStyle() {
        productListingPage.sortByStyle();
    }

    @And("User filters products by 'Leather-Non Leather' -> 'Non Leather'")
    public void userFiltersProductsByLeatherNonLeather() {
        productListingPage.sortByLeather();
    }

    @And("User filters products by Brand {string}")
    public void userFiltersProductsByBrand(String brand) {
        productListingPage.sortByBrand(brand);
    }

    @And("User filters products by 'Colour' -> 'Black'")
    public void userFiltersProductsByColor() {
        productListingPage.sortByColor();
    }

    @And("User click on desired item {string}")
    public void userClickOnDesiredItem(String title) {
        productListingPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        productListingPage.clickOnProductByTitle(title);
        productDetailPage = pageFactoryManager.getProductDetailPage();
    }

    @When("User saves product code")
    public void userSavesProductCode() {
        expected = Collections.singletonList((productDetailPage.getProductCode()));
    }

    @And("User clicks on Logo")
    public void userClicksOnLogo() {
        productListingPage.clickOnMainLogo();
    }

    @Then("User checks that products same")
    public void userChecksThatProductsSame() {
        Assert.assertEquals(expected, Collections.singletonList(productDetailPage.getProductCode()));
    }

    @And("User clicks 'Shop Men'")
    public void clickShopMen() {
        homePage.clickShopMenButton();
        menPage = pageFactoryManager.getMenPage();
    }

    @And("User clicks 'Jeans' tile in a front sale section")
    public void clickSaleJeans() {
        menPage.clickSaleJeansTile();
    }

    @And("User clicks 'Find your Fit Assistant size")
    public void clickFitAssistantLink() {
        productDetailPage = pageFactoryManager.getProductDetailPage();
        productListingPage.waitElementToBeClickable(DEFAULT_TIMEOUT, productDetailPage.getFitAdviseText());
        productDetailPage.clickFitAssistant();
    }

    @When("User enters height and weight{string},{string}")
    public void fillHeightAndWeight(String height, String weight) {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getHeightField());
        productDetailPage.fillHeightAndWeight(height, weight);
        productDetailPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
    }


    @And("User clicks 'Continue' button")
    public void clickContinueFitPopupButton() {
        //productListingPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        //productDetailPage.waitForPageLoadingComplete(DEFAULT_TIMEOUT);
        productDetailPage.waitElementToBeClickable(DEFAULT_TIMEOUT, productDetailPage.getContinueFitPopupButton());
        productDetailPage.clickContinueFitPopupButton();
    }

    @And("User checks if radiobutton is visible")
    public void checkVisbility() {
        productDetailPage.waitElementToBeClickable(LONG_TIMEOUT, productDetailPage.getRadioButtonRounder());
        productDetailPage.isVisible();
    }

    @And("User enters age{string}")
    public void fillAgeField(String age) {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getAgeInput());
        productDetailPage.fillAge(age);
    }

    @And("User set fit preference to 'On the loose side'")
    public void setFitPreferenceToLooseSide() {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getHandleFitPreference());
        productDetailPage.setFitPreference();
    }

    @And("User chooses Levis")
    public void chooseLevisBrand() {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getSecondBrandTile());
        productDetailPage.clickSecondBrandTile();
    }

    @And("User selects 'Regular Jeans' type")
    public void selectRegularJeansType() {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getTrousersTypeDropdown());
        productDetailPage.selectRegularJeansType();
    }

    @And("User skips question")
    public void skipQuestion() {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getSkipQuestion());
        productDetailPage.clickSkipQuestion();
    }

    @And("User selects L size")
    public void selectLSize() {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getJeansLSize());
        productDetailPage.selectLSize();
    }

    @And("User clicks 'Continue shopping'")
    public void clickContinueShopping() {
        productDetailPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productDetailPage.getContinueShoppingButton());
        expected = Collections.singletonList(productDetailPage.expectedFitResult());
        productDetailPage.clickContinueShopping();
    }

    @Then("User checks that size fit advise is shown")
    public void checkFitAdviseIsShown() {
        productDetailPage.waitForPageLoadingComplete(DEFAULT_TIMEOUT);
        List<String> actual = Collections.singletonList(productDetailPage.getFitAdviseText().getText());
        Assert.assertTrue(actual.get(0).contains(expected.get(0)));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}


