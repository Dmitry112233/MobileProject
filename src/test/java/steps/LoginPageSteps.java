package steps;

import io.appium.java_client.android.AndroidDriver;
import pages.LoginPage;

public class LoginPageSteps {

    private LoginPage loginPage;

    public LoginPageSteps(AndroidDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public LoginPageSteps login(String username, String password) {
        loginPage.clickPreloginBtn()
                .typeLogin(username)
                .typePassword(password)
                .clickLoginBtn();
        return this;
    }
}
