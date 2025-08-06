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

import static org.junit.jupiter.api.Assertions.*;

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

    // TC001 - Valid Employee Login
    @Test
    public void testEmployeeLoginSuccess() {
        driver.findElement(By.xpath("//button[text()='Employee']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 🛠️ Wait for email input to appear BEFORE using it
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='email']")
        ));
        emailInput.sendKeys("Achyutam.ire@gmail.com");

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        wait.until(ExpectedConditions.urlContains("/dashboard"));
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }


    // TC002 - Verify invalid login shows error message or stays on login page
    @Test
    public void testEmployeeLoginInvalid() {
        driver.findElement(By.xpath("//button[text()='Employee']")).click();

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Achyutam.ire@gmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("67890"); // Invalid password
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/employee-login")); // Expect to stay here

        assertTrue(driver.getCurrentUrl().contains("/employee-login"), "Should remain on login page after failed login");
    }


    // TC003 - Verify "New user? Sign up" redirects to signup page
    @Test
    public void testEmployeeSignupRedirect() {
        // Step 1: Click "Employee" on Home page
        driver.findElement(By.xpath("//button[text()='Employee']")).click();

        // Step 2: Click "New user? Sign Up" button/link
        WebElement signupLink = driver.findElement(By.xpath("//button[contains(text(), 'New user? Sign Up')]"));
        signupLink.click();

        // Step 3: Wait and assert redirection
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/signup"));

        // Step 4: Assert current URL contains /signup
        assertTrue(driver.getCurrentUrl().contains("/signup"), "Should navigate to signup page");

        // Optional: You can also validate form fields are visible
        WebElement nameField = driver.findElement(By.cssSelector("input[name='name']"));
        WebElement emailField = driver.findElement(By.cssSelector("input[name='email']"));
        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        WebElement roleField = driver.findElement(By.cssSelector("input[readonly]"));

        assertTrue(nameField.isDisplayed() && emailField.isDisplayed() && passwordField.isDisplayed() && roleField.isDisplayed(),
                "Signup form fields should be visible");
    }



    // TC004 – Verify signup with valid input creates a user and redirects to login
    @Test
    public void testEmployeeSignupSuccessRedirect() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Step 1: Click "Employee" on home page
            driver.findElement(By.xpath("//button[text()='Employee']")).click();

            // Step 2: Click "New user? Sign Up" button/link
            WebElement signupLink = driver.findElement(By.xpath("//button[contains(text(), 'New user? Sign Up')]"));
            signupLink.click();

            // Step 3: Fill signup form
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='name']"))).sendKeys("Test User");
            driver.findElement(By.cssSelector("input[name='email']")).sendKeys("testuser" + System.currentTimeMillis() + "@example.com");
            driver.findElement(By.cssSelector("input[name='password']")).sendKeys("testpassword123");

            // Step 4: Click "Sign Up"
            driver.findElement(By.xpath("//button[text()='Sign Up']")).click();

            // Step 5: Verify redirect to login
            wait.until(ExpectedConditions.urlContains("/employee-login"));
            assertTrue(driver.getCurrentUrl().contains("/employee-login"),
                    "User should be redirected to login after successful signup");

    }

    // TC005: Verify “Travel Reimbursement Form” button redirects correctly
    @Test
    public void testTravelReimbursementFormNavigation() {
        testEmployeeLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement travelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Travel Reimbursement Form')]")
        ));
        travelBtn.click();

        wait.until(ExpectedConditions.urlContains("/travel-request-form"));
        assertTrue(driver.getCurrentUrl().contains("/travel-request-form"));
    }

    // TC006 – Leave Request Form Navigation
    @Test
    public void testLeaveRequestFormNavigation() {
        testEmployeeLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement leaveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Leave Request Form')]")
        ));
        leaveBtn.click();

        wait.until(ExpectedConditions.urlContains("/leave-request-form"));
        assertTrue(driver.getCurrentUrl().contains("/leave-request-form"));
    }

    //TC007 – Logout Button Functionality
    @Test
    public void testLogoutRedirectsToHome() {
        testEmployeeLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Logout')]")
        ));
        logoutBtn.click();

        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/employee-login"));
        assertTrue(driver.getCurrentUrl().equals("http://localhost:3000/employee-login"));
    }

    //TC008 -  Verify login with empty email/password fields fails
    @Test
    public void testEmptyFieldValidationPopup() {
        driver.findElement(By.xpath("//button[text()='Employee']")).click();

        WebElement emailInput = driver.findElement(By.cssSelector("input[type='email']"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[text()='Login']"));

        loginBtn.click();

        // Use JavaScript to check validity
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", emailInput);

        // It should be invalid
        assertFalse(isValid, "Email input should be invalid when left blank");
    }

    //TC009 -  Submit Leave Request with Valid Data
    @Test
    public void testSubmitLeaveRequestValid() {
        // Log in using the existing utility
        testEmployeeLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Leave Request Form from dashboard
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Leave Request Form')]"))).click();
        wait.until(ExpectedConditions.urlContains("/leave-request-form"));

        // Fill the leave form
        driver.findElement(By.cssSelector("input[placeholder='Enter your name']")).sendKeys("Achyutam Vermma");
        driver.findElement(By.cssSelector("textarea[placeholder='Enter reason']")).sendKeys("Medical Leave");

        List<WebElement> dates = driver.findElements(By.cssSelector("input[type='date']"));
        dates.get(0).sendKeys("20-08-2025"); // Start date
        dates.get(1).sendKeys("28-08-2025"); // End date

        // Submit
        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

    }

    //TC10 - Submit Leave Form with Blank Fields (Validation Check)
    @Test
    public void testLeaveFormValidationBlankFields() {
        driver.findElement(By.xpath("//button[text()='Employee']")).click();

        // Login
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Achyutam.ire@gmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // Wait for dashboard
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/dashboard"));

        // Go to Leave Request form
        driver.findElement(By.xpath("//button[contains(text(),'Leave Request Form')]")).click();

        // Submit with all fields blank
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        // Wait for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Name is required')]")));

        assertTrue(nameError.isDisplayed(), "");
    }

    //TC11 - Submit Reimbursement form with valid inputs
    @Test
    public void testTravelRequestValidSubmission() throws InterruptedException {

        testEmployeeLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement travelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Travel Reimbursement Form')]")
        ));
        travelBtn.click();

        // Fill the form with valid data
        driver.findElement(By.cssSelector("input[placeholder='Enter your name']")).sendKeys("Test Employee");
        driver.findElement(By.cssSelector("input[placeholder='Enter destination']")).sendKeys("New York");
        driver.findElement(By.cssSelector("textarea[placeholder='Enter justification']")).sendKeys("Client meeting");
        driver.findElement(By.cssSelector("input[placeholder='Enter amount']")).sendKeys("250");

        Select travelTypeSelect = new Select(driver.findElement(By.cssSelector("select.form-select")));
        travelTypeSelect.selectByVisibleText("Business");

        // Travel dates
        List<WebElement> dateInputs = driver.findElements(By.cssSelector("input[type='date']"));
        dateInputs.get(0).sendKeys("20-08-2025");
        dateInputs.get(1).sendKeys("28-08-2025");

        // Submit form
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        Thread.sleep(500); // allow some time after scrolling
        submitButton.click();

    }


    //TC012: Submit with missing fields and validate errors
    @Test
    public void testTravelRequestMissingFields() {
        testEmployeeLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement travelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Travel Reimbursement Form')]")
        ));
        travelBtn.click();

        // Click submit
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));

        // Wait for error messages to appear
        WebElement locationError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Travel Location')] | //*[contains(text(),'required')]")
        ));

        assertTrue(locationError.isDisplayed(), "Validation errors should appear for blank fields");
    }


    //TC013: Submit with negative/zero amount and verify rejection
    @Test
    public void testTravelRequestInvalidAmount() throws InterruptedException {

        testEmployeeLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement travelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Travel Reimbursement Form')]")
        ));
        travelBtn.click();

        // Fill valid fields except amount
        driver.findElement(By.cssSelector("input[placeholder='Enter your name']")).sendKeys("Negative Amount Test");
        driver.findElement(By.cssSelector("input[placeholder='Enter destination']")).sendKeys("LA");
        driver.findElement(By.cssSelector("textarea[placeholder='Enter justification']")).sendKeys("Test travel");
        driver.findElement(By.cssSelector("input[placeholder='Enter amount']")).sendKeys("0"); // Invalid

        Select travelTypeSelect = new Select(driver.findElement(By.cssSelector("select.form-select")));
        travelTypeSelect.selectByVisibleText("Personal");

        List<WebElement> dateInputs = driver.findElements(By.cssSelector("input[type='date']"));
        dateInputs.get(0).sendKeys("20082025");
        dateInputs.get(1).sendKeys("28082025");

        // Scroll and submit
        WebElement submitBtn = driver.findElement(By.xpath("//button[text()='Submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);


        WebElement amountError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Amount must be greater than 0')]")
        ));
        assertTrue(amountError.isDisplayed(), "Validation message should appear for invalid amount");
    }

    //TC014 - Verify valid admin login redirects to Admin Dashboard
    @Test
    public void testAdminLoginSuccess() {
        driver.get("http://localhost:3000");

        driver.findElement(By.xpath("//button[text()='Admin']")).click();

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Dhruv.ire@gmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345"); // change as needed
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/admin-login")); // Expect to stay here

        assertTrue(driver.getCurrentUrl().contains("/admin-login"), "Should remain on login page after failed login");
    }

    // TC015 - Admin logs in and sees leave + reimbursement tabs

    @Test
    public void testAdminLoginAndTabsVisible() {
        driver.get("http://localhost:3000");

        driver.findElement(By.xpath("//button[text()='Admin']")).click();

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Dhruv.ire@gmail.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345"); // change as needed
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/admin-dashboard"));

        // Assert buttons are visible
        assertTrue(driver.findElement(By.xpath("//button[text()='Reimbursement Requests']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//button[text()='Leave Requests']")).isDisplayed());
    }

    //TC016 – Approve/Reject Leave Request

    @Test
    public void testAdminApproveAndRejectLeaveRequest() {
        testAdminLoginSuccess(); // make sure this waits for /admin-dashboard

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until "Leave Requests" button is clickable and click it
        WebElement leaveRequestsBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Leave Requests']")
        ));
        leaveRequestsBtn.click();

        // Wait for URL to change
        wait.until(ExpectedConditions.urlContains("/admin-leave"));

        // Approve the first leave request
        WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text()='Accept'])[1]")
        ));
        approveButton.click();

        // Reject the second leave request
        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text()='Reject'])[2]")
        ));
        rejectButton.click();
    }


    //TC017 - Approve/Reject Reimbursement Request

    @Test
    public void testAdminApproveAndRejectReimbursement() {
        testAdminLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until "Leave Requests" button is clickable and click it
        WebElement reimbursmentBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Reimbursement Requests']")
        ));
        reimbursmentBtn.click();

        wait.until(ExpectedConditions.urlContains("/admin-reimbursement"));

        // Approve the first reimbursement request
        WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text()='Accept'])[1]")
                ));
        approveButton.click();

        // Reject the second reimbursement request
        WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text()='Reject'])[2]")
        ));
        rejectButton.click();

    }

    //TC018 – Navigate between leave and reimbursement tabs

    @Test
    public void testAdminTabNavigation() {
        testAdminLoginSuccess();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Leave Requests (assuming this button exists)
        WebElement leaveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Leave Requests')]")));
        leaveBtn.click();
        wait.until(ExpectedConditions.urlContains("/admin-leave")); // ✅ Confirm navigation

        driver.navigate().back();

        WebElement ReimbursementBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Reimbursement Requests')]")));
        ReimbursementBtn.click();
        wait.until(ExpectedConditions.urlContains("/admin-reimbursement")); // ✅ Confirm page loaded
    }


    //TC019 – Admin logout returns to home
    @Test
    public void testAdminLogoutToLoginPage() {
        testAdminLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for and click Logout button
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Logout')]")
        ));
        logoutBtn.click();

        // Wait for redirection to admin login page
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/admin-login"));

        // Verify Admin Login header is present
        WebElement loginHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Admin Login')]")
        ));
        assertTrue(loginHeader.isDisplayed());

        // Optional: verify email input field is present
        WebElement emailInput = driver.findElement(By.xpath("//input[@type='email']"));
        assertTrue(emailInput.isDisplayed());
    }

    //TC020 Submit reimbursement and check in admin panel

    @Test
    public void testReimbursementAppearsInAdminPanel() throws InterruptedException {
        // Step 1: Submit a valid reimbursement request
        testTravelRequestValidSubmission(); // Reuses TC011

        // Step 2: Login as admin
        testAdminLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 3: Navigate to Reimbursement Requests tab
        WebElement reimbursementBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Reimbursement Requests']")
        ));
        reimbursementBtn.click();
        wait.until(ExpectedConditions.urlContains("/admin-reimbursement"));

        // Step 4: Assert the reimbursement request is visible
        WebElement requestRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(.,'Name') and contains(.,'Test Employee')]")
        ));
        assertTrue(requestRow.isDisplayed(), "Reimbursement request should appear in admin panel");

    }

    //TC021

    @Test
    public void testLeaveRequestAppearsInAdminPanel() throws InterruptedException {
        // Step 1: Submit a valid leave request
        testSubmitLeaveRequestValid(); // Reuse your TC009

        // Step 2: Login as admin
        testAdminLoginSuccess();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 3: Navigate to Leave Requests tab
        WebElement leaveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Leave Requests']")
        ));
        leaveBtn.click();
        wait.until(ExpectedConditions.urlContains("/admin-leave"));

        // Step 4: Assert that the leave request with name or reason is visible
        WebElement requestRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(.,'Name') and contains(.,'Achyutam Vermma')]")
        ));

        assertTrue(requestRow.isDisplayed(), "Leave request should appear in admin panel");
    }


    @AfterEach
        public void tearDown()
        {
            driver.quit();
        }
}


