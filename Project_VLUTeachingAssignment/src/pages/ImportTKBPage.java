package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ImportTKBPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public ImportTKBPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Mở trang Import TKB
    public void openImportTKB() throws InterruptedException {
        // Mở menu
        WebElement category = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[4]/a/span"));
        category.click();
        Thread.sleep(500);

        // Chọn Import TKB
        WebElement importTKB = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[4]/ul/li[1]/a/span"));
        importTKB.click();
    }

    // Chọn học kỳ
    public void selectTerm(String term) throws InterruptedException {
        WebElement termDropDown = driver.findElement(By.xpath("//*[@id=\"select2-term-container\"]"));
        termDropDown.click();
        WebElement termOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'" + term + "')]")));
        termOption.click();
        Thread.sleep(2000);
    }

    // Chọn ngành
    public void selectMajor(String major) throws InterruptedException {
        WebElement majorDropDown = driver.findElement(By.xpath("//*[@id=\"select2-major-container\"]"));
        majorDropDown.click();
        WebElement majorOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'" + major + "')]")));
        majorOption.click();
        Thread.sleep(2000);
    }

    // Upload file Import TKB
    public void uploadFile(String filePath) {
        WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));
        uploadInput.sendKeys(filePath);
    }

    // Thực hiện Import
    public void importTKB() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"submit-all\"]"))).click();

        // Click xác nhận 3 lần nếu có cảnh báo
        for (int i = 0; i < 3; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click();
            } catch (Exception e) {
                break;
            }
        }
    }
}
