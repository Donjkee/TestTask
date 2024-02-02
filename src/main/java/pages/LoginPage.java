package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage
{
    public WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="user-name")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="login-button")
    private WebElement loginBtn;

    public void inputUsername(String username)
    {
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password)
    {
        passwordField.sendKeys(password);
    }

    public void clickLoginBtn()
    {
        loginBtn.click();
    }

    public boolean isErrorOccurred()
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement errorElement = wait
                    .until(ExpectedConditions
                            .visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
            return errorElement.isDisplayed();
        } catch (TimeoutException e)
        {
            return false;
        }
    }
}
