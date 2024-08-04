package AssignmentSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class FitpeoInterviewTask {

	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "D:\\May22Testing\\Driver\\chromedriver.exe");
		
		//Initializing the chromedriver and lauching the URL
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.fitpeo.com/");
		
		//Maximizing the window of the webpage
		driver.manage().window().maximize();
		
		//checking title to verify we opened the expected webpage
		String actualTitle = driver.getTitle();
		String expectedTitle = "Remote Patient Monitoring (RPM) - fitpeo.com";
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("I m on correct page");
		}
		else {
			System.out.println("Page is not correct "+actualTitle);
		}
		
		//Clicking on Menu Icon
		WebElement menuIcon = driver.findElement(By.xpath("//*[@data-testid='MenuIcon']"));
		menuIcon.click();
		
		//Clicking on Revenue calculator
		WebElement revenueCalculator = driver.findElement(By.xpath("//*[@class='MuiListItemText-root inter css-1tsvksn']/span[text()='Revenue Calculator']"));
		revenueCalculator.click();
		
		//Scrolling till slider is visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement sliderPoint = driver.findElement(By.xpath("//*[contains(@class,'MuiSlider-thumb')]/input"));
		js.executeScript("arguments[0].scrollIntoView();", sliderPoint);
		
		//Adjusting the slider so that the value in the text field displays 817
		Actions action = new Actions(driver);
		int xoffset = 93;
		action.clickAndHold(sliderPoint).moveByOffset(xoffset, 0).release().perform();
	
		//Validating whether the text displayed in the patients text field is 817 or not
		WebElement patientsTextField = driver.findElement(By.xpath("//*[text()='Medicare Eligible Patients']//parent::div/div/div//input"));
		Assert.assertEquals(patientsTextField.getAttribute("value"), "817");
		
		//Clearing the previous text in the patients text field
		patientsTextField.sendKeys(Keys.CONTROL+"A");
		patientsTextField.sendKeys(Keys.DELETE);
		
		//Entering new text 560 in the patients text field and verifying the slider is moved accordingly
		patientsTextField.sendKeys("560");
		Assert.assertEquals(sliderPoint.getAttribute("value"), "560");
		
		WebElement checkBoxCPT99091 = driver.findElement(By.xpath("//*[text()='CPT-99091']//parent::div//input"));
		WebElement checkBoxCPT99453 = driver.findElement(By.xpath("//*[text()='CPT-99453']//parent::div//input"));
		WebElement checkBoxCPT99454 = driver.findElement(By.xpath("//*[text()='CPT-99454']//parent::div//input"));
		WebElement checkBoxCPT99474 = driver.findElement(By.xpath("//*[text()='CPT-99474']//parent::div//input"));

		//Clicking on 4 checkboxes having CPT codes CPT99091, CPT99453, CPT99454, CPT99474
		clickCheckbox(checkBoxCPT99091);
		clickCheckbox(checkBoxCPT99453);
		clickCheckbox(checkBoxCPT99454);
		clickCheckbox(checkBoxCPT99474);
		
		//Verifying the amount displayed for Total Recurring Reimbursement for all Patients
		WebElement totalRecurringReimbursement = driver.findElement(By.xpath("//*[text()='Total Recurring Reimbursement for all Patients Per Month']//parent::div//*[contains(@class,'MuiTypography-root MuiTypography-body1')]"));
		if(totalRecurringReimbursement.getText().equals("$110700"))
		{
			System.out.println("Total Recurring Reimbursement for all Patients Per Month is $110700");
		}
		else
		{
			System.out.println("Total Recurring Reimbursement for all Patients Per Month is not $110700");
		}
		
		//Closing the webdriver
		driver.quit();  
	}

	public static void clickCheckbox(WebElement element)
	{
		if(!element.isSelected())
		{
			element.click();
		}
	}
	
}
