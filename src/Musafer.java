import java.awt.im.InputContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Musafer {

	private static final String enabled = null;
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String[] webLang = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };

	@BeforeTest
	public void mySetup() {

		
		driver.get("https://www.almosafer.com/en?ncr=1");

		driver.manage().window().maximize();
	
	}




	@Test (enabled=false)
	public void checkLang() {
		
		String websiteLang = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(websiteLang, "en");
		
		 if (websiteLang.equals("en")) {
			 System.out.print("Test is passed");
		 }
		
		
	}
	
	@Test(enabled=false)
	public void checkCurrency() {
		
		WebElement websiteCurrency = driver.findElement(By.className("sc-hUfwpO"));
//		System.out.print(websiteCurrency.getText());
		
		Assert.assertEquals(websiteCurrency.getText(), "SAR");
		
		 if (websiteCurrency.getText().equals("SAR")) {
			 System.out.print("Test is passed");
		 }
		
}
	@Test (enabled=false)
	public void contactNumber() {
		WebElement contactNumber = driver.findElement(By.tagName("strong"));
//		System.out.print(websiteCurrency.getText());
		
		Assert.assertEquals(contactNumber.getText(),"+966554400000");
		
		 if (contactNumber.getText().equals("+966554400000")) {
			 System.out.print("contactNumber is right");
		 }
	
	}
	
	
	@Test (enabled=false)
	public void qitaLlogoDisplay() {
		WebElement qitafLogo = driver.findElement(By.tagName("footer"));
		boolean qitafLogoDisplayed = qitafLogo.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR")).isDisplayed();
		Assert.assertEquals(qitafLogoDisplayed, true);
	}
	
	@Test (enabled=false)
	public void hotalSearch() {
		
		WebElement staysTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String isDefaultSelected = staysTab.getDomAttribute("aria-selected");

		Assert.assertEquals(isDefaultSelected, "false");


	}

	@Test (enabled=false)
	public void flightDepature() {
		
		LocalDate date = LocalDate.now();
		LocalDate nextDay = date.plusDays(1);
		WebElement dates = driver.findElement(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String dayOfDeparture = dates.getText();
 		int ActualDepatureDate = nextDay.getDayOfMonth();
		Assert.assertEquals(ActualDepatureDate,dayOfDeparture);
	}
	
	
	@Test (enabled=false)
	public void flightReturn() {
		
		LocalDate date = LocalDate.now();
		LocalDate nextDay = date.plusDays(2);
		WebElement dates = driver.findElement(By.cssSelector(".sc-bYnzgO.sc-hvvHee.aiGEY"));
		WebElement dayOfReturn = dates.findElement(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String getDayOfReturn= dayOfReturn.getText();
 		int ActualReturnDate = nextDay.getDayOfMonth();
		Assert.assertEquals(ActualReturnDate,getDayOfReturn);
	}
	
	
	
	@Test 
	public void changeLanguage() {
		
		int randomLand = rand.nextInt(webLang.length);
		driver.get(webLang[randomLand]);

	}

	@Test 
	public void randomCity() {
		
		String[] CitiesInEnglish= { "dubai", "jeddah", "riyadh" };
		String[] CitiesInArabic = { "الرياض" ,"دبي", "جدة" };
		int randomEnglishCity = rand.nextInt(CitiesInEnglish.length);
		int randomArabicCity = rand.nextInt(CitiesInArabic.length);


		WebElement staysTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		staysTab.click();
		WebElement SearchInputField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));

		if (driver.getCurrentUrl().contains("ar")) {
			SearchInputField.sendKeys(CitiesInArabic[randomArabicCity]);

		} else {
			SearchInputField.sendKeys(CitiesInEnglish[randomEnglishCity]);
	}
	}
	
	@Test
public void randomHotelSearchBox() {
		
	String[] values = { "A", "B" };
	int randomValue = rand.nextInt(values.length);
	
	WebElement hotelSearchBox = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

	Select mySelector = new Select(hotelSearchBox);

	mySelector.selectByValue(values[randomValue]);

	driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk")).click();
	
}
	
	@Test
public void checkAPILoaded() throws InterruptedException {
		Thread.sleep(10000);
		String APIResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']")).getText();
	if (driver.getCurrentUrl().contains("en")) {
		Assert.assertEquals(APIResult.contains("found"), true);

	} else {
		Assert.assertEquals(APIResult.contains("مكان إقامة"), true);

	}

}
	
	
}
