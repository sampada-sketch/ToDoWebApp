package com.cat.automateit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public final class CaptureScreenshots {
    private static final Logger logger = Logger.getLogger(CaptureScreenshots.class.getName());

    public void takeScreenshot(WebDriver driver, String fileName, String folderName) {
        try {
            // Convert WebDriver object to TakesScreenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;

            // Capture the screenshot as a file
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Get the current package name
            String packageName = NegativeDOBTestCases.class.getPackage().getName();

            // Specify the destination folder and file
            Path destinationPath = Paths.get("src", packageName.replace(".", "/"), folderName, fileName);
            File destinationFile = destinationPath.toFile();

            // Create the folder if it doesn't exist
            destinationFile.getParentFile().mkdirs();

            // Copy the file to the destination
            org.openqa.selenium.io.FileHandler.copy(sourceFile, destinationFile);
            logger.info("PLEASE REFRESH THE PROJECT FOLDER to view screenshots!!");
            logger.info("Screenshot captured: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Failed to capture screenshot: " + e.getMessage());
        }
    }
}