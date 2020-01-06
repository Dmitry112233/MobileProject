package tests.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.*;
import steps.mobile.AccountPageSteps;
import steps.mobile.LoginPageSteps;
import steps.mobile.SearchPageSteps;
import steps.mobile.SubscribersSteps;
import tests.TestListener;
import utils.AppiumUtils;
import utils.BatRunner;
import utils.CapabilitiesUtil;
import utils.PropertyManager;

import java.net.URL;
import java.util.concurrent.TimeUnit;


@Listeners(TestListener.class)
public class BaseTest {

    protected String username;
    protected String password;

    protected String filePath;

    private String deleteUiAutomatorFileName = "deleteUiAutomator.bat";
    private String startAvdFileName = "startAVD.bat";
    private String starthub = "starthub.bat";

    private final String platformVersion = "9.0";
    private final String deviceName = "9634979d9805";
    private final String systemPort = "8200";

    private AppiumDriverLocalService appiumService;

    protected LoginPageSteps loginPageSteps;
    protected SearchPageSteps searchPageSteps;
    protected SubscribersSteps subscribersSteps;
    protected AccountPageSteps accountPageSteps;



    @BeforeMethod
    //@Parameters({"deviceName", "platformVersion", "systemPort"})
    //String deviceName, String platformVersion, String systemPort
    public void init() {
        try {
            this.username = PropertyManager.getInstance().getProp("user.name");
            this.password = PropertyManager.getInstance().getProp("user.password");
            filePath = PropertyManager.getInstance().getProp("file.path.save");

            //Start appium from code
            //appiumService = AppiumUtils.startAppiumDriverService("");

            //Delete UIAutomator for Android device, sometimes appium crashed without it
            new BatRunner().runBat(deleteUiAutomatorFileName);

            //Use when appium starts from code
            /*ThreadLocalDriver.setTLDriver(new AndroidDriver(appiumService,
                    new CapabilitiesUtil().getCapabilities(deviceName, platformVersion, systemPort)));
            ThreadLocalDriver.getTLDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);*/

            //Run appium manualy
            AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                    new CapabilitiesUtil().getCapabilities(deviceName, platformVersion, systemPort));
            ThreadLocalDriver.setTLDriver(driver);
            ThreadLocalDriver.getTLDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

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
            accountPageSteps = new AccountPageSteps(ThreadLocalDriver.getTLDriver());
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
        //appiumService.stop();
    }
}
