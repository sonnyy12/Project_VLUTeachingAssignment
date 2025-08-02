package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteMajorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DeleteMajorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openMajorPage() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-menu-navigation\"]/li[2]/a/span"))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]"))).click();
        Thread.sleep(2000);
    }

    public void deleteMajor(String majorName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tblMajor_filter\"]/label/input"))).sendKeys(majorName);
        Thread.sleep(2000);
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@onclick, \"deleteMajor('MD01111')\")]\r\n"
        		+ "")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click();
        Thread.sleep(2000);
    }
}