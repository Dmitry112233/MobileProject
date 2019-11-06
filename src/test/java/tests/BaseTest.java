package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.AppiumUtils;
import utils.CapabilitiesUtil;

public class BaseTest {

    protected AppiumDriver driver;
    private AppiumDriverLocalService appiumService;

    @BeforeClass
    protected void startAppiumServer() {
        appiumService = AppiumUtils.startAppiumDriverService();
    }

    @BeforeMethod
    public void init() {
        driver = new AppiumDriver(appiumService, new CapabilitiesUtil().getCapabilities());
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
