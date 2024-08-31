package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    //Login page elements
    @FindBy(name = "username")
    WebElement txtUsername;
    @FindBy(name = "password")
    WebElement txtPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    @FindBy(className = "oxd-userdropdown-img")
    public WebElement btnProfile;
    @FindBy(css = "[role = menuitem]")
    List<WebElement> dropDown;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public void doLogin(String username, String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnSubmit.click();

    }
    public void doLogout(){
        btnProfile.click();
        dropDown.get(3).click();

    }
}
