package com.cat.automateit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class PasswordTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "ForgetPasswordCases";
    private static final Logger logger = Logger.getLogger(PasswordTestCases.class.getName());

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
        positivePasswordCases(driver);
        driver.navigate().refresh();
        logger.info("----------------------------------------------");
        negativePasswordCases(driver);
        driver.quit();
    }

    public static void positivePasswordCases(WebDriver driver) throws InterruptedException {
        String email = "positive_test@yopmail.com";
        WebElement forgotPassword = driver.findElement(By.className("forgot-password"));
        forgotPassword.click();

        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        Thread.sleep(2000);
        WebElement submit = driver.findElement(By.className("btn"));
        submit.click();

        verifyRegisteredpw(driver);
        cs.takeScreenshot(driver, "Password1.png", FOLDER_NAME);
    }

    public static void negativePasswordCases(WebDriver driver) {
        String email = "not_registered@yopmail.com";

        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        WebElement submit = driver.findElement(By.className("btn"));
        submit.click();
        verifyNotRegisteredpw(driver);
        cs.takeScreenshot(driver, "Password1.png", FOLDER_NAME);
    }

    public static void verifyRegisteredpw(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("/html/body/div/div[14]/div/mat-dialog-container/app-email-sent/div/h1")));

        // Check if the modal title is "Email has been sent!"
        if (modalTitle.getText().equals("Email has been sent!")) {
            logger.info("Test Passed! Modal with title 'Email has been sent!' is displayed.");
        } else {
            logger.info("Test Failed! Modal with title 'Email has been sent!' is not displayed.");
        }
    }

    public static void verifyNotRegisteredpw(WebDriver driver) {
        WebElement errorElement = driver.findElement(By.xpath("/html/body/app-root/app-forgot-password/div/div/div[1]/p"));
        String errorSays = errorElement.getText();
        if (errorElement.isDisplayed()) {
            logger.info("Test is passed as invalid task name was provided, displayed error: " + errorSays);
        } else {
            logger.info("Test is failed as invalid task name was provided: " + errorSays);
        }
    }
}