package com.cydeo.tests.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RegistrationFormPageTest {


    @Test
    public void RegistrationFormPageTitleTest() {

        // TC#1: Registration Form Page Testing

        // 1. Open Chrome browser
        WebDriver driver = new ChromeDriver();

        // 2. Go to https://practice.cydeo.com/registration_form
        driver.get("https://practice.cydeo.com/registration_form");

        // 3. Verify title is as expected:
        // Expected: "Registration Form"
        String expectedTitle = "Registration Form" ;
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title verification is failed!");

        //Assert.assertTrue(actualTitle.equals(expectedTitle), "Title verification is failed!");

        // 4. Close browser
        driver.quit();
    }

    // TC#2: Registration Form Page Filling Form Test
    @Test
    public void registrationFormPageFillingOutTest() {

        // 1. Open Chrome browser
        WebDriver driver = new ChromeDriver();

        // 2. Go to https://practice.cydeo.com/registration_form
        driver.get("https://practice.cydeo.com/registration_form");

        // 3. Enter First name: "John"
        driver.findElement(By.name("firstname")).sendKeys("John");

        // 4. Enter Last name: "Smith"
        driver.findElement(By.name("lastname")).sendKeys("Smith");

        // 5. Enter Username: "johnsmith123"
        driver.findElement(By.xpath("//input[@placeholder='username']")).sendKeys("johnsmith123");

        // 6. Enter Email address: "john.smith@email.com"
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("john.smith@email.com");

        // 7. Enter password: "John1234"
        driver.findElement(By.name("password")).sendKeys("John1234");

        // 8. Enter Phone number: "123-456-7890"
        driver.findElement(By.name("phone")).sendKeys("123-456-7890");

        // 9. Click to "Male" from Gender
        driver.findElement(By.xpath("//input[@value='male']")).click();

        // 10. Enter Date of birth "01/28/1990"
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("01/28/1990");

        // 11. Select "Department of Engineering" from Department/Office dropdown
        Select selectDepartment = new Select(driver.findElement(By.name("department")));

//        When we want to select all options:
        List<WebElement> allOptions = selectDepartment.getOptions();
        for(WebElement eachOption : allOptions) {
            System.out.println(eachOption.getText());
        }

//        selectDepartment.selectByIndex(1);
//        selectDepartment.selectByValue("DE");
        selectDepartment.selectByVisibleText("Department of Engineering");

        String expectedOption = "Department of Engineering";
        String actualOption = selectDepartment.getFirstSelectedOption().getText();

        Assert.assertEquals(actualOption, expectedOption);

        // 12. Select "SDET" from Job title dropdown
        Select selectJobTitle = new Select(driver.findElement(By.cssSelector("select[name='job_title']")));

        selectJobTitle.selectByVisibleText("SDET");

        Assert.assertTrue(selectJobTitle.getFirstSelectedOption().getText().equals("SDET"));

        // 13. Click to "Java" from languages
        driver.findElement(By.xpath("//input[@value='java']")).click();

        List<WebElement> languages = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for(WebElement each : languages) {
            each.click();
        }

        // 14. Click to "Sign up" button
        driver.findElement(By.xpath("//button[text()='Sign up']")).click(); //xpath: //button[.='Sign up'];
                                                                                          //css id: button#wooden_spoon
                                                                                          //css class: .btn.btn-primary
        // 15. Verify text displayed on page
        //     Expected: "Well done!"
    }

}
