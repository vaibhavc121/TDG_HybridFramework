package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BrowserUtils
{

    private BrowserUtils()
    {

    }

    public static void openUrl(WebDriver driver, String url)
    {
        driver.navigate().to(url);
    }

    public static String getCurrentUrl(WebDriver driver)
    {
        return driver.getCurrentUrl();
    }

    public static String getTitle(WebDriver driver)
    {
        return driver.getTitle();
    }

    public static void refreshPage(WebDriver driver)
    {
        driver.navigate().refresh();
    }

    public static void maximizeWindow(WebDriver driver)
    {
        driver.manage().window().maximize();
    }

    public static void minimizeWindow(WebDriver driver)
    {
        driver.manage().window().minimize();
    }

    public static void closeBrowser(WebDriver driver)
    {
        driver.quit();
    }

    public static void navigateBack(WebDriver driver)
    {
        driver.navigate().back();
    }

    public static void navigateForward(WebDriver driver)
    {
        driver.navigate().forward();
    }

    // Switch Tab
    public static void openNewTab(WebDriver driver)
    {
        ((JavascriptExecutor) driver).executeScript("window.open();");
    }

    public static void switchToTabByIndex(WebDriver driver, int tabIndex)
    {
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        if (tabIndex < windowHandles.size())
        {
            driver.switchTo().window(windowHandles.get(tabIndex));
        }
    }

    public static void switchToLastTab(WebDriver driver)
    {
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(windowHandles.size() - 1));
    }

    public static void closeCurrentTab(WebDriver driver)
    {
        driver.close();
    }

    public static void switchToMainTab(WebDriver driver)
    {
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(0));
    }

}