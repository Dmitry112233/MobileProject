package pages.modals;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchPage;

public class BottomMenuModal {

    private AndroidDriver driver;
    private By btn_Search = By
            .xpath("//android.widget.FrameLayout[@content-desc=\"Search and Explore\"]/android.widget.ImageView");

    public BottomMenuModal(AndroidDriver driver) {
        this.driver = driver;
    }

    public SearchPage clickBtnSearch() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(btn_Search));
        driver.findElement(btn_Search).click();
        return new SearchPage(driver);
    }
}
