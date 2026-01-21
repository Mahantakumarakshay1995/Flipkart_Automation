package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import java.text.NumberFormat;
import java.util.Locale;

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
    public static Boolean getAllRatingUnderNumber(List<WebElement> allele, Double n){
        Boolean success;
        try {
            
        
                int count=0;

                for(int i=0;i< allele.size();i++){

                            
                    if(Double.parseDouble(allele.get(i).getText()) <=  n){
                    count++;
                    }
                }
                System.out.println("Count of washing machine which has star rating more than or equal "+n+": "+count);
                success = true;         
           
        }

            catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Exception occured");
            return success=false;

            }
             return success;
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
        public static Boolean findDiscountPercantageAndTitle(By locator,WebDriver driver,int discount){
            Boolean success;
            int count=0;
            try {
                HashMap<String,String> iphonemap=new HashMap<>();
                List<WebElement> listOfDiscount =driver.findElements(locator);//12
                
                for (WebElement productRow : listOfDiscount) {
                    String discountPercantage = productRow.getText();
                    int discountNumber =Integer.parseInt(discountPercantage.replaceAll("[^\\d]", ""));
                    if(discountNumber > discount){
                       String iPhoneTitle =productRow.findElement(By.xpath("//div[@class='HQe8jr']//span/ancestor::div[@class='ZFwe0M row']/descendant::div[@class='RG5Slk']")).getText();
                       iphonemap.put(discountPercantage, iPhoneTitle);
                       count++;
                    }
                    
                }
                System.out.println("Total number of element got ::"+count);
                for (Map.Entry<String,String> iphoneTitleDiscount : iphonemap.entrySet()) {
                    System.out.println("Iphone discount Percantage ::"+iphoneTitleDiscount.getKey()+
                                        " and Title is ::"+iphoneTitleDiscount.getValue());
                    
                }
                success=true;
                
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                System.out.println("Got Exception at Testcase number 4");
                success=false;
            }

            return success;

        }

        public static Boolean printTitleAndImageUrlOfCoffeMug(By locator ,WebDriver driver){
            Boolean  success;
            try {
               List<WebElement> userReviewElements=driver.findElements(locator);
               Set<Integer> userReviewSet=new HashSet<>();
               for (WebElement userReviwElement : userReviewElements) {
                int userReview=Integer.parseInt(userReviwElement.getText().replaceAll("[^\\d]",""));
                userReviewSet.add(userReview);
                
               }
               //System.out.println(userReviewSet);
               List<Integer> userReviewCountList=new ArrayList<>(userReviewSet);//convert set in to arraylist
               Collections.sort(userReviewCountList,Collections.reverseOrder());
               System.out.println(userReviewCountList);
               NumberFormat numberFormat=NumberFormat.getInstance(Locale.US);
               LinkedHashMap<String,String> productDetailsMap=new LinkedHashMap<>();
               for(int i=0;i<5;i++){
                String formatedUserReviewCount="("+numberFormat.format(userReviewCountList.get(i))+")";//2,000
                String productTitle = driver.findElement(By.xpath("//span[@class='PvbNMB' and contains(text(),'"+formatedUserReviewCount+"')]/ancestor::div[@class='RGLWAk']//a[@class='pIpigb']")).getText();
                String imageUrl=driver.findElement(By.xpath("//span[@class='PvbNMB' and contains(text(),'"+formatedUserReviewCount+"')]/ancestor::div[@class='RGLWAk']//img[@class='UCc1lI']")).getAttribute("src");
                //create a hashKey for linkedHashMAp
                String highestReviewCountAndProductTitle=String.format("%d highest review count : %s - %s", (i+1),formatedUserReviewCount,productTitle);

                productDetailsMap.put( highestReviewCountAndProductTitle,imageUrl);

               }
               //print Title and Image Url of product
               for (Map.Entry<String,String> productDetails : productDetailsMap.entrySet()) {
                System.out.println(productDetails.getKey()+"And Product Image URL is::"+productDetails.getValue());
                
               }
               success=true;
                
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                System.out.println("Got exception at testcase 5");
                success=false;
            }

            return success;

        }
    }

