package Tests;

import Pages.LoginPage;
import Utilities.DataFactory;
import Utilities.TestListeners;
import org.apache.hc.core5.http.nio.support.AbstractServerExchangeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;

@Listeners(TestListeners.class)
public class LoginTest extends BaseTest{
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    LoginPage loginPage;

    @Test
    public void validLoginTest(){
        loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();

        loginPage.fillCredentials(DataFactory.getEmail(), DataFactory.getPassword());

        Assert.assertTrue(loginPage.checkDashboardRedirection());
    }

    @Test
    public void invalidLoginTest(){
        loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();

        Assert.assertTrue(loginPage.verifyEmptyCredentialsValidation());

        Assert.assertTrue(loginPage.verifyInvalidCredentialsValidation(DataFactory.getEmail() , faker.internet().password()));

        Assert.assertTrue(loginPage.verifyRemainingOnLoginPage());
    }

    @Test
    public void invalidSecurityCodeValidation(){
        loginPage = new LoginPage(driver);

        loginPage.navigateToForgetPasswordPage();

        loginPage.fillForgetPageCredentials(DataFactory.getEmail() , "abcdefg");

        Assert.assertTrue(loginPage.checkInvalidCodeValidation());

        Assert.assertTrue(loginPage.verifyPasswordResetRequestNotSubmitted());
    }
}
