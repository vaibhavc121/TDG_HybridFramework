package utilities;

import java.time.Duration;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import base.BaseTest;

public class WaitUtils
{
    private WaitUtils()
    {

    }

    public static void WaitUntil(By locator)
    {
        Wait<WebDriver> fluentWait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        WebElement element = fluentWait.until(driver ->
        {
            WebElement el = driver.findElement(locator);
            return (el.isDisplayed() && el.isEnabled()) ? el : null;
        });

        element.click();
    }

    public static WebElement WaitForElement(By locator)
    {
        Wait<WebDriver> fluentWait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        return fluentWait.until(driver ->
        {
            WebElement el = driver.findElement(locator);
            return (el.isDisplayed() && el.isEnabled()) ? el : null;
        });
    }

    public static void wait(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitTS(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}