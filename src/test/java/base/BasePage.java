package base;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import factory.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import com.github.javafaker.Faker;

import org.testng.asserts.SoftAssert;
/*
import pageObjects.HRMS.Attendance.AttendancePage;
import pageObjects.HRMS.Global.TopNavigationBar;
import pageObjects.HRMS.HRCore.EmployeePage;
import pageObjects.HRMS.HRCore.HRCorePage;
import pageObjects.HRMS.Learning.LearningPage;
import pageObjects.HRMS.Login.LoginPage;
import pageObjects.HRMS.Onboarding.OnboardingPage;
import pageObjects.HRMS.Payroll.PayrollPage;
import pageObjects.HRMS.Recruitment.RecruitmentPage;
import pageObjects.HRMS.SelfService.SelfServicePage;
import pageObjects.HRMS.SuccessionPlanning.SuccessionPage;
*/
import utilities.BrowserUtils;
import utilities.JavaScriptUtils;
import utilities.ShadowDomUtils;

public class BasePage
{

    Robot robot;
    public static SoftAssert softAssert = new SoftAssert();

    // region Constructor
    public BasePage()
    {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
    // endregion

    //region Left Navigation Sidebar

    public static void openSidebar() throws InterruptedException
    {
        boolean value= ShadowDomUtils.getShadowElement(By.cssSelector("motif-panel-menu-item[label='Human In The Loop']"),
                By.cssSelector("p.panel-menu-item__header-label")).isDisplayed();
        if (!value)
        {
            /*ShadowDomUtils.clickShadowElement1(By.cssSelector("motif-icon"),
                    By.cssSelector("svg[aria-label='icon']"));*/
            //This Element is inside single shadow DOM.
            String cssSelectorForHost = "body > app:nth-child(1) > app-layout:nth-child(2) > app-header:nth-child(1) > motif-header:nth-child(1) > div:nth-child(2) > motif-button:nth-child(1) > motif-icon:nth-child(1)";
            Thread.sleep(1000);
            SearchContext shadow = DriverFactory.getDriver().findElement(By.cssSelector("body > app:nth-child(1) > app-layout:nth-child(2) > app-header:nth-child(1) > motif-header:nth-child(1) > div:nth-child(2) > motif-button:nth-child(1) > motif-icon:nth-child(1)")).getShadowRoot();
            Thread.sleep(1000);
            shadow.findElement(By.cssSelector("svg[aria-label='icon']")).click();
            BaseTest.log("Clicked on hamburger icon to open the sidebar");
        } else
        {
            BaseTest.log("Sidebar is already opend, no need to click on sidebar/logo icon");
        }

    }

    public static void clickMenuIcon() throws InterruptedException
    {
        openSidebar();
        clickMenuIcon1();
    }

    public static void clickMenuIcon1()
    {
        /*
        public static void openMenuIfNotOpened1()
        {
            try
            {
                // Check invisibility using JS instead of WebDriverWait
                JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();

                // Execute JS to see if "Apps" label is visible in DOM
                Boolean invisible = (Boolean) js.executeScript(
                        "let el = document.evaluate(\"//label[contains(text(),'Apps')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                                "return (el === null || el.offsetParent === null);"
                );

                if (invisible)
                {
                    BaseTest.log("Clicked on menu icon to access the modules.");

                    // Click the menu icon via JS
                    js.executeScript(
                            "document.evaluate(\"//i[@class='dx-icon dx-icon-grid-light']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();"
                    );
                } else
                {
                    BaseTest.log("Menu is already opened, no need to click on it.");
                }
            } catch (Exception e)
            {
                BaseTest.log("Menu is already opened, no need to click on it");
            }
        }

        */

        /*
         public static void openMenuIfNotOpened2()
        {
            try
            {
                JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();

                // Use JS to check if the <label> containing 'Apps' is present and visible
                Boolean isVisible = (Boolean) js.executeScript(
                        "let el = document.evaluate(\"//label[contains(text(),'Apps')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                                "return (el !== null && el.offsetParent !== null);"
                );

                if (!isVisible)
                {
                    // Click the menu icon using JS
                    WebElement menuIcon = (WebElement) js.executeScript(
                            "return document.evaluate(\"//i[@class='dx-icon dx-icon-grid-light']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
                    );
                    menuIcon.click();

                    BaseTest.log("Clicked on menu icon to access the modules");
                } else
                {
                    BaseTest.log("Menu is already opened, no need to click on it");
                }
            } catch (Exception e)
            {
                BaseTest.log("Exception while checking or clicking menu: " + e.getMessage());
            }
        }
        */

        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();

        // Check if Apps label exists in DOM
        String appsText = (String) js.executeScript(
                "var el = document.evaluate(\"//label[contains(text(),'Apps')]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                        "return el ? el.textContent.trim() : '';"
        );

        if (appsText == null || !appsText.contains("Apps"))
        {
            // Menu not opened, so click menu icon immediately
            js.executeScript(
                    "var el = document.evaluate(\"//i[@class='dx-icon dx-icon-grid-light']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                            "if (el) el.click();"
            );
            BaseTest.log("Clicked on menu icon to access the modules");
        } else
        {
            BaseTest.log("Menu is already opened, no need to click on it");
        }
        waitTS(1);
    }

    public static void clickOnModule(String moduleName)
    {
        waitForElement1(By.xpath("//span[normalize-space()='" + moduleName + "']")).click();
    }

    public static void navigateToModule(String moduleName) throws InterruptedException
    {
        openSidebar();
        clickMenuIcon();
        clickOnModule(moduleName);
        waitTS(2);
    }

    //region Modules
    /*
    public static void clickOnHRCore()
    {
        HRCorePage.clickHRCore();
    }
    public static void clickOnPayroll()
    {
        PayrollPage.clkPayroll();
    }
    public static void clickOnSelfService()
    {
        SelfServicePage.clickSelfService();
    }
    public static void clickOnAttendance()
    {
        AttendancePage.clickAttendance();
    }
    public static void clickOnLearning()
    {
        LearningPage.clickLearning();
    }
    public static void clickOnRecruitment()
    {
        RecruitmentPage.clickRecruitment();
    }
    public static void clickOnOnboarding()
    {
        OnboardingPage.clickOnboarding();
    }

    public static void clickOnSuccessionPlanning()
    {
        SuccessionPage.clickSuccessionPlanning();
    }
    */
    //endregion

    //endregion

    // region For fake data generation
    public Faker faker = new Faker();
    // endregion

    // region For Random data generation
    public String randomString()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return generatedString;
    }

