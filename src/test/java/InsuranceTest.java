import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class InsuranceTest {

    private WebDriver driver;
    private String baseUrl;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "stuff/chromedriver.exe");

        driver = new ChromeDriver();
        baseUrl = "http://www.sberbank.ru/ru/person";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void testInsurance () throws Exception {


        try {
            FileWriter writer = new FileWriter("G:\\GitHub\\Lesson1\\stuff\\logs.txt", true);
            writer.write("Step 1: Переход на сайт Сбербанка http://www.sberbank.ru/ru/person - Ok - " + LocalDateTime.now() + "\n");


            driver.findElement(By.xpath("//*[@aria-label='Раздел Застраховать себя  и имущество']")).click();
            writer.write("Step 2: Нажать на – Застраховать себя и имущество - Оk - " + LocalDateTime.now().toString() + "\n");



            Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
            wait.until(ExpectedConditions.visibilityOf(
                    driver.findElement(By.xpath("//A[@class='kit-link kit-link_color_black alt-menu-list__link alt-menu-list__link_level_1' and @href='/ru/person/bank_inshure/insuranceprogram/life/travel']"))
            ));

            driver.findElement(By.xpath("//A[@class='kit-link kit-link_color_black alt-menu-list__link alt-menu-list__link_level_1' and @href='/ru/person/bank_inshure/insuranceprogram/life/travel']")).click();
            writer.write("Step 3: Выбрать – Страхование путешественников - Ok - " + LocalDateTime.now().toString() + "\n");


            assertEquals("Страхование путешественников",
                    driver.findElement(By.xpath("//H1[not(@class='sr_only')]")).getText());
            writer.write("Step 4: Проверка наличия заголовка формы  - Страхование путешественников - Ok - " + LocalDateTime.now().toString() + "\n");


            driver.findElement(By.xpath("//*[text()='Оформить онлайн']")).click();
            driver.findElement(By.xpath("//IMG[@src='/portalserver/content/atom/contentRepository/content/person/travel/banner-zashita-traveler.jpg?id=f6c836e1-5c5c-4367-b0d0-bbfb96be9c53']")).click();
            Thread.sleep(1000);
            writer.write("Step 5: Нажать – Оформит онлайн - Ok - " + LocalDateTime.now().toString() + "\n");

            for (String winHandle : driver.getWindowHandles()) {

                driver.switchTo().window(winHandle);
            }

// //div[text()='Минимальная']   //DIV[@class='b-form-box-title ng-binding'][text()='Минимальная']
            driver.findElement(By.xpath("//DIV[@class='b-form-box-title ng-binding'][text()='Минимальная']/..")).click();
            writer.write("Step 6: Выбрать минимальную сумму страховой защиты - Ok - " + LocalDateTime.now().toString() + "\n");

            driver.findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Оформить']")).click();
            writer.write("Step 7: Нажать оформить - Ok - " + LocalDateTime.now().toString() + "\n");

            Thread.sleep(1000);


            //Заполнение данных страхуемого
            fillField(By.name("insured0_surname"), "PETROV");
            Thread.sleep(1000);
            fillField(By.name("insured0_name"), "PETR");
            Thread.sleep(1000);

            //ввод даты
            //driver.findElement(By.name("insured0_birthDate")).click();
            fillField(By.name("insured0_birthDate"), "01011990");
            Thread.sleep(1000);
            writer.write("Step 8: Заполнить данные страхуемого - Ok - " + LocalDateTime.now().toString() + "\n");


       /* работа с элементами даты
        driver.findElement(By.xpath("//INPUT[@id='dp1515951862770']")).click();
        new Select(driver.findElement(By.xpath("//SELECT[@class='ui-datepicker-year']"))).selectByVisibleText("1990");
        new Select(driver.findElement(By.xpath("//SELECT[@class='ui-datepicker-month']"))).selectByVisibleText("Янв");
        driver.findElement(By.xpath("//A[@class='ui-state-default ui-state-hover'][text()='1']")).click();
       */

            //Заполнение данных страхователя
            driver.findElement(By.xpath("//INPUT[@ng-model='formdata.insurer.CITIZENSHIP' and @value='0']")).click();
            Thread.sleep(1000);
            fillField(By.name("surname"), "ПЕТРОВ");
            Thread.sleep(1000);
            fillField(By.name("name"), "ПЕТРО");
            Thread.sleep(1000);
            fillField(By.name("middlename"), "ПЕТРОВИЧ");
            Thread.sleep(1000);
            fillField(By.name("birthDate"), "02021960");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"views\"]/section/form/section/section[2]/div/fieldset[8]/span[1]/input")).click();
            Thread.sleep(1000);
            writer.write("Step 9: Заполнить данные страхователя - Ok - " + LocalDateTime.now().toString() + "\n");


        /* работа с элементами даты
        driver.findElement(By.xpath("//INPUT[@id='dp1515951862768']")).click();
        new Select(driver.findElement(By.xpath("//SELECT[@class='ui-datepicker-year']"))).selectByVisibleText("1960");
        new Select(driver.findElement(By.xpath("//SELECT[@class='ui-datepicker-month']"))).selectByVisibleText("Фев");
        driver.findElement(By.xpath("//A[@class='ui-state-default ui-state-hover'][text()='2']")).click();
       */

            //Заполнение паспортных данных
            fillField(By.xpath("//INPUT[@ng-model='formdata.insurer.documentList[0].DOCSERIES']"), "4444");
            Thread.sleep(1000);
            fillField(By.xpath("//INPUT[@ng-model='formdata.insurer.documentList[0].DOCNUMBER']"), "666666");
            Thread.sleep(1000);
            fillField(By.xpath("//TEXTAREA[@name='issuePlace']"), "Отделением номер из города Энск тогда-то");
            Thread.sleep(1000);
            fillField(By.name("issueDate"), "02022005");
            Thread.sleep(1000);
            writer.write("Step 10: Заполнить паспортные данные страхователя и пропустить контактные данные - Ok - " + LocalDateTime.now().toString() + "\n");


            //проверяем правильность заполнения страхуемого
            assertEquals("PETROV", driver.findElement(By.xpath("//INPUT[@name='insured0_surname']")).getAttribute("value"));
            assertEquals("PETR", driver.findElement(By.xpath("//INPUT[@name='insured0_name']")).getAttribute("value"));
            assertEquals("01.01.1990", driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));
            writer.write("Step 11: Проверка правильности данных страхуемого - Ok - " + LocalDateTime.now().toString() + "\n");


            //проверяем правильность заполнения страхователя
            assertEquals("ПЕТРОВ", driver.findElement(By.xpath("//INPUT[@name='surname']")).getAttribute("value"));
            assertEquals("ПЕТРО", driver.findElement(By.xpath("//INPUT[@name='name']")).getAttribute("value"));
            assertEquals("ПЕТРОВИЧ", driver.findElement(By.xpath("//INPUT[@name='middlename']")).getAttribute("value"));
            assertEquals("02.02.1960", driver.findElement(By.name("birthDate")).getAttribute("value"));
            writer.write("Step 12: Проверка правильности данных страхователя - Ok - " + LocalDateTime.now().toString() + "\n");


            //проверяем правильность заполнения паспортных данных
            assertEquals("4444", driver.findElement(By.xpath("//INPUT[@ng-model='formdata.insurer.documentList[0].DOCSERIES']")).getAttribute("value"));
            assertEquals("666666", driver.findElement(By.xpath("//INPUT[@ng-model='formdata.insurer.documentList[0].DOCNUMBER']")).getAttribute("value"));
            assertEquals("Отделением номер из города Энск тогда-то", driver.findElement(By.xpath("//TEXTAREA[@name='issuePlace']")).getAttribute("value"));
            assertEquals("02.02.2005", driver.findElement(By.name("issueDate")).getAttribute("value"));
            writer.write("Step 13: Проверка правильности паспортных данных страхователя - Ok - " + LocalDateTime.now().toString() + "\n");


        /*assertEquals("b-radio-field b-checked-radio-field",
                driver.findElement(By.xpath("//*[@id=\"views\"]/section/form/section/section[2]/div/fieldset[8]/span[1]")).getClass());
                */

            driver.findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Продолжить']")).click();
            writer.write("Step 14: Нажать продолжить - Ok - " + LocalDateTime.now().toString() + "\n");


            assertEquals("Заполнены не все обязательные поля",
                    driver.findElement(By.xpath("//DIV[@ng-show='tryNext && myForm.$invalid']")).getText());
            Thread.sleep(5000);
            writer.write("Step 15: Проверка вывода сообщения об ошибке - Ok - " + LocalDateTime.now().toString() + "\n");


            writer.close();
        }
        catch (IOException E){System.out.println(E.getMessage());}
        System.out.println("Тест завершился без ошибок\n");

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private void fillField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
}
