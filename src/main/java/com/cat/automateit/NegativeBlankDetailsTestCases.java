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

public class NegativeBlankDetailsTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "BlankDetailsTestCases";
    private static final String MESSAGE = "Verification: No Successful Signup";
    private static final Logger logger = Logger.getLogger(NegativeBlankDetailsTestCases.class.getName());

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
        Thread.sleep(5000);
        negativeCreateAccEmptyFields(driver);
        driver.quit();
    }

    public static void negativeCreateAccEmptyFields(WebDriver driver) throws InterruptedException {
        // Negative test case: Empty fields
        String name = "";
        String dob = "";
        String country = "";
        String phoneNo = "";
        String emailMe = "";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement createMe = driver.findElement(By.xpath("//p[@class='form-footer']/ child::a[@href='/signUp']"));
        wait.until(ExpectedConditions.elementToBeClickable(createMe));
        createMe.click();
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        WebElement chooseDate = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
        chooseDate.sendKeys(dob);

        WebElement countrySelect = driver.findElement(By.xpath("//div[@class='iti__selected-flag dropdown-toggle']"));
        countrySelect.click();

        WebElement searchCountry = driver.findElement(By.id("country-search-box"));
        searchCountry.sendKeys(country);
        searchCountry.submit();

        WebElement nepal = driver.findElement(By.xpath("//span[@class='iti__country-name' and contains(text(), 'Nepal (नेपाल)')]"));
        nepal.click();

        WebElement phone = driver.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(phoneNo);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(emailMe);

        WebElement nextButton = driver.findElement(By.xpath("//button[@class='btn']"));
        nextButton.click();
        Thread.sleep(5000);

        WebElement signupButton = driver.findElement(By.xpath("//button[@class='btn']"));
        signupButton.click();

        // Implement similar steps as positiveCreateAcc for this negative scenario
        // Verify the absence of successful signup
        verifyBlankNameSignup(driver);
        verifyBlankGenderSignup(driver);
        verifyBlankDOBSignup(driver);
        verifyBlankPhoneSignup(driver);
        verifyBlankEmailSignup(driver);
        cs.takeScreenshot(driver, "BlankCases.png", FOLDER_NAME);
    }

    public static void verifyBlankNameSignup(WebDriver driver) {
        String expectedName = "Name is Required";
        WebElement nameErrorElement = driver.findElement(By
                .xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[1]/div/div"));
        Assert.assertTrue(nameErrorElement.isDisplayed(), MESSAGE);

        // Assert that the error message text matches the expected error message
        Assert.assertEquals(nameErrorElement.getText(), expectedName, MESSAGE);
        logger.info("Test is passed: System asked to input name");
        logger.info("---------------------------------------");
    }

    public static void verifyBlankGenderSignup(WebDriver driver) {

        String expectedGender = "Gender is Required";
        WebElement genderErrorElement = driver.findElement(By
                .xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[2]/div/div"));
        Assert.assertTrue(genderErrorElement.isDisplayed(), MESSAGE);

        // Assert that the error message text matches the expected error message
        Assert.assertEquals(genderErrorElement.getText(), expectedGender, MESSAGE);
        logger.info("Test is passed: System asked to input Gender");
        logger.info("---------------------------------------");
    }

    public static void verifyBlankDOBSignup(WebDriver driver) {

        String expectedDOB = "Date of Birth is Required";
        WebElement dobErrorElement = driver.findElement(By
                .xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[3]/div/div"));
        Assert.assertTrue(dobErrorElement.isDisplayed(), MESSAGE);

        // Assert that the error message text matches the expected error message
        Assert.assertEquals(dobErrorElement.getText(), expectedDOB, MESSAGE);
        logger.info("Test is passed: System asked to input Date of Birth");
        logger.info("---------------------------------------");
    }

    public static void verifyBlankPhoneSignup(WebDriver driver) {
        String expectedPhone = "Phone Number is Required";
        WebElement phoneErrorElement = driver.findElement(By
                .xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[4]/div[1]/div"));
        Assert.assertTrue(phoneErrorElement.isDisplayed(), MESSAGE);

        // Assert that the error message text matches the expected error message
        Assert.assertEquals(phoneErrorElement.getText(), expectedPhone, MESSAGE);
        logger.info("Test is passed: System asked to input Phone No.");
        logger.info("---------------------------------------");
    }

    public static void verifyBlankEmailSignup(WebDriver driver) {
        String expectedEmail = "Email is Required"; // Modify based on your application's error message
        WebElement emailErrorElement = driver.findElement(By
                .xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[5]/div/div"));

        // Assert that the error message element is displayed
        Assert.assertTrue(emailErrorElement.isDisplayed(), MESSAGE);

        // Assert that the error message text matches the expected error message
        Assert.assertEquals(emailErrorElement.getText(), expectedEmail, MESSAGE);
        logger.info("Test is passed: System asked to input email");
    }
}