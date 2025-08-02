package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchUserPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By userMenu = By.xpath("//*[@id=\"main-menu-navigation\"]/li[3]/a");
    private By searchBox = By.xpath("//*[@id=\"tblUser_filter\"]/label/input");

    public SearchUserPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openUserPage() {
        wait.until(ExpectedConditions.elementToBeClickable(userMenu)).click();
    }

    public void searchUser(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(username);
    }
}
