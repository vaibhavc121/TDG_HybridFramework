package testCases.greenLanes;

import base.BasePage;
import base.BaseTest;
import factory.DriverFactory;
import factory.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.greenLanes.GreenLanesPage;
import utilities.JavaScriptUtils;
import utilities.RetryAnalyzer;

public class GreenLanesTest extends BaseTest
{
    @Test(groups = "regression", retryAnalyzer = RetryAnalyzer.class)
    public void verifyGreenLaneCreation()
    {
        try
        {
            //String itSupportFile = FileUtils.getDataFile("SelfService", "SelfService", "SelfServiceData");
            //List<ITSupportModel> itSupportData = JsonUtils.convertJsonListDataModel(itSupportFile, "createITSupport", ITSupportModel.class);

            //region Identify & Lifecycle
            GreenLanesPage gl=new GreenLanesPage();

            gl.clickGreenLanes();
            log("Clicked on Green Lanes menu item");

            gl.clickCreateGreenLaneBtn();
            log("Clicked on Create Green Lane button");

            gl.provideName();
            log("Provided name for the new green lane");

            gl.provideDate();
            log("Provided date for the new green lane");

            gl.selectOwnership();
            log("Selected ownership for the new green lane");

            gl.selectServiceLine();
            log("Selected service line for the new green lane");

            gl.selectSuperRegion();
            log("Selected super region for the new green lane");

            gl.provideDesc();
            log("Provided description for the new green lane");

            gl.clickSaveAndNext();
            log("Clicked on Save and Next button to create the green lane");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Identify & Lifecycle not saved successfully");

            //endregion

            //region Scope and Purpose
            /*gl.provideServiceCode();
            log("Service code provided");*/

            //BasePage.scrollToElement();
            log("Page scrolled up");

            gl.selectDataType();
            log("Data type selected");

            gl.selectBusinessPurpose();
            log("Business purpose selected");

            gl.selectOriginCountry();
            log("Origin country selected");

            JavaScriptUtils.scrollToBottom(DriverFactory.getDriver());
            log("Page scrolled down");

            gl.clickSaveAndNext();
            log("Clicked on Save and Next button to save Scope and Purpose details");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Scope and Purpose details not saved successfully");
            //endregion

            //region Control Categories and Controls
            /*BasePage.waitTS(2);
            gl.chooseAdditionalControl();
            log("Additional control chosen");*/
            JavaScriptUtils.scrollToBottom(DriverFactory.getDriver());
            gl.clickSaveAndNext();
            log("Clicked on Save and Next button to save Control Categories and Controls details");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Control Categories and Controls details not saved successfully");
            //endregion

            //region Rules
            gl.clickSaveNext1();
            log("Clicked on Save and Next button to save Control Categories and Controls details");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Rules details not saved successfully");

            gl.clickSendForApproval();
            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Green Lane not sent for approval successfully");

            //endregion
        } catch (Exception e)
        {
            LoggerFactory.getLogger().error("Test failed due to exception: ", e);
            Assert.fail("Test case failed: " + e);
        }
    }
}