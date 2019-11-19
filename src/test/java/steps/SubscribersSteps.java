package steps;

import action.AppAction;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j;
import models.Users;
import pages.AccountPage;
import pages.SubscribersListPage;
import utils.XmlUtil;

import java.util.HashSet;
import java.util.List;

@Log4j
public class SubscribersSteps {

    AccountPage accountPage;
    SubscribersListPage subscribersListPage;
    AppAction appAction;

    public SubscribersSteps(AndroidDriver driver) {
        this.accountPage = new AccountPage(driver);
        this.subscribersListPage = new SubscribersListPage(driver);
        this.appAction = new AppAction(driver);
    }

    public Users parseAllSubscribers(int x, int y, int xEnd, int yEnd, Users savedUsers) {
        accountPage.openSubscribers();
        try {
            while (true){
                HashSet<String> allUsers = new HashSet();

                List<String> users = subscribersListPage.getUserNames();
                allUsers.addAll(users);

                savedUsers.addUsers(allUsers);

                log.info(String.format("%s users are added to list", allUsers.size()));
                log.info("Current size of parsed users is: " + savedUsers.getUser().size());

                appAction.scrollDown(x, y, xEnd, yEnd);
                if(!appAction.isElementNotPresent(subscribersListPage.getText_Recommendations())){
                    break;
                }
            }
        } catch (Exception e) {
            log.debug("Exception after trying to get users names or scroll the page");
            e.printStackTrace();
            return savedUsers;
        }
        return savedUsers;
    }

    public void saveUsersToXml(String filePath, Users users) {
        log.info("________________SAVE ALL USERS TO "  + filePath + ", NEW NUMBER IS: " + users.getUser().size() + "_________________");
        XmlUtil<Users> xmlUtil = new XmlUtil();
        xmlUtil.saveToXmlFile(filePath, users);
    }

    public Users getUsersFromXml(String filePath) {
        XmlUtil<Users> xmlUtil = new XmlUtil<>();
        Users users = xmlUtil.getFromXML(filePath, Users.class);
        if(users != null){
            log.info("Load all existed users from " + filePath + ", number: " + users.getUser().size());
            return users;
        }else {
            log.info("There are not existing users, return new Users Object");
            return new Users();
        }
    }
}
