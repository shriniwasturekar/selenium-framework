package testcases;

import base.BaseTest;
import models.LoginData;
import org.shriniwas.dataprovider.FrameworkDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.List;

public class LoginTests extends BaseTest {

    @Test(testName = "loginPageShouldDisplayCoreControls",
            description = "Verify the login page renders the mandatory login controls")
    public void loginPageShouldDisplayCoreControls() {

        LoginPage loginPage = new LoginPage().waitForPageToLoad();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be visible.");
        Assert.assertTrue(loginPage.areCoreFieldsDisplayed(), "Core login controls should be visible.");
    }

    @Test(testName = "loginShouldShowValidationMessagesForEmptySubmission",
            description = "Verify empty login submission surfaces validation messages")
    public void loginShouldShowValidationMessagesForEmptySubmission() {
        LoginPage loginPage = new LoginPage().waitForPageToLoad();

        loginPage.submitWithoutCredentials();

        Assert.assertTrue(loginPage.waitForValidationMessages() >= 1,
                "Expected at least one validation message after empty submission.");
    }

    @Test(testName = "forgotUserIdLinkShouldNavigateToRecoveryPage",
            description = "Verify the Forgot User ID link opens the user ID recovery page")
    public void forgotUserIdLinkShouldNavigateToRecoveryPage() {
        LoginPage loginPage = new LoginPage().waitForPageToLoad();

        loginPage.openForgotUserIdPage();
        loginPage.waitUntilUrlContains("ForgotUsername");

        Assert.assertTrue(loginPage.currentUrl().contains("ForgotUsername"),
                "Forgot User ID link should navigate to the recovery page.");
    }

    @Test(testName = "forgotPasswordLinkShouldNavigateToRecoveryPage",
            description = "Verify the Forgot Password link opens the password recovery page")
    public void forgotPasswordLinkShouldNavigateToRecoveryPage() {
        LoginPage loginPage = new LoginPage().waitForPageToLoad();

        loginPage.openForgotPasswordPage();
        loginPage.waitUntilUrlContains("ForgotPassword");

        Assert.assertTrue(loginPage.currentUrl().contains("ForgotPassword"),
                "Forgot Password link should navigate to the recovery page.");
    }

    @Test(testName = "validLoginTest",
            description = "Verify valid credentials land the user on the Cobius Applications home page",
            dataProviderClass = FrameworkDataProvider.class,
            dataProvider = "getData")
    public void validLoginTest(LoginData testData) {
        LoginPage loginPage = new LoginPage().waitForPageToLoad();

        loginPage.loginAs(testData.getUsername(), testData.getPassword(), testData.getClient());
        loginPage.waitUntilTitleIs("Cobius Applications");

        Assert.assertEquals(testData.getExpected(), "success", "Expected dataset should describe a successful login.");
        Assert.assertEquals(loginPage.currentTitle(), "Cobius Applications", "Successful login should reach the applications page.");
        Assert.assertEquals(loginPage.currentUrl(), "https://amstg.mrocorp.com/auth/", "Successful login should land on the auth home page.");
    }

    @Test(testName = "invalidPasswordLoginTest",
            description = "Verify an invalid password keeps the user on the logon page",
            dataProviderClass = FrameworkDataProvider.class,
            dataProvider = "getData")
    public void invalidPasswordLoginTest(LoginData testData) {
        LoginPage loginPage = new LoginPage().waitForPageToLoad();

        loginPage.loginAs(testData.getUsername(), testData.getPassword(), testData.getClient());
        loginPage.waitUntilTitleIs("Log On");
        loginPage.waitUntilUrlContains("Account/Logon");

        Assert.assertEquals(testData.getExpected(), "error", "Expected dataset should describe an invalid login.");
        Assert.assertEquals(loginPage.currentTitle(), "Log On", "Invalid credentials should keep the user on the logon page.");
        Assert.assertTrue(loginPage.currentUrl().contains("Account/Logon"),
                "Invalid credentials should keep the user on the login URL.");
    }

    @Test(testName = "missingClientLoginTest",
            description = "Verify client is mandatory for login submission",
            dataProviderClass = FrameworkDataProvider.class,
            dataProvider = "getData")
    public void missingClientLoginTest(LoginData testData) {
        LoginPage loginPage = new LoginPage().waitForPageToLoad();

        loginPage.loginAs(testData.getUsername(), testData.getPassword(), testData.getClient());
        loginPage.waitUntilTitleIs("Log On");
        loginPage.waitUntilUrlContains("Account/Logon");

        Assert.assertEquals(testData.getExpected(), "validation", "Expected dataset should describe a validation failure.");
        Assert.assertEquals(loginPage.currentTitle(), "Log On", "Validation failure should keep the user on the logon page.");
        Assert.assertTrue(loginPage.currentUrl().contains("Account/Logon"),
                "Missing client should keep the user on the login URL.");
        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Missing client should keep the login form visible.");
    }
}
