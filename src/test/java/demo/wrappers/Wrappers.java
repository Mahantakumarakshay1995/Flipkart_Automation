package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void findSearchAndClick(WebElement element,String text){
        try {
            element.clear();
            element.sendKeys(text);
            element.submit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    public static void scrollAndClick(WebElement element, ChromeDriver driver){
        try{
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        element.click();
        //element.submit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static int getAllRatingUnderNumber(List<WebElement> allele, Double n){
        int count=0;
        for(int i=0;i< allele.size();i++){

                    
            if(Double.parseDouble(allele.get(i).getText()) <=  n){
             count++;
            }
        }
            return count;
        }

        public static void clickOnFilter(WebElement element){
            element.click();
        } 
        public static void waitTillClicakable(WebElement element,ChromeDriver driver){
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("element located");
        }

        public static void getAllDiscountabove(List<WebElement> ele, int n, ChromeDriver driver){
            //int value=Integer.parseInt(s.split("%")[0].trim());
            int count=0;
            int count1=0;
            List<WebElement> titleslist =driver.findElements(By.xpath("//div[@class='HQe8jr']//ancestor::div[@class='ZFwe0M row']//child::div[@class='RG5Slk']"));
            for(int i=0;i<ele.size();i++){
                String titletext=titleslist.get(i).getText();
               String discounttext=ele.get(i).getText();

               //System.out.println("before trim: "+discounttext);
               int discountnumber=Integer.parseInt(discounttext.replaceAll("\\D+", ""));
               System.out.println("After trim: "+discountnumber);

               if(discountnumber >n){
                System.out.println("Inside if loop Titale name: "+titletext);
                System.out.println("Inside if loop Discount Number: "+discountnumber+" %");
                 System.out.println("--------------------------------");
                count1++;

               }

               count++;
            }
            System.out.println("Number of Total element are :"+count);
            System.out.println("Number of element Who are more than 17 percent discounted :"+count1);
           

        }
    }

