package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.ImportTKBPage;
import pages.LoginPage;

import java.time.Duration;

public class ImportTKBTest {
    private WebDriver driver;
    private ImportTKBPage importTKBPage;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        loginPage = new LoginPage(driver);
        importTKBPage = new ImportTKBPage(driver);

        // Điều hướng đến trang web và đăng nhập
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
        loginPage.navigateToLoginPage();
        loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
    }

    @Test(priority = 1)
    public void openImportTKB() throws InterruptedException {
        importTKBPage.openImportTKB();
    }

    @Test(priority = 2)
    public void importTKB() throws InterruptedException {
        importTKBPage.selectTerm("998");
        importTKBPage.selectMajor("Công Nghệ Thông Tin 28");
        importTKBPage.uploadFile("C:\\Users\\ADMIN\\Downloads\\CNTT UIS-ThoiKhoaBieu_TieuChuan_Mau.xlsx");
        importTKBPage.importTKB();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
