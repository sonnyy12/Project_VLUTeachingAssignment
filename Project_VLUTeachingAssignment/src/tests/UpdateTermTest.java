package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.UpdateTermPage;

import java.time.Duration;

public class UpdateTermTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private LoginPage loginPage;
	private UpdateTermPage updatetermPage;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		loginPage = new LoginPage(driver);
		updatetermPage = new UpdateTermPage(driver);
		loginPage.navigateToLoginPage();

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

	@Test(priority = 0)
	public void login() {
		loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
	}

	@Test(priority = 1)
	public void updateTermTest() {
		updatetermPage.navigateToTermPage();
		updatetermPage.searchTerm("983");
		updatetermPage.editTerm("2025", "2029");

		String successMessage = updatetermPage.getSuccessMessage();
		printTestResult("TC01", "Chỉnh sửa học kỳ", successMessage, "Cập nhật thành công!");
		Assert.assertEquals(successMessage, "Cập nhật thành công!");
	}

	// @AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}