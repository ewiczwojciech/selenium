package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TheInternetTests extends BaseTest{

	@Test
	public void loadingTest(){
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		driver.findElement(By.cssSelector("div#start button")).click();


		String loadingText = driver.findElement(By.cssSelector("#loading")).getText();

		Assert.assertEquals(loadingText, "Loading...");

		WebDriverWait wait = new WebDriverWait(driver, 10);



		WebElement finishElement = driver.findElement(By.cssSelector("#finish"));

		wait.until(ExpectedConditions.visibilityOf(finishElement));

		String finishText = finishElement.getText();

		Assert.assertEquals(finishText, "Hello World!");
	}

	@Test
	public void loadingTest2(){

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.cssSelector("div#start button")).click();

		String loadingText = driver.findElement(By.cssSelector("#loading")).getText();

		Assert.assertEquals(loadingText, "Loading...");

		WebDriverWait wait = new WebDriverWait(driver, 20);


		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish")));

		WebElement finishElement = driver.findElement(By.cssSelector("#finish"));
		String finishText = finishElement.getText();

		Assert.assertEquals(finishText, "Hello World!");
	}
}
