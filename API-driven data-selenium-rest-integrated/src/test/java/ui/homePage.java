package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {

	private WebDriver driver;

	@FindBy(xpath = "//li[contains(text(), 'Logged in as')]")
	private WebElement loggedInAsText;

	@FindBy(xpath = "//a[text()=' Delete Account']")
	private WebElement deleteAccountButton;

	public homePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isUserLoggedIn() {
		try {
			return loggedInAsText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickDeleteAccount() {
		deleteAccountButton.click();
	}
}