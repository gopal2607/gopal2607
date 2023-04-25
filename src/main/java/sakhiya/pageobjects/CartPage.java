package sakhiya.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sakhiya.Abstract.Abstract;

public class CartPage extends Abstract {

	WebDriver driver;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProduct;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public Boolean DisplayProductDisplay(String productname) {
		Boolean match = cartProduct.stream().anyMatch(name -> name.getText().equalsIgnoreCase(productname));
		return match;
	}

	public void Checkout() {
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
	}

	
}
