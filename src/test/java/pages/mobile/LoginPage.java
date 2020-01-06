package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public class LoginPage {

    private AndroidDriver driver;
    private By btn_Prelogin = By.id("com.instagram.android:id/log_in_button");
    private By btn_Login = By.id("com.instagram.android:id/next_button");
    private By field_Login = By.id("com.instagram.android:id/login_username");
    private By field_Password = By.id("com.instagram.android:id/password");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickPreloginBtn() {
        driver.findElement(btn_Prelogin).click();
        return this;
    }

    public LoginPage typeLogin(String login) {
        log.info("Type login: " + login);
        driver.findElement(field_Login).sendKeys(login);
        return this;
    }

    public LoginPage typePassword(String password) {
        log.info("Type login: " + password);
        driver.findElement(field_Password).sendKeys(password);
        return this;
    }

    public LoginPage clickLoginBtn() {
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.elementToBeClickable(btn_Login));
        driver.findElement(btn_Login).click();
        return this;
    }


}
