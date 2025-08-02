package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UpdateMajorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By majorMenu = By.xpath("//*[@id=\"main-menu-navigation\"]/li[2]/a/span");
    private By majorTab = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]");
    private By searchBox = By.xpath("//*[@id=\"tblMajor_filter\"]/label/input");
    private By editButton = By.xpath("//*[@id=\"tblMajor\"]/tbody/tr/td[6]/a[1]/i");
    private By nameField = By.xpath("//*[@id=\"name\"]");
    private By abbreviationField = By.xpath("//*[@id=\"abbreviation\"]");
    private By categoryDropdown = By.xpath("//*[@id=\"select2-program_type-container\"]");
    private By categoryOption = By.xpath("//li[contains(text(), 'Đặc biệt')]");
    private By saveButton = By.xpath("//*[@id=\"major-form\"]/div[5]/button[2]");

    public UpdateMajorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openMajorPage() {
        wait.until(ExpectedConditions.elementToBeClickable(majorMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(majorTab)).click();
    }

    public void updateMajor(String searchText, String newName, String newAbbreviation) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys(searchText);
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).sendKeys(newName);
        wait.until(ExpectedConditions.elementToBeClickable(abbreviationField)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(abbreviationField)).sendKeys(newAbbreviation);
        driver.findElement(categoryDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(categoryOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }
}