    public String randomNumber()
    {
        String generatedNumber = RandomStringUtils.randomNumeric(4);
        return generatedNumber;
    }

    public String randomAlphaNumeric()
    {
        String alphanumeric = RandomStringUtils.randomAlphanumeric(10);
        return alphanumeric;
    }

    public String randomEmail()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        String generatedNumber = RandomStringUtils.randomNumeric(3);
        return generatedString + generatedNumber + "@" + "gmail.com";
    }

    public String randomMblNum()
    {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }
    // public String randomAlphaNumeric()
    // {
    // String generatedString = RandomStringUtils.randomAlphabetic(3);
    // String generatedNumber = RandomStringUtils.randomNumeric(3);
    // return (generatedString + "@" + generatedNumer);
    // }
    // endregion

    // region Listing Filters (Relative xpath)
    public static void filterByIndex(int columnIndex, String value)
    {
        waitTS(2);
        // I expect the index to change dynamically
        String xpath = "(//input[@class='dx-texteditor-input'])[" + columnIndex + "]";
        try
        {
            waitForElement1(By.xpath(xpath)).clear();
            BaseTest.log("value cleared in filter box");
        } catch (Exception e)
        {
            waitForElement1(By.xpath(xpath)).clear();
            BaseTest.log("value cleared in filter box");
        }

        waitForElement1(By.xpath(xpath)).sendKeys(value);
        BaseTest.log("provided filter value");
        pressEnter();
    }

    // other approach
    public static void filterValue(int columnIndex, String value)
    {
        String xpath = "(//tbody//tr)[11]//td[" + columnIndex + "]";
        waitForElement1(By.xpath(xpath)).sendKeys(value);
    }

    public static void filterDateByIndex(int columnIndex, String value)
    {
        String xpath = "(//input[@class='dx-texteditor-input'])[" + columnIndex + "]";
        waitForElement1(By.xpath(xpath)).clear();
        waitForElement1(By.xpath(xpath)).sendKeys(value);
        pressEnter();
    }

    public static void filterAndOpenTransaction(int filterIndex, int resultIndex, String expValue, String mode)
    {
        filterByIndex(filterIndex, expValue);
        waitTS(2);
        String actValue = resultValue(resultIndex);
        if (actValue.contains(expValue))
        {
            selectRow();
            if (mode.contains("view"))
            {
                clickOnViewListing();
            } else
            {
                clickOnEditListing();
            }
        } else
        {
            throw new RuntimeException("VRC- No matching record found");
        }
    }
    // endregion

    // region Listing result (Relative xpath)
    public static void selectRow()
    {
        // DriverFactory.getDriver().findElement(By.xpath("(//tr)[12]//td[2]")).click();
        //(//td[@role='gridcell'])[13]
        try
        {
            waitForElement1(By.xpath("(//tr)[6]//td[2]")).click();
        } catch (Exception e)
        {
            waitForElement1(By.xpath("(//tr)[6]//td[1]")).click();
        }
        BaseTest.log("row selected");
    }

    public static String resultValue(int columnIndex)
    {
        // String result =
        // DriverFactory.getDriver().findElement(By.xpath("(//tbody//tr)[12]//td[2]")).getText();
        // return result;
        String xpath = "(//tbody//tr)[6]//td[" + columnIndex + "]";
        try
        {
            BaseTest.log("extracting text from the result");
            String result = waitForElement1(By.xpath(xpath)).getText();
            BaseTest.log("text extracted from the result");
            return result;
        } catch (Exception e)
        {
            BaseTest.log("No matching record found");
            return "No matching record found";
        }
        // String xpath = "(//tbody//tr)[12]//td[" + columnIndex + "]";
        // String result = DriverFactory.getDriver().findElement(By.xpath(xpath)).getText();
        // return result;
    }

    public static void clickOnEditListing()
    {
        //img[@id='MainMenu_DXI15_PImg']
        //clickOnElement(By.xpath("//img[contains(@class,'dxWeb_mAdaptiveMenu_Office365 dxm-pImage')]"));
        //clickOnElement(By.xpath("//span[@title='Edit']"));
        try
        {
            clickOnElement(By.xpath("//img[@id='MainMenu_DXI3_Img']"));
        } catch (Exception e)
        {
            clickOnElement(By.xpath("//img[@id='MainMenu_DXI4_Img']"));
        }
    }
    // endregion

    // region Transaction form related Action Methods
    public static void clickOnHamburgerMenu()
    {
        waitForElement1(By.id("leftAreaMenu_DXI0_")).click();
    }

    public static void clickOnOk()
    {
        waitForElement1(By.xpath("//span[normalize-space()='OK']")).click();
    }

    public static void clickOnElement(By locator)
    {
        WebElement element = waitForElement1(locator);
        element.click();
    }

    public static void clickOnElementNS(String value)
    {
        WebElement element = waitForElement1(By.xpath("//*[normalize-space()='" + value + "']"));
        element.click();
    }

    public static void clickOnElement1(WebElement ele)
    {
        WebElement element = waitForElement(ele);
        element.click();
    }

    public static void clickOnSave()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Save']")).click();
        waitTS(2);
    }

    public void clickSubmit()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Submit']")).click();
    }

    public void clickSaveSubmit()
    {
        waitForElement1(By.xpath("//span[text()='Save and Submit']")).click();
    }

    public static void clickSaveAndBack()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Save']")).click();
        DriverFactory.getDriver().navigate().back();
    }

    public static void clickOnViewListing()
    {
        waitForElement1(By.xpath("//img[@id='MainMenu_DXI3_Img']")).click();
        BaseTest.log("Clicked on view");
    }

    public static void clickOnViewTxn()
    {
        waitForElement1(By.xpath("//span[normalize-space()='View']")).click();
        waitTS(2);
    }

    public static void clickOnApprove()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Approve']")).click();
    }

    public static void clickOnViewApproveBack()
    {
        waitForElement1(By.xpath("//span[normalize-space()='View']")).click();
        waitTS(1);
        waitForElement1(By.xpath("//span[normalize-space()='Approve']")).click();
        waitTS(1);
        DriverFactory.getDriver().navigate().back();
    }

    public static void clickOnSaveAndSubmitBack()
    {
        WebElement element = waitForElement1(By.xpath("//span[normalize-space()='Save and Submit']"));
        JavaScriptUtils.clickElementByJavaScript(DriverFactory.getDriver(), element);
        waitTS(1);
        DriverFactory.getDriver().navigate().back();
    }

    public static void clickOnSaveViewApproveBack()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Save']")).click();
        waitTS(1);
        waitForElement1(By.xpath("//span[normalize-space()='View']")).click();
        waitTS(1);
        waitForElement1(By.xpath("//span[normalize-space()='Approve']")).click();
        waitTS(1);
        DriverFactory.getDriver().navigate().back();
    }

    public static void clickReject()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Reject']")).click();
    }

    public static void clickRevise()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Revise']")).click();
    }

    public static void clickAttachFile()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Attach Files']")).click();
    }

    public static void clickViewAndBack()
    {
        waitForElement1(By.xpath("//span[normalize-space()='View']")).click();
        waitTS(2);
        clickOnEdit();
        clickOnViewTxn();
        DriverFactory.getDriver().navigate().back();
    }

    public static void clickOnEdit()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Edit']")).click();
        BaseTest.log("Clicked on edit");
    }

    public static void clickApproveAndBack()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Approve']")).click();
        DriverFactory.getDriver().navigate().back();
    }

    public static void clickSubmitAndBack()
    {
        waitForElement1(By.xpath("//span[normalize-space()='Submit']")).click();
        DriverFactory.getDriver().navigate().back();
    }

    public static void clickOnNew()
    {
        try
        {
            waitForElement1(By.xpath("//span[normalize-space()='New']")).click();
        } catch (Exception e)
        {
            waitForElement1(By.xpath("//span[normalize-space()='New']")).click();
        }
    }

    public static void selectDropdownOption(String expectedValue)
    {
        List<WebElement> dropdownList = waitForElements2(By.xpath("//div[@class='dx-item dx-list-item']"));
        for (WebElement dropdownElement : dropdownList)
        {
            String actualValue = dropdownElement.getText().trim().toLowerCase();
            if (actualValue.contains(expectedValue.toLowerCase()))
            {
                dropdownElement.click();
                BaseTest.log("Value selected from result: " + expectedValue);
                break;
            }
        }
        BaseTest.log("No matching value found in result: " + expectedValue);
    }

    public static void selectDropdownValue(String value)
    {
        while (true)
        {
            List<WebElement> valuesList = waitForElements2(By.xpath("//div[@class='grid-row-template']"));
            for (WebElement valueElement : valuesList)
            {
                String actualValue = valueElement.getText();
                if (actualValue.contains(value))
                {
                    valueElement.click();
                    return;
                }
            }
            waitForElement1(By.xpath("//i[@class='dx-icon dx-icon-next-icon']")).click();
            waitTS(3);
        }
    }

    public static void selectDropdownValueOffice365(String value)
    {
        List<WebElement> valuesList = waitForElements2(By.xpath("//tr[@class='dxeListBoxItemRow_Office365']"));
        for (WebElement valueElement : valuesList)
        {
            String actualValue = valueElement.getText();
            if (actualValue.contains(value))
            {
                valueElement.click();
                break;
            }
        }
    }

    public static void selectValue(By locator, String value)
    {
        List<WebElement> paymentTypeBtns = waitForElements2(locator);

        for (WebElement btn : paymentTypeBtns)
        {
            if (btn.getText().equalsIgnoreCase(value))
            {
                clickOnElement1(btn);
                break;
            }
        }
    }

    public static void clearAndProvide(By locator, String value)
    {
        WebElement element = waitForElement1(locator);
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public static void clearAndProvide1(WebElement locator, String value)
    {
        WebElement element = waitForElement(locator);
        element.click();
        Actions actions = new Actions(DriverFactory.getDriver());
        waitTS(1);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
        waitTS(1);
        element.sendKeys(value);
    }

    public static void clearAndProvide2(WebElement locator, String value) throws InterruptedException
    {
        WebElement element = waitForElement(locator);
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).click().doubleClick().sendKeys(value).build().perform();
        waitTS(2);
    }

    public static void provideAndEnter(WebElement locator, String value)
    {
        WebElement element = waitForElement(locator);
        element.click();
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
        element.sendKeys(value);
        waitTS(2);
        element.sendKeys(Keys.ENTER);
    }

    public static void provideValueJS(WebElement locator, String value)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement element = waitForElement(locator);
        jsExecutor.executeScript("arguments[0].value='" + value + "';", element);
    }

    public static void provideDescription(String value)
    {
        By description = By.xpath("//textarea[contains(@id,'Description')]");
        clearAndProvide(description, value);
    }

    public static void globalSearch(String value)
    {
        /*        WebElement globalSearchInput = waitForElement1(By.id("GlobalSearch"));
        globalSearchInput.click();
        WebElement comboBoxInput = waitForElement1(By.xpath("//input[@role='combobox']"));
        comboBoxInput.sendKeys(value);
        waitTS(2);
        selectDropdownOption(value);*/

        /*TopNavigationBar.globalSearch(value);*/
    }

    public static void scrollDownWebPageSample()
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement element = waitForElement1(By.xpath("//input[contains(@id,'OldContractSalary')]"));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollDownWebPage(WebElement locator)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement element = waitForElement(locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickOnNewLine()
    {
        waitForElement1(By.xpath("//i[@class='dx-icon dx-icon-new-icon']")).click();
    }

    public static void hoverAndClick(WebElement locator, WebElement locator1)
    {
        WebElement elementToHover = waitForElement(locator);
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(elementToHover).perform();
        waitForElement(locator1).click();
    }

    public static void deleteTxn(int index, String value)
    {
        filterByIndex(index, value);
        waitTS(2);
        try
        {
            waitForElement1(By.xpath("(//tr)[6]//td[2]")).click();
            BaseTest.log("row selected");
        } catch (Exception e)
        {
            Assert.fail("Vaibhav- There is no active records..");
            System.exit(1);
        }
        try
        {
            clickOnViewListing();
            BaseTest.log("clickOnView");
        } catch (Exception e)
        {
            clickOnEdit();
            BaseTest.log("clickOnEdit");
        }
        waitTS(5);
        try
        {
            waitForElement1(By.xpath("(//img[@class='dxWeb_mAdaptiveMenu_Office365 dxm-pImage'])[1]")).click();
        } catch (Exception e)
        {
            waitForElement1(By.xpath("(//img[@class='dxWeb_mAdaptiveMenu_Office365 dxm-pImage'])[8]")).click();
        }

        BaseTest.log("clciked on setting");
        waitForElement1(By.xpath("//span[normalize-space()='Delete']")).click();
        BaseTest.log("clicked on delete");
        waitTS(1);
        pressKey("enter");
        BaseTest.log("enter pressed");
        DriverFactory.getDriver().navigate().back();
        BaseTest.log("went back to listing");
    }

    public static void deleteTxnFromListing(String expValue, int filterIndex, int resultIndex)
    {
        filterByIndex(filterIndex, expValue);
        waitTS(2);
        String actValue = resultValue(resultIndex);
        if (actValue.contains(expValue))
        {
            selectRow();
            clickOnElement(By.xpath("//img[@id='MainMenu_DXI11_PImg']"));
            BaseTest.log("Clicked on Context Menu");
            clickOnElement(By.xpath("//span[@title='Delete']"));
            BaseTest.log("Clicked on Delete option");
            waitTS(2);
            pressEnter();
        } else
        {
            throw new RuntimeException("VRC- No matching record found");
        }
    }

    public static void performAction(int index, String value, String action)
    {
        filterByIndex(index, value);
        waitTS(2);
        try
        {
            // Need to select row to click on view
            try
            {
                waitForElement1(By.xpath("(//tr)[6]//td[2]")).click();
            } catch (Exception e)
            {
                waitForElement1(By.xpath("(//tr)[9]//td[2]")).click();
            }

            BaseTest.log("clicked on row");
        } catch (Exception e)
        {
            Assert.fail("Vaibhav - There are no active records.");
            System.exit(1); // Exit application
        }
        try
        {
            try
            {
                clickOnViewListing();
            } catch (Exception e)
            {
                clickOnElement(By.xpath("//img[@id='MainMenu_DXI4_Img']"));
            }
            BaseTest.log("clicked on view");
        } catch (Exception e)
        {
            //clickOnEdit();
            clickOnEditListing();
            BaseTest.log("clicked on edit");
        }
        waitTS(5);

        // Click on menu image icon
        try
        {
            waitForElement1(By.xpath("(//img[contains(@class,'dxWeb_mAdaptiveMenu_Office365 dxm-pImage')])[1]")).click();
        } catch (Exception e)
        {
            waitForElement1(By.xpath("//img[@id='MainMenu_DXI16_PImg']")).click();
        }

        waitTS(2);
        BaseTest.log("Clicked on context menu");

        //if action is cancel
        if (action.equalsIgnoreCase("Cancel"))
        {
            waitForElement1(By.xpath("//span[normalize-space()='" + action + "']")).click();
            BaseTest.log("Clicked on " + action);
            return;
        }

        if (action.equalsIgnoreCase("Cancel Resumption"))
        {
            waitForElement1(By.xpath("//span[normalize-space()='Cancel Resumption']")).click();
            waitTS(1);
            pressKey("enter");
            BaseTest.log("pressed enter");
            BrowserUtils.navigateBack(DriverFactory.getDriver());
            BaseTest.log("navigated back to listing");
            return;
        }

        // Click the action (e.g., Delete, View, Edit)
        waitForElement1(By.xpath("//span[normalize-space()='" + action + "']")).click();
        BaseTest.log("Clicked on " + action);

        if (action.equalsIgnoreCase("Edit Release"))
        {
            return;
        }

        waitTS(1);
        pressKey("enter");
        BaseTest.log("pressed enter");

        if (action.equalsIgnoreCase("Delete"))
        {
            return;
        }

        // Delete the transaction
        try
        {
            waitForElement1(By.xpath("(//img[contains(@class,'dxWeb_mAdaptiveMenu_Office365 dxm-pImage')])[1]")).click();
        } catch (Exception e)
        {
            waitForElement1(By.xpath("//img[@id='MainMenu_DXI16_PImg']")).click();
        }
        waitTS(2);
        BaseTest.log("clicked on context menu");

        waitForElement1(By.xpath("//span[normalize-space()='Delete']")).click();
        BaseTest.log("clicked on delete");
        BaseTest.log("Transaction deleted successfully");

        waitTS(1);

        pressKey("enter");
        BaseTest.log("pressed enter button");

        BrowserUtils.navigateBack(DriverFactory.getDriver());
        BaseTest.log("navigate back to listing");
    }

    public static void deleteHrCoreTxn(int ColumnIndex, String value)
    {
        filterByIndex(ColumnIndex, value);
        waitTS(2);
        try
        {
            if (ColumnIndex == 2)
            {
                waitForElement1(By.xpath("(//tr)[6]//td[1]")).click();
                BaseTest.log("row selected from listing");
            } else
            {
                waitForElement1(By.xpath("(//tr)[6]//td[2]")).click();
                BaseTest.log("row selected from listing");
            }
        } catch (Exception e)
        {
            Assert.fail("Vaibhav- There is no active records..");
            System.exit(1);
        }
        try
        {
            clickOnViewListing();
        } catch (Exception e)
        {
            waitForElement1(By.xpath("(//img[contains(@class,'dxWeb_mAdaptiveMenu_Office365 dxm-pImage')])[1]")).click();
            BaseTest.log("Clicked on context menu");
            clickOnEdit();
        }
        waitTS(5);
        try
        {
            waitForElement1(By.xpath("(//img[contains(@class,'dxWeb_mAdaptiveMenu_Office365 dxm-pImage')])[1]")).click();
            BaseTest.log("Clicked on context menu");
        } catch (Exception e)
        {
            try
            {
                waitForElement1(By.xpath("//img[@id='MainMenu_DXI26_PImg']")).click();
                BaseTest.log("Clicked on context menu");
            } catch (Exception e1)
            {
                waitForElement1(By.xpath("//img[@id='MainMenu_DXI16_PImg']")).click();
                BaseTest.log("Clicked on context menu");
            }
        }

        waitForElement1(By.xpath("//span[normalize-space()='Delete']")).click();
        BaseTest.log("Clicked on delete");
        waitTS(1);
        pressKey("enter");
        BaseTest.log("Pressed enter key");
        DriverFactory.getDriver().navigate().back();
        BaseTest.log("Navigated back to listing");
    }

    public static void clickOnContextMenu()
    {
        waitForElement1(By.id("MainMenu_DXI11_P")).click();
    }
    // endregion

    // region Employee listing
    public static void filterEmployee(String value)
    {
        // DriverFactory.getDriver().findElement(By.id("//input[@aria-describedby='dx-col-4']")).sendKeys(value);
        waitForElement1(By.id("//input[@aria-describedby='dx-col-4']")).sendKeys(value);
    }

    public static String resultEmployee()
    {
        // String result = DriverFactory.getDriver().findElement(By.xpath(
        // "/html[1]/body[1]/div[6]/div[2]/div[1]/div[2]/div[1]/div[7]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[2]/p[1]/span[1]/a[1]")).getText();
        String result = waitForElement1(By.xpath(
                "/html[1]/body[1]/div[6]/div[2]/div[1]/div[2]/div[1]/div[7]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/div[2]/p[1]/span[1]/a[1]"))
                .getText();
        return result;
    }

    public static String result()
    {
        // String result = DriverFactory.getDriver().findElement(By.xpath("//td[@class='list-hyperlink
        // dx-cell-focus-disabled']")).getText();
        String result = waitForElement1(By.xpath("//td[@class='list-hyperlink dx-cell-focus-disabled']")).getText();
        return result;
    }

    public static void clickResult(String value)
    {
        // WebElement employee =
        // DriverFactory.getDriver().findElement(By.xpath("//td[@class='list-hyperlink
        // dx-cell-focus-disabled']"));
        WebElement employee = waitForElement1(By.xpath("//td[@class='list-hyperlink dx-cell-focus-disabled']"));
        String result = employee.getText();
        if (result.contains(value))
        {
            employee.click();
        }
    }

    public static void navigateToEmployee(String value)
    {
        // selectFilterAll();
        filterByIndex(2, value);
        BaseTest.log("value filtered");
        waitTS(2);
        String employee = resultValue(1);
        // Thread.sleep(2000);
        if (employee.contains(value))
        {
            selectRow();
            BaseTest.log("row selected");
            try
            {
                try
                {
                    String empText = waitForElement1(By.xpath("//span[@class='dxeBase_Office365 form-title listing-title dx-nowrap']")).getText();
                    if (empText.contains("Employees"))
                    {
                        clickOnElement(By.xpath("//img[@id='MainMenu_DXI4_Img']"));
                    } else
                    {
                        clickOnViewListing();
                    }
                } catch (Exception e1)
                {
                    clickOnElement(By.xpath("//img[@id='MainMenu_DXI4_Img']"));
                }
                BaseTest.log("clicked on view");
            } catch (Exception e)
            {
                //clickOnEdit();
                clickOnEditListing();
                BaseTest.log("clicked On Edit");
            }
        } else
        {
            throw new RuntimeException("VRC- No matching record found");
        }
    }

    public static void switchTab()
    {
        String originalWindow = DriverFactory.getDriver().getWindowHandle();
        // Get all window handles
        Set<String> allWindows = DriverFactory.getDriver().getWindowHandles();
        // Iterate through the window handles
        for (String windowHandle : allWindows)
        {
            if (!windowHandle.equals(originalWindow))
            {
                // Switch to the new window
                DriverFactory.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void switchTabByTitle(String expectedTitle)
    {
        String originalWindow = DriverFactory.getDriver().getWindowHandle();
        Set<String> allWindows = DriverFactory.getDriver().getWindowHandles();

        for (String windowHandle : allWindows)
        {
            DriverFactory.getDriver().switchTo().window(windowHandle);
            String actualTitle = DriverFactory.getDriver().getTitle();

            if (actualTitle.equalsIgnoreCase(expectedTitle))
            {
                System.out.println("Switched to window with title: " + actualTitle);
                return; // Found the correct tab
            }
        }

        // If no matching title found, switch back to the original
        DriverFactory.getDriver().switchTo().window(originalWindow);
        throw new RuntimeException("No window found with title: " + expectedTitle);
    }

    public static void closeUnwantedTab()
    {
        // Step 1: Store all tab handles
        Set<String> allTabs = DriverFactory.getDriver().getWindowHandles();
        Iterator<String> tabIterator = allTabs.iterator();

        // Step 2: Get first and second tab handles
        String firstTab = tabIterator.next();
        String secondTab = tabIterator.next(); // This is the one currently focused (to be closed)

        // Step 3: Close current (second) tab
        DriverFactory.getDriver().close(); // This will close the current focused tab

        // Step 4: Switch back to the first tab
        DriverFactory.getDriver().switchTo().window(firstTab);

        // region Alternative (Safe for Dynamic Handle Order):
        // If you're not 100% sure about tab order (sometimes order varies), use:

        String currentTab = DriverFactory.getDriver().getWindowHandle(); // Current is 2nd tab

        // Find the other tab (1st)
        Set<String> handles = DriverFactory.getDriver().getWindowHandles();
        for (String handle : handles)
        {
            if (!handle.equals(currentTab))
            {
                DriverFactory.getDriver().close(); // Close current (2nd)
                DriverFactory.getDriver().switchTo().window(handle); // Switch to 1st
                break;
            }
        }

        // endregion

    }

    public static void closeTab()
    {
        String originalWindow = DriverFactory.getDriver().getWindowHandle();
        // Get all window handles
        Set<String> allWindows = DriverFactory.getDriver().getWindowHandles();
        // Iterate through the window handles
        for (String windowHandle : allWindows)
        {
            if (!windowHandle.equals(originalWindow))
            {
                // Switch to the new window
                DriverFactory.getDriver().switchTo().window(windowHandle);
                DriverFactory.getDriver().close();
                break;
            }
        }
    }
    // endregion

    // region Common Actions
    /*
    public static void logoutAndLogin(String username, String pwd)
    {
        EmployeePage ep = new EmployeePage();
        ep.clickRightAreaMenu();
        BaseTest.log("clickRightAreaMenu");

        ep.clicklogOff();
        BaseTest.log("clicklogOff");

        LoginPage lp = new LoginPage();
        lp.login(username, pwd);
    }
    */
    public static void selectFilterAll()
    {
        // DriverFactory.getDriver().findElement(By.id("//img[@id='ListingViews_B-1Img']")).click();
//		WebElement element = DriverFactory.getDriver().findElement(By.id("//input[@name='ListingViews']"));
//		clickElementByJavaScript(DriverFactory.getDriver(), element);
        By locator = By.id("//img[@id='ListingViews_B-1Img']");
        waitForElement1(locator).click();
        selectDropdownValueOffice365("All");
    }
    // endregion

    // region Keyboard Actions

    public static void pause(String key)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.pause(Duration.ofSeconds(2)).perform(); // wait for 2 seconds
    }

    public static void enterKey(String key)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.sendKeys(key).perform();
    }

    public static void enterCapitalKey(String key)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.keyDown(Keys.SHIFT).sendKeys(key).keyUp(Keys.SHIFT).perform();
    }

    public static void pressKey(String key)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.sendKeys(getKeyFromString(key)).perform();
    }

    public static Keys getKeyFromString(String key)
    {
        switch (key.toLowerCase())
        {
            // Editing keys
            case "enter":
                return Keys.ENTER;
            case "tab":
                return Keys.TAB;
            case "escape":
                return Keys.ESCAPE;
            case "backspace":
                return Keys.BACK_SPACE;
            case "delete":
                return Keys.DELETE;
            case "insert":
                return Keys.INSERT;
            case "space":
                return Keys.SPACE;
            // Modifier keys
            case "shift":
                return Keys.SHIFT;
            case "control":
                return Keys.CONTROL;
            case "alt":
                return Keys.ALT;
            // Navigation keys
            case "arrowup":
                return Keys.ARROW_UP;
            case "arrowdown":
                return Keys.ARROW_DOWN;
            case "arrowleft":
                return Keys.ARROW_LEFT;
            case "arrowright":
                return Keys.ARROW_RIGHT;
            case "home":
                return Keys.HOME;
            case "end":
                return Keys.END;
            case "pageup":
                return Keys.PAGE_UP;
            case "pagedown":
                return Keys.PAGE_DOWN;
            // Function keys
            case "f1":
                return Keys.F1;
            case "f2":
                return Keys.F2;
            case "f3":
                return Keys.F3;
            case "f4":
                return Keys.F4;
            // Separator and control keys
            case "add":
                return Keys.ADD;
            case "subtract":
                return Keys.SUBTRACT;
            case "multiply":
                return Keys.MULTIPLY;
            case "divide":
                return Keys.DIVIDE;
            case "decimal":
                return Keys.DECIMAL;
            default:
                throw new IllegalArgumentException("Invalid key name");
        }
    }

    public static void pressTab()
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.sendKeys(Keys.TAB).perform();
    }

    public static void pressEnter()
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.sendKeys(Keys.ENTER).perform();
    }
    // endregion

    // region Waits

    public static void waitUntil(By locator)
    {
        Wait<WebDriver> fluentWait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        WebElement element = fluentWait.until(d ->
        {
            WebElement el = DriverFactory.getDriver().findElement(locator);
            return (el.isDisplayed() || el.isEnabled()) ? el : null;
        });
        element.click();
    }

    public static WebElement waitForElement(WebElement element)
    {
        Wait<WebDriver> fluentWait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Element NOT found: " + element.toString());
        return fluentWait.until(d ->
        {
            WebElement el = element;
            return (el.isDisplayed() || el.isEnabled()) ? el : null;
        });
    }

    public static WebElement waitForElement1(By locator)
    {
        Wait<WebDriver> fluentWait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .withMessage("Element NOT found: " + locator.toString());
        return fluentWait.until(d ->
        {
            WebElement el = DriverFactory.getDriver().findElement(locator);
            return (el.isDisplayed() || el.isEnabled()) ? el : null;
        });
    }

    public static List<WebElement> waitForElements2(By locator)
    {
        Wait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver()).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Element NOT found: " + locator.toString());

        return wait.until(d ->
        {
            List<WebElement> elements = DriverFactory.getDriver().findElements(locator);
            return (!elements.isEmpty()) ? elements : null;
        });
    }

    public static void wait(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitTS(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    // endregion

    // region Validations
    public static boolean isValuePresent(List<WebElement> element, String value)
    {
        List<WebElement> valuesList = element;
        for (WebElement valueElement : valuesList)
        {
            String actualValue = valueElement.getText();
            if (actualValue.contains(value))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isTransactionCreated()
    {
        String message = waitForElement1(By.xpath("//div[@class='dx-toast-message']")).getText();
        if (message.contains("created successfully"))
        {
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean isEmployeeDeleted()
    {
        String message = waitForElement1(By.xpath("//div[@class='dx-toast-message']")).getText();
        if (message.contains("deleted successfully"))
        {
            return true;
        } else
        {
            return false;
        }
    }

    public static void validation(String expectedMessage)
    {
        WebElement element = waitForElement1(By.className("dx-toast-message"));
        String actualMessage = element.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    public static boolean validateListing(String filterValue, int filterIndex, int resultIndex)
    {
        filterByIndex(filterIndex, filterValue);
        waitTS(2);
        String actualValue = resultValue(resultIndex);
        if (actualValue.contains(filterValue))
        {
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean validateListing2Fields(String value1, int filterIndex1, int resultIndex1, String value2,
                                                 int filterIndex2, int resultIndex2)
    {
        // Apply first filter
        filterByIndex(filterIndex1, value1);
        waitTS(2);
        // Apply second filter
        filterByIndex(filterIndex2, value2);
        waitTS(2);
        // Get results
        String actualValue1 = resultValue(resultIndex1);
        String actualValue2 = resultValue(resultIndex2);
        // Validate results
        boolean isValid1 = actualValue1.contains(value1);
        boolean isValid2 = actualValue2.contains(value2);
        return isValid1 && isValid2;
    }

    public static boolean validateListing1(String expDate, String expEmp, String expStatus)
    {
        if (expDate != null && !expDate.isEmpty())
        {
            filterDateByIndex(2, expDate);
            waitTS(2);
        }
        if (expEmp != null && !expEmp.isEmpty())
        {
            filterByIndex(2, expEmp);
            waitTS(2);
        }
        if (expStatus != null && !expStatus.isEmpty())
        {
            filterByIndex(7, expStatus);
            waitTS(2);
        }
        boolean isMatch = true;
        if (expDate != null && !expDate.isEmpty())
        {
            String actualDate = resultValue(2);
            isMatch &= actualDate.contains(expDate);
        }
        if (expEmp != null && !expEmp.isEmpty())
        {
            String actualEmp = resultValue(1);
            isMatch &= actualEmp.contains(expEmp);
        }
        if (expStatus != null && !expStatus.isEmpty())
        {
            String actualStatus = resultValue(7);
            isMatch &= actualStatus.contains(expStatus);
        }
        return isMatch;
        // IsTransactionCreated(expDate: "2025-04-15"); // Only check date
        // IsTransactionCreated(expEmp: "John", expStatus: "Completed"); // Check
        // employee and status
        // IsTransactionCreated("2025-04-15", "John", "Completed"); // Check all three
    }

    public static void validateMessage(String expectedMessage) throws InterruptedException
    {
        //if u want to see actual failure message then remove try catch block
        WebElement element;
        try
        {
            element = DriverFactory.getDriver().findElement(By.className("dx-toast-message"));
            String actualMessage = element.getText();
            Thread.sleep(500);

            softAssert.assertTrue(actualMessage.contains(expectedMessage),
                    "Expected message to contain: " + expectedMessage + " but found: " + actualMessage);

            softAssert.assertAll();
        } catch (Exception e)
        {
            BaseTest.log(">>Soft Assert failed..<<");
        }
    }

    // endregion

    // region Alert Handling
    public static void acceptAlert(WebDriver driver)
    {
        DriverFactory.getDriver().switchTo().alert().accept();
    }

    public void dismissAlert(WebDriver driver)
    {
        DriverFactory.getDriver().switchTo().alert().dismiss();
    }

    public String getAlertText(WebDriver driver)
    {
        return DriverFactory.getDriver().switchTo().alert().getText();
    }

    public void sendKeysToAlert(WebDriver driver, String text)
    {
        DriverFactory.getDriver().switchTo().alert().sendKeys(text);
    }
    // endregion

    // region JavaScript Executor
    public static void executeScript(WebDriver driver, String script, Object... args)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        jsExecutor.executeScript(script, args);
    }

    public Object executeScriptWithReturn(WebDriver driver, String script, Object... args)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        return jsExecutor.executeScript(script, args);
    }

    public void scrollToBottom(WebDriver driver)
    {
        executeScript(DriverFactory.getDriver(), "window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop(WebDriver driver)
    {
        executeScript(DriverFactory.getDriver(), "window.scrollTo(0, 0);");
    }

    public void scrollIntoView(WebDriver driver, WebElement element)
    {
        executeScript(DriverFactory.getDriver(), "arguments[0].scrollIntoView(true);", element);
    }

    public static void clickElementByJavaScript(WebDriver driver, WebElement element)
    {
        executeScript(DriverFactory.getDriver(), "arguments[0].click();", element);
    }

    public void setAttribute(WebDriver driver, WebElement element, String attributeName, String attributeValue)
    {
        executeScript(DriverFactory.getDriver(), "arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", element);
    }

    public String getAttribute(WebDriver driver, WebElement element, String attributeName)
    {
        return (String) executeScriptWithReturn(DriverFactory.getDriver(), "return arguments[0].getAttribute('" + attributeName + "');",
                element);
    }

    public void highlightElement1(WebDriver driver, WebElement element)
    {
        executeScript(DriverFactory.getDriver(), "arguments[0].style.border='3px solid red'", element);
    }

    public static void highlightElement(WebDriver driver, WebElement element, boolean highlight)
    {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        if (highlight)
        {
            // Add a red border to highlight the element
            js.executeScript("arguments[0].style.border='3px solid red'", element);
        } else
        {
            // Remove the border to unhighlight the element
            js.executeScript("arguments[0].style.border=''", element);
        }
    }

    public void refreshBrowserUsingJS(WebDriver driver)
    {
        executeScript(DriverFactory.getDriver(), "history.go(0);");
    }

    public void openNewTabUsingJS(WebDriver driver)
    {
        executeScript(DriverFactory.getDriver(), "window.open();");
    }
    // endregion

    // region Frame Handling
    public void switchToFrameByIndex(int index)
    {
        DriverFactory.getDriver().switchTo().frame(index);
    }

    public void switchToFrameByNameOrId(String nameOrId)
    {
        DriverFactory.getDriver().switchTo().frame(nameOrId);
    }

    public void switchToFrameByElement(WebElement element)
    {
        WebElement frameElement = element;
        DriverFactory.getDriver().switchTo().frame(frameElement);
    }

    public void switchToDefaultContent()
    {
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public void switchToParentFrame()
    {
        DriverFactory.getDriver().switchTo().parentFrame();
    }
    // endregion

    // region Mouse Actions
    public void hoverOverElement(WebElement locator)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(locator).perform();
    }

    public void dragAndDrop1(WebElement sourceLocator, WebElement targetLocator)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.dragAndDrop(waitForElement(sourceLocator), waitForElement(targetLocator)).perform();
    }

    public static void moveToElement(WebElement element)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void clickAndHold(WebElement element)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.clickAndHold(element).perform();
    }

    public static void release()
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.release().perform();
    }

    public static void doubleClick(WebElement element)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.doubleClick(element).perform();
    }

    public static void contextClick(WebElement element)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.contextClick(element).perform();
    }

    public static void dragAndDrop(WebElement source, WebElement target)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.dragAndDrop(source, target).perform();
    }

    public static void dragAndDropByOffset(WebElement element, int xOffset, int yOffset)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.dragAndDropBy(element, xOffset, yOffset).perform();
    }

    public static void moveSliderByOffset(WebElement locator, int xOffset)
    {
        Actions move = new Actions(DriverFactory.getDriver());
        move.clickAndHold(waitForElement(locator)).moveByOffset(xOffset, 0) // Move right horizontally; adjust pixel
                // value as per
                // your slider
                .release().perform();
    }

    public void scrollToElement(WebDriver driver, WebElement element)
    {
        WheelInput.ScrollOrigin origin = WheelInput.ScrollOrigin.fromElement(element);
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.scrollFromOrigin(origin, 0, 0).perform();
    }

    public void moveToLocation(WebDriver driver, int x, int y)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveByOffset(x, y).perform();
    }

    public void scrollByAmount(WebDriver driver, int deltaX, int deltaY)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.scrollByAmount(deltaX, deltaY).perform();
    }

    public void scrollFromOrigin(WebDriver driver, WheelInput.ScrollOrigin origin, int deltaX, int deltaY)
    {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.scrollFromOrigin(origin, deltaX, deltaY).perform();
    }
    // endregion

    // region File Upload
    public void uploadFile(WebElement locator, String filePath)
    {
        waitForElement(locator).sendKeys(filePath);
    }

    public static void uploadFileWithRobot(String filePath)
    {
        try
        {
            // Copy file path to clipboard
            StringSelection selection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            waitTS(1); // ensure clipboard is ready

            // Use Robot to paste and press ENTER
            Robot robot = new Robot();
            robot.delay(500);

            // CTRL + V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.delay(500);

            // Press ENTER
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Optional: wait to verify upload
            waitTS(2);
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
    }
    // endregion
}