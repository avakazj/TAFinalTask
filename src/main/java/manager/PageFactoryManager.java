package manager;

import org.openqa.selenium.WebDriver;
import pageobject.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage getCheckoutPage() {
        return new CheckoutPage(driver);
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public ProductDetailPage getProductDetailPage() {
        return new ProductDetailPage(driver);
    }

    public SaveForLaterPage getSaveForLaterPage() {
        return new SaveForLaterPage(driver);
    }

    public ProductListingPage getProductListingPage() {
        return new ProductListingPage(driver);
    }

    public ShoppingBagPage getShoppingBagPage() {
        return new ShoppingBagPage(driver);
    }

    public WomenPage getWomenPage() {
        return new WomenPage(driver);
    }

    public MenPage getMenPage() {
        return new MenPage(driver);
    }

    public SignInPage getSignInPage() {
        return new SignInPage(driver);
    }
}
