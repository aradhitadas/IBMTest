package StepDefinition;

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.Assert;


import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;
import cucumber.api.java.After;

public class Steps {				

	WebDriver driver; String card1,card2;
	
    @Given("^Navigate to DBS home page$")				
    public void Navigate_to_DBS_home_page() throws Throwable							
    {		
        System.out.println("Open the browser and launch DBS home page.");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");					
    	driver= new ChromeDriver();	
        driver.manage().window().maximize();	
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.dbs.com.sg/personal/default.page");
    }		

    @When("^I click on Card from Menu$")					
    public void I_click_on_Card_from_Menu() throws Throwable 							
    {		
       System.out.println("This step click on cards from Menu.");
       driver.findElement(By.linkText("Cards")).click();
       
    }		

    
    @When("^I click on Credit Cards$")					
    public void I_click_on_Credit_Cards() throws Throwable 							
    {		
    	driver.findElement(By.linkText("Credit Cards")).click();	
       
    }	
    
    @Then("^All credit card options are displayed$")
    public void All_credit_card_options_are_displayed() throws Throwable
    {
    	System.out.println("All credit card options are displayed");
    	WebElement d = driver.findElement(By.xpath("//div[contains(@class, 'tab-content')]"));
    	Assert.assertNotEquals(0,d.findElements(By.xpath("./child::*")).size());
    }
    
    @When("^Select two credit card options \"([^\"]*)\" , \"([^\"]*)\"$")					
    public void Select_two_credit_card_options(String card1,String card2) throws Throwable
    {	
    	System.out.println("selecting 2 card");
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	
    
    	WebElement ele1 = driver.findElement(By.xpath("//div[.='" + card1 + "']"));
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele1);
		WebElement ele2 = driver.findElement(By.xpath("//div[.='" + card2 + "']"));
		jse.executeScript("arguments[0].click()", ele2);
		
    	   	
    	driver.findElement(By.id("cardCompareBtn")).click();
    	
    	
    }
    
    @Then("^The detsils of selected cards \"([^\"]*)\" , \"([^\"]*)\" display correctly$")
    public void The_detsils_of_selected_cards_display_correctly(String card1,String card2) throws Throwable
    {	
    	System.out.println("Check selected card are displayed");
    	
    	String value,value1,v; int i=1,j; WebElement ele;
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='comparisonResults']")));
    	
    	WebElement result = driver.findElement(By.xpath("//div[@id='comparisonResults']"));
    	
    	List<WebElement> c = result.findElements(By.xpath("./child::*"));
    	for(WebElement element:c)
    	{	
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    		
    		value = element.findElement(By.xpath("//div[@id='comparisonResults']/div["+i+"]/div[2]/div")).getText();
    		
    		if(i==1)
    			Assert.assertEquals(card1,value);
    		if(i==2)
    			Assert.assertEquals(card2,value);
    		System.out.println(value);
    		
    		for(j=1;j<=6;j++)
    		{	
    			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    			ele = element.findElement(By.xpath("//div[@id='comparisonResults']/div["+i+"]/div[6]/div["+j+"]"));
    			value1 = ele.getText();
    			System.out.println(value1);
    			
    			if(i==1 && ele.getAttribute("display")!= "none")
    			{
    				if(j==1)
    				Assert.assertEquals("Best For",value1);
    				if(j==2)
    				Assert.assertEquals("It's the fastest way to fly anywhere.",value1);
    				if(j==3)
    				Assert.assertEquals("Card Type",value1);
    				if(j==4)
    				Assert.assertEquals("VISA",value1);
    				if(j==5)
    				Assert.assertEquals("Min Income Per Annum",value1);
    				if(j==6)
    				Assert.assertEquals("S$30,000",value1);
    			}
    			
        		if(i==2)
        		{
        			if(j==1)
            		Assert.assertEquals(" ",value1);
        			if(j==2)
    				Assert.assertEquals("Shopping is the new black",value1);
        			if(j==3)
    				Assert.assertEquals(" ",value1);
        			if(j==4)
    				Assert.assertEquals("VISA",value1);
        			if(j==5)
    				Assert.assertEquals(" ",value1);
        			if(j==6)
    				Assert.assertEquals("S$30,000",value1);
        		}
        			
        		
    		}
    		   		
    		i++;
    		
    		
    	}
    	
       
    }
    
    @After
    public void afterScenario() {
    	driver.close();
    }
    

}
