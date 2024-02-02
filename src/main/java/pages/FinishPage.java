package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinishPage
{
    public WebDriver driver;

    public FinishPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className="complete-header")
    WebElement confirmationMessage;

    @FindBy(className = "complete-text")
    WebElement completeText;

    @FindBy(id = "back-to-products")
    WebElement backToProductBtn;

    public boolean isConfirmationMessageAppeared(String message)
    {
        return confirmationMessage.getText().equals(message);
    }

    public boolean isCompleteTextAppeared(String message)
    {
        return completeText.getText().equals(message);
    }

    public void clickBackToProductBtn()
    {
        backToProductBtn.click();
    }
}
