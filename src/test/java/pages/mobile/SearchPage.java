package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public class SearchPage {

    private AndroidDriver driver;
    private By field_Search = By.id("com.instagram.android:id/action_bar_search_edit_text");
    private String item_By_Name = "//android.widget.TextView[@text='%s']";
    private By button_back = By.id("com.instagram.android:id/action_bar_button_back");

    private By button_reload = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android." +
            "widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget." +
            "FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget." +
            "ViewAnimator/android.widget.ImageView");

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public SearchPage searchItem(String name) {
        try {
            driver.findElement(field_Search).click();
            driver.findElement(field_Search).sendKeys(Character.toString(name.charAt(0)));
            log.info("Search account: " + name);
            Thread.sleep(1000);
            driver.findElement(field_Search).sendKeys(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void clickItem(String name) {
        try {
            String item = String.format(item_By_Name, name);
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(item)));
            driver.findElement(By.xpath(item)).click();
        } catch (Exception e) {
            String item = String.format(item_By_Name, name);
            driver.findElement(button_back).click();
            if (driver.findElements(button_reload).size() > 0) {
                driver.findElement(button_reload).click();
            }
            driver.findElement(field_Search).click();
            driver.findElement(field_Search).sendKeys(name.charAt(0) + name.charAt(1) + "");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            driver.findElement(field_Search).sendKeys(name.charAt(0) + name.charAt(1) + name.charAt(2) + "");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            driver.findElement(field_Search).sendKeys(name);
            if (driver.findElements(By.xpath(item)).size() == 0){
                driver.findElement(button_back).click();
                throw new NoSuchElementException("No fine element");
            }else{
                driver.findElement(By.xpath(item)).click();
            }
        }
    }
}
