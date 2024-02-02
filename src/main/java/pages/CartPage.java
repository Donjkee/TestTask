package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage
{
    public WebDriver driver;

    public CartPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(name="checkout")
    WebElement checkoutBtn;

    public boolean checkForAllItemsInCart(List<String> itemNames)
    {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        for(WebElement element : cartItems)
        {
            String name = element
                    .findElement(By.className("inventory_item_name"))
                    .getText();
            if(!element
                    .findElement(By.className("cart_quantity"))
                    .getText()
                    .equals("1")
                || !itemNames.contains(name))
            {
                return false;
            }
        }
        return true;
    }

    public void clickCheckoutBtn()
    {
        checkoutBtn.click();
    }

}
