import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/*
This class contains functions which starts appium server
 */
public class AppiumServerSetupTest {
    @Test
    public void AppiumTestWithManualServerStartup() throws MalformedURLException {

        //start appium server manually by running command appium

        //configure details like platform, device etc
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Kajal_Pixel");
//        options.setApp("/Users/kajal/Desktop/AppiumProject/src/test/resources/ApiDemos-debug.apk");
        options.setApp("/Users/kajal/Desktop/AppiumProject/src/test/resources/General-Store.apk");

        //configuration for client to send commands to server specified address and port
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        //close app
        driver.quit();

        //stop appium server manually
    }

    @Test
    public void AppiumTestWithAutomationServerStartup() throws URISyntaxException, MalformedURLException {
        //start appium server instead of manually
        AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("//usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Kajal_Pixel");
        options.setApp("/Users/kajal/Desktop/AppiumProject/src/test/resources/ApiDemos-debug.apk");

        //configuration for client to send commands to server specified address and port
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        //close app
        driver.quit();

        //stop appium server
        service.stop();
    }
}
