package steps.mobile;

import io.appium.java_client.android.AndroidDriver;
import models.Limit;
import pages.mobile.AccountPage;

public class AccountPageSteps {

    private AccountPage accountPage;

    public AccountPageSteps(AndroidDriver driver) {
        accountPage = new AccountPage(driver);
    }

    public AccountPageSteps followUser(Limit limit){
        accountPage.likeRandomPhoto(limit)
                .followUser(limit);
        return this;
    }
}
