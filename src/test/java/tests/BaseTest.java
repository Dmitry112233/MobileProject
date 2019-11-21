package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.*;
import steps.LoginPageSteps;
import steps.SearchPageSteps;
import steps.SubscribersSteps;
import utils.AppiumUtils;
import utils.BatRunner;
import utils.CapabilitiesUtil;
import utils.PropertyManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


@Listeners(TestListener.class)
public class BaseTest {

    protected String username = PropertyManager.getInstance().get("user.name");
    protected String password = PropertyManager.getInstance().get("user.password");

    private String deleteUiAutomatorFileName = "deleteUiAutomator.bat";
    private String startAvdFileName = "startAVD.bat";

    protected AndroidDriver driver;
    private AppiumDriverLocalService appiumService;

    protected LoginPageSteps loginPageSteps;
    protected SearchPageSteps searchPageSteps;
    protected SubscribersSteps subscribersSteps;

    @BeforeClass
    protected void startAppiumServer() {
        appiumService = AppiumUtils.startAppiumDriverService();
    }

    @BeforeMethod
    public void init() {
        try {
            new BatRunner().runBat(startAvdFileName);
            new BatRunner().runBat(deleteUiAutomatorFileName);
            Thread.sleep(20000);
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), new CapabilitiesUtil().getCapabilities());
            //driver = new AndroidDriver(appiumService, new CapabilitiesUtil().getCapabilities());
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            loginPageSteps = new LoginPageSteps(driver);
            searchPageSteps = new SearchPageSteps(driver);
            subscribersSteps = new SubscribersSteps(driver);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void quite() {
        driver.closeApp();
    }

    @AfterClass
    protected void stopAppiumService() {
        driver.quit();
        appiumService.stop();
    }
}
