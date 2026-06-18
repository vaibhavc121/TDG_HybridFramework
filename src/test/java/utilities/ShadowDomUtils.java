package utilities;

import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShadowDomUtils
{
    private ShadowDomUtils()
    {}
    static WebDriverWait wait;


    // Get Shadow Root
    public static SearchContext getShadowRoot(By shadowHost)
    {
        WebElement hostElement = DriverFactory.getDriver().findElement(shadowHost);
        return hostElement.getShadowRoot();
    }

    // Click Element Inside Shadow DOM
    public static void clickShadowElement(By shadowHost, By elementLocator)
    {
        SearchContext shadow = getShadowRoot(shadowHost);
        shadow.findElement(elementLocator).click();
    }

    public static void clickShadow2Element(By shadowHost0, By shadowHost1, By elementLocator)
    {
        SearchContext shadow0 = getShadowRoot(shadowHost0);
        SearchContext shadow1 = shadow0.findElement(shadowHost1).getShadowRoot();
        shadow1.findElement(elementLocator).click();
    }

    public static void clickShadowElementJS(By shadowHost, By shadowElement)
    {
        wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        WebElement host = wait.until(
                ExpectedConditions.visibilityOfElementLocated(shadowHost));

        SearchContext shadowRoot = host.getShadowRoot();

        WebElement element = shadowRoot.findElement(shadowElement);

        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();

        js.executeScript("arguments[0].scrollIntoView(true);", element);

        wait.until(ExpectedConditions.visibilityOf(element));

        wait.until(ExpectedConditions.elementToBeClickable(element));

        try
        {
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e)
        {
            element.click();
        }
    }

    public static void clickShadowElement2(By shadowHostLocator, By innerElementLocator) {

        WebDriver driver = DriverFactory.getDriver(); // however you get driver
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1: Find shadow host
        WebElement shadowHost = driver.findElement(shadowHostLocator);

        // Step 2: Get shadow root
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // Step 3: Find inner element
        WebElement element = shadowRoot.findElement(innerElementLocator);

        // ✅ Step 4: Scroll into view FIRST
        js.executeScript("arguments[0].scrollIntoView();", true);

        // ✅ Step 5: Then click using JS
        js.executeScript("arguments[0].click();", element);
    }

    // Send Text Inside Shadow DOM
    public static void sendKeysShadowElement(By shadowHost,
                                      By elementLocator,
                                      String text)
    {
        SearchContext shadow = getShadowRoot(shadowHost);
        shadow.findElement(elementLocator).sendKeys(text);
    }

    // Get Text From Shadow DOM Element
    public static String getTextShadowElement(By shadowHost,
                                       By elementLocator)
    {
        SearchContext shadow = getShadowRoot(shadowHost);
        return shadow.findElement(elementLocator).getText();
    }

    public static WebElement getShadowElement(By shadowHost, By shadowElement)
    {
        SearchContext shadow = getShadowRoot(shadowHost);
        return shadow.findElement(shadowElement);
    }

}