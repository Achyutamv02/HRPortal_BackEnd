package com.example.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelRequestForm {

    WebDriver driver;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);

        System.setProperty("webdriver.chrome.driver", "/Users/Achyutam/Masters/Sem3/Prototype/HRPortal_BackEnd/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000"); // your React frontend
    }
    // Employee Login
    @Test
    public void EmployeeLoginPage()
    {
        driver.findElement(By.xpath("//button[text()='Employee']")).click();
    }
    // Admin Login
    @Test
    public void AdminLoginPage()
    {
        driver.findElement(By.xpath("//button[text()='Admin']")).click();
    }

    // Employee Login
    @Test
    public void EmployeeLoginSuccesfull()
    {
        driver.findElement(By.xpath("//button[text()='Admin']")).click();

        // Wait until email input is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));

        // Enter email
        WebElement emailInput = driver.findElement(By.cssSelector("input[type='email']"));
        emailInput.sendKeys("Achyutam.ire@gmail.com");

        // Enter password
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.sendKeys("12345");

        // Click "Login" button
        WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginBtn.click();
    }

    // Employee Travel Request Reimbursement Form
    @Test
    public void fillTravelRequestForm() throws InterruptedException {

        driver.findElement(By.xpath("//button[text()='Employee']")).click();

        // Wait until email input is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));

        // Enter email
        WebElement emailInput = driver.findElement(By.cssSelector("input[type='email']"));
        emailInput.sendKeys("Achyutam.ire@gmail.com");

        // Enter password
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.sendKeys("12345");

        // Click "Login" button
        WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginBtn.click();


        // Fill in Name
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[placeholder='Enter your name']")
        ));
        nameInput.sendKeys("Achyutam Vermma");

        // Travel Location
        WebElement locationInput = driver.findElement(
                By.cssSelector("input[placeholder='Enter destination']")
        );
        locationInput.sendKeys("Pune");

        // Justification
        WebElement justificationInput = driver.findElement(
                By.cssSelector("textarea[placeholder='Enter justification']")
        );
        justificationInput.sendKeys("Conference attendance");

        // Amount
        WebElement amountInput = driver.findElement(
                By.cssSelector("input[placeholder='Enter amount']")
        );
        amountInput.sendKeys("1500");

        // Travel Type Dropdown (select the first option after placeholder)
        Select travelTypeSelect = new Select(driver.findElement(
                By.cssSelector("select.form-select")
        ));
        travelTypeSelect.selectByIndex(1); // or use selectByVisibleText("Flight") etc.

        // Start and End Date (use today + tomorrow as example)
        WebElement startDate = driver.findElements(By.cssSelector("input[type='date']")).get(0);
        WebElement endDate = driver.findElements(By.cssSelector("input[type='date']")).get(1);

        startDate.sendKeys("27/05/2025");
        endDate.sendKeys("27/06/2025");


        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));

        // Scroll to make it visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

        // Wait until it is clickable
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        // Small pause (optional)
        Thread.sleep(300);

        // Click
        submitButton.click();


        // Wait for confirmation or redirection
        //wait.until(ExpectedConditions.urlContains("travel-request-form")); // or confirmation message
    }

    // S2: Submit Leave Request (missing date)
    @Test
    public void fillTravelRequestFormWithoutDate() throws InterruptedException {

        driver.findElement(By.xpath("//button[text()='Employee']")).click();

        // Wait until email input is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));

        // Enter email
        WebElement emailInput = driver.findElement(By.cssSelector("input[type='email']"));
        emailInput.sendKeys("Achyutam.ire@gmail.com");

        // Enter password
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.sendKeys("12345");

        // Click "Login" button
        WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text(), 'Login')]"));
        loginBtn.click();


        // Fill in Name
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[placeholder='Enter your name']")
        ));
        nameInput.sendKeys("Achyutam Vermma");

        // Travel Location
        WebElement locationInput = driver.findElement(
                By.cssSelector("input[placeholder='Enter destination']")
        );
        locationInput.sendKeys("Pune");

        // Justification
        WebElement justificationInput = driver.findElement(
                By.cssSelector("textarea[placeholder='Enter justification']")
        );
        justificationInput.sendKeys("Conference attendance");

        // Amount
        WebElement amountInput = driver.findElement(
                By.cssSelector("input[placeholder='Enter amount']")
        );
        amountInput.sendKeys("1500");

        // Travel Type Dropdown (select the first option after placeholder)
        Select travelTypeSelect = new Select(driver.findElement(
                By.cssSelector("select.form-select")
        ));
        travelTypeSelect.selectByIndex(1); // or use selectByVisibleText("Flight") etc.
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        Thread.sleep(300);
        submitButton.click();

    }

    // S3: Admin Approves Leave
    @Test
    public void adminApprovesLeaveRequest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1️⃣: Submit a leave request as Employee
       fillTravelRequestForm();

        // Step 2️⃣: "Logout" by navigating to homepage (session reset)
        driver.get("http://localhost:3000/");

        // Step 3️⃣: Log in as Admin
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Admin']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")))
                .sendKeys("Dhruv.ire@gmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        // Step 4️⃣: Find and approve the first "Pending" request only
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("list-group-item")));
        List<WebElement> requestCards = driver.findElements(By.className("list-group-item"));
        boolean approved = false;

        for (WebElement card : requestCards) {
            String statusText = card.findElement(By.xpath(".//p[strong[text()='Status:']]")).getText();
            if (statusText.contains("Pending")) {
                WebElement approveBtn = card.findElement(By.xpath(".//button[contains(text(),'Accept')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", approveBtn);
                Thread.sleep(300);  // optional: smooth interaction
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", approveBtn);
                approved = true;

                // Step 5️⃣: Re-check the status inside this same card
                WebElement updatedStatus = card.findElement(By.xpath(".//p[strong[text()='Status:']]"));
                wait.until(ExpectedConditions.textToBePresentInElement(updatedStatus, "Accepted"));
                String finalStatus = updatedStatus.getText();
                System.out.println("Updated Status: " + finalStatus);
                assertTrue(finalStatus.contains("Accepted"), "Status should be 'Accepted'");
                break;
            }
        }

        if (!approved) {
            Assertions.fail("No Pending leave request found to approve.");
        }
    }

    // TC: Employee login page loads but login attempt is skipped or fails
    @Test
    public void employeeLoginWithoutClick() {
        // Do nothing after loading the page
        // Check that the URL or title has not changed or no form is shown
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://localhost:3000", currentUrl, "URL should remain unchanged when no button is clicked.");
    }

    //TC: Admin login form does not appear due to missing elements
    @Test
    public void adminLoginFormNotPresent() {
        driver.findElement(By.xpath("//button[text()='Admin']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isPresent = driver.findElements(By.cssSelector("input[type='email']")).isEmpty();
        Assertions.assertFalse(isPresent, "Admin email field should be present.");
    }

    // TC: Admin login with wrong password
    @Test
    public void adminLoginWithInvalidPassword() {
        driver.findElement(By.xpath("//button[text()='Admin']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")))
                .sendKeys("Achyutam.ire@gmail.com");

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("wrong-password");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        // Check for login failure, could be an alert, toast, or unchanged URL
        Assertions.assertEquals("http://localhost:3000/admin-login", driver.getCurrentUrl(), "Should stay on login page.");
    }

   // TC: Leave all fields blank and attempt submission
    @Test
    public void fillTravelFormWithAllFieldsBlank() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Employee']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")))
                .sendKeys("Achyutam.ire@gmail.com");

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        // Leave all fields blank
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        Thread.sleep(300);
        submitButton.click();

        // Assert that error message(s) show up (adjust according to your app’s behavior)
        List<WebElement> errors = driver.findElements(By.className("invalid-feedback"));
        Assertions.assertFalse(errors.isEmpty(), "Validation errors should be shown.");
    }


    //TC: End date before start date

    @Test
    public void travelRequestWithInvalidDateRange() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Employee']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")))
                .sendKeys("Achyutam.ire@gmail.com");

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        // Fill all fields properly but provide invalid date
        driver.findElement(By.cssSelector("input[placeholder='Enter your name']")).sendKeys("Achyutam");
        driver.findElement(By.cssSelector("input[placeholder='Enter destination']")).sendKeys("Goa");
        driver.findElement(By.cssSelector("textarea[placeholder='Enter justification']")).sendKeys("Conference");
        driver.findElement(By.cssSelector("input[placeholder='Enter amount']")).sendKeys("2000");
        new Select(driver.findElement(By.cssSelector("select.form-select"))).selectByIndex(1);

        List<WebElement> dates = driver.findElements(By.cssSelector("input[type='date']"));
        dates.get(0).sendKeys("30/06/2025"); // Start Date
        dates.get(1).sendKeys("25/06/2025"); // End Date before start date

        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        Thread.sleep(300);
        submitButton.click();

        // Check for rejection, validation message or no redirection
        Assertions.assertTrue(driver.getPageSource().contains("End date must be after start date"), "Validation error expected for date.");
    }

    //TC: Admin attempts to re-approve an already "Accepted" request

    @Test
    public void adminTriesToReApproveRequest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Admin']"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")))
                .sendKeys("Dhruv.ire@gmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("list-group-item")));
        List<WebElement> requestCards = driver.findElements(By.className("list-group-item"));

        for (WebElement card : requestCards) {
            String statusText = card.findElement(By.xpath(".//p[strong[text()='Status:']]")).getText();
            if (statusText.contains("Accepted")) {
                boolean hasApproveBtn = !card.findElements(By.xpath(".//button[contains(text(),'Accept')]")).isEmpty();
                Assertions.assertFalse(hasApproveBtn, "Approve button should not be shown for already accepted request.");
                return;
            }
        }
        Assertions.fail("No 'Accepted' requests found to test re-approval.");
    }








    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

