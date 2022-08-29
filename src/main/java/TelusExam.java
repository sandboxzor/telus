import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TelusExam {

    WebDriver driver;
    String strUrl = "https://www.telusinternational.ai/cmp";

    //Telus Dashboard Page Elements
    final By txtUsername = By.name("email");
    final By btnContinue = By.xpath("//button/span[text()='Continue']/..");
    final By txtPassword = By.name("password");
    final By btnLogin = By.xpath("//button/span[text()='Sign in']/..");
    final By icoProfile = By.xpath("//button/div[@aria-label='User Avatar']");
    final By aMyProfile = By.xpath("//a/span[text()='My Profile']");
    final By aLanguages = By.xpath("//a[text()='Languages']");
    final By btnAddLanguage = By.xpath("//button/span[text()='Add']/..");
    final By ddAddLanguage = By.xpath("//input[@aria-label='Type language and select*']");
    final By ddAddProficiency = By.xpath("//input[@aria-label='Select proficiency level*']");
    final By btnSave = By.xpath("//button/span[text()='Save']");
    final By aSignOut = By.xpath("//a/span[text()='Sign Out']");

    public TelusExam() {
        //placeholder
    }

    @BeforeTest
    public synchronized WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "D:\\Work\\automation\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

    // ---------------------------------
    // Generic Methods
    // ---------------------------------
    public WebElement getElement(By byEle) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
    }

    // ---------------------------------
    // Telus Methods
    // ---------------------------------
    public void loginUser() {
        getElement(txtUsername).sendKeys("jeric.urrutia@gmail.com");
        getElement(btnContinue).click();
        getElement(txtPassword).sendKeys("Tester123@@");
        getElement(btnLogin).click();
    }

    public void goToMyProfile() {
        getElement(icoProfile).click();
        getElement(aMyProfile).click();
    }

    public void addLanguage() {
        getElement(aLanguages).click();
        getElement(btnAddLanguage).click();
        getElement(ddAddLanguage).sendKeys("Filipino (Philippines)");
        getElement(ddAddProficiency).sendKeys("Native of bilingual proficiency");
        getElement(btnSave).click();
    }

    public void logoutUser() {
        getElement(icoProfile).click();
        getElement(aSignOut).click();
    }

    @Test
    public void happyPath() {
        driver.get(strUrl);
        loginUser();
        goToMyProfile();
        addLanguage();
        logoutUser();
    }

    @AfterTest
    public void driverTearDown() {
        driver.quit();
        driver = null;
    }

}
