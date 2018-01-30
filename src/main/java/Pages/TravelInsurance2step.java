package Pages;

import com.sun.tools.javac.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;



public class TravelInsurance2step {

    @FindBy(name = "insured0_name")
    WebElement insuredName;

    @FindBy(name = "insured0_surname")
    WebElement insuredSurname;

    @FindBy(name = "insured0_birthDate")
    WebElement insuredBirthdate;

    @FindBy(xpath = "//span[contains(@ng-class,'CITIZENSHIP')]") //блок гражданства
    public WebElement citizenship;

    @FindBy(xpath = "//span[contains(@ng-class,'GENDER')]") //блок полня
    public WebElement gender;

    @FindBy(name = "name")
    WebElement insurantName;

    @FindBy(name = "surname")
    WebElement insurantSurname;

    @FindBy(name = "middlename")
    WebElement insurantMiddlename;

    @FindBy(name = "birthDate")
    WebElement insurantBirthdate;

    @FindBy(name = "passport_series")
    WebElement insurantIDSeries;

    @FindBy(name = "passport_number")
    WebElement insurantIDNumber;

    @FindBy(name = "issueDate")
    WebElement insurantIDIssueDate;

    @FindBy(name = "issuePlace")
    WebElement insurantIDIssuePlace;



    @FindBy(xpath = "//*[text()='Продолжить']") //кнопка продолжить
    public WebElement continueButton;

    @FindBy(xpath = "//DIV[@ng-show='tryNext && myForm.$invalid']") //блок сообщения об ошибке
    public WebElement errorMessage;



    public TravelInsurance2step(WebDriver driver){
        PageFactory.initElements(driver, this);
        Wait<WebDriver> wait = new WebDriverWait(driver,10, 1000);
        wait.until(ExpectedConditions.visibilityOf(continueButton));
    }


    //заполнение полей
    public void fillOurPainFields (String fildName, String value){
        switch (fildName){
            case "Фамилия страхуемого":
                innerFillMethod(insuredSurname, value);
                break;
            case "Имя страхуемого":
                innerFillMethod(insuredName, value);
                break;
            case "Дата рождения страхуемого":
                innerFillMethod(insuredBirthdate, value);
                break;
            case "Фамилия страхователя":
                innerFillMethod(insurantSurname, value);
                break;
            case "Имя страхователя":
                innerFillMethod(insurantName, value);
                break;
            case "Отчество страхователя":
                innerFillMethod(insurantMiddlename, value);
                break;
            case "Дата рождения страхователя":
                innerFillMethod(insurantBirthdate, value);
                break;
            case "Серия паспорта страхователя":
                innerFillMethod(insurantIDSeries, value);
                break;
            case "Номер паспорта страхователя":
                innerFillMethod(insurantIDNumber, value);
                break;
            case "Дата выдачи паспорта страхователя":
                innerFillMethod(insurantIDIssueDate, value);
                break;
            case "Кем выдан паспорт страхователя":
                innerFillMethod(insurantIDIssuePlace, value);
                break;
            default: throw new AssertionError("Поле '" + fildName + "' не объявлено на странице");
        }
    }

    //проверка правильности заполнения
    public void checkThemPay (String fieldToCheck, String expectedValue) {
        switch (fieldToCheck) {
            case "Фамилия страхуемого":
                assertEquals(expectedValue, insuredSurname.getAttribute("value"));
                break;
            case "Имя страхуемого":
                assertEquals(expectedValue, insuredName.getAttribute("value"));
                break;
            case "Дата рождения страхуемого":
                assertEquals(expectedValue, insuredBirthdate.getAttribute("value"));
                break;
            case "Фамилия страхователя":
                assertEquals(expectedValue, insurantSurname.getAttribute("value"));
                break;
            case "Имя страхователя":
                assertEquals(expectedValue, insurantName.getAttribute("value"));
                break;
            case "Отчество страхователя":
                assertEquals(expectedValue, insurantMiddlename.getAttribute("value"));
                break;
            case "Дата рождения страхователя":
                assertEquals(expectedValue, insurantBirthdate.getAttribute("value"));
                break;
            case "Серия паспорта страхователя":
                assertEquals(expectedValue, insurantIDSeries.getAttribute("value"));
                break;
            case "Номер паспорта страхователя":
                assertEquals(expectedValue, insurantIDNumber.getAttribute("value"));
                break;
            case "Дата выдачи паспорта страхователя":
                assertEquals(expectedValue, insurantIDIssueDate.getAttribute("value"));
                break;
            case "Кем выдан паспорт страхователя":
                assertEquals(expectedValue, insurantIDIssuePlace.getAttribute("value"));
                break;
            default:
                throw new AssertionError("Поле '" + fieldToCheck + "' не объявлено на странице");
        }
    }

    //выбор гражданства
    public void chooseCitezenship (String citizenornot) {
        switch (citizenornot) {
            case "гражданинРФ":
                citizenship.findElement(By.xpath(".//input[contains(@value,'0')]")).click();
                break;
            case "иностранец":
                citizenship.findElement(By.xpath(".//input[contains(@value,'1')]")).click();
                break;
            default:
                throw new AssertionError("Поле '" + citizenornot + "' не объявлено на странице");
        }
    }

    //выбор пола
        public void chooseGender (String sex){
            switch (sex) {
                case "мужской":
                    gender.findElement(By.xpath(".//input[contains(@value,'0')]"));
                    break;
                case "женский":
                    gender.findElement(By.xpath(".//input[contains(@value,'1')]"));
                    break;
                default:
                    throw new AssertionError("Поле '" + sex + "' не объявлено на странице");
            }
        }

    protected void innerFillMethod (WebElement element, String value)
    {
        element.clear();
        element.sendKeys(value);
    }

    public  void checkErrorMessage(String InputData){
        assertEquals(InputData,errorMessage.getText());
    }




}


