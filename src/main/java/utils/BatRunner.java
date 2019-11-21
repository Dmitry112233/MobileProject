package utils;

import lombok.extern.log4j.Log4j;

import java.io.IOException;

@Log4j
public class BatRunner {

    public void runBat(String batName){
        try {
            Process p =Runtime.getRuntime().exec("cmd /C start \"start YO\" \"C:\\Users\\Dmitry\\Desktop\\" + batName + "\"");
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
