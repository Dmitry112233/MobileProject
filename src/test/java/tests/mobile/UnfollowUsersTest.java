package tests.mobile;

import lombok.extern.log4j.Log4j;
import models.User;
import models.Users;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Log4j
public class UnfollowUsersTest extends BaseTest {

    private static final String EXTENSION = ".xml";
    private static final String LIMIT_NAME = "limit.dat";
    private static final String FOLLOWED_USERS = "followed";
    private int numberToUnfollow = 10;

    @Test
    public void unfollowUsers() {

        Users groupUsers = subscribersSteps.getUsersFromXml(filePath + FOLLOWED_USERS + EXTENSION);
        ArrayList<User> allUsersList = new ArrayList(groupUsers.getUser());
        log.info("USERS FROM " + FOLLOWED_USERS + " COUNT BEFORE UNFOLLOWING: " + allUsersList.size());

        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<User> usersForUnfollow = allUsersList.stream().filter((p)-> Period.between(LocalDate.parse(p.getDate(), dtf), now).getDays() >= 5)
                .collect(Collectors.toList());

        loginPageSteps.login(username, password);

        try {
            for (int i = 0; i < numberToUnfollow; i++) {
                try {
                    int index = new Random().nextInt(allUsersList.size());
                    User user = allUsersList.get(index);
                    user.setDateFormated(new Date());
                    //TODO logic for open account if failed
                    searchPageSteps.openAccount(user.getName());
                   // accountPageSteps.followUser(limit);
                    allUsersList.remove(index);
                    //followedUsers.getUser().add(user);
                } catch (Exception e) {
                    log.debug("ERROR during following user");
                    e.printStackTrace();
                    continue;
                }
                Thread.sleep(360000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
