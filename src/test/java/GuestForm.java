import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestForm {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void visitWebsite(){
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        driver.findElement(By.id("first_name")).sendKeys("Afrina");
        driver.findElement(By.id("last_name")).sendKeys("Prity");
        driver.findElement(By.id("user_email")).sendKeys("afrina31@gmail.com");
        driver.findElement(By.id("radio_1665627729_Female")).click();
        driver.findElement(By.id("user_pass")).sendKeys("RoadtoSdet123#");

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,500)");

        List <WebElement> date = driver.findElements(By.cssSelector("[type = text]"));
        date.get(2).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");
        List<WebElement> phone = driver.findElements(By.id("phone_1665627880"));
        phone.get(1).sendKeys("01797234123");
        Select country = new Select(driver.findElement(By.id("country_1665629257")));
        country.selectByVisibleText("Bangladesh");

//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//        javascriptExecutor.executeScript("window.scrollBy(0,900)");

        driver.findElement(By.id("privacy_policy_1665633140")).click();
        List<WebElement> button = driver.findElements(By.cssSelector("[type=submit]"));
        button.get(2).click();

        WebElement element = driver.findElement(By.id("ur-submit-message-node"));
        String ActualResult = element.getText();
        String ExpectedResult = "User successfully registered.";
        Assertions.assertEquals(ActualResult,ExpectedResult);
    }
    @AfterAll
    public void tearDown(){
        driver.quit();
    }

}
