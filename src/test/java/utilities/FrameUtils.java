package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameUtils
{
    private FrameUtils()
    {

    }

    public static void switchToFrameByIndex(WebDriver driver, int index)
    {
        driver.switchTo().frame(index);
    }

    public static void switchToFrameByNameOrId(WebDriver driver, String nameOrId)
    {
        driver.switchTo().frame(nameOrId);
    }

    public static void switchToFrameByElement(WebDriver driver, By locator)
    {
        WebElement frameElement = driver.findElement(locator);
        driver.switchTo().frame(frameElement);
    }

    public static void switchToDefaultContent(WebDriver driver)
    {
        driver.switchTo().defaultContent();
    }

    public static void switchToParentFrame(WebDriver driver)
    {
        driver.switchTo().parentFrame();
    }
}