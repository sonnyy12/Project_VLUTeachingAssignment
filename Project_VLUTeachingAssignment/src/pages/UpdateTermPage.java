package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UpdateTermPage {
	 private WebDriver driver;
	    private WebDriverWait wait;
    
    
    private By successMessage = By.xpath("//*[@id=\"toast-container\"]/div/div");
    
    public UpdateTermPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Điều hướng đến trang Học kỳ
    public void navigateToTermPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main-menu-navigation']/li[2]/a/i"))).click();
    }

    // Tìm kiếm học kỳ theo mã
    public void searchTerm(String termCode) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tblTerm_filter']/label/input")));
        searchBox.clear();
        searchBox.sendKeys(termCode);
    }

    // Chỉnh sửa học kỳ
    public void editTerm(String startYear, String endYear) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tblTerm']/tbody/tr/td[9]/a[1]/i"))).click();
        
        // Chọn năm bắt đầu
        wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-start_year-container"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + startYear + "']"))).click();

        // Chọn năm kết thúc
        wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-end_year-container"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + endYear + "']"))).click();
        
    
        // Nhấn nút cập nhật
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='term-form']/div[7]/button[2]"))).click();
        
    }
    
    // Kiểm tra thông báo thành công
    public String getSuccessMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
        } catch (Exception e) {
            return "Không có thông báo thành công.";
        }
    }

}