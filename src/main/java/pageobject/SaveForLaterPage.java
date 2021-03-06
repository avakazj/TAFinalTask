package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SaveForLaterPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'title')]//p")
    private List<WebElement> productTitles;

    public SaveForLaterPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getSaveForLaterProductTitles() {
        return productTitles
                .stream()
                .map(WebElement::getText)
                .sorted()
                .collect(Collectors.toList());
    }
}
