package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pages.LoginPage;
import pages.UpdateUserPage;

import java.time.Duration;

public class UpdateUserTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private UpdateUserPage updateUserPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage(driver);
        updateUserPage = new UpdateUserPage(driver, wait);
        
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
        loginPage.navigateToLoginPage();
    }

    @Test
    public void login() {
        loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
    }

    @Test(dependsOnMethods = "login")
    public void updateUser() {
    	updateUserPage.openUsermenu();
        updateUserPage.searchUser("Tran Son");
        updateUserPage.clickEditButton();
        updateUserPage.updateFullName("Son Tran");
        updateUserPage.updateLecturerType();
        updateUserPage.updateRole();
        updateUserPage.saveChanges();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
