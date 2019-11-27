package utils;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesUtil {

    public DesiredCapabilities getCapabilities(String deviceName, String platformVersion, String systemPort){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("deviceName", deviceName);
        //desiredCapabilities.setCapability("applicationName", applicationName);
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceName);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        desiredCapabilities.setCapability("appPackage", "com.instagram.android");
        desiredCapabilities.setCapability("appActivity", "com.instagram.mainactivity.MainActivity");
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,3600);
        desiredCapabilities.setCapability("noReset",false);
        //desiredCapabilities.setCapability("id", "id1");
        return desiredCapabilities;
    }
}
