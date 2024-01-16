package com.cat.automateit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;

public class NegativeNameTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "NamesTestCases";
    private static final Logger logger = Logger.getLogger(NegativeNameTestCases.class.getName());

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
        Thread.sleep(5000);
        nameWithNumbers(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        nameWithSpecialChars(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);

        driver.quit();
    }

    public static void nameWithNumbers(WebDriver driver) throws InterruptedException {
        String name = "Dummy 012";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement createMe = driver.findElement(By.xpath("//p[@class='form-footer']/ child::a[@href='/signUp']"));
        wait.until(ExpectedConditions.elementToBeClickable(createMe));
        createMe.click();

        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(name);
        WebElement signupButton = driver.findElement(By.xpath("//button[@class='btn']"));
        signupButton.click();

        Thread.sleep(2000);
        // Verify successful signup
        verifyNoSuccessfulSignup(driver);
        cs.takeScreenshot(driver, "NameWithNumbers.png", FOLDER_NAME);
    }

    public static void nameWithSpecialChars(WebDriver driver) throws InterruptedException {
        String name = "Dummy.hey";
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(name);
        WebElement signupButton = driver.findElement(By.xpath("//button[@class='btn']"));
        signupButton.click();

        Thread.sleep(2000);
        // Verify successful signup
        verifyNoSuccessfulSignup(driver);
        cs.takeScreenshot(driver, "NameWithSpecialChars.png", FOLDER_NAME);
    }

    public static void verifyNoSuccessfulSignup(WebDriver driver) {
        String expectedError = "Name can only contain letters";
        WebElement errorMessageElement = driver.findElement(By.xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[1]/div/div"));
        Assert.assertTrue(errorMessageElement.isDisplayed(), "Verification: No Successful Signup");

        // Assert that the error message text matches the expected error message
        String actualErrorMessage = errorMessageElement.getText();
        Assert.assertEquals(expectedError, actualErrorMessage, "ASSERTION FAILS");
        logger.info("Test is passed: No Successful Signup");
    }
}