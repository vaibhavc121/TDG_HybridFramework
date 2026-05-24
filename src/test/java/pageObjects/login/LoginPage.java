package pageObjects.login;

import base.BasePage;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ShadowDomUtils;

public class LoginPage extends BasePage
{
    //region Locators
    /*@FindBy(xpath="//input[@id='motif-input-actjjv']") private WebElement emailId;*/
    @FindBy(tagName="input") WebElement emailId;
    //endregion

    //region Action Methods
    public void provideEmail(String email)
    {
        clearAndProvide1(emailId, email);
    }

    public void clickLogin() throws InterruptedException
    {
        //ShadowDomUtils.clickShadowElement(By.cssSelector(".login-btn.hydrated"),   By.cssSelector("button"));
        ShadowDomUtils.clickShadowElement1(By.cssSelector(".login-btn.hydrated"), By.cssSelector("button"));

        /*
        Thread.sleep(1000);
        SearchContext shadow = DriverFactory.getDriver()
                .findElement(By.cssSelector(".login-btn.hydrated"))
                .getShadowRoot();
        Thread.sleep(1000);
        shadow.findElement(By.cssSelector("slot")).click();

         */
    }
    //endregion
}