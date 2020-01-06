package action.mobile;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

@Log4j
public class AppAction {

    private AndroidDriver driver;

    public AppAction(AndroidDriver driver) {
        this.driver = driver;
    }

    public AppAction scrollDown(int x, int y, int endX, int endY) throws WebDriverException {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(x, y)).moveTo(PointOption.point(endX, endY)).release().perform();
        return this;
    }

    public boolean isElementNotPresent(By locator) {
      try {
          if (driver.findElements(locator).size() > 0) {
              return false;
          } else {
              return true;
          }
      }
      catch(Exception e){
          log.debug("Error with check suggestion users line have benn caught");
          return true;
      }
    }
}
