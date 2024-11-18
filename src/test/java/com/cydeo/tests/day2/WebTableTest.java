package com.cydeo.tests.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WebTableTest {

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
    public void web_table_test(){
        // 2. Go to https://practice.cydeo.com/tables
        driver.get("https://practice.cydeo.com/tables");

        // 3. Print table data as a single String, not so common, but useful sometimes
        WebElement table1 = driver.findElement(By.xpath("//table[@id='table1']"));
        System.out.println("table1.getText() = " + table1.getText());

        // 4. verify tconway@earthlink.net is anywhere in the table
        Assert.assertTrue(table1.getText().contains("tconway@earthlink.net"));

        // 5. print all column names in single line
        WebElement allColNames = driver.findElement(By.xpath("//table[@id='table1']//thead"));
        System.out.println("allColNames.getText() = " + allColNames.getText());

        // 6. print each column name in separate lines using a loop and findElements method
        List<WebElement> listOfColNames = driver.findElements(By.xpath("//table[@id='table1']//thead//th"));

        for (WebElement eachColName : listOfColNames) {
            System.out.println("eachColName.getText() = " + eachColName.getText());
        }

        // 7. verify Tim due money is $50.00
        WebElement timDueMoney = driver.findElement(By.xpath("//table[@id='table1']//td[.='tconway@earthlink.net']/../td[.='$50.00']"));
        // xpath = //table[@id='table1']//td[.='tconway@earthlink.net']/following-sibling::td[1]

        Assert.assertEquals(timDueMoney.getText(),"$50.00");
    }
}