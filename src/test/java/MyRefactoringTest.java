import Pages.*;
import org.junit.*;

import static junit.framework.TestCase.assertTrue;



public class MyRefactoringTest extends BaseTest {

    @Test

    public void newInsuranceTest() throws InterruptedException {
        driver.get(baseUrl);
        MainPerson mainPerson = new MainPerson(driver);
        mainPerson.selectMainMenuItem("Застраховать себя"); //наводимся на нужное меню
        mainPerson.selectSubMenuItem("Страхование путешественников"); //выбираем пункт в подменю

        BankInsurance bankInsurance = new BankInsurance(driver);

        //проверям заголовок на странице
        String actualTitle = bankInsurance.pageTitle.getText();
        String expectedTitle = "Страхование путешественников";
        assertTrue(String.format("Заголовок равен [%s]. Ожидалось - [%s]",
                actualTitle, expectedTitle), actualTitle.contains(expectedTitle));

        bankInsurance.chooseRequestType("онлайн");
        bankInsurance.bigBannerButtonClick();

        //переключаемся на новое окно
        for (String winHandle : driver.getWindowHandles()) {

            driver.switchTo().window(winHandle);
        }

        TravelInsurance1step travelInsurance1step = new TravelInsurance1step(driver);

        //выбираем сумму страховой защиты
        travelInsurance1step.selectInsurance("Минимальная");
        travelInsurance1step.AcceptButtonClick();

        TravelInsurance2step travelInsurance2step = new TravelInsurance2step(driver);
        //вводим данные страхуемого
        travelInsurance2step.fillOurPainFields("Фамилия страхуемого","Tokugawa");
        travelInsurance2step.fillOurPainFields("Имя страхуемого","Ieyasy");
        travelInsurance2step.fillOurPainFields("Дата рождения страхуемого","31.01.1941");

        //выбираем гражданство страхователя
        travelInsurance2step.chooseCitezenship("гражданинРФ");
        //заполняем ФИО и ДР страхователя
        travelInsurance2step.fillOurPainFields("Имя страхователя","Нобунага");
        travelInsurance2step.fillOurPainFields("Фамилия страхователя","Ода");
        travelInsurance2step.fillOurPainFields("Отчество страхователя","Нобухидэ");
        travelInsurance2step.fillOurPainFields("Дата рождения страхователя","23.06.1934");
        //выбираем пол страхователя
        travelInsurance2step.chooseGender("мужской");

        //заполняем паспортные данные страхователя
        travelInsurance2step.fillOurPainFields("Серия паспорта страхователя","1212");
        travelInsurance2step.fillOurPainFields("Номер паспорта страхователя","123123");
        travelInsurance2step.fillOurPainFields("Дата выдачи паспорта страхователя","23.06.1946");
        travelInsurance2step.fillOurPainFields("Кем выдан паспорт страхователя","УВД замка Фураватари в провинции Овари");

        //проверяем правильность заполнения полей
        travelInsurance2step.checkThemPay("Фамилия страхуемого","Tokugawa");
        travelInsurance2step.checkThemPay("Имя страхуемого","Ieyasy");
        travelInsurance2step.checkThemPay("Дата рождения страхуемого","31.01.1941");
        travelInsurance2step.checkThemPay("Имя страхователя","Нобунага");
        travelInsurance2step.checkThemPay("Фамилия страхователя","Ода");
        travelInsurance2step.checkThemPay("Отчество страхователя","Нобухидэ");
        travelInsurance2step.checkThemPay("Дата рождения страхователя","23.06.1934");
        travelInsurance2step.checkThemPay("Серия паспорта страхователя","1212");
        travelInsurance2step.checkThemPay("Номер паспорта страхователя","123123");
        travelInsurance2step.checkThemPay("Дата выдачи паспорта страхователя","23.06.1946");
        travelInsurance2step.checkThemPay("Кем выдан паспорт страхователя","УВД замка Фураватари в провинции Овари");

        //жмём кнопку и проверяем сообщение об ошибке
        travelInsurance2step.continueButton.click();
        travelInsurance2step.checkErrorMessage("Заполнены не все обязательные поля");

        System.out.println("Test successfully completed\n");
    }

}
