package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsUtils implements ITestListener
{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext)
    {

        /*
         * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =
         * new Date(); String currentdatetimestamp = df.format(dt);
         */

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        File reportsDir = new File("reports");
        if (!reportsDir.exists())
        {
            reportsDir.mkdirs();
        }
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

        sparkReporter.config().setDocumentTitle("Test Execution Report"); // Title of report
        sparkReporter.config().setReportName("HRMS Regression Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Automation Engineer (SDET)", "Vaibhav Chavan");
        extent.setSystemInfo("Application", "Enfinity HRMS");
        extent.setSystemInfo("Module", "HRCore");
        // extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environemnt", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty())
        {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        String fullTestName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        test = extent.createTest(fullTestName);
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getMethod().getMethodName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        String fullTestName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        test = extent.createTest(fullTestName);
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getMethod().getMethodName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try
        {
            String imgPath = ScreenshotUtils.captureScreen(result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        String fullTestName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        test = extent.createTest(fullTestName);
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getMethod().getMethodName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext)
    {
        extent.flush();

        // optional code, it will automatically open the report on the browser
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try
        {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

//		try
//		{
//			@SuppressWarnings("deprecation")
//			URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
//			// Create the email message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.googlemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("vaibhavc121@gmail.com", "416310416310"));
//			email.setSSLOnConnect(true);
//			email.setFrom("vaibhavc121@gmail.com"); // Sender
//			email.setSubject("Test Results");
//			email.setMsg("Please find Attached Report .... ");
//			email.addTo("vaibhav.chavan@spit.ac"); // Receiver
//			email.attach(url, "extent report", "please check report ... ");
//			email.send(); // send the email
//
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}

    }
}