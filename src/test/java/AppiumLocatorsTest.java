import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/*
Verify functionality using different types of locators
 */
public class AppiumLocatorsTest extends BaseTest {

    @Test
    public void wifiSettingsName() throws InterruptedException {
        //navigate to wifi settings
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

        //get wifi setting alert title
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings");

        //set wifi name
        driver.findElement(By.className("android.widget.EditText")).sendKeys("test wifi");
        driver.findElements(By.className("android.widget.Button")).get(1).click();


    }

}
