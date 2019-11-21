package tests;

import lombok.extern.log4j.Log4j;
import models.Users;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyManager;

@Log4j
public class ParseUsersTest extends BaseTest {

    //Public name
    private static final String ACCOUNT_NAME = "lsmasha";
    //Path and extension
    private static final String EXTENSION = ".xml";
    private static final String DEFAULT_PATH = PropertyManager.getInstance().get("file.path");
    //Screen size for scroll
    private static final int MIA1_X = 450;
    private static final int MIA1_Y = 430;
    private static final int MIA1_X_END = 450;
    private static final int MIA1_Y_END = -1200;


    @Test(dataProvider = "allPublics")
    public void parseUsers(String publicName) {
        loginPageSteps.login(username, password);
        searchPageSteps.openAccount(publicName);

        Users users = subscribersSteps.getUsersFromXml(DEFAULT_PATH + publicName + EXTENSION);

        Users allUsers = subscribersSteps.parseAllSubscribers(MIA1_X, MIA1_Y,
                MIA1_X_END, MIA1_Y_END, users);

        subscribersSteps.saveUsersToXml(DEFAULT_PATH + publicName + EXTENSION, allUsers);
    }

    @DataProvider(name = "allPublics")
    public Object[][] getAllPublics() {
        Object[] publicNames = PropertyManager.getInstance().get("public.names").split(",");
        Object[][] provider = new Object[publicNames.length][1];
        for (int i = 0; i < publicNames.length; i++) {
            provider[i] = new Object[]{publicNames[i]};
        }
        return provider;
    }
}