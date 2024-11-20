package com.cydeo.tests.day3;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebDriverWaitPractice {

    WebDriver driver;

    @BeforeMethod
    public void setup(){

        // 1. Open Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //findElement()
    }

    @Test
    public void implicit_wait_test(){
        // 1. Go to https://practice.cydeo.com/dynamic_loading/1
        driver.get("https://practice.cydeo.com/dynamic_loading/1");

        // 2. Click to start
        driver.findElement(By.xpath("//button[.='Start']")).click();

        // 3. Wait until loading bar disappears
        WebElement loadingBar = driver.findElement(By.id("loading"));

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOf(loadingBar));

        // 4. Assert username inputbox is displayed
        WebElement username = driver.findElement(By.cssSelector("#username"));
        Assert.assertTrue(username.isDisplayed());

        // 5. Enter username: tomsmith
        username.sendKeys("tomsmith");

        // 6. Enter password: incorrectpassword
        Faker faker = new Faker();
        driver.findElement(By.id("pwd")).sendKeys(faker.internet().password());

        // 7. Click to Submit button
        driver.findElement(By.xpath("//button[.='Submit']")).click();

        // 8. Assert “Your password is invalid!” text is displayed.
        WebElement errorMsg = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMsg.isDisplayed());

    }
}
