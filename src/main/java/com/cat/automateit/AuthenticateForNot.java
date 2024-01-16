package com.cat.automateit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

public class AuthenticateForNot {

    private static final String MESSAGE_PREFIX = "Following the logout, in order to view the dashboard, users need to log in again.Therefore, an alert prompting for login is";
    private static final String MESSAGE_SUFFIX = "displayed. Consequently, the test has been successful.";
    private static final Logger logger = Logger.getLogger(AuthenticateForNot.class.getName());

    public static void main(String[] args) throws InterruptedException {
    	
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
        loginViaCreatedEmail(driver);
        logout(driver);
        homePageAuth(driver);
        resetPasswordAuth(driver);
        driver.quit();
    }

    private static void loginViaCreatedEmail(WebDriver driver) throws InterruptedException {
        String emailMe = "positive_test@yopmail.com";
        String password = "Weak@123";
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(emailMe);

        WebElement passcode = driver.findElement(By.id("password"));
        passcode.sendKeys(password);
        passcode.submit();
        Thread.sleep(4000);
    }

    private static void logout(WebDriver driver) {
        WebElement logout = driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[1]"));
        logout.click();
    }

    private static void homePageAuth(WebDriver driver) {
        WebElement dashboardAuth = driver.findElement(By.xpath("(//span[@class='mat-button-wrapper'])[3]"));
        dashboardAuth.click();
        if (isAlertPresent(driver)) {
            logger.info(MESSAGE_PREFIX + MESSAGE_SUFFIX);
        } else {
            logger.info(MESSAGE_PREFIX + " not " + MESSAGE_SUFFIX);
        }
        verifyAlert(driver);
    }

    private static void resetPasswordAuth(WebDriver driver) {
        driver.get("https://flamboyant-allen-00cf47.netlify.app/home");
        WebElement resetAuth = driver.findElement(By.xpath("(//span[@class='mat-button-wrapper'])[5]"));
        resetAuth.click();
        if (isAlertPresent(driver)) {
            logger.info(MESSAGE_PREFIX + MESSAGE_SUFFIX);
        } else {
            logger.info(MESSAGE_PREFIX + " not " + MESSAGE_SUFFIX);
        }
        verifyAlert(driver);
    }

    private static void verifyAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        // Perform actions on the alert
        logger.info("Alert Text: " + alert.getText());
        alert.accept();
    }

    private static boolean isAlertPresent(WebDriver driver) {
        try {
            // Try switching to an alert
            driver.switchTo().alert();
            // If switching to an alert is successful, an alert is present
            return true;
        } catch (Exception e) {
            // If switching to an alert throws an exception, no alert is present
            return false;
        }
    }
}