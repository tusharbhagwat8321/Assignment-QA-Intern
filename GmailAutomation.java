package Action_class;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GmailAutomation {

    public static void main(String[] args) throws InterruptedException {
        // Replace with your own Gmail credentials
       String email = "tusharbhagwat2303@gmail.com";
        String password = "your_password";

        ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
        //WebDriver driver = new ChromeDriver();

        // Task 1: Login to Gmail
        driver.get("https://mail.google.com/");
        driver.findElement(By.id("identifierId")).sendKeys(email);
        driver.findElement(By.id("identifierNext")).click();
       //
        Wait<WebDriver> wait = new WebDriverWait(driver, null);
     // WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("passwordNext")).click();

        // Task 2: Compose an Email
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("to"))).sendKeys(email);
        driver.findElement(By.name("subjectbox")).sendKeys("Test Mail");
        WebElement emailBody = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        emailBody.sendKeys("Test Email Body");

        // Task 3: Label the Email and Send
        driver.findElement(By.xpath("//div[text()='Send']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Inbox')]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Test Mail']")));

        // Task 4: Wait for the Email to Arrive (optional)
        Thread.sleep(10000);  // Waiting for the email to arrive (You can use more robust waiting techniques if needed)

        // Task 5: Mark the Email as Starred
        driver.findElement(By.xpath("//span[text()='Test Mail']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='More options']"))).click();
        driver.findElement(By.xpath("//div[text()='Add star']")).click();

        // Task 6: Open the Received Email
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Inbox')]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Test Mail']"))).click();

        // Task 7: Verify the Email
        // Verify label
        WebElement label = driver.findElement(By.xpath("//span[@data-tooltip='Social']"));
        assert label.isDisplayed() : "Label 'Social' not found";

        // Verify subject
        WebElement subjectElement = driver.findElement(By.xpath("//h2[contains(@class, 'hP')]"));
        String subject = subjectElement.getText();
        assert subject.equals("Test Mail") : "Subject mismatch. Expected 'Test Mail', got '" + subject + "'";

        // Verify body
        WebElement bodyElement = driver.findElement(By.xpath("//div[@dir='ltr']"));
        String body = bodyElement.getText();
        assert body.equals("Test Email Body") : "Body mismatch. Expected 'Test Email Body', got '" + body + "'";

        // Close the browser session
        driver.quit();
    }
}
