import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import javax.swing.*;

/*
This class contains test to verify scroll functionality
 */

public class ScrollTest extends BaseTest{

    @Test
    public void scrollGestureUsingGoogleEngineMethod() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));
        Thread.sleep(2000);
    }

    @Test
    public void scrollToEndUsingAppiumShortcut(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
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
}
