package tests.mobile;

import lombok.extern.log4j.Log4j;
import models.Users;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyManager;

@Log4j
public class ParseUsersTest extends BaseTest {

    //Public name
    private static final String ACCOUNT_NAME = "dmitrys73";
    //Path and extension
    private static final String EXTENSION = ".xml";
    //Screen size for scroll
    private static final int MIA1_X = 450;
    private static final int MIA1_Y = 430;
    private static final int MIA1_X_END = 450;
    private static final int MIA1_Y_END = -1200;

    private static final int userCount = 9000;
    private static final String MERGED_XML = "merged";


    @Test(dataProvider = "allPublics")
    public void parseUsers(String publicName) {
        loginPageSteps.login(username, password);
        searchPageSteps.openAccount(publicName);

        String filePath = PropertyManager.getInstance().getProp("file.path.save");

        Users users = subscribersSteps.getUsersFromXml(filePath + publicName + EXTENSION);

        Users allUsers = subscribersSteps.parseAllSubscribers(MIA1_X, MIA1_Y,
                MIA1_X_END, MIA1_Y_END, users, publicName, userCount);

        subscribersSteps.saveUsersToXml(filePath + publicName + EXTENSION, allUsers);
    }

    @Test(dataProvider = "allPublics")
    public void mergeXmlFiles(String publicName) {

        log.info("Start merging");
        String filePath = PropertyManager.getInstance().getProp("file.path.save");

        Users groupUsers = subscribersSteps.getUsersFromXml(filePath + publicName + EXTENSION);
        log.info("Group user counts: " + groupUsers.getUser().size());

        Users mergedUsers = subscribersSteps.getUsersFromXml(filePath + MERGED_XML + EXTENSION);
        mergedUsers.getUser().addAll(groupUsers.getUser());

        subscribersSteps.saveUsersToXml(filePath + MERGED_XML + EXTENSION, mergedUsers);
        log.info("Merged user counts: " + mergedUsers.getUser().size());
    }

    @DataProvider(name = "allPublics")
    public Object[][] getAllPublics() {
        Object[] publicNames = PropertyManager.getInstance().getProp("public.names").split(",");
        Object[][] provider = new Object[publicNames.length][1];
        for (int i = 0; i < publicNames.length; i++) {
            provider[i] = new Object[]{publicNames[i]};
        }
        return provider;
    }
}