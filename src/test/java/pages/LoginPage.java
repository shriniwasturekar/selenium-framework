package pages;

import org.openqa.selenium.By;
import org.shriniwas.keywords.ElementActions;

public class LoginPage {

    private static final By LOGIN_HEADER = By.xpath("//h3[normalize-space()='Log On']");
    private static final By LOGIN_FORM = By.xpath("//section[@id='loginForm']//form");
    private static final By USERNAME_INPUT = By.xpath("//input[@id='UserName']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@id='Password']");
    private static final By CLIENT_INPUT = By.xpath("//input[@id='Client']");
    private static final By REMEMBER_USER_CHECKBOX = By.xpath("//input[@id='SaveUserName']");
    private static final By REMEMBER_CLIENT_CHECKBOX = By.xpath("//input[@id='SaveClient']");
    private static final By LOGON_BUTTON = By.xpath("//input[@type='submit' and @value='Log On']");
    private static final By FORGOT_USER_ID_LINK = By.xpath("//a[contains(@href,'ForgotUsername')]");
    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[contains(@href,'ForgotPassword')]");
    private static final By VALIDATION_MESSAGES = By.xpath("//span[contains(@class,'field-validation-error')]");

    public LoginPage waitForPageToLoad() {
        ElementActions.waitForElementVisible(LOGIN_HEADER);
        ElementActions.waitForElementVisible(LOGIN_FORM);
        return this;
    }

    public boolean isLoginFormDisplayed() {
        return ElementActions.isElementDisplayed(LOGIN_FORM);
    }

    public boolean areCoreFieldsDisplayed() {
        return ElementActions.isElementDisplayed(USERNAME_INPUT)
                && ElementActions.isElementDisplayed(PASSWORD_INPUT)
                && ElementActions.isElementDisplayed(CLIENT_INPUT)
                && ElementActions.isElementDisplayed(REMEMBER_USER_CHECKBOX)
                && ElementActions.isElementDisplayed(REMEMBER_CLIENT_CHECKBOX)
                && ElementActions.isElementDisplayed(LOGON_BUTTON);
    }

    public void submitWithoutCredentials() {
        ElementActions.clickElement(LOGON_BUTTON);
    }

    public void loginAs(String username, String password, String client) {
        ElementActions.setText(USERNAME_INPUT, username);
        ElementActions.setText(PASSWORD_INPUT, password);
        ElementActions.setText(CLIENT_INPUT, client);
        ElementActions.clickElement(LOGON_BUTTON);
    }

    public int waitForValidationMessages() {
        return ElementActions.waitForNumberOfElementsMoreThan(VALIDATION_MESSAGES, 0);
    }

    public void openForgotUserIdPage() {
        ElementActions.clickElement(FORGOT_USER_ID_LINK);
    }

    public void openForgotPasswordPage() {
        ElementActions.clickElement(FORGOT_PASSWORD_LINK);
    }

    public void waitUntilUrlContains(String value) {
        ElementActions.waitForUrlContains(value);
    }

    public void waitUntilTitleIs(String value) {
        ElementActions.waitForTitle(value);
    }

    public String currentUrl() {
        return ElementActions.getCurrentUrl();
    }

    public String currentTitle() {
        return ElementActions.getPageTitle();
    }
}
