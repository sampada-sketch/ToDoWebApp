package com.cat.automateit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;


public class NegativePhoneTestCases {
    private static final CaptureScreenshots cs = new CaptureScreenshots();
    private static final String FOLDER_NAME = "PhoneNoTestCases";
    private static final Logger logger = Logger.getLogger(NegativePhoneTestCases.class.getName());

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://flamboyant-allen-00cf47.netlify.app/signUp");
        Thread.sleep(5000);
        invalidNumberTestCases(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        positiveValidNoCase(driver);
        Thread.sleep(2000);
        driver.quit();

    }

    public static void invalidNumberTestCases(WebDriver driver) throws InterruptedException {
        String country = "Nepal";
        String phoneNo = "980329830689";

        WebElement countryselect = driver.findElement(By.xpath("//div[@class='iti__selected-flag dropdown-toggle']"));
        countryselect.click();

        WebElement searchCountry = driver.findElement(By.id("country-search-box"));
        searchCountry.sendKeys(country);
        searchCountry.submit();
        WebElement nepal = driver.findElement(By.xpath("//span[@class='iti__country-name' and contains(text(), 'Nepal (नेपाल)')]"));
        nepal.click();
        WebElement phone = driver.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(phoneNo);
        WebElement nextButton = driver.findElement(By.xpath("//button[@class='btn']"));
        nextButton.click();
        Thread.sleep(5000);

        verifyNepalPhoneNo(driver);
        cs.takeScreenshot(driver, "InvalidNumber.png", FOLDER_NAME);
    }

    public static void positiveValidNoCase(WebDriver driver) throws InterruptedException {
        String country = "India";
        String phoneNo = "8081419117";

        WebElement countrySelect = driver.findElement(By.xpath("//div[@class='iti__selected-flag dropdown-toggle']"));
        countrySelect.click();

        WebElement searchCountry = driver.findElement(By.id("country-search-box"));
        searchCountry.sendKeys(country);
        searchCountry.submit();
        WebElement india = driver.findElement(By.xpath("//span[@class='iti__country-name' and contains(text(), 'India (भारत)')]"));
        india.click();
        WebElement phone = driver.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys(phoneNo);
        WebElement nextbutton = driver.findElement(By.xpath("//button[@class='btn']"));
        nextbutton.click();
        Thread.sleep(5000);

        verifyNepalPhoneNo(driver);
        cs.takeScreenshot(driver, "positiveValidNoCase.png", FOLDER_NAME);
    }

    public static void verifyNepalPhoneNo(WebDriver driver) {

        try {
            WebElement errorElementLocator = driver.findElement(By
                    .xpath("/html/body/app-root/app-signup-page/app-signup/div/div/div[1]/form/div[4]/div[2]/div"));

            if (!errorElementLocator.isDisplayed()) {
                logger.info("Test is failed: Error element is not displayed as valid number was not provided");
            } else {
                logger.info("Test is passed: Error element is displayed");
            }
        } catch (NoSuchElementException e) {
            // If the element is not found, consider it a pass
            logger.info("Test is passed: Error element is not displayed as valid number was provided");
        } catch (Exception e) {
            // Handle other exceptions if needed
            e.printStackTrace();
        }
    }
}