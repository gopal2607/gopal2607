package sakhiya.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sakhiya.TestComponent.BaseTest;
import sakhiya.pageobjects.CartPage;
import sakhiya.pageobjects.CheckoutPage;
import sakhiya.pageobjects.ConfirmationPage;
import sakhiya.pageobjects.LandingPage;
import sakhiya.pageobjects.OrderPage;
import sakhiya.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		LandingPage landingpage = new LandingPage(driver);
		landingpage.LoginApplication(input.get("email"), input.get("password"));

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(input.get("productname"));

		CartPage cartpage = new CartPage(driver);
		cartpage.clickOnCartHeader();
		Boolean match = cartpage.DisplayProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		cartpage.Checkout();

		CheckoutPage checkoutpage = new CheckoutPage(driver);
		checkoutpage.SelectCountry("india");
		checkoutpage.Submit();

		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		String confirmMessage = confirmationpage.ConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String, String> input) {
		LandingPage landingpage = new LandingPage(driver);
		landingpage.LoginApplication(input.get("email"), input.get("password"));
		OrderPage orderpage = new OrderPage(driver);
		orderpage.clickOnOrders();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(input.get("productname")));
	}
	
	public String getScreenshot(String testcasename) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File File = new File(System.getProperty("user.dir")+"\\reports\\"+ testcasename + ".png");
		FileUtils.copyFile(Source, File);
		return System.getProperty("user.dir")+"\\reports\\"+ testcasename + ".png";
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String >> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\sakhiya\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "gopal@scaletech.xyz");
//		map.put("password", "Password@123");
//		map.put("productname", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "scaletechqa@gmail.com");
//		map1.put("password", "Password@123");
//		map1.put("productname", "ZARA COAT 3");
//		return new Object[][] { { map }, { map1 } };

//		return new Object[][] { { "gopal@scaletech.xyz", "Password@123", "ZARA COAT 3" },
//				{ "scaletechqa@gmail.com", "Password@123", "ZARA COAT 3" } };
	}

}