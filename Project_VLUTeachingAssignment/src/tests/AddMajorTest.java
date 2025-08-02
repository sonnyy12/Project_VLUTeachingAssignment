package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddMajorPage;
import pages.LoginPage;

public class AddMajorTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private AddMajorPage majorPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");

        loginPage = new LoginPage(driver);
        majorPage = new AddMajorPage(driver);
        performLogin();
        
    }

    private void performLogin() {
    	loginPage.navigateToLoginPage();
        loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
    }

    @BeforeMethod
    public void openMajorPage() {
        majorPage.openMajorPage();
    }

    @Test(priority = 1)
    public void testCreateMajorValid() {
    	
        majorPage.createMajor("MD01111", "Sửa xe", "SX", "Tiêu chuẩn");
        
        Assert.assertEquals(majorPage.getSuccessMessage(), "Lưu thành công!");
        
        System.out.println("\n----- Kết quả kiểm thử -----");
        System.out.println("Thêm ngành | Mong đợi: Thêm ngành thành công");
        System.out.println(" ✅ TC01: Tạo ngành hợp lệ - Thành công!");
    }

    @Test(priority = 2)
    public void testCreateMajorWithoutID() throws InterruptedException {
        majorPage.createMajor("", "Sửa xe", "SX", "Tiêu chuẩn");
        Thread.sleep(1000);
        Assert.assertEquals(majorPage.getErrorMessage(), "Bạn chưa nhập mã ngành");
        majorPage.closeForm();
        System.out.println("\n----- Kết quả kiểm thử -----");
        System.out.println("Chưa thêm mã ngành | Mong đợi: Thông báo lỗi chưa thêm mã ngành");
        System.out.println(" ✅ TC02: Không nhập mã ngành - Lỗi hiển thị đúng!");
    }

    @Test(priority = 3)
    public void testCreateMajorWithoutName() throws InterruptedException {
        majorPage.createMajor("MD01111", "", "SX", "Tiêu chuẩn");
        Thread.sleep(1000);
        Assert.assertEquals(majorPage.getErrorMessage(), "Bạn chưa nhập tên ngành");
        majorPage.closeForm();
        System.out.println("\n----- Kết quả kiểm thử -----");
        System.out.println("Chưa thêm tên ngành | Mong đợi: Thông báo lỗi chưa thêm tên ngành");
        System.out.println(" ✅ TC03: Không nhập tên ngành - Lỗi hiển thị đúng!");
    }

    @Test(priority = 4)
    public void testCreateMajorWithoutAbbreviation() throws InterruptedException {
        majorPage.createMajor("MD01111", "Sửa xe", "", "Tiêu chuẩn");
        Thread.sleep(1000);
        Assert.assertEquals(majorPage.getErrorMessage(), "Bạn chưa nhập tên viết tắt của ngành");
        majorPage.closeForm();
        System.out.println("\n----- Kết quả kiểm thử -----");
        System.out.println("Chưa thêm tên viết tắt | Mong đợi: Thông báo lỗi chưa thêm tên viết tắt");
        System.out.println(" ✅ TC04: Không nhập viết tắt - Lỗi hiển thị đúng!");
    }

    @Test(priority = 5)
    public void testCreateMajorWithoutCTDT() throws InterruptedException {
    	Thread.sleep(1000);
        majorPage.createMajor("MD01111", "Sửa xe", "SX", "");
        Assert.assertEquals(majorPage.getErrorMessage(), "Bạn chưa chọn CTĐT");
        majorPage.closeForm();
        System.out.println("\n----- Kết quả kiểm thử -----");
        System.out.println("Chưa thêm CTĐT | Mong đợi: Thông báo lỗi chưa thêm CTĐT");
        System.out.println(" ✅ TC05: Không chọn CTDT - Lỗi hiển thị đúng!");
    }

    //@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
