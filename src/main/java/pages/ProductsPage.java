package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage
{
    public WebDriver driver;

    public ProductsPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className="inventory_list")
    private WebElement inventoryList;

    @FindBy(className="shopping_cart_link")
    WebElement shoppingCartBtn;

    public void addItemsToCart(List<String> itemNames) {
        List<WebElement> itemsList = inventoryList.findElements(By.className("inventory_item"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (WebElement element : itemsList) {
            String itemName = element.findElement(By.className("inventory_item_name")).getText();
            if (itemNames.contains(itemName)) {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(element.findElement(By.tagName("button"))));
                if (!button.getAttribute("name").contains("remove")) {
                    button.click();
                }
            }
        }
    }

    public int howMuchElementsAdded() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement shoppingCartBadge = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_badge")));
            return Integer.parseInt(shoppingCartBadge.getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }


    public void clickToShoppingCartBtn()
    {
        shoppingCartBtn.click();
    }
}
