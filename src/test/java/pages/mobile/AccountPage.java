package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;
import models.Limit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

@Log4j
public class AccountPage {

    private AndroidDriver driver;
    private By btn_Subscribers = By.id("com.instagram.android:id/row_profile_header_textview_followers_title");
    private By button_back = By.id("com.instagram.android:id/action_bar_button_back");
    private String item_By_Name = "//android.widget.TextView[@text='%s']";
    private By button_follow = By.xpath("//android.widget.TextView[@text='Follow']");
    private By photos = By.xpath("//android.widget.ImageView[@class='android.widget.ImageView'" +
            " and contains(@content-desc,'Photo by')]");
    private By button_like = By.xpath("//android.widget.ImageView[@content-desc=\"Like\"]");


    public AccountPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public SubscribersListPage openSubscribers(String name) {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(btn_Subscribers));
            driver.findElement(btn_Subscribers).click();
        }catch (Exception e){
            while(driver.findElements(btn_Subscribers).size() < 0) {
                driver.findElement(button_back).click();
                String item = String.format(item_By_Name, name);
                driver.findElement(By.xpath(item)).click();
            }
        }
        return new SubscribersListPage(driver);
    }

    public AccountPage followUser(Limit limit){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(button_follow));
        List<WebElement> buttonsFollow = driver.findElements(button_follow);
        if(buttonsFollow.size() > 0) {
            driver.findElement(button_follow).click();
            limit.setFollowedNumber(limit.getFollowedNumber()+1);
            log.info("User has been followed");
        }
        return this;
    }

    public AccountPage likeRandomPhoto(Limit limit){
        List<WebElement> allPhotos = driver.findElements(photos);
        try {
            if (allPhotos.size() > 0 && limit.getLikedNumber() < 500) {
                int index = new Random().nextInt(allPhotos.size());
                allPhotos.get(index).click();
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.presenceOfElementLocated(button_like));
                driver.findElement(button_like).click();
                limit.setLikedNumber(limit.getLikedNumber() + 1);
                driver.findElement(button_back).click();
                log.info("Photo has been liked");
            }else{
                log.info("No photo or limit like is large: " + limit.getLikedNumber());
            }
        }catch (Exception e){
            log.debug("Error after try to like photo");

        }
        return this;
    }
}
