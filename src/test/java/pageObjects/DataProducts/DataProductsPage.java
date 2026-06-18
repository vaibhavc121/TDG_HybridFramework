package pageObjects.DataProducts;

import base.BasePage;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.DriverUtils;
import utilities.JavaScriptUtils;
import utilities.ShadowDomUtils;

import static utilities.ShadowDomUtils.getShadowRoot;

public class DataProductsPage extends BasePage
{
    //region Locators
    //region Basic Info
    @FindBy(xpath="//motif-vertical-navigation-menu-item[@slot='verticalNavigationMenuElements']//span[@slot='verticalNavigationMenuItemElements'][normalize-space()='Data Products']") private WebElement dataProducts;
    @FindBy(xpath = "//input[contains(@aria-label,'Data Product Name')]") private WebElement dataProductName;
    @FindBy(xpath="//textarea[@class='motif-textarea motif-input-size-ref motif-textarea-no-auto-resize-height']") private WebElement businessValueStatement;
    @FindBy(xpath = "//input[contains(@aria-label,'Fusion Link')]") private WebElement fusionLink;
    //endregion

    //region Enabling Data Product and Governance
    @FindBy(xpath = "//input[contains(@aria-label,'Green lane (Not mandatory)')]") private WebElement greenLaneTextbox;
    //endregion

    //endregion

    //region Action Methods

    //region Basic Info
    public void clickDataProducts()
    {
        clickOnElement1(dataProducts);
    }

    public void clickCreateDataProduct()
    {
        /*SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("motif-button[type='button'][class='hydrated'][variant='primary']")).getShadowRoot();
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

    public void provideataProductName(String dpName)
    {
        clearAndProvide1(dataProductName, dpName);
    }

    public void selectBusinessProductManager()
    {
        SearchContext shadow0 = DriverFactory.getDriver().findElement(By.cssSelector("motif-select[class='hydrated'][label='Business Product Manager *']")).getShadowRoot();
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        shadow1.findElement(By.cssSelector("svg[aria-label='icon']")).click();

        waitTS(1);
        pressArrowDown();
        waitTS(1);
        pressEnter();
        pressEnter();
    }

    public void selectRegion()
    {
        SearchContext shadow0 = DriverFactory.getDriver().findElement(By.cssSelector("motif-select[class='hydrated'][label='Super Region *']")).getShadowRoot();
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        shadow1.findElement(By.cssSelector("svg[aria-label='icon']")).click();

        waitTS(1);
        pressArrowDown();
        waitTS(1);
        pressEnter();
        pressEnter();
    }

    public void selectDataType()
    {
        SearchContext shadow0 = DriverFactory.getDriver().findElement(By.cssSelector("motif-select[class='hydrated'][label='Data Type *']")).getShadowRoot();
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        shadow1.findElement(By.cssSelector("svg[aria-label='icon']")).click();

        waitTS(1);
        pressArrowDown();
        waitTS(1);
        pressEnter();
        pressEnter();
    }

    public void selectProductClassification()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Product Classification *']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectSyntheticType()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Synthetic Type *']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectGenerationApproach()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Generation Approach *']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void provideBusinessValueStatement()
    {
        clearAndProvide1(businessValueStatement, faker.lorem().sentence());
    }

    public void clickSaveAndNextBtn()
    {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement element = (WebElement) js.executeScript("return document.querySelector('motif-button.hydrated').shadowRoot.querySelector('button.motif-button')");
        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }
    public void clickFusionLink()
    {
        clickOnElement1(fusionLink);
    }
    //endregion

    //region Enabling Data Product and Governance
    public void selectGreenLane()
    {
        clearAndProvide1(greenLaneTextbox, "test vrc");
        pressArrowDown();
        pressEnter();
    }
    public void selectAccess()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Access']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectRestrictedToSuperRegion()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Restricted to Super Region']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectRestrictedToServiceLine()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Restricted to Service Line']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectRestrictedToDomain()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Restricted to Industry Sectors']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectContentJurisdiction()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Content Jurisdiction']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectStorageJurisdiction()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Storage Jurisdiction']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectPermittedUseCases()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Permitted Use Cases']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));

        SearchContext shadow0 = getShadowRoot(By.cssSelector("motif-select[class='hydrated'][label='Permitted Use Cases']"));
        SearchContext shadow1 = shadow0.findElement(By.cssSelector(".motif-select-button-trigger--icon.hydrated")).getShadowRoot();
        shadow1.findElement(By.cssSelector("svg[aria-label='icon']")).click();
        //note--clicked on 2 nested shadow dom
    }

    public void selectProhibitedUses()
    {

        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Prohibited Uses']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }
    //endregion

    //region Data Product Usage
    public void selectSourceSystem()
    {
        waitTS(2);
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Source System *']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void selectSourceTypePerSystem()
    {

        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Source Type Per System']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }
    public void selectQualityDimensions()
    {
        selectOption2(By.cssSelector("motif-select[class='hydrated'][label='Quality Dimensions']"),
                By.cssSelector(".motif-select-button-trigger--icon.hydrated"),
                By.cssSelector("svg[aria-label='icon']"));
    }

    public void clickSendForApproval()
    {
        ShadowDomUtils.clickShadowElementJS(By.cssSelector("motif-button[type='button'][class='hydrated'][variant='primary']"), By.cssSelector(".motif-button.motif-button--sm.motif-button--primary.motif-button--default"));
    }
    //endregion

    //endregion
}