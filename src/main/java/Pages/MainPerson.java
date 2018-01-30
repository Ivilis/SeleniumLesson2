package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPerson extends BasePage{

    @FindBy(xpath = "//div[contains(@data-pid,'widget_3719')]")
    WebElement mainMenu;

    @FindBy(xpath = "//div[contains(@class,'bp-area header-container')]")
    WebElement subMenu;

    public MainPerson(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        Wait<WebDriver> wait = new WebDriverWait(driver,10, 2000);
        wait.until(ExpectedConditions.visibilityOf(mainMenu));
    }

    public void selectMainMenuItem(String menuItem){
        Wait<WebDriver> wait = new WebDriverWait(driver,10, 2000);
        wait.until(ExpectedConditions.visibilityOf(mainMenu.findElement(By.xpath(".//*[contains(text(),'"+menuItem+"')]")))).click();

    }

    public void selectSubMenuItem(String menuItem){
        Wait<WebDriver> wait = new WebDriverWait(driver,10, 2000);
        wait.until(ExpectedConditions.visibilityOf(subMenu.findElement(By.xpath(".//*[contains(text(),'"+menuItem+"')]")))).click();
    }


}
