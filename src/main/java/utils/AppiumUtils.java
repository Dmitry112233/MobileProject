package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.log4j.Log4j;

import java.io.File;

/**
 * https://github.com/appium/java-client/blob/master/docs/The-starting-of-an-app-using-Appium-node-server-started-programmatically.md#fyi
 */
@Log4j
public final class AppiumUtils {

    public static AppiumDriverLocalService startAppiumDriverService() {
        final String url = PropertyManager.getInstance().get("appium.server.url");
        final int appiumServerPort = IOUtils.nextFreePort();
        final int bootstrapPortNumber = IOUtils.nextFreePort();
        final String appiumJsRunner = PropertyManager.getInstance().get("appium.server.js.path");
        log.debug(String.format("Starting Appium server at %s, port %s, bootstrap port %s using runner location: %s", url, appiumServerPort, bootstrapPortNumber,
                appiumJsRunner));

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .usingPort(appiumServerPort)
                        .withIPAddress(url)
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "warn")
                        .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, String.valueOf(bootstrapPortNumber))
                        .withAppiumJS(new File(appiumJsRunner)));

        service.start();
        return service;
    }
}
