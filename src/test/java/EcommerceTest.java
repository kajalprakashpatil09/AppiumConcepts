import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/*
This class contains E2E tests to verify eCommerce mobile app functionality
 */

public class EcommerceTest extends BaseTest{

    /*
     Fill the form details and verify login functionality
     */
    @Test
    public void fillForm() throws InterruptedException {
        Thread.sleep(5000);

        //Enter name
        driver.findElement(By.className("android.widget.EditText")).sendKeys("testName");
        driver.hideKeyboard();

        //Select gender
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();

        //Select country
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();

        //Click on shop button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    /*
    Verify error message on Home screen
     */
    @Test
    public void verifyToastMessage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    /*
    PENDING
      Verify items selected on products page matching with cart items
      Verify long press functionality
      Verify navigation to webview
     */
    @Test
    public void verifyProductSelection() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.className("android.widget.EditText")).sendKeys("testName");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Thread.sleep(5000);

        //Scroll to specified product
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));

        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        System.out.println("count:" + count);

        for(int i=0;i<count;i++){
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(productName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }

        Thread.sleep(3000);
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        String cartProductName = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getAttribute("text");
//        Assert.assertEquals(cartProductName,"Jordan 6 Rings");

        //Accept terms by long pressing button
        WebElement termsElement = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPress(termsElement);
        driver.findElement(By.id("android:id/button1"));

        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(3000);

    }

    /*
    PENDING
    Validate total amount displayed in checkout page matches with items selected in product list
    */
    public void verifyCartProductPriceTotal(){
        driver.findElement(By.className("android.widget.EditText")).sendKeys("testName");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text('Jordan 6 Rings'))"));

    }

    /*
    Verify hybrid application frunctionality using Appium
     */
    public void testHybridApp() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.className("android.widget.EditText")).sendKeys("testName");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        //Retrieve all contexts
        Set<String> contexts = driver.getContextHandles();
        for(String s:contexts){
            System.out.println("current context:" + s);
        }

        //Switch to webview
        driver.context("webview");

        //Perform operations on browser -> Following test can be executed if we set chromeDriverExecutable in options
        driver.findElement(By.cssSelector("#APjFqb")).sendKeys("test google home");
        driver.findElement(By.cssSelector("#APjFqb")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //switch back to mobile app
        driver.context("NATIVE_APP");
    }



}
