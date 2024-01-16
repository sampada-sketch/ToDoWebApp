package com.cat.automateit;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeCreateTasksAndLists {
	private static final CaptureScreenshots cs = new CaptureScreenshots();

	private static String folderName = "CreateList&TasksCases";

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://flamboyant-allen-00cf47.netlify.app/login");
		LoginviacreatedEmail(driver);
		Thread.sleep(3000);
		createTasks(driver);
		Thread.sleep(3000);
		System.out.println("------------------------------------");
		craeteList(driver);
		Thread.sleep(3000);
		driver.quit();

	}
	public static void LoginviacreatedEmail( WebDriver driver) throws InterruptedException
	{
		String emailMe = "positive_test@yopmail.com";
		String password = "Weak@123";
		WebElement email= driver.findElement(By.id("email"));
		email.sendKeys(emailMe);

		WebElement passcode = driver.findElement(By.id("password"));
		passcode.sendKeys(password);
		passcode.submit();
		Thread.sleep(4000);
	}

	public static void createTasks(WebDriver driver) throws InterruptedException {
		String task = "Invalid task name with number 012";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement createTask = driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(createTask));
		createTask.click();

		WebElement taskName = driver.findElement(By.name("taskName"));
		taskName.sendKeys(task);
		String buttonXPath = "(//span[@class='mat-button-wrapper'])[2]";

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
		
		WebElement cancelElement = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-new-task/div/h1/span/i"));
		cancelElement.click();
		
		verifycreatedTask(driver);
		cs.takeScreenshot(driver, "TaskCreate.png",folderName);
		
	}
	public static void craeteList(WebDriver driver) throws InterruptedException
	{
		String ListName = "Invalid list name with number 012";
		String buttonXPath = "(//span[@class='mat-button-wrapper'])[2]";

		WebElement createList = driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[3]"));
		createList.click();

		WebElement listName = driver.findElement(By.xpath("//input[@name='list']"));
		listName.sendKeys(ListName);

		WebElement listBtn = driver.findElement(By.xpath(buttonXPath));
		listBtn.click();
		
		verifycreatedList(driver);
		cs.takeScreenshot(driver, "ListCreate.png",folderName);
		

	}
	public static void verifycreatedTask(WebDriver driver)
	{
		WebElement errorElement = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-new-task/div/div[1]/form/div[1]/mat-error"));
		String Errorsays= errorElement.getText();
		if(errorElement.isDisplayed())
		{
			System.out.println("Test is passed as invalid task name was provided, displayed error: " +Errorsays);
			
		}
		else
		{
			System.out.println("Test is failed as invalid task name was provided"+Errorsays);
			
		}
		
	}
	public static void verifycreatedList(WebDriver driver)
	{
		WebElement errorElement = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-new-list/div/div[1]/form/div/div"));
		String Errorsays= errorElement.getText();
		if(errorElement.isDisplayed())
		{
			System.out.println("Test is passed as invalid list name was provided, displayed error: " +Errorsays);
		}
		else
		{
			System.out.println("Test is failed as invalid list name was provided"+Errorsays);
		}
	}

}
