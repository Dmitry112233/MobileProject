package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j
@Data
public class SubscribersListPage {

    private AndroidDriver driver;
    private By field_Search = By.id("com.instagram.android:id/row_search_edit_text");
    private By subscribers_Names = By.xpath("//*[@resource-id='com.instagram.android:id/follow_list_username']");
    private By text_Recommendations = By.id("com.instagram.android:id/row_header_textview");

    public SubscribersListPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public List<String> getUserNames() throws WebDriverException, NoSuchElementException {
        List<WebElement> users;
        List<String> userNames = new ArrayList<>();
        users = driver.findElements(subscribers_Names);
        for (WebElement elem : users) {
            userNames.add(elem.getText());
        }
        return userNames;
    }
}
