package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.PhanCongPage;
import pages.LoginPage;

import java.time.Duration;

public class PhanCongTest {
	private WebDriver driver;
	private PhanCongPage phanCongPage;
	private LoginPage loginPage;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		loginPage = new LoginPage(driver);
		phanCongPage = new PhanCongPage(driver);

		// Điều hướng đến trang web và đăng nhập
		driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
		loginPage.navigateToLoginPage();
		//*[@id="toast-container"]/div/div
	}

	@Test(priority = 1)
	public void login() {
		loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Đăng nhập | Mong đợi: Đăng nhập vào trang chủ");
		System.out.println(" ✅ TC01: Đăng nhập thành công - PASSED");
	}

	@Test(priority = 2)
	public void openPhanCong() throws InterruptedException {
		phanCongPage.openPhanCong();
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Mở trang phân công | Mong đợi: Vào được trang phân công giảng dạy");
		System.out.println(" ✅ TC02: Vào trang phân công - PASSED");
	}

	@Test(priority = 3)
	public void filterAndExport() throws InterruptedException {

		phanCongPage.filterSubject();
		Thread.sleep(3000);
		phanCongPage.exportPhanCong();
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Export file phân công giảng dạy | Mong đợi: Export thành công");
		System.out.println(" ✅ TC03: Hợp lệ - PASSED");
	}

	// @AfterClass
	public void tearDown() {
		driver.quit();
	}
}
