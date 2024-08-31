package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PIMTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1, description = "Admin cannot Login with invalid creds")
    public void doLoginWithWrongCreds(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("wrongUser","wrongPass");
        WebElement alertText = driver.findElement(By.className("oxd-alert-content-text"));
        Assert.assertEquals(alertText.getText(),"Invalid credentials");

    }
    @Test(priority = 2, description = "Admin can login with correct creds")
    public void doLogin(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
        Assert.assertTrue(loginPage.btnProfile.isDisplayed());
    }
    PIMPage pimPage;
    @Test(priority = 3, description = "Add Employee")
    public void addEmployee() throws IOException, ParseException {
        pimPage  =new PIMPage(driver);
        pimPage.leftMenubar.get(1).click();
        pimPage.addBtn.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName  = faker.name().lastName();
        String username  = faker.name().username();
        String password  = "Password1#*";
        WebElement empIdTxtbox = pimPage.txtBoxes.get(4);
        Random random = new Random();
        int id = 1000 + random.nextInt(9000);
        String empId = String.valueOf(id);
        pimPage.addEmployee(firstName,lastName,empId,username,password);
        WebElement headerTextElem = driver.findElement(By.xpath("//h6[text() = \"Personal Details\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(headerTextElem));
        String expectedText = "Personal Details";
        String actualText   = headerTextElem.getText();
        Assert.assertEquals(actualText, expectedText);

        Utils.saveUsers(empId,firstName,lastName,username,password);
        pimPage.leftMenubar.get(7).click();
        pimPage.leftMenubar.get(1).click();
        pimPage.addBtn.get(1).click();
        pimPage.searchEmp(empId);
    }
    @Test(priority = 4, description = "Search with employeeId")
    public void searchEmployee()
    {
        pimPage  =new PIMPage(driver);


    }

}
