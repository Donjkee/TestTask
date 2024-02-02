package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage
{
    public WebDriver driver;

    public LogoutPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id="react-burger-menu-btn")
    WebElement reactBurgerMenuBtn;

    public void logout()
    {
        reactBurgerMenuBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logout = wait
                .until(ExpectedConditions
                        .visibilityOf(driver.findElement(By.id("logout_sidebar_link"))));
        logout.click();
    }
}
