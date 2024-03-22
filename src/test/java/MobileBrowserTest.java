import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MobileBrowserTest extends MobileBrowserBaseTest{

    @Test
    public void browserTest(){
        driver.get("http://google.com");
        driver.getTitle();
        driver.findElement(By.cssSelector("#APjFqb")).sendKeys("test google home");
        driver.findElement(By.cssSelector("#APjFqb")).sendKeys(Keys.ENTER);
    }
}
