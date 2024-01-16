# ToDo Web App Automation using Selenium in Java

## Overview
This project is intended to use Selenium in Java to automate the functionality of ToDo WEb Application. It has been developed using Maven build, Eclipse IDE, JDK 11, and Selenium version 4.15.0. 

## Key Technologies Used
1. **Maven Build:** The project is built and managed using Apache Maven, ensuring a streamlined build process and dependency management.
2. **Eclipse IDE**: The development environment for this project is Eclipse, providing a robust and user-friendly platform for Java development.
3. **JDK 11:** The project is developed using Java Development Kit (JDK) version 11, taking advantage of the latest features and improvements in the Java language.
4. **Selenium 4.15.0**: Selenium is utilized for automated testing in this project. Version 4.15.0 provides the latest enhancements and fixes for efficient web automation.

## Software Requirements:
1. **Eclipse or IntelliJ**
2. **Jdk 8 or above** (I am using Jdk 11)
3. **Dependency of WebDriverManager:** [WebDriverManager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager) (I am using version 5.6.3)
4. **Selenium dependency or jar file version**
5. **TestNG Library**
6. **Chrome Browser driver is not required** as this project is implemented using `WebDriverManager()` method

## Project Setup:
1. Download [Eclipse](https://www.eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/).
2. Download Jdk 8.0 or above.
3. Clone and Import the project "ToDoWeb App" from repository (Branch : **Master**)
4. Add dependency of WebDriverManager in `pom.xml`. (already present in pom.xml but if you are using other version, you need to add)
5. Add dependency of Selenium in `pom.xml`. (already present in pom.xml but if you are using other version, you need to add)
6. Alternatively, download Selenium jar and configure in build path by adding external JARs.
7. Add TestNG library
8. There are several classes containing test cases, they should be executed
9. If error persists and the recorded screenshots can be achieved in path: C:\Java-Eclipse\ToDoWebApp\src\com\cat\Automateit\CreateList&TasksCases\screenshot_example.png (project should be Refreshed)

## How to Add TestNG library? (To be Marked)
Install TestNG from Eclipse MarketPlace:
1. Open Eclipse IDE.
2. Go to "Help" > "Eclipse Marketplace."
3. Search for "TestNG."
4. Find "TestNG for Eclipse" in the search results.
5. Click "Go to the Marketplace" and then click "Install."
6. Follow the installation instructions to complete the process.
7. Right-click on your project in the "Project Explorer."
8. Select "Properties" from the context menu.
9. In the "Properties" dialog, navigate to "Java Build Path."
10. Click on the "Libraries" tab.
11. Click the "Add Library..." button.
12. Select "TestNG" from the list and click "Next."
13. Choose the TestNG library version and click "Finish."
14. Click "Apply and Close" in the "Properties" dialog.
    

   
## Project Structure
1. `project-root 
2. `│ 
3. `├── src (not in use so hidden by Git)
4. `│ ├── main 
5. `│ │ ├── java - contains positive and negative test cases on the basis of test cases
6.  | |  | -- Contains positive and negative test scenarios, have to execute every one by one for logging and verifying the results
7. `| | | |-- Screenshots (Logs of errors)
8. `│ │ ├── resources 
9. `│ │
10. `│ ├── test 
11. `│ │ ├── java # Both test class and page class can be found here 
12. `│ │ │ ├── HamroPage.java (Page Class) 
13. `│ │ │ ├── TestScriptHamro.java (Test Class) - Test script to be executed as "Java Application" 
14. `│ │ ├── resources # Test-specific configuration files and resources








