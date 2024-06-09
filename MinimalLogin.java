package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.animation.model.KeyframeStyle;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class MinimalLogin {
    WebDriver driver;
    @BeforeMethod
     public void Setup()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
    }
    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://minimals.cc/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        //login
        driver.findElement(By.xpath("(//a[@href='/dashboard'])[1]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("(//input[@name='email'])")).sendKeys("demo@minimals.cc");
        driver.findElement(By.xpath("(//*[@name='password'])")).sendKeys("demo1234");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[@type='submit'])")).click();
        Thread.sleep(4000);


        //test case 1
        driver.findElement(By.xpath("//span[contains(text(),'user')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'account')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(" //button[normalize-space()='Billing']")).click();
        Thread.sleep(1000);
        String prename = driver.findElement(By.xpath("(//button[normalize-space()='Jayvion Simon'])[1]")).getText();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[normalize-space()='Jayvion Simon']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys("Deja Brady");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//h6[normalize-space()='Deja Brady']")).click();
        String udpatename = driver.findElement(By.xpath("(//button[normalize-space()='Deja Brady'])[1]")).getText();
        Assert.assertNotEquals(prename,udpatename,"Billing name not updated successfully");
        Thread.sleep(2000);
        String payment = driver.findElement(By.xpath("//button[normalize-space()='**** **** **** 5678']")).getText();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space()='**** **** **** 5678']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//*[name()='svg'][contains(@role,'img')])[40]")).click();

       // Test case 2
        driver.findElement(By.xpath("//span[contains(text(),'order')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'list')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Search customer or order number...']")).sendKeys("cor");

        String searchResult = driver.findElement(By.xpath("//span[contains(text(), 'Cortez Herring')]")).getText();
        String searchResultRow = driver.findElement(By.xpath("//p[contains(text(), '1')]")).getText();
        String searchRowCount = searchResultRow.substring(7, 8);
        Assert.assertEquals("Cortez Herring", searchResult, "search result is not correct");
        Assert.assertEquals("1", searchRowCount, "search Result row count more then one ");
        System.out.println("Test case 2 successfully run");


        //Test case 3
        driver.findElement(By.xpath(" //span[contains(text(),'job')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'list')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Filters']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'On Demand')]//parent::label//input")).click();
        driver.findElement(By.xpath("//div[@role='presentation']//button[2]")).click();

        List<WebElement> cardsText = driver.findElements
                (By.xpath("//div[contains(@class, 'MuiStact-root css-15t28ni')]//span[contains(text(),'On Demand')]"));

        //verify the number of card found
        Assert.assertEquals(cardsText.size(),4,"Expected number of cards is 4");

        for (WebElement card : cardsText)
        {
            Assert.assertTrue(card.getText().contains("On Demand"),"card does not contains text 'on Demand':"+card.getText());
        }
        System.out.println("Test cases 3 successfully run");


        //Test Case 4
        driver.findElement(By.xpath("//span[contains(text(), 'chat')]")).click();
        driver.findElement(By.xpath("(//h6[contains(text(), 'Deja Brady')])[1]")).click();
        WebElement element =driver.findElement(By.xpath("//input[@placeholder='Type a message']"));
        Thread.sleep(1000);
        String message = "Hello, How are you";
        element.sendKeys(message + Keys.ENTER);

        List<WebElement> messages = driver.findElements(By.xpath("//div[@class='MuiStack-root css-t9gybc']"));
boolean messageFound =false;
for(WebElement msg : messages)
{
    if (msg.getText().contains(message)){
        messageFound = true;
        break;

    }
}
Assert.assertTrue(messageFound, "The message was not found in the chat area");
System.out.println("Test cases 4 successfully run");



        //Test Cases 5 Delete files
        driver.findElement(By.xpath("//span[contains(text(),'File Manager')]")).click();
        driver.findElement(By.xpath("//th//input[@class='PrivateSwitchBase-input css-1m9pwf3']")).click();
        driver.findElement(By.xpath("//button[@aria-label,'Delete']")).click();
        driver.findElement(By.xpath("(//h2[contains(text(),'Delete')]//following ::button)[1]")).click();
        String emptyFileManagerText = driver.findElement(By.xpath("//div[@class='MuiStack-root css-tm32ml']//span")).getText();
        Assert.assertEquals(emptyFileManagerText, " No Data", "File Manager is not empty after deleting all itmes");
        System.out.println("Test cases 5 successfully run");
    }
    @AfterTest
    void TearDown() {
        driver.quit();
    }


    }








