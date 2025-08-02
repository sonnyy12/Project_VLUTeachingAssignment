package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.AddTermPage;

public class AddTermTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private AddTermPage termPage;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");

		loginPage = new LoginPage(driver);
		termPage = new AddTermPage(driver);
		
		loginPage.navigateToLoginPage();
		loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
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


	@Test(priority = 1)
	public void testCreateTermValidData() {
		termPage.openTermPage();
		termPage.createNewTerm("983", "2022", "2025", "01/12/2022");

		String successMessage = termPage.getSuccessMessage();
		printTestResult("TC01", "Tạo học kỳ với dữ liệu hợp lệ", successMessage, "Lưu thành công!");
		Assert.assertEquals(successMessage, "Lưu thành công!");
	}

	@Test(priority = 2)
	public void testCreateTermWithExistingID() {
		termPage.openTermPage();
		termPage.createNewTerm("983", "2023", "2026", "01/19/2023");
		termPage.coutinue();
		String errorMessage = termPage.getErrorMessage();
		printTestResult("TC02", "Tạo học kỳ với mã đã tồn tại", errorMessage, "Học kỳ này đã được tạo trong hệ thống!");
		Assert.assertEquals(errorMessage, "Học kỳ này đã được tạo trong hệ thống!");
		
	}

	@Test(priority = 3)
	public void testCreateTermWithoutID() {
		termPage.openTermPage();
		termPage.createNewTerm("", "2022", "2025", "01/12/2022");
		
		String errorMessage = termPage.getErrorMessage();
		printTestResult("TC03", "Tạo học kỳ mà không nhập mã", errorMessage, "Bạn chưa nhập học kỳ");
		Assert.assertEquals(errorMessage, "Bạn chưa nhập học kỳ");
		
	}

	@Test(priority = 4)
	public void testCreateTermWithoutStartDate() {
		termPage.openTermPage();
		termPage.createNewTerm("991", "2022", "2025", "");

		String errorMessage = termPage.getErrorMessage();
		printTestResult("TC04", "Tạo học kỳ mà không có ngày bắt đầu", errorMessage, "Bạn chưa chọn ngày bắt đầu");
		Assert.assertEquals(errorMessage, "Bạn chưa chọn ngày bắt đầu");
	}

	@Test(priority = 5)
	public void testCreateTermInvalidYearRange() {
		termPage.openTermPage();
		termPage.createNewTerm("990", "2025", "2024", "01/19/2022");

		String errorMessage = termPage.getErrorMessage();
		printTestResult("TC05", "Tạo học kỳ với năm kết thúc nhỏ hơn năm bắt đầu", errorMessage,
				"Năm kết thúc không thể nhỏ hơn năm bắt đầu!");
		Assert.assertEquals(errorMessage, "Năm kết thúc không thể nhỏ hơn năm bắt đầu!");
	}

	//@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
