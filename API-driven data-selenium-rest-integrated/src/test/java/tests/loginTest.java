package tests;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.Assert;
//import com.aventstack.extentreports.util.Assert;

import api.accountManager;
import base.baseTest;
import ui.homePage;
import ui.loginPage;

/*//  Register user via API -->> login via selenium UI */

public class loginTest extends baseTest {

	@Test
	public void testLoginWithApiCreatedUser() {

		System.out.println("--- Step 1: Creating User via API ---");
		Map<String, String> user = accountManager.registerNewUser();
		String email = user.get("email");
		String password = user.get("password");

		System.out.println("--- Step 2: Logging in via Selenium ---");

		driver.get("https://automationexercise.com/login");

		loginPage loginPage = new loginPage(driver);
		loginPage.login(email, password);

		homePage homePage = new homePage(driver);

		System.out.println(">>> Success! Hybrid Test Passed for user: " + email);

	}
}
