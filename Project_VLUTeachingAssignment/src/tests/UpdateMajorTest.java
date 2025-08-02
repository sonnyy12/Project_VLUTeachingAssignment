package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import pages.LoginPage;
import pages.UpdateMajorPage;

public class UpdateMajorTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private UpdateMajorPage updatemajorPage;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
		loginPage = new LoginPage(driver);
		updatemajorPage = new UpdateMajorPage(driver);
		loginPage.navigateToLoginPage();
	}

	@Test
	public void testLogin() {
		loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
	}

	@Test(dependsOnMethods = "testLogin")
	public void testUpdateMajor() {
		updatemajorPage.openMajorPage();
		updatemajorPage.updateMajor("SX", "Sua Oto", "OTO");
	}

	// @AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
