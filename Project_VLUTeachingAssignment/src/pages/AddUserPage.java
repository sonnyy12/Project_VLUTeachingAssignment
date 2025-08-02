package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddUserPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By userMenu = By.xpath("//*[@id=\"main-menu-navigation\"]/li[3]/a");
	private By addUserButton = By.xpath("//*[@id=\"tblUser_wrapper\"]/div[1]/div[2]/div/div[2]/button");
	private By idField = By.id("staff_id");
	private By nameField = By.id("full_name");
	private By emailField = By.id("email");

	private By typeDropdown = By.xpath("//*[@id=\"select2-type-container\"]");

	private By roleDropdown = By.xpath("//*[@id=\"select2-role_id-container\"]");

	private By saveButton = By.xpath("//*[@id=\"user-form\"]/div[7]/button[2]");
	private By closeButton = By.xpath("/html/body/div[3]/div[1]/button"); 

	private By errorMessage = By.xpath("//label[contains(@id, '-error')]\r\n" + "");
	private By successMessage = By.xpath("//*[@id='toast-container']/div/div");

	public AddUserPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	private void scrollToElementAndClick(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public void openUserPage() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(userMenu)).click();
	}

	public void createUser(String id, String name, String email, String type, String role) throws InterruptedException {
		
		WebElement addUserBtn = wait.until(ExpectedConditions.elementToBeClickable(addUserButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addUserBtn);

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(idField)).sendKeys(id);
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);

		if (!type.isEmpty()) {
			scrollToElementAndClick(typeDropdown);
			scrollToElementAndClick(By.xpath("//li[contains(text(), '" + type + "')]"));
		}

		if (!role.isEmpty()) {
			scrollToElementAndClick(roleDropdown);
			scrollToElementAndClick(By.xpath("//li[contains(text(), '" + role + "')]"));
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton)).click();
		Thread.sleep(1000);
		
	}

	public String getErrorMessage() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText().trim();
		} catch (Exception e) {
			return "Không có thông báo lỗi.";
		}
	}

	public String getSuccessMessage() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText().trim();
		} catch (Exception e) {   
			return "Không có thông báo thành công.";
		}
	}
}
