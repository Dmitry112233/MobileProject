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

import java.util.concurrent.TimeUnit;


@Listeners(TestListener.class)
public class BaseTest {

    protected String username;
    protected String password;

    private String deleteUiAutomatorFileName = "deleteUiAutomator.bat";
    private String startAvdFileName = "startAVD.bat";
    private String starthub = "starthub.bat";

    private AppiumDriverLocalService appiumService;

    protected LoginPageSteps loginPageSteps;
    protected SearchPageSteps searchPageSteps;
    protected SubscribersSteps subscribersSteps;

    @BeforeMethod
    @Parameters({"deviceName", "platformVersion", "systemPort"})
    public void init(String deviceName, String platformVersion, String systemPort) {
        try {
            this.username = PropertyManager.getInstance().getProp("user.name");
            this.password = PropertyManager.getInstance().getProp("user.password");

            //Start appium from code
            appiumService = AppiumUtils.startAppiumDriverService("");
            //Delete UIAutomator for Android device, sometimes appium crashed without it
            new BatRunner().runBat(deleteUiAutomatorFileName);

            ThreadLocalDriver.setTLDriver(new AndroidDriver(appiumService,
                    new CapabilitiesUtil().getCapabilities(deviceName, "9.0", "8200")));
            ThreadLocalDriver.getTLDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

            //start emulator
            //new BatRunner().runBat(startAvdFileName);

            //start selenium grid hub
            //new BatRunner().runBat(starthub);

            //Run test in parallel mode
            /*AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4444/wd/hub"),
                    new CapabilitiesUtil().getCapabilities(deviceName, platformVersion, systemPort));
            ThreadLocalDriver.setTLDriver(driver);*/

            loginPageSteps = new LoginPageSteps(ThreadLocalDriver.getTLDriver());
            searchPageSteps = new SearchPageSteps(ThreadLocalDriver.getTLDriver());
            subscribersSteps = new SubscribersSteps(ThreadLocalDriver.getTLDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void quite() {
        ThreadLocalDriver.getTLDriver().closeApp();
    }

    @AfterClass
    protected void stopAppiumService() {
        ThreadLocalDriver.getTLDriver().quit();
        appiumService.stop();
    }
}
