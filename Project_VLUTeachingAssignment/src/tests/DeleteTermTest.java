package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import pages.LoginPage;
import pages.DeleteTermPage;

public class DeleteTermTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private DeleteTermPage deleteTermPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", 
                "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");

        loginPage = new LoginPage(driver);
        deleteTermPage = new DeleteTermPage(driver, wait);
        loginPage.navigateToLoginPage();
        loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
    }

    @Test(priority = 0)
    public void deleteTermTest() throws InterruptedException {
        deleteTermPage.navigateToTermPage();
        deleteTermPage.searchTerm("983");
        deleteTermPage.deleteTerm();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
