package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.github.javafaker.Faker;
import factory.DriverFactory;
import factory.LoggerFactory;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import org.testng.asserts.SoftAssert;
import pageObjects.login.LoginPage;

public class BaseTest
{
    //region Global Variables and Logger Initialization
    //public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();  // Thread-safe WebDriver
    // public static SelfHealingDriver driver; // updated to SelfHealingDriver
    public Properties p;
    //public static Logger logger; // log4j
    public Faker faker = new Faker(); // for generating random data
    public SoftAssert softAssert;

    //    public static void log(String message)
//    {
//        log(message);
//    }
    public static void log(String message)
    {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        LoggerFactory.getLogger().info(methodName + " - " + message);
    }

    // Getter for WebDriver
    public static WebDriver getDriver()
    {
        //return driver.get();
        return DriverFactory.getDriver();
    }

    //endregion

    //region Setup

    //region Before Method
    @BeforeMethod(groups = {"regression", "datadriven", "functional"}, enabled = true)
    public void prepareNavigation() throws InterruptedException
    {
        BasePage.openSidebar();
        log("Opened sidebar");
    }

    //endregion

    @SuppressWarnings("deprecation")
    @BeforeClass(groups = {"regression", "datadriven", "functional"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException, InterruptedException
    {
        //region CloseBrowserWhenClickStopDebugging
        Runtime.getRuntime().addShutdownHook(new Thread(DriverFactory::quitAllDrivers));
        //endregion\

        //region SoftAssert Initialization
        softAssert = new SoftAssert();
        //endregion

        //region Selenide Setup
        //Configuration.timeout = 5000;
        //Configuration.browser = "chrome";
        //Configuration.startMaximized = true;
        //open("https://testhrms.onenfinity.com");
        //endregion

        //region config.properties file setup
        // Loading config.properties file
        // read- e- input stream
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);
        //endregion

        //region Logger Setup
        LoggerFactory.setLogger(this.getClass());
        LoggerFactory.getLogger().info(">>======>>======>> Automation Engineer (SDET)- Vaibhav Chavan <<======<<======<<");
        LoggerFactory.getLogger().info("--test execution started--");
        //endregion

        //region If execution on selenium grid or Remote Env
        if (p.getProperty("execution_env").equals("remote"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //region Set Platform

            // OS (we are getting os from xml file)
            if (os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("linux"))
            {
                capabilities.setPlatform(Platform.LINUX);
            } else if (os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("android"))
            {
                capabilities.setPlatform(Platform.ANDROID);
            } else
            {
                System.out.println("no matching os");
                return; // It will automatically exit
            }
            //endregion

            //region Set Browser
            // browser (we are getting browser from xml file)
            switch (browser.toLowerCase())
            {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    capabilities.setVersion("129");
                    // to work this in local env need to install the browser of ver 129, it is
                    // working on browserstack

                    break;

                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    capabilities.setVersion("130");
                    // to work this in local env need to install the browser of ver 130, it is
                    // working on browserstack

                    break;

                case "firefox":
                    capabilities.setBrowserName("firefox");
                    capabilities.setVersion("131");
                    // to work this in local env need to install the browser of ver 131, it is
                    // working on browserstack

                    break;

                case "safari":
                    capabilities.setBrowserName("safari");

                    break;

                default:
                    System.out.println("No matching browser ");
                    return;
                // It will automatically exit from switch case statement
            }
            //endregion

            //region For selenium grid standalone
            // WebDriver remoteDriver = new RemoteWebDriver(new URL("http://192.168.102.117:4444/wd/hub"), capabilities);
            // driver.set(remoteDriver);
            // driver = SelfHealingDriver.create(_driver);
            //endregion

            //region For docker container on selenium grid
            // WebDriver remoteDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            // driver.set(remoteDriver);
            // driver = SelfHealingDriver.create(_driver);
            //endregion

            //region For browserstack
//            WebDriver remoteDriver = new RemoteWebDriver(
//                    new URL("https://vaibhavchavan_vXTnjK:VjyZRpR7fkRybdm1cyAb@hub-cloud.browserstack.com/wd/hub"),
//                    capabilities);
            // driver.set(remoteDriver);
            // driver = SelfHealingDriver.create(_driver);
            //endregion

        }
        //endregion

        //region If execution on Local
        if (p.getProperty("execution_env").equals("local"))
        {
            ChromeOptions options = new ChromeOptions();

            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);

            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>()
            {{
                put("credentials_enable_service", false);
                put("profile.password_manager_enabled", false);
            }});

            // Map<String, String> mobileEmulation = new HashMap<>();
            // mobileEmulation.put("deviceName", "Pixel 2");
            // options.setExperimentalOption("mobileEmulation", mobileEmulation);
            // options.addArguments("--headless"); // Run in headless mode
            // options.addArguments("--no-sandbox"); // Required for CI environments
            // options.addArguments("--disable-dev-shm-usage"); // Required for CI environments

            WebDriver localDriver;

            switch (browser.toLowerCase())
            {
                case "chrome":
//                     driver = new ChromeDriver(options);
                    localDriver = new ChromeDriver(options);
                    log("browser opened");
                    // driver = SelfHealingDriver.create(_driver);
                    // log("Chrome browser opened with Healenium");
                    // log("browser opened");

                    break;

                case "edge":
                    localDriver = new EdgeDriver();
                    log("browser opened");
                    // driver = SelfHealingDriver.create(_driver);
                    // log("Edge browser opened with Healenium");
                    // log("browser opened");

                case "firefox":
                    localDriver = new FirefoxDriver();
                    log("browser opened");
                    // driver = SelfHealingDriver.create(_driver);
                    // log("Firefox browser opened with Healenium");
                    // log("browser opened");

                    break;

                default:
                    System.out.println("invalid browser name");
                    return; // return- totally exit from the execution
            }
            //driver.set(localDriver); // assign driver to current thread
            DriverFactory.setDriver(localDriver);
        }
        // region Browser Setup

        // driver = new ChromeDriver();
        // log("browser opened");
        DriverFactory.getDriver().manage().deleteAllCookies();
        log("cookies deleted");
        DriverFactory.getDriver().manage().window().maximize();
        log("browser maximized");
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log("implicit wait applied for 10 seconds");
        DriverFactory.getDriver().get(p.getProperty("appurl")); // Reading URL from properties file
        log("provided app URL in browser");

        //endregion

        //region Login
        LoginPage lp = new LoginPage();
        lp.provideEmail(p.getProperty("email"));
        log("provided email");
        lp.clickLogin();
        log("clicked on log in button");

        //endregion
        //endregion

    }
    //endregion

    //region TearDown
    @AfterClass(groups = {"regression", "datadriven", "functional"})
    public void teardown()
    {
       /*
        if (getDriver() != null)
        {
            getDriver().quit();
            driver.remove();
        }
       */
        DriverFactory.cleanupDriver();
        LoggerFactory.cleanupLogger();
        softAssert.assertAll();
    }
    //endregion
}