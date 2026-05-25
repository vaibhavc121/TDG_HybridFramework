package pageObjects.greenLanes;

import base.BasePage;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.DriverUtils;
import utilities.ShadowDomUtils;


public class GreenLanesPage extends BasePage
{
    //region Locators
    @FindBy(id="motif-input-1eze8zd") private WebElement name;
    @FindBy(id="ownership-input") private WebElement ownership;
    @FindBy(xpath="(//textarea[@class='motif-textarea motif-input-size-ref motif-textarea-no-auto-resize-height'])[1]") private WebElement description;
    //endregion

    //region Action Methods
    public void clickGreenLanes()
    {
        ShadowDomUtils.clickShadowElement(
                By.cssSelector("motif-panel-menu-item[label='Green Lanes']"),
                By.cssSelector("p.panel-menu-item__header-label")
        );

    }

    public void clickCreateGreenLaneBtn()
    {
        //This Element is inside single shadow DOM.
        /*String cssSelectorForHost = "motif-button[type='button'][class='hydrated'][variant='primary']";

        SearchContext shadow = DriverUtils.getDriver().findElement(By.cssSelector("motif-button[type='button'][class='hydrated'][variant='primary']")).getShadowRoot();

        shadow.findElement(By.cssSelector("slot")).click();*/

        /*String cssSelectorForHost = "motif-button[type='button'][class='hydrated'][variant='primary']";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-button[type='button'][class='hydrated'][variant='primary']")).getShadowRoot();
        //Thread.sleep(1000);
        shadow.findElement(By.cssSelector(".motif-button.motif-button--sm.motif-button--primary.motif-button--default")).click();*/

        WebElement host = DriverUtils.getDriver()
                .findElement(By.cssSelector("motif-button[variant='primary']"));

        SearchContext shadow = host.getShadowRoot();

        WebElement button = shadow.findElement(By.cssSelector("button"));

        //  Scroll into view (important!)
        ((JavascriptExecutor) DriverUtils.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", button);

        //  Use JS click (fix interception issue)
        ((JavascriptExecutor) DriverUtils.getDriver())
                .executeScript("arguments[0].click();", button);
    }

    public void provideName()
    {
        //clearAndProvide1(name, "GL 24-5");


        /*WebElement inputBox = DriverUtils.getDriver()
                .findElement(By.id("motif-input-pdqjhm"));

        ((JavascriptExecutor) DriverUtils.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", inputBox);

        inputBox.click();
        inputBox.clear();
        inputBox.sendKeys("Test Green Lane");*/

        WebElement inputBox = DriverUtils.getDriver()
                .findElement(By.cssSelector("input[aria-label='Name *']"));

        ((JavascriptExecutor) DriverUtils.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", inputBox);

        inputBox.click();
        inputBox.clear();
        inputBox.sendKeys("Test Green Lane");




    }

    public void provideDate()
    {
        //This Element is inside single shadow DOM.
        String cssSelectorForHost = "motif-date-picker[class='hydrated']";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-date-picker[class='hydrated']")).getShadowRoot();
        //Thread.sleep(1000);
        shadow.findElement(By.cssSelector("#motif-input-ziktpm")).sendKeys("24/05/2026");
    }
    public void selectOwnership()
    {
        clickOnElement1(ownership);
        clearAndProvide1(ownership, "Damian");
        String cssSelectorForHost = "motif-truncate[class='motif-truncate-width hydrated'][placement='bottom']";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-truncate[class='motif-truncate-width hydrated'][placement='bottom']")).getShadowRoot();
        //Thread.sleep(1000);
        shadow.findElement(By.cssSelector(".motif-truncate.motif-truncate-lines")).click();
    }

    public void selectServiceLine()
    {
        //This Element is inside 2 nested shadow DOM.
        String cssSelectorForHost1 = "motif-select[class='hydrated'][label='Service Line *']";
        String cssSelectorForHost2 = ".motif-select-button-trigger--icon.hydrated";
        //Thread.sleep(1000);
        SearchContext shadow0 = DriverFactory.getDriver().findElement(By.cssSelector("motif-select[class='hydrated'][label='Service Line *']")).getShadowRoot();
        //Thread.sleep(1000);
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        //Thread.sleep(1000);
        shadow1.findElement(By.cssSelector("svg[aria-label='icon']")).click();

        waitTS(1);
        //This Element is inside single shadow DOM.
        String cssSelectorForHost = "motif-select-option[value='ASU']";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-select-option[value='ASU']")).getShadowRoot();
        //Thread.sleep(1000);
        shadow.findElement(By.cssSelector("slot")).click();
    }

    public void selectSuperRegion()
    {
        //This Element is inside 2 nested shadow DOM.
        String cssSelectorForHost1 = "motif-select[class='hydrated'][label='Super Region *']";
        String cssSelectorForHost2 = ".motif-select-button-trigger--icon.hydrated";
        //Thread.sleep(1000);
        SearchContext shadow0 = DriverFactory.getDriver().findElement(By.cssSelector("motif-select[class='hydrated'][label='Super Region *']")).getShadowRoot();
        //Thread.sleep(1000);
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        //Thread.sleep(1000);
        shadow1.findElement(By.cssSelector("svg[aria-label='icon']")).click();

        waitTS(1);
        //This Element is inside single shadow DOM.
        String cssSelectorForHost = "motif-select-option[value='Gbl']";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-select-option[value='Gbl']")).getShadowRoot();
        //Thread.sleep(1000);
        shadow.findElement(By.cssSelector("slot")).click();
    }

    public void provideDesc()
    {
        clearAndProvide1(description, "This is description for GL 24-5");
    }

    public void clickSaveAndNext()
    {
        //This Element is inside single shadow DOM.
        String cssSelectorForHost = "motif-button[type='button'][class='hydrated'][variant='primary']";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-button[type='button'][class='hydrated'][variant='primary']")).getShadowRoot();
        //Thread.sleep(1000);
        shadow.findElement(By.cssSelector("slot")).click();
    }

    //endregion
}