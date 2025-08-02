package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pages.LoginPage;
import pages.SearchUserPage;

import java.time.Duration;

public class SearchUserTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private SearchUserPage searchUserPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage(driver);
        searchUserPage = new SearchUserPage(driver, wait);

        loginPage.navigateToLoginPage();
    }

    @Test
    public void login() {
        loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
    }

    @Test(dependsOnMethods = "login")
    public void openUserPage() {
        searchUserPage.openUserPage();
    }

    @Test(dependsOnMethods = "openUserPage")
    public void searchUser() {
        searchUserPage.searchUser("Tran Son");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
