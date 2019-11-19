package tests;

import lombok.extern.log4j.Log4j;
import models.Users;
import org.testng.annotations.Test;
import utils.PropertyManager;

import java.io.IOException;

@Log4j
public class InstagramTest extends BaseTest {

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


    @Test
    public void parseUsers() {
        try {
            loginPageSteps.login(username, password);
            searchPageSteps.openAccount(ACCOUNT_NAME);

            Users users = subscribersSteps.getUsersFromXml(DEFAULT_PATH + ACCOUNT_NAME + EXTENSION);

            Users allUsers = subscribersSteps.parseAllSubscribers(MIA1_X, MIA1_Y,
                    MIA1_X_END, MIA1_Y_END, users);

            subscribersSteps.saveUsersToXml(DEFAULT_PATH + ACCOUNT_NAME + EXTENSION, allUsers);

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}