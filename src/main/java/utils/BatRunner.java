package utils;

import lombok.extern.log4j.Log4j;

import java.io.IOException;

@Log4j
public class BatRunner {

    public void runBat(String batName){
        try {
            String batPath = PropertyManager.getInstance().getProp("file.path.bats");
            Process p =Runtime.getRuntime().exec(String.format("cmd /C start \"start bat\" \"%s" + batName + "\"", batPath));
            p.waitFor();
        } catch (IOException e) {
            log.debug("Exception after trying to run bat file: " + batName);
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.debug("Exception after during running bat file: " + batName);
            e.printStackTrace();
        }
    }
}
