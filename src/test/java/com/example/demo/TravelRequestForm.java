package com.example.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void Employee()
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

        // Reason
        WebElement reasonInput = driver.findElement(
                By.cssSelector("textarea[placeholder='Enter reason']")
        );
        reasonInput.sendKeys("Conference attendance");

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


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

