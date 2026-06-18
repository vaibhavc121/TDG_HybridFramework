package pageObjects.greenLanes;

import base.BasePage;
import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.DriverUtils;
import utilities.JavaScriptUtils;
import utilities.ShadowDomUtils;

import java.util.List;

public class GreenLanesPage extends BasePage
{
    //region Locators

    //region Identify & Lifecycle
    @FindBy(xpath = "(//span[text()='Green Lanes'])[1]") private WebElement greenLane;
    @FindBy(id="motif-input-1eze8zd") private WebElement name;
    @FindBy(id="ownership-input") private WebElement ownership;
    @FindBy(xpath="(//textarea[@class='motif-textarea motif-input-size-ref motif-textarea-no-auto-resize-height'])[1]") private WebElement description;

    //endregion

    //region Scope and Purpose
    @FindBy(xpath = "//input[contains(@id,'motif-input')]") private WebElement serviceCode;
    @FindBy(id="country-input") private WebElement originCountry;
    //endregion

    //region Control Categories and Controls
    @FindBy(xpath="//span[contains(text(),'Choose additional controls from the options below')]") private WebElement chooseAdditionalControls;
    //endregion

    //endregion

    //region Action Methods

    //region Identify & Lifecycle

    public void clickGreenLanes()
    {
        /*ShadowDomUtils.clickShadowElement(
                By.cssSelector("motif-panel-menu-item[label='Green Lanes']"),
                By.cssSelector("p.panel-menu-item__header-label")
        );*/

        clickOnElement1(greenLane);
        //waitTS(3);
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

        //waitTS(3);
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

        JavaScriptUtils.scrollIntoView(DriverFactory.getDriver(), inputBox);

        ((JavascriptExecutor) DriverUtils.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", inputBox);

        inputBox.click();
        inputBox.clear();
        inputBox.sendKeys(faker.name().firstName() );




    }

    public void provideDate()
    {
        //This Element is inside single shadow DOM.
        String cssSelectorForHost = "motif-date-picker[class='hydrated']";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-date-picker[class='hydrated']")).getShadowRoot();
        //Thread.sleep(1000);
        shadow.findElement(By.cssSelector("input[id*='motif-input']")).sendKeys("24/05/2026");

      /*  List<WebElement>ele= DriverUtils.getDriver()
                .findElements(By.cssSelector("motif-tooltip-trigger"));
        System.out.println(ele.size());

        WebElement host = DriverUtils.getDriver()
                .findElements(By.cssSelector("motif-tooltip-trigger"))
                .get(2);   // ✅ correct index

        SearchContext shadow = host.getShadowRoot();

        WebElement inputBox = shadow.findElement(By.cssSelector("input"));

        ((JavascriptExecutor) DriverUtils.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", inputBox);

        inputBox.click();
        inputBox.clear();
        inputBox.sendKeys("25/05/2026");*/









    }
    public void selectOwnership()
    {
        clickOnElement1(ownership);
        clearAndProvide1(ownership, "Damian");
        String cssSelectorForHost = "motif-truncate[class='motif-truncate-width hydrated'][placement='bottom']";
        waitTS(3);
        pressEnter();
        //Thread.sleep(1000);
        //SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-truncate[class='motif-truncate-width hydrated'][placement='bottom']")).getShadowRoot();
        //Thread.sleep(1000);
        //shadow.findElement(By.cssSelector(".motif-truncate.motif-truncate-lines")).click();
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
        pressArrowDown();
        waitTS(1);
        pressEnter();
        pressEnter();
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
        pressArrowDown();
        waitTS(1);
        pressEnter();
        pressEnter();
    }

    public void provideDesc()
    {
        clearAndProvide1(description, "This is description for GL 24-5");
    }

    /*public void clickSaveAndNext()
    {
        ShadowDomUtils.clickShadowElement1(By.cssSelector("motif-button[type='button'][class='hydrated'][variant='primary']"), By.cssSelector(".motif-button.motif-button--sm.motif-button--primary.motif-button--default"));
    }*/

    //endregion

    //region Scope and Purpose

    public void provideServiceCode()
    {
        clearAndProvide1(serviceCode, "1234");
    }
    public void selectDataType()
    {
        //This Element is inside 2 nested shadow DOM.
        String cssSelectorForHost1 = "motif-select[class='hydrated'][label='Data type *']";
        String cssSelectorForHost2 = ".motif-select-button-trigger--icon.hydrated";
        //Thread.sleep(1000);
        SearchContext shadow0 = DriverFactory.getDriver().findElement(By.cssSelector("motif-select[class='hydrated'][label='Data type *']")).getShadowRoot();
        //Thread.sleep(1000);
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        //Thread.sleep(1000);
        WebElement icon= shadow1.findElement(By.cssSelector("svg[aria-label='icon']"));

        JavaScriptUtils.scrollIntoView(DriverFactory.getDriver(), icon);
        icon.click();
        //note: implemented scoll into view logic for shadow dom element

        waitTS(1);
        pressArrowDown();
        waitTS(1);
        pressEnter();
        pressEnter();
    }

    public void selectBusinessPurpose()
    {
        //This Element is inside 2 nested shadow DOM.
        String cssSelectorForHost1 = "motif-select[class='hydrated'][label='Business Purpose *']";
        String cssSelectorForHost2 = ".motif-select-button-trigger--icon.hydrated";
        //Thread.sleep(1000);
        SearchContext shadow0 = DriverFactory.getDriver().findElement(By.cssSelector("motif-select[class='hydrated'][label='Business Purpose *']")).getShadowRoot();
        //Thread.sleep(1000);
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        //Thread.sleep(1000);
        shadow1.findElement(By.cssSelector("svg[aria-label='icon']")).click();

        waitTS(1);
        pressArrowDown();
        waitTS(1);
        pressEnter();
        pressEnter();
    }

    public void selectOriginCountry()
    {
        clearAndProvide1(originCountry, "India");
        waitTS(3);
        pressEnter();
    }
    //endregion

    //region Control Categories and Controls
    //endregion
    public void chooseAdditionalControl()
    {
        JavaScriptUtils.scrollIntoView(DriverFactory.getDriver(), chooseAdditionalControls);
        //This Element is inside single shadow DOM.
        String cssSelectorForHost = "body > app:nth-child(1) > app-layout:nth-child(2) > div:nth-child(2) > div:nth-child(2) > app-new-greenlane:nth-child(2) > div:nth-child(2) > div:nth-child(2) > motif-card:nth-child(2) > app-step-control-domain-controls:nth-child(1) > div:nth-child(1) > motif-card:nth-child(1) > div:nth-child(3) > div:nth-child(2) > motif-accordion:nth-child(2) > motif-accordion-item:nth-child(1) > motif-accordion-content:nth-child(2) > div:nth-child(1) > div:nth-child(1) > motif-truncate:nth-child(2)";
        //Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("body > app:nth-child(1) > app-layout:nth-child(2) > div:nth-child(2) > div:nth-child(2) > app-new-greenlane:nth-child(2) > div:nth-child(2) > div:nth-child(2) > motif-card:nth-child(2) > app-step-control-domain-controls:nth-child(1) > div:nth-child(1) > motif-card:nth-child(1) > div:nth-child(3) > div:nth-child(2) > motif-accordion:nth-child(2) > motif-accordion-item:nth-child(1) > motif-accordion-content:nth-child(2) > div:nth-child(1) > div:nth-child(1) > motif-truncate:nth-child(2)")).getShadowRoot();
        //Thread.sleep(1000);
        WebElement ele= shadow.findElement(By.cssSelector(".motif-truncate.motif-truncate-lines"));

        JavaScriptUtils.scrollIntoView(DriverFactory.getDriver(), ele);
        ele.click();
    }

    public void clickSaveNext()
    {
        for(int i=0;i<=18;i++)
        {
            Actions actions = new Actions(DriverFactory.getDriver());
            actions.sendKeys(Keys.TAB).perform();
            waitTS(1);
        }
        pressEnter();
    }

    public void clickSaveNext1()
    {
        JavaScriptUtils.scrollToBottom(DriverFactory.getDriver());
        clickSaveAndNext();
    }

    public void clickSendForApproval()
    {
        JavaScriptUtils.scrollToBottom(DriverFactory.getDriver());

        String cssSelectorForHost = "motif-button[type='button'][class='hydrated'][variant='primary']";
        SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-button[type='button'][class='hydrated'][variant='primary']")).getShadowRoot();
        shadow.findElement(By.cssSelector(".motif-button.motif-button--sm.motif-button--primary.motif-button--default")).click();
    }


    //endregion
}