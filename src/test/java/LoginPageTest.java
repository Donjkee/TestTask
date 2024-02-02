import config.ConfigProvider;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.LoginPage;

import java.time.Duration;

import static config.ConfigProvider.CONFIG_PROPS;
import static org.junit.Assert.*;

public class LoginPageTest
{
    private static WebDriver driver;
    private static LoginPage loginPage;

    @BeforeClass
    public static void setup()
    {
        System.setProperty("webdriver.chrome.driver", CONFIG_PROPS.webdriverPath());

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(ConfigProvider.CONFIG_PROPS.webUrl());
    }

    @AfterClass
    public static void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

    @Before
    public void waitForPageToLoad()
    {
        driver.navigate().to(ConfigProvider.CONFIG_PROPS.webUrl());

        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState")
                                .equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(CONFIG_PROPS.driverTimeout())));
        wait.until(pageLoadCondition);

        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginPageAccessibility()
    {
        assertTrue(driver.getTitle().contains("Swag Labs"));
    }

    @Test
    public void testValidLogin()
    {
        String userName = "standard_user";
        String password = "secret_sauce";

        String inventoryUrl = "https://www.saucedemo.com/inventory.html";

        loginPage.inputUsername(userName);
        loginPage.inputPassword(password);

        loginPage.clickLoginBtn();

        assertEquals(inventoryUrl, driver.getCurrentUrl());
    }

    @Test
    public void testInvalidLogin()
    {
        String userName = "qwerty";
        String password = "qwerty";

        loginPage.inputUsername(userName);
        loginPage.inputPassword(password);

        loginPage.clickLoginBtn();

        assertTrue(loginPage.isErrorOccurred());
    }
}

