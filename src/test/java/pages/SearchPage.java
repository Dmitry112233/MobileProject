package pages;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public class SearchPage {

    private AndroidDriver driver;
    private By field_Search = By.id("com.instagram.android:id/action_bar_search_edit_text");
    private String item_By_Name = "//android.widget.TextView[@text='%s']";

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public SearchPage searchItem(String name) {
        driver.findElement(field_Search).click();
        log.info("Search account: " + name);
        driver.findElement(field_Search).sendKeys(name);
        return this;
    }

    public void clickItem(String name) {
        String item = String.format(item_By_Name, name);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(item)));
        driver.findElement(By.xpath(item)).click();
    }

}
