import config.ConfigProvider;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static config.ConfigProvider.CONFIG_PROPS;
import static org.junit.Assert.assertEquals;

public class ProductPageTest
{
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static ProductsPage productPage;

    @BeforeClass
    public static void setup()
    {
        System.setProperty("webdriver.chrome.driver", CONFIG_PROPS.webdriverPath());

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(ConfigProvider.CONFIG_PROPS.webUrl());

        loginPage = new LoginPage(driver);
        productPage = new ProductsPage(driver);
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

    }

    @Test
    public void testAddingTwoItemsToCart()
    {
        List<String> items = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt");

        String userName = "standard_user";
        String password = "secret_sauce";

        loginPage.inputUsername(userName);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();

        productPage.addItemsToCart(items);

        assertEquals(items.size(), productPage.howMuchElementsAdded());
    }
}
