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

public class NegativeEmailTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "EmailTestCases";
    private static final String name = "Dummy";
    private static final String DOB = "1/9/1998";
    private static final String country = "Nepal";
    private static final String phoneNo = "9803792939";
    private static final String emailMe = "sam_suw@yopmail.com";
    private static final Logger logger = Logger.getLogger(NegativeEmailTestCases.class.getName());

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");

        negativeCreateAccInvalidEmail(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        negativeEmailNoDomain(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        negativeEmailInvalidDomain(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        negativeCreateAccExistingEmail(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);

        driver.quit();
    }

    public static void negativeCreateAccInvalidEmail(WebDriver driver) throws InterruptedException {
        // Negative test case: Invalid email format
        String emailMe = "sam_suw@yopmail";


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement createMe = driver.findElement(By.xpath("//p[@class='form-footer']/ child::a[@href='/signUp']"));
        wait.until(ExpectedConditions.elementToBeClickable(createMe));
        createMe.click();
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        WebElement chooseGender = driver.findElement(By.xpath("(//span[@class='mat-radio-inner-circle'])[2]"));

        if (!chooseGender.isSelected()) {
            chooseGender.click();
        }
        Thread.sleep(2000);

        WebElement chooseDate = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
        chooseDate.sendKeys(DOB);

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

        // Implement similar steps as positiveCreateAcc for this negative scenario
        // Verify the absence of successful signup
        verifyNoSuccessfulSignup(driver);
        cs.takeScreenshot(driver, "InvalidEmail.png", FOLDER_NAME);
    }


    public static void negativeEmailNoDomain(WebDriver driver) throws InterruptedException {
        // Negative test case: Invalid email format
        String emailMe = "sam_suw";


        WebElement namefield = driver.findElement(By.id("name"));
        namefield.sendKeys(name);

        WebElement choosegender = driver.findElement(By.xpath("(//span[@class='mat-radio-inner-circle'])[2]"));

        if (!choosegender.isSelected()) {
            choosegender.click();
        }
        Thread.sleep(2000);

        WebElement chooseDate = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
        chooseDate.sendKeys(DOB);

        WebElement countryselect = driver.findElement(By.xpath("//div[@class='iti__selected-flag dropdown-toggle']"));
        countryselect.click();

        WebElement searchCountry = driver.findElement(By.id("country-search-box"));
        searchCountry.sendKeys(country);
        searchCountry.submit();

        WebElement nepal = driver.findElement(By.xpath("//span[@class='iti__country-name' and contains(text(), 'Nepal (नेपाल)')]"));
        nepal.click();

        WebElement phone = driver.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(phoneNo);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(emailMe);

        WebElement nextbtn = driver.findElement(By.xpath("//button[@class='btn']"));
        nextbtn.click();
        Thread.sleep(5000);

        // Implement similar steps as positiveCreateAcc for this negative scenario
        // Verify the absence of successful signup
        verifyNoSuccessfulSignup(driver);
        cs.takeScreenshot(driver, "EmailNoDomain.png", FOLDER_NAME);
    }


    // Add more negative test case methods as needed
    public static void negativeEmailInvalidDomain(WebDriver driver) throws InterruptedException {
        // Negative test case: Invalid email format (without domain)
        String emailMe = "sam_suw@@yopmail.com"; // Update with an invalid email


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement createMe = driver.findElement(By.xpath("//p[@class='form-footer']/ child::a[@href='/signUp']"));
        wait.until(ExpectedConditions.elementToBeClickable(createMe));
        createMe.click();

        WebElement namefield = driver.findElement(By.id("name"));
        namefield.sendKeys(name);

        WebElement choosegender = driver.findElement(By.xpath("(//span[@class='mat-radio-inner-circle'])[2]"));

        if (!choosegender.isSelected()) {
            choosegender.click();
        }
        Thread.sleep(2000);

        WebElement chooseDate = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
        chooseDate.sendKeys(DOB);

        WebElement countryselect = driver.findElement(By.xpath("//div[@class='iti__selected-flag dropdown-toggle']"));
        countryselect.click();

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

        // Implement similar steps as positiveCreateAcc for this negative scenario
        // Verify the absence of successful signup
        verifyNoSuccessfulSignup(driver);
        cs.takeScreenshot(driver, "EmailInvalidDomain.png", FOLDER_NAME);

    }

    public static void negativeCreateAccExistingEmail(WebDriver driver) throws InterruptedException {
        // Negative test case: Using an existing email
        String password = "Weak@123";
        String confirmpw = "Weak@123";

        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(name);

        WebElement chooseGender = driver.findElement(By.xpath("(//span[@class='mat-radio-inner-circle'])[2]"));

        if (!chooseGender.isSelected()) {
            chooseGender.click();
        }
        Thread.sleep(2000);

        WebElement chooseDate = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
        chooseDate.sendKeys(DOB);

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

        WebElement passwordLocate = driver.findElement(By.id("password"));
        passwordLocate.sendKeys(password);

        WebElement confirmPass = driver.findElement(By.name("confirmPassword"));
        confirmPass.sendKeys(confirmpw);

        WebElement signupButton = driver.findElement(By.xpath("//button[@class='btn']"));
        signupButton.click();
        // Implement similar steps as positiveCreateAcc for this negative scenario
        // Verify the absence of successful signup
        verifyExistingEmail(driver);
        cs.takeScreenshot(driver, "ExistingEmail.png", FOLDER_NAME);
    }

    public static void verifyNoSuccessfulSignup(WebDriver driver) {
        String expectedErrorMessage = "Please Enter Valid Email"; // Modify based on your application's error message
        WebElement errorMessageElement = driver.findElement(By.xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[5]/div/div"));

        // Assert that the error message element is displayed
        Assert.assertTrue(errorMessageElement.isDisplayed(), "Verification: No Successful Signup");

        // Assert that the error message text matches the expected error message
        String actualErrorMessage = errorMessageElement.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Verification: No Successful Signup");
        logger.info("Test is passed: No Successful Signup");
    }

    public static void verifyExistingEmail(WebDriver driver) throws InterruptedException {
        String expectedURL = "https://flamboyant-allen-00cf47.netlify.app/signUp"; // Modify based on your application's error message
        Thread.sleep(2000);
        String actualURL = driver.getCurrentUrl();
        if (expectedURL.equals(actualURL)) {
            logger.info("Test is passed because existing email is not able to login");
        } else {
            logger.info("Test is failed because existing email is  able to login");
        }
    }
}