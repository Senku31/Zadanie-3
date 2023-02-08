package Zadanie;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Zadanie {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.automationtesting.in/Datepicker.html");
    }

    @Test
    public void enterDateManually() throws InterruptedException {
        WebElement datepicker = driver.findElement(By.id("datepicker2"));
        datepicker.sendKeys("02/25/2022");
        String selectedDate = datepicker.getAttribute("value");
        Assert.assertEquals(selectedDate,"02/25/2022");
    }

    @Test
    public void pickDateFromCalendar() throws InterruptedException {
        WebElement datepicker = driver.findElement(By.id("datepicker1"));
        datepicker.click();

        int day = 25;
        String month = "February";
        String year = "2022";

        String monthEle = "";
        String yearEle = "";
        WebElement backCalBtn;
        Thread.sleep(500);

        while (true) {
            yearEle = driver.findElement(By.cssSelector(".ui-datepicker-year")).getText();
            monthEle = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
            if ((yearEle.equals(year) && monthEle.equals(month))) {
                break;
            }
            backCalBtn = driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w"));
            backCalBtn.click();
        }
        List<WebElement> calendar = driver.findElements(By.cssSelector(".ui-state-default"));
        calendar.get(day - 1).click();

        String selectedDate = datepicker.getAttribute("value");
        Assert.assertEquals(selectedDate, "02/25/2022");
    }

    @AfterMethod
    public void shutDown(){
        driver.close();
    }
}
