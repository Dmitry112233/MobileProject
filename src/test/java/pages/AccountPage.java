package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class AccountPage {

    private AndroidDriver driver;
    private By btn_Subscribers = By.id("com.instagram.android:id/row_profile_header_textview_followers_title");

    public AccountPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public SubscribersListPage openSubscribers() {
        driver.findElement(btn_Subscribers).click();
        return new SubscribersListPage(driver);
    }
}
