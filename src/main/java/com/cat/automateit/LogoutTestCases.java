package com.cat.automateit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

public class LogoutTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "logoutCase";
    private static final Logger logger = Logger.getLogger(LogoutTestCases.class.getName());

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
        loginViaCreatedEmail(driver);
        logout(driver);
        driver.quit();
    }

    public static void loginViaCreatedEmail(WebDriver driver) throws InterruptedException {
        String emailMe = "positive_test@yopmail.com";
        String password = "Weak@123";
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(emailMe);

        WebElement passcode = driver.findElement(By.id("password"));
        passcode.sendKeys(password);
        passcode.submit();
        Thread.sleep(4000);
    }

    public static void logout(WebDriver driver) {
        WebElement logout = driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[1]"));
        logout.click();

        verifyLogout(driver);
        cs.takeScreenshot(driver, "HomePage.png", FOLDER_NAME);
    }

    public static void verifyLogout(WebDriver driver) {
        String expectedURL = "https://flamboyant-allen-00cf47.netlify.app/home";
        String actualURL = driver.getCurrentUrl();

        if (expectedURL.equals(actualURL)) {
            logger.info("Test is passed as logging out got redirected to Homepage");
        } else {
            logger.info("Test is failed as logging out did not got redirected to Homepage");
        }
    }
}