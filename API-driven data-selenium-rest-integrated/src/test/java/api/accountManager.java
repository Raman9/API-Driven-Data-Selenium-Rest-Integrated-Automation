package api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;

public class accountManager {

	private static final Faker faker = new Faker();

	public static Map<String, String> registerNewUser() {

		String name = faker.name().firstName();
		String email = faker.internet().emailAddress();
		String password = "TestPassword123!";

		Response response = RestAssured.given().contentType("multipart/form-data").multiPart("name", name)
				.multiPart("email", email).multiPart("password", password).multiPart("title", "Mr")
				.multiPart("birth_date", "10").multiPart("birth_month", "April").multiPart("birth_year", "1990")
				.multiPart("firstname", name).multiPart("lastname", "User").multiPart("company", "Nexus")
				.multiPart("address1", "123 Main St").multiPart("country", "United States")
				.multiPart("zipcode", "10001").multiPart("state", "New York").multiPart("city", "New York")
				.multiPart("mobile_number", "9876543210").when().post(apiEndPoints.CREATE_ACCOUNT);

		if (response.statusCode() != 200) {
			throw new RuntimeException("API Failed to create user! Status: " + response.statusCode() + " Response: "
					+ response.getBody().asString());
		}

		System.out.println(">>> [API] Created User: " + email);

		Map<String, String> userData = new HashMap<>();
		userData.put("email", email);
		userData.put("password", password);
		userData.put("name", name);

		return userData;
	}

	public static void deleteUser(String email, String password) {
		RestAssured.given().contentType("multipart/form-data").multiPart("email", email).multiPart("password", password)
				.when().delete(apiEndPoints.DELETE_ACCOUNT).then().statusCode(200);

		System.out.println(">>> [API] Deleted User: " + email);
	}
}
