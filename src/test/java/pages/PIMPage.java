package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> leftMenubar;

    @FindBy(className = "oxd-button")
    public List<WebElement> addBtn;

    @FindBy(className = "oxd-input")
    public List<WebElement> txtBoxes;

    @FindBy(className = "oxd-button")
    List<WebElement> btns;


    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void addEmployee(String firstName, String lastName,String empId, String username, String password){
        txtBoxes.get(1).sendKeys(firstName);
        txtBoxes.get(3).sendKeys(lastName);
        txtBoxes.get(4).sendKeys(Keys.CONTROL + "a");
        txtBoxes.get(4).sendKeys(Keys.DELETE);
        txtBoxes.get(4).sendKeys(empId);
        txtBoxes.get(5).sendKeys(username);
        txtBoxes.get(6).sendKeys(password);
        txtBoxes.get(7).sendKeys(password);
        btns.get(1).click();
    }
    public void searchEmp (String empId){
        txtBoxes.get(1).sendKeys(empId);
        btns.get(1).click();
    }
}
