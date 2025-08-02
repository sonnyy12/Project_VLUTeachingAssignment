package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateUserPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By openUsermenu = By.xpath("//*[@id=\"main-menu-navigation\"]/li[3]");
	private By searchBox = By.xpath("//*[@id=\"tblUser_filter\"]/label/input");
	private By editButton = By.xpath("//*[@id=\"tblUser\"]/tbody/tr[1]/td[7]/a[1]");
	private By fullNameField = By.xpath("//*[@id=\"full_name\"]");

	private By saveButton = By.xpath("//*[@id=\"user-form\"]/div[7]/button[2]");

	public UpdateUserPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void openUsermenu() {
		wait.until(ExpectedConditions.elementToBeClickable(openUsermenu)).click();
	}

	public void searchUser(String username) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(username);
	}

	public void clickEditButton() {
		wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
	}

	public void updateFullName(String newName) {
		WebElement fullNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameField));
		fullNameInput.clear();
		fullNameInput.sendKeys(newName);
	}

	public void updateLecturerType() {
		By dropdown = By.xpath("//*[@id=\"select2-type-container\"]");
		wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
		By lecturerType = By.xpath("//li[text()='Thỉnh giảng']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(lecturerType));
		wait.until(ExpectedConditions.elementToBeClickable(lecturerType)).click();
	}

	public void updateRole() {
		By dropdown1 = By.xpath("//*[@id=\"select2-role_id-container\"]");
		wait.until(ExpectedConditions.elementToBeClickable(dropdown1)).click();
		By role = By.xpath("//li[text()='Bộ môn']");
		wait.until(ExpectedConditions.elementToBeClickable(role)).click();

	}

	public void saveChanges() {
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
	}
}
