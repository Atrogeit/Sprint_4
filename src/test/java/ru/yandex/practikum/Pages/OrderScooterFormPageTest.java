package ru.yandex.practikum.Pages;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practikum.Pages.MainPage;
import ru.yandex.practikum.Pages.OrderScooterFormPage;

//import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;



@RunWith(Parameterized.class)
public class OrderScooterFormPageTest {

    private WebDriver driver;
    private final String buttonPosition;
    private final String name;
    private final String secondName;
    private final String adress;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;

    private final String rentTime;
    private final String colour;
    private final String comment;


    public OrderScooterFormPageTest(String buttonPosition, String name, String secondName, String adress, String metroStation, String phoneNumber, String deliveryDate, String rentTime, String colour, String comment){
        this.buttonPosition = buttonPosition;
        this.name = name;
        this.secondName = secondName;
        this.adress = adress;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentTime = rentTime;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[] getOrderData() {
        return new Object[][] {
                {"header", "Егор", "Захаров", "Москва, Университетская", "Универ", "89111111111", "29-е ноября 2022 г.", "сутки", "черный", "Уже можно кататься?"}, // LocalDate.now().plusDays(5),  },
                {"main", "Георгий", "Юматов", "Улица Черняховского", "Аэро", "89999999999", "10-е ноября 2022 г.", "трое суток", "серый", "-" }, // LocalDate.now().plusDays(1),  "серый", "-"},
                {"header", "какоетоимя", "somesecondname", "Улица Пушкина", "Маяк", "81112223344", "29-е ноября 2022 г.", "шестеро суток", "серый", "Ненужон нам этот ваш самукат" }, // LocalDate.now().plusDays(3)
        };
    }


    @Test

    public void testingOrderSequences() {

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objStartOnMainPage = new MainPage(driver);
        objStartOnMainPage.clickOnOrderButtons(buttonPosition);

        OrderScooterFormPage objFormPage = new OrderScooterFormPage(driver);
        objFormPage.waitForOrderFormPageLoad();

        objFormPage.setUsernameField(name);

        objFormPage.setUserSecondNameField(secondName);

        objFormPage.setAdressField(adress);

        objFormPage.enterMetroStation(metroStation);

        objFormPage.setPhoneNumber(phoneNumber);

        objFormPage.clickNextStepButton();

        objFormPage.datePicker(deliveryDate);

        objFormPage.rentDatePicker(rentTime);

        objFormPage.scooterColourSwitch(colour);

        objFormPage.inputComment(comment);

        objFormPage.clickPlaceOrderButton();

        objFormPage.confirmOrderWindowVisible();

        objFormPage.getOrderConfirmationText();

        String expectedText = "Заказ оформлен";
        String actual = driver.findElement(By.xpath(".//div[@class= 'Order_ModalHeader__3FDaJ' and contains(text(), 'Заказ оформлен')]")).getText();


        MatcherAssert.assertThat("Неверный текст окна подтверждения заказа!", actual, containsString(expectedText));


    }



    @After
    public void tearDown() { //throws Exception

        driver.quit();
    }


}