package ru.yandex.practikum.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



// создали класс для страницы начала оформления заказа
public class OrderScooterFormPage {

    private WebDriver driver;
    // Назначили локаторы всем полям
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By adressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationInputField = By.xpath(".//input[@placeholder = '* Станция метро']");
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By scooterDeliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By deliveryDatePicker = By.xpath(".//div[@class= 'react-datepicker']");
    private By chooseDeliveryDateMenu = By.xpath(".//div[@class='react-datepicker__month-container']");
    private By enterRentTime = By.xpath(".//div[@class= 'Dropdown-placeholder']");
    private By rentTimeOptions = By.xpath("../div[@class= 'Dropdown-menu']");
    private By blackColourCheckBox = By.xpath(".//input[@id= 'black']");
    private By greyColourCheckBox = By.xpath(".//input[@id= 'grey']");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By acceptOrderButton = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']");
    private By cancelOrderButton = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    private By confirmOrderWindow = By.xpath(".//div[@class= 'Order_Modal__YZ-d3']");
    private By yesButtonToOrder = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Да')]");
    private By orderConfirmedWindow = By.xpath(".//div[@class= 'Order_Modal__YZ-d3']//div[@class= 'Order_ModalHeader__3FDaJ']");

    // инициализированы переменные для метода выбора даты аренды
    String dateLabel = ".//div[contains(@aria-label, '%s')]";
    String rentOptionPicker = ".//div[@role='option' and contains(text(), '%s')]";


    // локатор кнопки "Далее"
    private By buttonNextStep = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']");


    public OrderScooterFormPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загрузки страницы
    public void waitForOrderFormPageLoad() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));;
    }
    //метод ввода Имени
    public void setUsernameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    //метод ввода Фамилии
    public void setUserSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }
    //метод ввода Адреса
    public void setAdressField(String userAdress) {
        driver.findElement(adressField).sendKeys(userAdress);
    }
    //метод выбора метро
    public void enterMetroStation(String metroStation) {
        driver.findElement(metroStationInputField).sendKeys(metroStation);
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(metroStationInputField), Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

    }

    //метод ввода телефона
    public void setPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    //метод клика по кнопке Далее
    public void clickNextStepButton() {
        driver.findElement(buttonNextStep).isEnabled();
        driver.findElement(buttonNextStep).click();
    }

    //метод выбора даты аренды
    public void datePicker(String deliveryDate) {
        driver.findElement(scooterDeliveryDate).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(deliveryDatePicker));
        driver.findElement(By.xpath(String.format(dateLabel, deliveryDate))).click();
    }

    //метод выбора цвета скутера
    public void scooterColourSwitch(String colour) {
        switch (colour) {
            case "черный" :
                driver.findElement(blackColourCheckBox).click();
                break;
            case "серый" :
                driver.findElement(greyColourCheckBox).click();
                break;
        }
    }
    //метод выбора длительности аренды
    public void rentDatePicker(String rentTime) {
        driver.findElement(enterRentTime).click();
        //new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(rentTimeOptions));
        driver.findElement(By.xpath(String.format(rentOptionPicker, rentTime))).click();
    }

    //метод ввода в поле комментарий
    public void inputComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    //метод клика по кнопке "Заказать"
    public void clickPlaceOrderButton() {
        driver.findElement(acceptOrderButton).isEnabled();
        driver.findElement(acceptOrderButton).click();
    }
    //Метод проверки отображения вплывабщего окна заказа
    public void confirmOrderWindowVisible() {
        driver.findElement(confirmOrderWindow).isDisplayed();
        driver.findElement(yesButtonToOrder).click();
        driver.findElement(orderConfirmedWindow).isDisplayed();
    }
    //метод получения текста из всплывающего окна подтверждения заказа
    public void getOrderConfirmationText() {
        driver.findElement(orderConfirmedWindow).getText();
    }



}
