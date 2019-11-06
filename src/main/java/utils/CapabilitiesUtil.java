package utils;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesUtil {

    public DesiredCapabilities getCapabilities(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("uuid", "9634979d9805");
        desiredCapabilities.setCapability("appPackage", "org.telegram.messenger");
        desiredCapabilities.setCapability("appActivity", "org.telegram.ui.LaunchActivity");
        desiredCapabilities.setCapability("deviceName", "Mi A1");
        return desiredCapabilities;
    }
}
