package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DeleteUserPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public DeleteUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Mở trang User
    public void openUserPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main-menu-navigation']/li[3]"))).click();
    }

    // Tìm kiếm user
    public void searchUser(String username) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tblUser_filter']/label/input")))
            .sendKeys(username);
        Thread.sleep(2000);
    }

    // Xóa user
    public void deleteUser() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tblUser']/tbody/tr/td[7]/a[2]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click();
    }
}
