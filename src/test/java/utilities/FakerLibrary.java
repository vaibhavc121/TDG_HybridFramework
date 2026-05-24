package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

public class FakerLibrary
{
    public static void main(String args[])
    {
        // Set up Faker instance
        Faker faker = new Faker();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.findElement(By.id("name")).sendKeys(faker.name().firstName());
        // driver.findElement(By.id("")).sendKeys(lastName);
        driver.findElement(By.id("email")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("phone")).sendKeys(faker.phoneNumber().subscriberNumber(10));
        driver.findElement(By.id("textarea")).sendKeys(faker.address().fullAddress());
        driver.findElement(By.id("input1")).sendKeys(faker.country().name());

    }
}