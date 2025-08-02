package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddMajorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By majorMenu = By.xpath("//*[@id='main-menu-navigation']/li[2]/a/span");
    private By majorSubMenu = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a");
    private By addMajorButton = By.xpath("//*[@id='tblMajor_wrapper']/div[1]/div[2]/div/div[2]/button");
    private By idField = By.id("id");
    private By nameField = By.id("name");
    private By abbreviationField = By.id("abbreviation");
    private By categoryDropdown = By.id("select2-program_type-container");
    private By saveButton = By.xpath("//*[@id='major-form']/div[5]/button[2]");
    private By closeButton = By.id("btnClose");

    private By errorMessage = By.xpath("//label[contains(@id, '-error')]");  
    private By successMessage = By.xpath("//*[@id=\"toast-container\"]/div/div");

    public AddMajorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Hàm scroll & click, nếu bị chặn thì dùng JavaScript click
    private void scrollToElementAndClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void openMajorPage() {
        scrollToElementAndClick(majorMenu);
        scrollToElementAndClick(majorSubMenu);
    }

    public void createMajor(String id, String name, String abbreviation, String category) {
        scrollToElementAndClick(addMajorButton);

      
        wait.until(ExpectedConditions.visibilityOfElementLocated(idField));

        driver.findElement(idField).sendKeys(id);
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(abbreviationField).sendKeys(abbreviation);

       
        if (!category.isEmpty()) {
            scrollToElementAndClick(categoryDropdown);
            scrollToElementAndClick(By.xpath("//li[contains(text(), '" + category + "')]"));
        }

        scrollToElementAndClick(saveButton);
    }

    public void closeForm() {
        scrollToElementAndClick(closeButton);
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
