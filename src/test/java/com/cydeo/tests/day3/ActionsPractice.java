package com.cydeo.tests.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ActionsPractice {

    WebDriver driver;

    @BeforeMethod
    public void setup(){

        // 1. Open Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //findElement()
    }

    @Test
    public void actions_test() throws InterruptedException {
        //  1. Go to  "https://www.globalsqa.com/demo-site/draganddrop/"
        driver.get("https://www.globalsqa.com/demo-site/draganddrop/");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame lazyloaded']")));

        //  2. Locate "High Tatras" and "High Tatras 2" as sources and  "Trash" as target elements
        WebElement highTatras = driver.findElement(By.xpath("//h5[.='High Tatras']"));
        WebElement highTatras2 = driver.findElement(By.xpath("//h5[.='High Tatras 2']"));

        WebElement trash = driver.findElement(By.id("trash"));


        //  3. Drag and Drop "High Tatras" and "High Tatras 2" into "Trash"
        Actions actions = new Actions(driver);

        actions.dragAndDrop(highTatras,trash).perform(); // if this method does not work iy come from timing issue

        actions.moveToElement(highTatras2).clickAndHold().moveToElement(trash).pause(2000).release().perform();

        Thread.sleep(3000);
        //  4. Verify 2 images moved to the trash
        List<WebElement> trashImages = driver.findElements(By.xpath("//div[@id='trash']//li"));

        Assert.assertTrue(trashImages.size()==2);


    }

}
