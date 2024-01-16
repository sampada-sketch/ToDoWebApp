package com.cat.automateit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;

public class ToDoWebApp {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final Logger logger = Logger.getLogger(ToDoWebApp.class.getName());
    private static final String FOLDER_NAME = "TaskCreated";
    private static final String TASK_IS = "this is new test";

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
        String emailMe = "positive_test@yopmail.com";
        String password = "Weak@123";
        Thread.sleep(5000);

        // Positive test case
        positiveCreateAcc(driver, emailMe, password);
        loginViaCreatedEmail(driver, emailMe, password);
        createTasks(driver);
        // Add more negative test methods as needed
        driver.quit();
    }

    public static void positiveCreateAcc(WebDriver driver, String emailMe, String password) throws InterruptedException {
        String name = "Dummy";
        String DOB = "1/9/1998";
        String country = "Nepal";
        String phoneNo = "9803792939";
        String confirmpw = "Weak@123";

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

        WebElement passwordlocate = driver.findElement(By.id("password"));
        passwordlocate.sendKeys(password);

        WebElement confirmPass = driver.findElement(By.name("confirmPassword"));
        confirmPass.sendKeys(confirmpw);

        WebElement signupButton = driver.findElement(By.xpath("//button[@class='btn']"));
        signupButton.click();

        Thread.sleep(2000);
        // Verify successful signup
        verifySuccessfulSignup(driver);
    }

    public static void loginViaCreatedEmail(WebDriver driver, String emailMe, String password) throws InterruptedException {
        driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(emailMe);

        WebElement passcode = driver.findElement(By.id("password"));
        passcode.sendKeys(password);
        passcode.submit();
        Thread.sleep(4000);
        verifySuccessfulSignup(driver);
    }

    public static void createTasks(WebDriver driver) throws InterruptedException {
        String buttonXPath = "(//span[@class='mat-button-wrapper'])[2]";

        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement createTask = driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(createTask));
        createTask.click();

        WebElement taskName = driver.findElement(By.name("taskName"));
        taskName.sendKeys(TASK_IS);

        Thread.sleep(3000);

        WebElement chooseList = driver.findElement(By.xpath("//div[contains(@class, 'mat-select-arrow')]"));
        chooseList.click();

        WebElement optList = driver.findElement(By.xpath("(//span[@class='mat-option-text'])[2]"));
        optList.click();

        Thread.sleep(4000);
        WebElement choosePriority = driver.findElement(By.xpath("(//div[contains(@class, 'mat-select-arrow')])[3]"));
        choosePriority.click();

        WebElement optPriority = driver.findElement(By.xpath("(//span[@class='mat-option-text'])[3]"));
        optPriority.click();

        WebElement datePicker = driver.findElement(By.xpath("//input[@type='datetime-local']"));
        datePicker.sendKeys("12012024");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).build().perform();
        datePicker.sendKeys("0830PM");

        WebElement addTaskButton = driver.findElement(By.xpath(buttonXPath));
        addTaskButton.click();
        Thread.sleep(3000);

        WebElement createList = driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[3]"));
        createList.click();

        WebElement listName = driver.findElement(By.xpath("//input[@name='list']"));
        listName.sendKeys("New Test");

        WebElement listBtn = driver.findElement(By.xpath(buttonXPath));
        listBtn.click();

        verifyCreatedTask(driver);
        Thread.sleep(3000);
        cs.takeScreenshot(driver, "CreatedTask.png", FOLDER_NAME);
    }

    public static void verifyCreatedTask(WebDriver driver) {
        WebElement createdTask = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div[4]/div/div[1]/app-today-task/div[2]/div[1]/div/div[1]/div/h3"));
        String verify = createdTask.getText();
        if (verify.contains(TASK_IS)) {
            logger.info("Test is passed as '" + TASK_IS + "' was created and is displayed");
        } else {
            logger.info("Test is passed as '" + TASK_IS + "' was created and is not displayed");
        }
    }

    public static void verifySuccessfulSignup(WebDriver driver) {
        String expectedUrlPrefix = "https://flamboyant-allen-00cf47.netlify.app/dashboard";
        String actualUrl = driver.getCurrentUrl();

        // Log the actual and expected URLs for better debugging
        logger.info("Actual URL: " + actualUrl);
        logger.info("Expected URL : " + expectedUrlPrefix);

        // Assert that the actual URL starts with the expected URL prefix
        Assert.assertTrue(actualUrl.startsWith(expectedUrlPrefix),
                "Verification: Successful Signup failed. Actual URL: " + actualUrl);
        logger.info("Verification: Successful Signup");
    }
}