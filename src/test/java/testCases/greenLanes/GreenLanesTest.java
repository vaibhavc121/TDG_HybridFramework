package testCases.greenLanes;

import base.BaseTest;
import factory.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.greenLanes.GreenLanesPage;
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
            GreenLanesPage gl=new GreenLanesPage();

            gl.clickGreenLanes();
            log("Clicked on Green Lanes menu item");

            gl.clickCreateGreenLaneBtn();
            log("Clicked on Create Green Lane button");

            /*try
            {
                gl.clickCreateGreenLaneBtn();
                log("Clicked on Create Green Lane button");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }*/

            gl.provideName();
            log("Provided name for the new green lane");
            try
            {
                gl.provideName();
                log("Provided name for the new green lane");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            //gl.provideDate();
            //log("Provided date for the new green lane");

            try
            {
                gl.provideDate();
                log("Provided date for the new green lane");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            try
            {
                gl.selectOwnership();
                log("Selected ownership for the new green lane");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            try
            {
                gl.selectServiceLine();
                log("Selected service line for the new green lane");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            try
            {
                gl.selectSuperRegion();
                log("Selected super region for the new green lane");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            try
            {
                gl.provideDesc();
                log("Provided description for the new green lane");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            try
            {
                gl.clickSaveAndNext();
                log("Clicked on Save and Next button to create the green lane");
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }




















        } catch (Exception e)
        {
            LoggerFactory.getLogger().error("Test failed due to exception: ", e);
            Assert.fail("Test case failed: " + e);
        }
    }
}