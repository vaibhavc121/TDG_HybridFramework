package testCases.DataProducts;

import base.BasePage;
import base.BaseTest;
import factory.DriverFactory;
import factory.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DataProducts.DataProductsPage;
import utilities.DataUtils;
import utilities.DateUtils;
import utilities.JavaScriptUtils;
import utilities.RetryAnalyzer;

public class DataProductsTest extends BaseTest
{
    public String dataProductName="Vaibhav"+ "_"+ DateUtils.getCurrentDate("dd-MM-yy")+"_"+ DataUtils.randomAlphaNumeric();

    @Test(groups = "regression", retryAnalyzer = RetryAnalyzer.class)
    public void verifyDataProductCreation()
    {
        try
        {
            /*String itSupportFile = FileUtils.getDataFile("SelfService", "SelfService", "SelfServiceData");
            List<ITSupportModel> itSupportData = JsonUtils.convertJsonListDataModel(itSupportFile, "createITSupport", ITSupportModel.class);*/


            //region Basic Info
            DataProductsPage dp=new DataProductsPage();
            dp.clickDataProducts();
            log("Clicked on Data Products menu item");

            dp.clickCreateDataProduct();
            log("Clicked on Create Data Product button");

            dp.provideataProductName(dataProductName);
            log("Provided name for the new data product: "+dataProductName);

            dp.selectBusinessProductManager();
            log("Selected Business Product Manager for the new data product");

            dp.selectRegion();
            log("Selected Region for the new data product");

            dp.selectDataType();
            log("Selected Data Type for the new data product");

            /*
            dp.selectProductClassification();
            log("Selected Product Classification for the new data product");

            dp.selectSyntheticType();
            log("Selected Synthetic Type for the new data product");

            dp.selectGenerationApproach();
            log("Selected Generation Approach for the new data product");

             */

            dp.provideBusinessValueStatement();
            log("Provided Business Value Statement for the new data product");

            dp.clickSaveAndNext();
            log("Clicked on Save and Next button to create the data product");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Basic Info not saved successfully");

            //endregion

            //region Enabling Data storage
            BasePage.waitTS(2);
            dp.clickFusionLink();
            BasePage.pressTab(3);
            BasePage.pressEnter();
            //dp.clickSaveAndNextBtn();

            log("Clicked on Save and Next button to save Enabling Data Storage details");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Enabling Data Storage details not saved successfully");
            //endregion

            //region Enabling Data Product and Governance
            dp.selectGreenLane();
            log("Selected Green Lane for the new data product");

            dp.selectAccess();
            log("Selected Access for the new data product");

            dp.selectRestrictedToSuperRegion();
            log("Selected Restricted to Super Region for the new data product");

            dp.selectRestrictedToServiceLine();
            log("Selected Restricted to Service Line for the new data product");

            dp.selectRestrictedToDomain();
            log("Selected Restricted to Domain for the new data product");

            dp.selectContentJurisdiction();
            log("Selected Content Jurisdiction for the new data product");

            dp.selectStorageJurisdiction();
            log("Selected Storage Jurisdiction for the new data product");

            dp.selectPermittedUseCases();
            log("Selected Permitted Use Cases for the new data product");

            JavaScriptUtils.scrollToBottom(DriverFactory.getDriver());
            /*
            dp.selectProhibitedUses();
            log("Selected Prohibited Uses for the new data product");

             */

            dp.clickSaveAndNext();
            log("Clicked on Save and Next button to save Enabling Data Product and Governance details");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Enabling Data Product and Governance details not saved successfully");
            //endregion

            //region Data Product Usage
            dp.selectSourceSystem();
            log("Selected Source System for the new data product");

            dp.selectSourceTypePerSystem();
            log("Selected Source Type per System for the new data product");

            /*dp.selectQualityDimensions();
            log("Selected Quality Dimensions for the new data product");*/

            JavaScriptUtils.scrollToBottom(DriverFactory.getDriver());

            dp.clickSaveAndNext();
            log("Clicked on Save and Next button to save Data Product Usage details");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Data Product Usage details not saved successfully");

            JavaScriptUtils.scrollToBottom(DriverFactory.getDriver());

            dp.clickSendForApproval();
            log("Clicked on Send for Approval button to send the new data product for approval");

            Assert.assertTrue(BasePage.isTransactionCreated(),
                    "Data product not sent for approval successfully");

            Assert.assertTrue(BasePage.validateListingEy(dataProductName),
                    "Data product: " + dataProductName + " is not created successfully");

            //endregion
        } catch (Exception e)
        {
            LoggerFactory.getLogger().error("Test failed due to exception: ", e);
            Assert.fail("Test case failed: " + e);
        }
    }
}