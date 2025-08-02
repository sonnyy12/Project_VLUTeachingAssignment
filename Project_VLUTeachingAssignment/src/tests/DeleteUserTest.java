package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.DeleteUserPage;
import pages.LoginPage;

import java.time.Duration;

public class DeleteUserTest {
	private WebDriver driver;
	private DeleteUserPage deleteUserPage;
	private LoginPage loginPage;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		loginPage = new LoginPage(driver);
		deleteUserPage = new DeleteUserPage(driver);

		
		driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
		loginPage.navigateToLoginPage();
		loginPage.login("son.207CT65800@vanlanguni.vn", "VLU01022002");
	}

	@Test(priority = 1)
	public void openUserPage() {
		deleteUserPage.openUserPage();
	}

	@Test(priority = 2)
	public void searchAndDeleteUser() throws InterruptedException {
		deleteUserPage.searchUser("Son Tran");
		deleteUserPage.deleteUser();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
