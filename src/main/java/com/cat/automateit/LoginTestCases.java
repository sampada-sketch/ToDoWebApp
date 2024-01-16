package com.cat.automateit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.logging.Logger;

public class LoginTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "LoginNegative";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String LOGIN_URL = "https://flamboyant-allen-00cf47.netlify.app/login";
    private static final Logger logger = Logger.getLogger(LoginTestCases.class.getName());
    private static WebDriver driver;


    public static void main(String[] args) throws InterruptedException {
        initializeDriver();
        String emailMe = "positive_test@yopmail.com";
        String password = "Weak@123";

        firstCreateAcc(emailMe, password);
        Thread.sleep(3000);
        loginViaCreatedEmail(emailMe, password);
        Thread.sleep(3000);
        loginWithNewEmail();

        // Close the browser after test execution
        driver.quit();
    }

    private static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_URL);
    }

    public static void firstCreateAcc(String emailMe, String password) throws InterruptedException {
        driver.get("https://flamboyant-allen-00cf47.netlify.app/signUp");
        String name = "Dummy";
        String dob = "1/9/1998";
        String country = "Nepal";
        String phoneNo = "9803792939";
        String confirmPassword = "Weak@123";

        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        WebElement chooseGender = driver.findElement(By.xpath("(//span[@class='mat-radio-inner-circle'])[2]"));
        if (!chooseGender.isSelected()) {
            chooseGender.click();
        }
        Thread.sleep(2000);

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

        WebElement email = driver.findElement(By.id(EMAIL));
        email.sendKeys(emailMe);

        WebElement nextButton = driver.findElement(By.xpath("//button[@class='btn']"));
        nextButton.click();
        Thread.sleep(5000);

        WebElement passwordLocate = driver.findElement(By.id(PASSWORD));
        passwordLocate.sendKeys(password);

        WebElement confirmPass = driver.findElement(By.name("confirmPassword"));
        confirmPass.sendKeys(confirmPassword);

        WebElement signupButton = driver.findElement(By.xpath("//button[@class='btn']"));
        signupButton.click();

        Thread.sleep(4000);
        // Verify successful signup
        verifySuccessfulSignup(driver);
    }

    public static void loginViaCreatedEmail(String emailMe, String password) throws InterruptedException {
        driver.get(LOGIN_URL);
        WebElement email = driver.findElement(By.id(EMAIL));
        email.sendKeys(emailMe);

        WebElement passcode = driver.findElement(By.id(PASSWORD));
        passcode.sendKeys(password);
        passcode.submit();
        Thread.sleep(4000);
        verifySuccessfulSignup(driver);
    }

    public static void loginWithNewEmail() throws InterruptedException {
        driver.get(LOGIN_URL);
        String emailMe = "new_email@yopmail.com";
        String password = "New_test";

        WebElement email = driver.findElement(By.id(EMAIL));
        email.sendKeys(emailMe);

        WebElement passcode = driver.findElement(By.id(PASSWORD));
        passcode.sendKeys(password);
        passcode.submit();
        Thread.sleep(4000);
        verifyNoSuccessfulSignup(driver);
        Thread.sleep(2000);
        cs.takeScreenshot(driver, "NewEmail.png", FOLDER_NAME);
    }

    public static void verifySuccessfulSignup(WebDriver driver) {
        String expectedDashboardUrl = "https://flamboyant-allen-00cf47.netlify.app/dashboard";
        String actualUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(actualUrl, expectedDashboardUrl, "Assertion failed");
            logger.info("Test is passed: Successful Signup");
        } catch (AssertionError e) {
            handleAssertionError(e);
        }
    }

    private static void handleAssertionError(AssertionError e) {
        logger.info("Assertion failed: " + e.getMessage());
        // Handle the assertion error as needed (e.g., take a screenshot, log information)
    }

    public static void verifyNoSuccessfulSignup(WebDriver driver) {
        String actualUrl = driver.getCurrentUrl();
        try {
            Assert.assertEquals(actualUrl, LOGIN_URL, "Assertion failed");
            logger.info("Test is passed: No Successful Signup as not existent email was provided for login");
        } catch (AssertionError e) {
            handleAssertionError(e);
        }
    }
}