package com.cydeo.tests.day2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertPractice {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // TC#3 : Alert practice
        // 1. Open browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //findElement();
    }

    @Test
    public void handle_simple_alert() {

        // 2. Go to website: https://practice.cydeo.com/javascript_alerts
        driver.get("https://practice.cydeo.com/javascript_alerts");

        // 3. Click to “Click for JS Prompt” button
        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();

        // 4. Send “hello” text to alert
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("hello");

        // 5. Click to OK button from the alert
        alert.accept();

        // 6. Verify “You entered: hello” text is displayed.
        WebElement resultText = driver.findElement(By.id("result"));

        Assert.assertEquals(resultText.getText(), "You entered: hello", "Text verification is failed!");
    }

    @AfterMethod
    public void tearDown() {
        // TC#3 : Alert practice
        // 7. Close the browser
        driver.quit();
    }

}
