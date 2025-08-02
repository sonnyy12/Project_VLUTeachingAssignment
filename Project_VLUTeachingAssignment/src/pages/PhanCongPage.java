package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PhanCongPage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public PhanCongPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	// Mở trang Phân Công
	public void openPhanCong() throws InterruptedException {
		// Mở menu
		WebElement category = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[4]/a/span"));
		category.click();
		Thread.sleep(500);

		// Chọn Phân Công
		WebElement phancong = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[4]/ul/li[2]/a"));
		phancong.click();
	}

	// Lọc theo môn học
	public void filterSubject() throws InterruptedException {
		
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"select2-term-container\"]"))).click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//li[contains(text(), '998')]"))).click();
		Thread.sleep(2000);
		
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"assignLecturerDiv\"]/div[2]/div[1]/div[4]/span/span[1]/span"))).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"assignLecturerDiv\"]/div[2]/div[1]/div[4]/span[2]/span/div/button[2]"))).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"assignLecturerDiv\"]/div[2]/div[1]/div[4]/span[1]/span[1]/span"))).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[contains(text(), 'Nhập môn Công nghệ thông tin')]"))).click();
		
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"assignLecturerDiv\"]/div[2]/div[1]/div[5]/span[1]/span[1]/span"))).click();
		
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"assignLecturerDiv\"]/div[2]/div[1]/div[5]/span[2]/span/div/button[2]"))).click();
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"assignLecturerDiv\"]/div[2]/div[1]/div[5]/span[1]/span[1]/span"))).click();
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//li[contains(text(), 'Nguyễn Thành Đạt')]"))).click();
		
	}
	// Export danh sách phân công
	public void exportPhanCong() throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"assignLecturerDiv\"]/div[2]/div[2]/div/button"))).click();
		Thread.sleep(2000);
	}
}
