package tests.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CapabilitiesUtil;
import utils.PropertyManager;

public class BaseTest {

    protected WebDriver driver;
    private String driverName = "chromedriver.exe";

    @BeforeMethod
    public void init(){
        System.setProperty("webdriver.chrome.driver", PropertyManager.getInstance()
                .getProp("file.path.drivers") + driverName);

        driver = new ChromeDriver(new CapabilitiesUtil().getCapabilitiesWeb());
        driver.get("https://instagram.com");

    }

    @Test
    public void start(){

    }
}
