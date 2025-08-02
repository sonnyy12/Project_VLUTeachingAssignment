package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddTermPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	
	

	//import từng xpath cho từng lable

	private By termMenu = By.xpath("//*[@id='main-menu-navigation']/li[2]/a/i");
	private By addTermBtn = By.xpath("//*[@id='tblTerm_wrapper']/div[1]/div[2]/div/div[2]/button");
	private By termIdField = By.id("id");
	private By startYearDropdown = By.id("select2-start_year-container");
	private By endYearDropdown = By.id("select2-end_year-container");
	private By startDateField = By.cssSelector("input.flatpickr-input");
	private By saveBtn = By.xpath("//*[@id='term-form']/div[7]/button[2]");
	private By coutinueBtn = By.xpath("//button[contains(@class, 'swal2-confirm') and text()='OK']\r\n"
			+ "");
	
	
	  
	//import xpath khung thông báo lỗi để xuất ra console câu kiểm tra thông báo lỗi 
	private By termError = By.xpath("//*[@id=\"swal2-html-container\"]");
	private By idError = By.xpath("//*[@id='id-error']");
	private By startDateError = By.xpath("//*[@id='start_date-error']");
	private By endYearError = By.xpath("// *[@id='end_year-error']");
	private By duplicateTermError = By.xpath("//*[@id='swal2-html-container']");
	private By successMessage = By.xpath("//*[@id='toast-container']/div/div");

	public AddTermPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void scrollToElementAndClick(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void openTermPage() {
		scrollToElementAndClick(termMenu);

	}

	public void createNewTerm(String termId, String startYear, String endYear, String startDate) {
		wait.until(ExpectedConditions.elementToBeClickable(addTermBtn)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(termIdField)).sendKeys(termId);

		// Chọn năm bắt đầu
		wait.until(ExpectedConditions.elementToBeClickable(startYearDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + startYear + "']"))).click();

		// Chọn năm kết thúc
		wait.until(ExpectedConditions.elementToBeClickable(endYearDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + endYear + "']"))).click();

		// Nhập ngày bắt đầu nếu có
		if (!startDate.isEmpty()) {
			WebElement dateField = driver.findElement(startDateField);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + startDate + "')",
					dateField);
		}

		wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

	}

	// ✅ Chỉnh sửa getErrorMessage() để lấy lỗi cụ thể
	public String getErrorMessage() {
		try {
			if (isElementDisplayed(idError)) {
				return driver.findElement(idError).getText().trim(); // Lỗi thiếu mã học kỳ
			} else if (isElementDisplayed(startDateError)) {
				return driver.findElement(startDateError).getText().trim(); // Lỗi thiếu ngày bắt đầu
			} else if (isElementDisplayed(endYearError)) {
				return driver.findElement(endYearError).getText().trim(); // Lỗi năm kết thúc nhỏ hơn năm bắt đầu
			} else if (isElementDisplayed(duplicateTermError)) {
				return driver.findElement(duplicateTermError).getText().trim(); // Lỗi học kỳ đã tồn tại
			} else if (isElementDisplayed(termError)) {
				return driver.findElement(termError).getText().trim();
			} else {
				return "Không có thông báo lỗi.";
			}
		} catch (Exception e) {
			return "Không có thông báo lỗi.";
		}
	}
	public void coutinue() {
		wait.until(ExpectedConditions.elementToBeClickable(coutinueBtn)).click();
	}

	public String getSuccessMessage() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
		} catch (Exception e) {
			return "Không có thông báo thành công.";
		}
	}

	private boolean isElementDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
