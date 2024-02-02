package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage
{
    public WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="first-name")
    private WebElement firstNameField;

    @FindBy(id="last-name")
    private WebElement lastNameField;

    @FindBy(id="postal-code")
    private WebElement postalCodeField;

    @FindBy(id="continue")
    private WebElement continueBtn;

    public void inputFirstName(String name)
    {
        firstNameField.sendKeys(name);
    }
    public void inputLastName(String name)
    {
        lastNameField.sendKeys(name);
    }
    public void inputPostalCode(String code)
    {
        postalCodeField.sendKeys(code);
    }

    public void pressContinue()
    {
        continueBtn.click();
    }

    public boolean isErrorOccurred()
    {
        try
        {
            WebElement element = driver.findElement(By.cssSelector("[data-test='error']"));
            return !element.isDisplayed();
        } catch (NoSuchElementException e)
        {
            return true;
        }
    }
}
