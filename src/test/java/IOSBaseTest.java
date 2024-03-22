import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/*
This class contains reusable functions to perform on iOS device
 */

public class IOSBaseTest {
    public IOSDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        //start appium server instead of manually
        service= new AppiumServiceBuilder().withAppiumJS(new File("//usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14 Pro");
        options.setApp("/Users/kajal/Desktop/AppiumProject/src/test/resources/UIKitCatalog.app");
        options.setPlatformVersion("16.1");
        options.setWdaLaunchTimeout(Duration.ofSeconds(5));

        //configuration for client to send commands to server specified address and port
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown(){
        //close app
        driver.quit();

        //stop appium server
        service.stop();
    }

    public void swipeAction(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "left",
                "percent", 0.75
        ));
    }

    public void canScrollMore(){
        boolean canScrollMore;
        do
        {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while(canScrollMore);
    }

    public void longPress(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "duration", 2000
        ));
    }

    public void dragAndDrop(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 661,
                "endY", 587
        ));
    }

}
