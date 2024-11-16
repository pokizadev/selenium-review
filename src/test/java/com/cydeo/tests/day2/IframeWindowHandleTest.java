package com.cydeo.tests.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class IframeWindowHandleTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // TC#1 : Iframe - WindowHandle
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void iframe_window_handle_test() {
        // 1- Go to http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target
        driver.get("http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target");

        // 2- Get the current window’s handle and write to the console window. It must be the first window handle.
        String mainWindow = driver.getWindowHandle();
        System.out.println("Main window handle: " + mainWindow);

        // 3- Click on “Visit W3Schools.com!” link
        //with index frame
        //driver.switchTo().frame(1);

        //with string frame;
        //driver. switchTo().frame("iframeResult");

        //with Web Element frame
        driver.switchTo().frame(driver.findElement(By.id("iframeResult")));

        //By linkText is failed without line no 43. Because link's html is inside of inner html which is covered with iframe
        driver.findElement(By.linkText("Visit W3Schools.com!")).click();

        // 4- Verify total window handle number is 2.
        Set<String> allWindows = driver.getWindowHandles();

        Assert.assertEquals(allWindows.size(), 2);

        // 5- Switch to the second window.
        for(String eachWindow : allWindows) {
            driver.switchTo().window(eachWindow);
        }
        // 6- Verify upper left side logo is displayed in second window.
        WebElement logo = driver.findElement(By.cssSelector(".fa.fa-logo.ws-hover-text-green"));
        Assert.assertTrue(logo.isDisplayed());

        // 7- Go back (Switch) to the first window.
        driver.switchTo().window(mainWindow);

        // 8- Check the See Run Button Text. It must contain “Run >” text.
        WebElement runBtn = driver.findElement(By.id("runbtn"));

        Assert.assertEquals(runBtn.getText(), "Run >");
    }

    @AfterMethod
    public void tearDown() {
        // TC#1 : Iframe - WindowHandle
        // 9- Close all the windows.
        //driver.quit();
    }
}


