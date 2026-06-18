package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory1
{
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get driver
    public static WebDriver getDriver()
    {
        return driver.get();
    }

    // Initialize driver based on browser
    public static void setDriver(String browser)
    {
        WebDriver driverInstance = null;

        if (browser.equalsIgnoreCase("chrome"))
        {
            driverInstance = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge"))
        {
            driverInstance = new EdgeDriver();
        }

        driver.set(driverInstance);
    }

    // Quit driver
    public static void quitDriver()
    {
        if (driver.get() != null)
        {
            driver.get().quit();
            driver.remove();
        }
    }
}