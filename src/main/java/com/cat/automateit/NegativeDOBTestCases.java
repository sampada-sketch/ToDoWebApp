package com.cat.automateit;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.logging.Logger;

public class NegativeDOBTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "DOBTestCases";
    private static final String DATE_XPATH = "//input[@id='mat-input-0']";
    private static final String MESSAGE = "Please enter valid DOB";
    private static final Logger logger = Logger.getLogger(NegativeDOBTestCases.class.getName());

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/signUp");
        Thread.sleep(2000);
        negativeInvalidDOB(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        negativeFutureDOB(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        negativeWrongMonDOB(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        negativeDOBWithChars(driver);
        Thread.sleep(2000);
        driver.quit();
    }

    public static void negativeInvalidDOB(WebDriver driver) throws InterruptedException {
        String dob = "1/1/201678";
        WebElement chooseDate = driver.findElement(By.xpath(DATE_XPATH));
        chooseDate.sendKeys(dob);
        Thread.sleep(2000);

        verifyInvalidDOB(driver);
        cs.takeScreenshot(driver, "InvalidDOB.png", FOLDER_NAME);
    }

    public static void negativeWrongMonDOB(WebDriver driver) throws InterruptedException {
        String dob = "13/13/2000";
        WebElement chooseDate = driver.findElement(By.xpath(DATE_XPATH));
        chooseDate.sendKeys(dob);
        Thread.sleep(2000);

        cs.takeScreenshot(driver, "WrongMonDOB.png", FOLDER_NAME);
        verifyWrongMonDOB(driver);
    }

    public static void negativeDOBWithChars(WebDriver driver) throws InterruptedException {
        String dob = "testing";
        WebElement chooseDate = driver.findElement(By.xpath(DATE_XPATH));
        chooseDate.sendKeys(dob);
        Thread.sleep(2000);

        verifyWrongMonDOB(driver);
        cs.takeScreenshot(driver, "DOBWithChars.png", FOLDER_NAME);
    }

    public static void negativeFutureDOB(WebDriver driver) throws InterruptedException {
        String dob = "1/1/2026";

        WebElement chooseDate = driver.findElement(By.xpath(DATE_XPATH));
        chooseDate.sendKeys(dob);
        Thread.sleep(2000);

        verifyFutureDOB(driver);
        cs.takeScreenshot(driver, "FutureDOB.png", FOLDER_NAME);
    }

    public static void verifyWrongMonDOB(WebDriver driver) {
        // Directly locate the element without waiting
        WebElement errorElement = driver.findElement(By
                .xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[3]/div/div"));
        String actualErrorDOB = errorElement.getText();
        try {
            Assert.assertEquals(actualErrorDOB, MESSAGE, "Assertion failed");
        } catch (AssertionError e) {
            e.printStackTrace();
            logger.info("Test is failed as the system failed to display 'proper invalid message' of Date of Birth");
            logger.info("System should Display: " + MESSAGE);
            logger.info("But the system displayed: " + actualErrorDOB);
        }
    }

    public static void verifyFutureDOB(WebDriver driver) {
        if (driver.getCurrentUrl().equals("https://flamboyant-allen-00cf47.netlify.app/signUp/setPassword")) {
            logger.info("Test is failed as system failed to validate 'future value' of Date of Birth");
        } else {
            logger.info("Test is passed as expected");
        }
    }

    public static void verifyInvalidDOB(WebDriver driver) {
        if (driver.getCurrentUrl().equals("https://flamboyant-allen-00cf47.netlify.app/signUp/setPassword")) {
            logger.info("Test is failed as system failed to validate 'invalid value' Date of Birth");
        } else {
            logger.info("Test is passed as expected");
        }
    }
}