package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToLoginPage() {
        driver.navigate().to("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("details-button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("proceed-link"))).click();
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("OpenIdConnect"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116"))).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
    }
}