package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.xml.xpath.XPath;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;





import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * get washing machine whose popularity rating is more than 4 and give number how many product is there in the 1st page
     */
    @Test
    public void testCase01() throws InterruptedException {
       
        driver.get("https://www.flipkart.com/");
        WebElement search =driver.findElement(By.xpath("//input[@name='q']"));
        Wrappers.findSearchAndClick(search,"Washing Machine");
        Thread.sleep(2000);
        System.out.println("Wait1");

        WebElement popularelement = driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
        popularelement.click();

        Thread.sleep(3000);

        List<WebElement> listOfRatings=driver.findElements(By.xpath("//div[@class='MKiFS6']//parent::span"));
       Boolean result  =Wrappers.getAllRatingUnderNumber(listOfRatings,4.0);
        Assert.assertTrue(result);


    }
    /*search iphone click on filter- 10% or more and print title and discount percantage
     */
     @Test
    public void testCase02() throws InterruptedException {
        driver.get("https://www.flipkart.com/");
        WebElement search =driver.findElement(By.xpath("//input[@name='q']"));
        Wrappers.findSearchAndClick(search,"iPhone");
        Thread.sleep(2000);
        System.out.println("Wait1");

       WebElement discount =driver.findElement(By.xpath("//div[contains(text(),'10% or more')]"));
        Wrappers.clickOnFilter(discount);

        Thread.sleep(1000);
        System.out.println("Wait2");


        List<WebElement> offelements=driver.findElements(By.xpath("//div[@class='HQe8jr']//span"));
        Wrappers.getAllDiscountabove(offelements, 17,driver);

    }
    /*
    enter coffe mug get title,image url,and rating of 1st five element
     */

    @Test
    public void testCase03() throws InterruptedException {
        driver.get("https://www.flipkart.com/");
        WebElement search =driver.findElement(By.xpath("//input[@name='q']"));
        Wrappers.findSearchAndClick(search,"Coffee Mug");
        Thread.sleep(5000);
        System.out.println("Wait1");
 
        WebElement ratingButton =driver.findElement(By.xpath("//div[contains(text(),'4') and contains(text(),'above')]"));
        Wrappers.waitTillClicakable(ratingButton,driver);
        Wrappers.clickOnFilter(ratingButton);
        Thread.sleep(2000);
        System.out.println("Wait2");

        List<WebElement> titleselement=driver.findElements(By.xpath("(//div[contains(@class,'dLgFEE')])[3]//a[contains(@class,'pIpigb')]"));
        List<String> title=new ArrayList<>();
        for(int i=0;i< 5;i++){
            String text =titleselement.get(i).getText();
            title.add(text);
            System.out.println("Title is : " + (i+1) + ":" + text);
        }

         List<WebElement> imageurlelement=driver.findElements(By.xpath("(//div[contains(@class,'dLgFEE')])[3]//a[contains(@class,'GnxRXv')]"));
        List<String> image=new ArrayList<>();
        for(int i=0;i< 5;i++){
            WebElement element =imageurlelement.get(i);
            String hrefvalue = element.getAttribute("href");
            //image.add(text1);
            System.out.println("Image URL is : " + (i+1) + ":" + hrefvalue);
        }

        
         List<WebElement> ratingurlelement=driver.findElements(By.xpath("(//div[contains(@class,'dLgFEE')])[3]//span[contains(@class,'PvbNMB')]"));
        List<String> rating=new ArrayList<>();
        for(int i=0;i< 5;i++){
            WebElement ratingelement =ratingurlelement.get(i);
            String ratingtext = ratingelement.getText();
            //ratingtext=ratingtext.split(ratingtext)
            rating.add(ratingtext);
            System.out.println("Rating  is : " + (i+1) + ":" + ratingtext);
        }
        

    }
    /*
        Testcase for enter iphone and check 5 percent more discounted product and get title
    */
     @Test(enabled = true)
    public void testCase04() throws InterruptedException {
        driver.get("https://www.flipkart.com/");
        WebElement search =driver.findElement(By.xpath("//input[@name='q']"));
        Wrappers.findSearchAndClick(search,"iPhone");
        Thread.sleep(2000);
        System.out.println("Wait1");
        int discount=5;
       Boolean result =Wrappers.findDiscountPercantageAndTitle(By.xpath("//div[@class='HQe8jr']//span"),driver, discount);
        Assert.assertTrue(result);

    }
    /*
    get the highest 5 review in descending order and print Title and image URL
    */
    @Test
    public void testCase05() throws InterruptedException {
        System.out.println("START TC 5");
        driver.get("https://www.flipkart.com/");
        WebElement search =driver.findElement(By.xpath("//input[@name='q']"));
        Wrappers.findSearchAndClick(search,"Coffee Mug");
        Thread.sleep(5000);
        System.out.println("Wait1");

        WebElement ratingButton =driver.findElement(By.xpath("//div[contains(text(),'4') and contains(text(),'above')]"));
        Wrappers.waitTillClicakable(ratingButton,driver);
        Wrappers.clickOnFilter(ratingButton);
        Thread.sleep(2000);
        System.out.println("Wait2");

        Boolean result=Wrappers.printTitleAndImageUrlOfCoffeMug(By.xpath("//span[@class='PvbNMB']"),driver);
        Assert.assertTrue(result);
        System.out.println("End of TC5");

        

    }

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

       

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}