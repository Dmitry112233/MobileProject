package utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesUtil {

    public DesiredCapabilities getCapabilities(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("deviceName", "Android Emulator");
        //desiredCapabilities.setCapability("uuid", "emulator-5554");
        desiredCapabilities.setCapability("appPackage", PropertyManager.getInstance().get("application.package.name"));
        desiredCapabilities.setCapability("appActivity", PropertyManager.getInstance().get("application.activity.name"));
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"3600");
        //desiredCapabilities.setCapability("id", "id1");
        return desiredCapabilities;
    }
}
