package sakhiya.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sakhiya.Abstract.Abstract;

public class LandingPage extends Abstract {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement email = driver.findElement(By.id("userEmail"));
	@FindBy(id = "userEmail")
	WebElement Email;

	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(id = "login")
	WebElement Submit;

	public void LoginApplication(String email, String password) {
		Email.sendKeys(email);
		Password.sendKeys(password);
		Submit.click();
	}

	public  void GoTo() {
		driver.get("https://rahulshettyacademy.com/client");
	

	}
}
