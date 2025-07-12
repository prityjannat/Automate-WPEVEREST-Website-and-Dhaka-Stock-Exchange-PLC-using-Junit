import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TableData {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void visitWebsite() throws InterruptedException, IOException {
        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");
        Thread.sleep(7000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".shares-table tbody tr")));
        WebElement table = driver.findElement(By.className("floatThead-wrapper"));
        List<WebElement> allrows = table.findElements(By.cssSelector("tbody tr"));
     //   System.out.println(allrows.size());

        String data = "";
        for (WebElement row:allrows){
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement column : columns){
                 data += column.getText().trim() + " | ";
            }
            FileWriter fileWriter = new FileWriter("./src/test/resources/DataStore.txt");
       //     fileWriter.flush();
            fileWriter.write(data + "\n");
        //    fileWriter.close();
        }

    }
    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
