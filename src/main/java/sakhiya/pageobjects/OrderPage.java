package sakhiya.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sakhiya.Abstract.Abstract;

public class OrderPage extends Abstract {

	WebDriver driver;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames; 
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public Boolean VerifyOrderDisplay(String productname) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
		return match;
	}

}
