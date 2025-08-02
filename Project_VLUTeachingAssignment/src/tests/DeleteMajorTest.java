package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import pages.DeleteMajorPage;
import pages.LoginPage;


public class DeleteMajorTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private DeleteMajorPage deletemajorPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        
        loginPage = new LoginPage(driver);
        deletemajorPage = new DeleteMajorPage(driver);
        loginPage.navigateToLoginPage();
    }

    @Test
    public void loginTest() {
        loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
    }

    @Test(dependsOnMethods = "loginTest")
    public void deleteMajor() throws InterruptedException {
        deletemajorPage.openMajorPage();
        deletemajorPage.deleteMajor("MD01111");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}