package ru.yandex.practikum.Pages;

import org.openqa.selenium.*;

public class MainPage {

    private WebDriver driver;
    // Описаны поля кнопок
    private By orderFirstButton = By.xpath(".//button[@class= 'Button_Button__ra12g']");
    private By orderSecondButton = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']");




    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //метод, проверяющий доступность кнопки "Заказать" в заголовке
    public boolean checkOrderButtonIsEnabled() {
        return driver.findElement(orderFirstButton).isEnabled();
    }
    //метод, сочетающий в себе условие переключения между кнопками "Заказать" в header и в  main
    public void clickOnOrderButtons(String orderButtons) {
        switch (orderButtons) {
            case "header" :
                checkOrderButtonIsEnabled();
                driver.findElement(orderFirstButton).click();
                break;
            case "main" :
                WebElement secondOrderButton = driver.findElement(orderSecondButton);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", secondOrderButton);
                checkOrderButtonIsEnabled();
                secondOrderButton.click();
                break;
        }
    }


}
