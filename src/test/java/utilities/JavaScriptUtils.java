package utilities;

import factory.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static base.BasePage.waitForElement;

public class JavaScriptUtils
{
    private JavaScriptUtils()
    {

    }

    public static void provideValueJS(WebElement locator, String value)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement element = waitForElement(locator);
        jsExecutor.executeScript("arguments[0].value='" + value + "';", element);
    }

    public static void executeScript(WebDriver driver, String script, Object... args)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(script, args);
    }

    public static Object executeScriptWithReturn(WebDriver driver, String script, Object... args)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }

    public static void scrollToBottom(WebDriver driver)
    {
        executeScript(driver, "window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void scrollToTop(WebDriver driver)
    {
        executeScript(driver, "window.scrollTo(0, 0);");
    }

    public static void scrollIntoView(WebDriver driver, WebElement element)
    {
        executeScript(driver, "arguments[0].scrollIntoView(true);", element);
    }

    public static void clickElementByJavaScript(WebDriver driver, WebElement element)
    {
        executeScript(driver, "arguments[0].click();", element);
    }

    public static void setAttribute(WebDriver driver, WebElement element, String attributeName, String attributeValue)
    {
        executeScript(driver, "arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName,
                attributeValue);
    }

    public static String getAttribute(WebDriver driver, WebElement element, String attributeName)
    {
        return (String) executeScriptWithReturn(driver, "return arguments[0].getAttribute(arguments[1]);", element,
                attributeName);
    }

    public static void highlightElement(WebDriver driver, WebElement element)
    {
        executeScript(driver, "arguments[0].style.border='3px solid red'", element);
    }

    public static void refreshBrowserUsingJS(WebDriver driver)
    {
        executeScript(driver, "history.go(0);");
    }

    public static void openNewTabUsingJS(WebDriver driver)
    {
        executeScript(driver, "window.open();");
    }
}