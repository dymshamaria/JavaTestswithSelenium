import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Java_05\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 1000);


        try {

            driver.get("https://127.0.0.1:5500/index.html");
            Thread.sleep(5000);

            WebElement element = driver.findElement(By.id("a"));
            WebElement element2 = driver.findElement(By.id("b"));
            WebElement element3 = driver.findElement(By.id("c"));

            element.click();

            Alert alert = wait.until(alertIsPresent());
            alert.accept();

            element2.click();

            Alert prompt = wait.until(alertIsPresent());

            prompt.sendKeys("Super !!");
            prompt.accept();

            Alert alert2 = wait.until(alertIsPresent());
            Thread.sleep(4000);
            alert2.accept();

            element3.click();
            Alert alert3 = wait.until(alertIsPresent());
            alert3.dismiss();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
