package com.cydeo.tests.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class UploadFileTest {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        // TC#2 : Web table practice
        // 1. Open Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //findElement()
    }


    @Test
    public void upload_file_test() throws InterruptedException {

        // TC#1: Guru99 Upload Test
        //1. Go to  “https://demo.guru99.com/test/upload”
        driver.get("https://demo.guru99.com/test/upload");

        //2. Upload file into Choose File
        WebElement chooseFileBtn = driver.findElement(By.id("uploadfile_0"));
        chooseFileBtn.sendKeys("file:///Users/a13478/Documents/resume-pokizadev.pdf");

        //3. Click terms of service check box
        driver.findElement(By.id("terms")).click();

        //4. Click Submit File button
        driver.findElement(By.id("submitbutton")).click();

        //5. Verify expected message appeared.
        // Expected: “1 file
        // has been successfully uploaded.

        WebElement resultMsg = driver.findElement(By.cssSelector("h3[id='res']>center"));

        Thread.sleep(3000);

        String expectedText = "1 file\nhas been successfully uploaded.";
        String actualText = resultMsg.getText();

        Assert.assertEquals(actualText,expectedText,"Text verification is failed!");

    }
}
