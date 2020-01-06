package utils;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeOptions;
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
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);
        desiredCapabilities.setCapability("noReset",false);
        //desiredCapabilities.setCapability("id", "id1");
        return desiredCapabilities;
    }

    public ChromeOptions getCapabilitiesWeb(){

       /* String proxyStr = "46.16.228.6:8080";*/

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50" +
                " (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
        //options.addArguments("--proxy-server=https://" + proxyStr);
        //options.addArguments("--proxy-server=http://" + proxyStr);
        /*options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--no-cache");
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage");*/
        options.addArguments("--window-size=300,700");

        return options;
    }
}
