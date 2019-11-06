package utils;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ThreadLocalRandom;

@Log4j
public final class IOUtils {

    public static Integer nextFreePort() {
        int port = ThreadLocalRandom.current().nextInt(49152, 65535);
        while (true) {
            try (ServerSocket endpoint = new ServerSocket(port)) {
                log.debug(String.format("Trying to setup process at port: %s", port));
                return port;
            } catch (IOException e) {
                log.warn(String.format("Cannot open port: %s Checking another one", port));
                port = ThreadLocalRandom.current().nextInt(49152, 65535);
            }
        }
    }
}
