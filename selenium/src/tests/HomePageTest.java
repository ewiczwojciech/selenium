package tests;


import java.io.Console;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{


	@Test
	public void checkContactText(){

		WebElement we = driver.findElement(By.id("contact-link"));
		String linkText = we.getText();
		Assert.assertEquals(linkText, "Kontakt z nami");

	}

	@Test
	public void checkHomePageTitle(){
		String title = driver.getTitle();

		Assert.assertEquals(title, "Automation Sample Shop");
		driver.quit();
	}

	@Test(priority = 1)
	public void chooseShirtSize(){
		driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");

		WebElement selectElement = driver.findElement(By.id("group_1"));
		Select selectSize = new Select(selectElement);
		selectSize.selectByVisibleText("XL");

		WebElement selectedValueElement = selectSize.getFirstSelectedOption();
		String selectedValue = selectedValueElement.getText();

		Assert.assertEquals(selectedValue, "XL");

	}

	@Test(priority = 2)
	public void checkDefaultQuantity(){
		driver.get("http://sampleshop.inqa.pl/home-accessories/7-mug-the-adventure-begins.html");
		WebElement quantityBox = driver.findElement(By.id("quantity_wanted"));

		String quantity = quantityBox.getAttribute("value");

		Assert.assertEquals(quantity, "1");
	}

	@Test
	public void openMensCategory(){
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("category-3"))).pause(1000).moveToElement(driver.findElement(By.id("category-4"))).click().build().perform();

//		wait

//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		WebElement we = driver.findElement(By.cssSelector(".h1"));

		Assert.assertEquals(we.getText(), "MEN");


	}

//
//	@Test
//	public void openWomenCategory

//	@Test(priority = 3)
//	public void checkTotalPrice(){
//		driver.get("http://sampleshop.inqa.pl/home-accessories/7-mug-the-adventure-begins.html");
//		WebElement itemPrice = driver.findElement(By.className("current-price"));
//		WebElement quantityBox = driver.findElement(By.id("quantity_wanted"));
//
//		String quantityText = quantityBox.getAttribute("value");
//		String singlePriceText = itemPrice.getText();
//		String singePriceTextTrim = singlePriceText.substring(0,5);
//
//		double quantity = Double.parseDouble(quantityText);
//		double singlePrice = Double.parseDouble(singePriceTextTrim);
//
//		var totalPrice = quantity * singlePrice;
//
//		Assert.assertEquals(totalPrice, 10);
//	}

	@Test
	public void isExpectedPriceOk(){
		driver.get("http://sampleshop.inqa.pl/6-accessories");
		WebElement itemName = driver.findElement(By.xpath("/html/body/main/section/div/div[2]/section/section/div[3]/div/div[1]/div[5]/article/div/div[1]/h2/a"));

		String name = itemName.getText();

		if(name.equals("Mountain Fox Cushion")){
			WebElement itemPrice = driver.findElement(By.cssSelector("div.product:nth-child(5) > article:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > span:nth-child(1)"));
			Assert.assertEquals(itemPrice.getText(), "23,25 zł");
		} else {
			Assert.fail("wrong item name");
		}



	}


}
