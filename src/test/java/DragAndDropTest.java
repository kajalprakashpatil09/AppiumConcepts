import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
This class contains test to verify drag and drop functionality
 */

public class DragAndDropTest extends BaseTest{

    @Test
    public void dragAndDropGesture(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement elementToDrag = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) elementToDrag).getId(),
                "endX", 661,
                "endY", 587
        ));

       String textAfterSuccessfulDrop = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
       Assert.assertEquals(textAfterSuccessfulDrop,"Dropped!");
    }
}
