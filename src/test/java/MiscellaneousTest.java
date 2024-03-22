import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Key;

/*
This class contains test to verify orientation, copy-paste functionality. It also contains test to launch app using
activity name
 */

public class MiscellaneousTest extends BaseTest {

    @Test
    public void orientationAndClipboardCopyPasteTest() throws InterruptedException {
        //Navigate to wifi settings
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(By.id("android:id/checkbox")).click();

        //Device rotation
        DeviceRotation landscape = new DeviceRotation(0,0,90);
        driver.rotate(landscape);

        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

        //Get wifi setting alert title
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings");

        //Set wifi name by copy-paste from clipboard
        driver.setClipboardText("wifi test");
        driver.findElement(By.className("android.widget.EditText")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.findElements(By.className("android.widget.Button")).get(1).click();

        Thread.sleep(3000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        Thread.sleep(3000);
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    @Test
    public void openAppByUsingActivityName() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("mobile:startActivity", ImmutableMap.of(
                "intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
        ));

        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

        //Get wifi setting alert title
        String wifiAlertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(wifiAlertTitle, "WiFi settings");
    }

}
