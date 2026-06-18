package testCases.login;

import base.BaseTest;
import org.testng.annotations.Test;
import utilities.RetryAnalyzer;

public class LoginTest extends BaseTest
{
    @Test(groups = "regression", retryAnalyzer = RetryAnalyzer.class)
    public void login()
    {}
}