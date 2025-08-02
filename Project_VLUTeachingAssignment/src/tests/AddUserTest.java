package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.AddUserPage;
import pages.LoginPage;

import java.time.Duration;

public class AddUserTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private AddUserPage addUserPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage(driver);
        addUserPage = new AddUserPage(driver);

        loginPage.navigateToLoginPage();
    }

    @Test(priority = 1)
    public void login() {
        loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
    }

    @Test(priority = 2)
    public void openUserPage() {
        addUserPage.openUserPage();
    }

    @Test(priority = 3)
    public void createUserValidData() throws InterruptedException {
        addUserPage.createUser("207ct65800cntt", "Tran Son", "207ct65800@vanlanguni.vn", "Cơ hữu", "BCN khoa");
        System.out.println("\n----- Kết quả kiểm thử -----");
        System.out.println("Tạo user đầy đủ thông tin| Mong đợi: Tạo user thành công");
        System.out.println("✅ TC01: Tạo user - Tạo user thành công!");
    }

    @Test(priority = 4)
    public void createUserWithIncorrectFormatId() throws InterruptedException {
        addUserPage.createUser("SON TRAN", "Tran Son", "207ct65800@vanlanguni.vn", "Cơ hữu", "BCN khoa");
      
        String actualMessage = addUserPage.getErrorMessage() ;
        String expectedMessage = "Chỉ được nhập số-chữ không dấu và không có khoảng trắng!";
        printTestResult("TC02", "Kiểm tra lỗi khi nhập mã giảng viên không đúng định dạng", actualMessage, expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test(priority = 5)
    public void createUserWithoutStaffId() throws InterruptedException {
        addUserPage.createUser("", "Tran Son", "207ct65800@vanlanguni.vn", "Cơ hữu", "BCN khoa");
    
        String actualMessage = addUserPage.getErrorMessage();
        String expectedMessage = "Bạn chưa nhập mã giảng viên";
        printTestResult("TC03", "Kiểm tra lỗi khi thiếu mã giảng viên", actualMessage, expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test(priority = 6)
    public void createUserWithoutFullName() throws InterruptedException {
        addUserPage.createUser("207ct65800cntt", "", "207ct65800@vanlanguni.vn", "Cơ hữu", "BCN khoa");
       
        String actualMessage = addUserPage.getErrorMessage();
        String expectedMessage = "Bạn chưa nhập tên giảng viên";
        printTestResult("TC04", "Kiểm tra lỗi khi thiếu tên giảng viên", actualMessage, expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test(priority = 7)
    public void createUserWithoutUserType() throws InterruptedException {
        addUserPage.createUser("207ct65800cntt", "Tran Son", "207ct65800@vanlanguni.vn", "", "BCN khoa");
        
        String actualMessage = addUserPage.getErrorMessage();
        String expectedMessage = "Bạn chưa chọn loại giảng viên";
        printTestResult("TC05", "Kiểm tra lỗi khi thiếu loại giảng viên", actualMessage, expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test(priority = 8)
    public void createUserWithoutRole() throws InterruptedException {
        addUserPage.createUser("207ct65800cntt", "Tran Son", "207ct65800@vanlanguni.vn", "Cơ hữu", "");
        
        String actualMessage = addUserPage.getErrorMessage();
        String expectedMessage = "Bạn chưa chọn role";
        printTestResult("TC06", "Kiểm tra lỗi khi thiếu role", actualMessage, expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    private void printTestResult(String tcId, String description, String actual, String expected) {
        System.out.println("----- Kết quả kiểm thử -----");
        System.out.println(description + " | Mong đợi: " + expected);

        if (actual.equals(expected)) {
            System.out.println("✅ " + tcId + ": Kiểm thử thành công: Thông tin hiển thị đúng!\n");
        } else {
            System.out.println("❌ " + tcId + ": Kiểm thử thất bại! Giá trị thực tế: " + actual + "\n");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
