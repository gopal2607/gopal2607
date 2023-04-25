package sakhiya.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sakhiya.Abstract.Abstract;

public class ProductCatalogue extends Abstract {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.xpath("//button[contains(text(),'Add To Cart')]");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProdByName(String productname) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addProductToCart(String productname) throws InterruptedException {

		WebElement prod = getProdByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
//		waitForElementToDisappear(spinner);
		Thread.sleep(2000);

	}

}
