package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

	private WebDriver driver;

	@FindBy(css = "input[data-qa='login-email']")
	private WebElement emailField;

	@FindBy(css = "input[data-qa='login-password']")
	private WebElement passwordField;

	@FindBy(css = "button[data-qa='login-button']")
	private WebElement loginButton;

	public loginPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void login(String email, String password) {
		System.out.println(">>> [UI] Logging in with: " + email);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
	}

	public String getTitle() {
		return driver.getTitle();
	}
}