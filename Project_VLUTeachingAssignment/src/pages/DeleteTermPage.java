package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteTermPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public DeleteTermPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateToTermPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-menu-navigation\"]/li[2]/a/i"))).click();
    }

    public void searchTerm(String termCode) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tblTerm_filter\"]/label/input"))).sendKeys(termCode);
    }

    public void deleteTerm() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tblTerm\"]/tbody/tr/td[9]/a[2]"))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[6]/button[1]"))).click();
        Thread.sleep(2000);
    }
}
