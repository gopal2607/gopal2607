package sakhiya.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sakhiya.pageobjects.CartPage;
import sakhiya.pageobjects.OrderPage;

public class Abstract {

	WebDriver driver;

	
	public Abstract(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public OrderPage clickOnOrders() {
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;

	}
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='dashboard/myorders']")
	WebElement orderHeader;


	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage clickOnCartHeader() {
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}

}
