package utilities;

import java.time.Duration;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import base.BaseTest;

public class DriverUtils
{
    private static WebDriver driver = DriverFactory.getDriver();

    private DriverUtils()
    {

    }

    public static void initializeDriver(String browserType)
    {
        if (driver != null)
        {
            return;
        }

        if (browserType.equalsIgnoreCase("Chrome"))
        {
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("Edge"))
        {
            driver = new EdgeDriver();
        } else
        {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    public static WebDriver getDriver()
    {
        if (driver == null)
        {
            throw new IllegalStateException("Driver is not initialized. Call initializeDriver first.");
        }
        return driver;
    }

    public static void quitDriver()
    {
        if (driver != null)
        {
            driver.quit();
            driver = null;
        }
    }

    public static void maximizeWindow()
    {
        driver.manage().window().maximize();
    }

    public static void minimizeWindow()
    {
        driver.manage().window().minimize();
    }

    public static void deleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }

    public static void setImplicitWait(int seconds)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public static void quitDriver(WebDriver driver)
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

    // Alert Handling Methods
    public static void acceptAlert()
    {
        driver.switchTo().alert().accept();
    }

    public static void dismissAlert()
    {
        driver.switchTo().alert().dismiss();
    }

    public static String getAlertText()
    {
        return driver.switchTo().alert().getText();
    }

    public static void sendKeysToAlert(String text)
    {
        driver.switchTo().alert().sendKeys(text);
    }

    // Frame Handling Methods
    public static void switchToFrameByIndex(int index)
    {
        driver.switchTo().frame(index);
    }

    public static void switchToFrameByNameOrId(String nameOrId)
    {
        driver.switchTo().frame(nameOrId);
    }

    public static void switchToFrameByElement(By locator)
    {
        WebElement frameElement = driver.findElement(locator);
        driver.switchTo().frame(frameElement);
    }

    public static void switchToDefaultContent()
    {
        driver.switchTo().defaultContent();
    }

    public static void switchToParentFrame()
    {
        driver.switchTo().parentFrame();
    }
}