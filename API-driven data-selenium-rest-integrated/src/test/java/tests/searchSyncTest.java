package tests;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.accountManager;
import base.baseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/* Perform search using API and count the items / Perform search using UI and 
 *  count the items  -->> Check the correct count is returned in results for both */
public class searchSyncTest extends baseTest {
	@Test
	public void testSearchConsistency() {
		String searchItem = "T-Shirt";

		JsonPath apiResponse = RestAssured.given().baseUri("https://automationexercise.com")
				.contentType("application/x-www-form-urlencoded").formParam("search_product", searchItem) // Specific
																											// param for
																											// this site
				.when().post("/api/searchProduct").then().statusCode(200).extract().jsonPath();

		int apiCount = apiResponse.getList("products").size();
		System.out.println("API found " + apiCount + " products.");

		driver.get("https://automationexercise.com/products");
		driver.findElement(By.id("search_product")).sendKeys(searchItem);
		driver.findElement(By.id("submit_search")).click();

		List<WebElement> uiProducts = driver.findElements(By.cssSelector(".product-image-wrapper"));
		int uiCount = uiProducts.size();
		System.out.println("UI showed " + uiCount + " products.");

		Assert.assertEquals(uiCount, apiCount, "UI is hiding products! API found more items than displayed.");

	}

}
