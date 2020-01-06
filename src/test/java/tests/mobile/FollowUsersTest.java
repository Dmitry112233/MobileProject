package tests.mobile;

import lombok.extern.log4j.Log4j;
import models.Limit;
import models.User;
import models.Users;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

@Log4j
public class FollowUsersTest extends BaseTest {

    private static final String EXTENSION = ".xml";
    private static final String PUBLIC_NAME = "autofind.by";
    private static final String LIMIT_NAME = "limit.dat";
    private static final String FOLLOWED_USERS = "followed";
    private int numberToFollow = 10;


    @Test
    public void followUsers() {

        Limit limit = getLimits();
        resetLimit(limit);

        if(limit.getFollowedNumber() < 80) {
            Users groupUsers = subscribersSteps.getUsersFromXml(filePath + PUBLIC_NAME + EXTENSION);
            ArrayList<User> allUsersList = new ArrayList(groupUsers.getUser());
            log.info("POSSIBLE USERS FROM " + PUBLIC_NAME + " COUNT BEFORE FOLLOWING: " + allUsersList.size());

            Users followedUsers = subscribersSteps.getUsersFromXml(filePath + FOLLOWED_USERS + EXTENSION);
            log.info("FOLLOWED USERS COUNT BEFORE FOLLOWING: " + followedUsers.getUser().size());

            loginPageSteps.login(username, password);

            try {
                for (int i = 0; i < numberToFollow; i++) {
                    try {
                        int index = new Random().nextInt(allUsersList.size());
                        User user = allUsersList.get(index);
                        user.setDateFormated(new Date());
                        //TODO logic for open account if failed
                        searchPageSteps.openAccount(user.getName());
                        accountPageSteps.followUser(limit);
                        allUsersList.remove(index);
                        followedUsers.getUser().add(user);
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

            log.info("POSSIBLE USERS FROM " + PUBLIC_NAME + " COUNT AFTER FOLLOWING: " + allUsersList.size());
            HashSet<User> usersAfterEdit = new HashSet<>(allUsersList);
            groupUsers.setUser(usersAfterEdit);

            log.info("FOLLOWED USERS COUNT AFTER FOLLOWING: " + followedUsers.getUser().size());
            subscribersSteps.saveUsersToXml(filePath + FOLLOWED_USERS + EXTENSION, followedUsers);
            subscribersSteps.saveUsersToXml(filePath + PUBLIC_NAME + EXTENSION, groupUsers);

            log.info("NEW LIMITS TO SAVE: Followed: " + limit.getFollowedNumber() + ", liked: " + limit.getLikedNumber()
                    + ", Current date: " + limit.getCurrentDay());
            saveLimit(limit);
        }else{
            log.info("MORE THEN 80 USER HAVE BEEN FOLLOWED FOR TODAY, SKIP RUN");
        }
    }

    private Limit getLimits(){
        Limit limit = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath + LIMIT_NAME)))
        {
            limit =(Limit) ois.readObject();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return limit;
    }

    public void saveLimit(Limit limit){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath + LIMIT_NAME)))
        {
            if(limit == null) {
                limit = new Limit(0, 0, LocalDate.now());
            }
            oos.writeObject(limit);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void resetLimit(Limit limit){
        LocalDate now = LocalDate.now();
        if(now.compareTo(limit.getCurrentDay()) == 1){
            limit.setFollowedNumber(0);
            limit.setLikedNumber(0);
            limit.setCurrentDay(now);
        }
    }
}