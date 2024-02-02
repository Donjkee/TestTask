package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage {
    public WebDriver driver;

    @FindBy(className = "cart_list")
    private WebElement cartList;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    public CheckoutOverviewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean checkAllItemsInCart(List<String> itemNames) {
        int count = 0;
        List<WebElement> itemsList = getCartItems();
        for (WebElement element : itemsList) {
            String itemName = getInventoryItemName(element);
            if (itemNames.contains(itemName)) {
                count++;
            }
        }
        return count == itemNames.size();
    }

    public boolean checkPriceIsRightWithTax() {
        double price = calculateTotalPriceWithTax();
        double subtotalValue = getSubtotalValueWithTax();
        return price == subtotalValue;
    }

    public boolean checkPriceIsRightWithoutTax() {
        double price = calculateTotalPriceWithoutTax();
        double subtotalValue = getSubtotalValueWithoutTax();
        return price == subtotalValue;
    }

    public void clickFinishBtn()
    {
        finishBtn.click();
    }




    private List<WebElement> getCartItems() {
        return cartList.findElements(By.className("cart_item"));
    }

    private String getInventoryItemName(WebElement element) {
        return element.findElement(By.className("inventory_item_name")).getText();
    }

    private double calculateTotalPriceWithoutTax() {
        double price = 0.0;
        List<WebElement> pricesList = getInventoryItemPrices();
        for (WebElement element : pricesList) {
            String currentPrice = element.getText().replaceAll("[^\\d.]", "");
            price += Double.parseDouble(currentPrice);
        }
        return price;
    }

    private double calculateTotalPriceWithTax()
    {
        return calculateTotalPriceWithoutTax() + getTaxValue();
    }

    private List<WebElement> getInventoryItemPrices() {
        return driver.findElements(By.className("inventory_item_price"));
    }

    private double getSubtotalValueWithTax() {
        return getSubtotalValue() + getTaxValue();
    }

    private double getSubtotalValueWithoutTax() {
        return getSubtotalValue();
    }

    private double getSubtotalValue() {
        String realPrice = getSubtotalLabel().getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(realPrice);
    }

    private WebElement getSubtotalLabel() {
        return driver.findElement(By.className("summary_subtotal_label"));
    }

    private double getTaxValue() {
        String taxPrice = getTaxLabel().getText().replaceAll("[^\\d.]", "");
        return Double.parseDouble(taxPrice);
    }

    private WebElement getTaxLabel() {
        return driver.findElement(By.className("summary_tax_label"));
    }
}
